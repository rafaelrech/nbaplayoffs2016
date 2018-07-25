package rio2016.web;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import rech.bolao.bean.User;
import rech.bolao.util.BaseSessionUtil;
import rech.bolao.web.WebUtils;
import rio2016.bean.Rio2016MedalBet;
import rio2016.bean.Rio2016Sport;
import rio2016.dao.Rio2016MedalBetDao;
import rio2016.dao.Rio2016SportDao;

@WebServlet("/rio2016/BetServlet")
public class BetServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public BetServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String target = request.getParameter("target");
		target += "?orderBy=" + request.getParameter("orderBy") + "&showDates=" + request.getParameter("showDates")
				+ "&showCompleted=" + request.getParameter("showCompleted") + "&onlyPending="
				+ request.getParameter("onlyPending");
		String action = request.getParameter("action");
		String pCompetitorId = request.getParameter("competitorId");
		String pSportId = request.getParameter("sportId");
		String pBetValue = request.getParameter("betValue");

		HttpSession session = request.getSession();
		User u = (User) session.getAttribute("loggedUser");

		if (action == null || action.isEmpty()) {
			fail(response, session, "'action' is null or empty", getRedirectInfo(target));
			return;
		}
		if (pCompetitorId == null || pCompetitorId.isEmpty()) {
			fail(response, session, "'pCompetitorId' is null or empty", getRedirectInfo(target));
			return;
		}
		if (pSportId == null || pSportId.isEmpty()) {
			fail(response, session, "'pSportId' is null or empty", getRedirectInfo(target));
			return;
		}
		if (pBetValue == null || pBetValue.isEmpty()) {
			fail(response, session, "'pBetValue' is null or empty", getRedirectInfo(target));
			return;
		}

		int competitorId = new Integer(pCompetitorId).intValue();
		int sportId = new Integer(pSportId).intValue();
		int betValue = new Integer(pBetValue).intValue();

		if (action.equalsIgnoreCase("BET")) {
			Rio2016MedalBet bean = Rio2016MedalBetDao.getInstance().get(competitorId, sportId, u.getId());
			if (betValue >= 1 && betValue <= 3) {
				Rio2016Sport sport = Rio2016SportDao.getInstance().get(sportId);
				if (WebUtils.truncate(sport.getStartDate()).getTimeInMillis() <= WebUtils
						.truncate(Calendar.getInstance()).getTimeInMillis()) {
					fail(response, session, "Modalidade já iniciada", getRedirectInfo(target));
					return;
				}
				int goldCount = 0;
				int silverCount = 0;
				int bronzeCount = 0;
				try {
					ArrayList<Rio2016MedalBet> userBets = Rio2016MedalBetDao.getInstance()
							.listAll(String.format(" USER_ID = %d AND SPORT_ID = %d", u.getId(), sportId));
					for (Rio2016MedalBet bet : userBets) {
						if (bet.getBet() == 1) {
							goldCount++;
						}
						if (bet.getBet() == 2) {
							silverCount++;
						}
						if (bet.getBet() == 3) {
							bronzeCount++;
						}
					}
				} catch (SQLException e) {
				}
				switch (betValue) {
				case 1:
					if (goldCount >= sport.getQtyGold()) {
						fail(response, session, "Número de apostas em ouros ultrapassada", getRedirectInfo(target));
						return;
					}
					break;
				case 2:
					if (silverCount >= sport.getQtySilver()) {
						fail(response, session, "Número de apostas em pratas ultrapassada", getRedirectInfo(target));
						return;
					}
					break;
				case 3:
					if (bronzeCount >= sport.getQtyBronze()) {
						fail(response, session, "Número de apostas em bronzes ultrapassada", getRedirectInfo(target));
						return;
					}
					break;
				default:
					break;
				}
				if (bean == null) {
					Rio2016MedalBetDao.getInstance()
							.insert(new Rio2016MedalBet(competitorId, sportId, u.getId(), betValue, 0));
				} else {
					bean.setBet(betValue);
					Rio2016MedalBetDao.getInstance().update(bean);
				}
			} else {
				Rio2016MedalBetDao.getInstance().delete(competitorId, sportId, u.getId());
			}
		}

		BaseSessionUtil.udpateSessionInfo(session, u);
		response.sendRedirect(getRedirectInfo(target));
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

	private void fail(HttpServletResponse response, HttpSession session, String msg, String targetPage)
			throws IOException {
		BaseSessionUtil.setSessionMessage(session, "FAIL! " + msg);
		response.sendRedirect(targetPage);
	}

	private String getRedirectInfo(String page) {
		return WebUtils.getRedirectInfo(page, "bets.jsp");
	}

}
