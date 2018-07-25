package nbaplayoffs2016.web;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import nbaplayoffs2016.bean.Nba2016Game;
import nbaplayoffs2016.dao.Nba2016GameDao;
import nbaplayoffs2016.util.Nba2016WebUtil;
import rech.bolao.bean.User;
import rech.bolao.util.BaseSessionUtil;
import rech.bolao.util.xmlstats.XmlStatsUtil;
import rech.bolao.web.WebUtils;

@WebServlet("/CreateNewGameServlet")
public class CreateNewGameServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public CreateNewGameServlet() {
		super();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String source = request.getParameter("source");
		String target = request.getParameter("target");

		String datetime = "2016/" + request.getParameter("gameDate") + " " + request.getParameter("gameTime");
		String fase = request.getParameter("fase");
		String bracketId = request.getParameter("bracketId");
		String homeTeam = request.getParameter("homeTeam");
		String visitTeam = request.getParameter("visitTeam");

		HttpSession session = request.getSession();
		User u = BaseSessionUtil.getLoggedUser(session);
		if (u == null) {
			fail(response, session, "Not logged in", getRedirectInfo(source));
			return;
		}
		if (!u.getRole().equalsIgnoreCase("admin")) {
			fail(response, session, "You are not an admin", getRedirectInfo(source));
			return;
		}

		Calendar gameDate = Calendar.getInstance();
		SimpleDateFormat sf = new SimpleDateFormat("yyyy/MM/dd hh:mm");
		try {
			gameDate.setTime(sf.parse(datetime));
		} catch (ParseException e) {
			e.printStackTrace();
		}

		Nba2016Game g = new Nba2016Game(-1, XmlStatsUtil.getInstance().getGameId(homeTeam, visitTeam, gameDate),
				Integer.valueOf(fase).intValue(), Integer.valueOf(bracketId).intValue(), gameDate, homeTeam, -1,
				visitTeam, -1, 0, 0);

		// '20160416-houston-rockets-at-golden-state-warriors', 1, 5, '2016-4-16
		// 15:30', 'GS', 104, 'HOU', 78, 0, 2);
		if (g.getExternalId().length() <= 20) {
			fail(response, session, "Failed generating the id", getRedirectInfo(source));
			return;
		}
		Nba2016GameDao.getInstance().insert(g);
		BaseSessionUtil.udpateSessionInfo(session, u);
		BaseSessionUtil.setSessionMessage(session, "Game saved!");

		response.sendRedirect(getRedirectInfo(source));
	}

	private void fail(HttpServletResponse response, HttpSession session, String msg, String targetPage)
			throws IOException {
		BaseSessionUtil.setSessionMessage(session, "FAIL! " + msg);
		response.sendRedirect(targetPage);
	}

	private String getRedirectInfo(String page) {
		return WebUtils.getRedirectInfo(page, Nba2016WebUtil.DASHBOARD_JSP);
	}
}
