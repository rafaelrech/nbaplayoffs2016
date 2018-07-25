package nbaplayoffs2016.web;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import nbaplayoffs2016.bean.Nba2016Game;
import nbaplayoffs2016.bean.Nba2016GameBet;
import nbaplayoffs2016.dao.Nba2016GameBetDao;
import nbaplayoffs2016.dao.Nba2016GameDao;
import nbaplayoffs2016.util.Nba2016WebUtil;
import rech.bolao.bean.LogEntry;
import rech.bolao.bean.User;
import rech.bolao.dao.LoggingDao;
import rech.bolao.util.BaseSessionUtil;
import rech.bolao.web.WebUtils;

/**
 * Servlet implementation class BetServlet
 */
@WebServlet("/BetServlet")
public class BetServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public BetServlet() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String source = request.getParameter("source");
		String target = request.getParameter("target");
		String gameId = request.getParameter("gameId");
		String home = request.getParameter("home");
		boolean homeWins = (home != null && home.equals("1"));
		String ot = request.getParameter("ot");
		boolean inOT = (ot != null && ot.equals("1"));

		HttpSession session = request.getSession();
		User u = BaseSessionUtil.getLoggedUser(session);

		if (u == null) {
			fail(response, session, "Not logged in", getRedirectInfo(WebUtils.LOGIN_JSP));
			return;
		}
		if (gameId == null || gameId.isEmpty()) {
			fail(response, session, "Game not informed", getRedirectInfo(source));
			return;
		}

		Nba2016Game game = Nba2016GameDao.getInstance().get(Integer.valueOf(gameId));
		if (game.getData().before(Calendar.getInstance())) {
			LoggingDao.getInstance()
					.insert(new LogEntry(0, Calendar.getInstance(), u.getUsername(),
							String.format("User %s tentou apostar no jogo %s-%s em %s com o jogo em andamento",
									u.getUsername(), game.getHomeTeam(), game.getVisitTeam(),
									new SimpleDateFormat("MM/dd hh:mm").format(game.getData().getTime()))));
			fail(response, session, "Game already in progress", getRedirectInfo(source));
			return;
		}

		Nba2016GameBet bet = Nba2016GameBetDao.getInstance().get(new Integer(gameId), new Integer(u.getId()));
		if (bet == null) {
			bet = new Nba2016GameBet(new Integer(gameId).intValue(), u.getId(),
					homeWins ? game.getHomeTeam() : game.getVisitTeam(), 0, 0, inOT ? 1 : 0, 0);
			Nba2016GameBetDao.getInstance().insert(bet);
		} else {
			bet.setWinner(homeWins ? game.getHomeTeam() : game.getVisitTeam());
			bet.setOverTime(inOT ? 1 : 0);
			Nba2016GameBetDao.getInstance().update(bet);
		}
		LoggingDao.getInstance().insert(new LogEntry(0, Calendar.getInstance(), u.getUsername(),
				String.format("User %s bet on game %s-%s on %s : %s %s", u.getUsername(), game.getHomeTeam(),
						game.getVisitTeam(), new SimpleDateFormat("MM/dd hh:mm").format(game.getData().getTime()),
						bet.getWinner(), bet.getOverTime() == 1 ? " in OT" : "")));

		BaseSessionUtil.udpateSessionInfo(session, u);
		BaseSessionUtil.setSessionMessage(session, "BET SAVED!");
		response.sendRedirect(getRedirectInfo(source));
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
		return WebUtils.getRedirectInfo(page, Nba2016WebUtil.GAMES_JSP);
	}
}
