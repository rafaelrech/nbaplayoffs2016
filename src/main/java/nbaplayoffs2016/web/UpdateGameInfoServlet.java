package nbaplayoffs2016.web;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import nbaplayoffs2016.bean.Nba2016Game;
import nbaplayoffs2016.bean.Nba2016Series;
import nbaplayoffs2016.dao.Nba2016GameDao;
import nbaplayoffs2016.dao.Nba2016SeriesDao;
import nbaplayoffs2016.util.Nba2016SessionUtil;
import nbaplayoffs2016.util.Nba2016WebUtil;
import rech.bolao.bean.User;
import rech.bolao.util.BaseSessionUtil;
import rech.bolao.web.WebUtils;

/**
 * Servlet implementation class UpdateGameInfoServlet
 */
@WebServlet("/UpdateGameInfoServlet")
public class UpdateGameInfoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public UpdateGameInfoServlet() {
		super();
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	@SuppressWarnings("unchecked")
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String source = request.getParameter("source");
		String target = request.getParameter("target");

		String datetime = request.getParameter("datetime");
		String gameId = request.getParameter("gameId");
		String visitScore = request.getParameter("visitScore");
		String homeScore = request.getParameter("homeScore");
		String ot = request.getParameter("ot");
		Calendar gameDate = null;

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

		if (!u.getRole().equalsIgnoreCase("gameId")) {
			fail(response, session, "Game not informed", getRedirectInfo(source));
			return;
		}

		if (visitScore == null || visitScore.isEmpty()) {
			visitScore = "0";
		}
		if (homeScore == null || homeScore.isEmpty()) {
			homeScore = "0";
		}
		if (datetime != null && !datetime.isEmpty()) {
			try {
				Calendar aux = Calendar.getInstance();
				aux.set(2105, Integer.valueOf(datetime.substring(0, 2)), Integer.valueOf(datetime.substring(3, 5)),
						Integer.valueOf(datetime.substring(6, 8)), Integer.valueOf(datetime.substring(9, 11)));
				gameDate = aux;
			} catch (Throwable t) {
			}
		}

		ArrayList<Nba2016Game> allGames = (ArrayList<Nba2016Game>) session.getAttribute(Nba2016SessionUtil.ALL_GAMES);
		for (Nba2016Game game : allGames) {
			if (game.getId() != Integer.valueOf(gameId)) {
				continue;
			}
			boolean dateChanged = false, scoreChanged = false;
			if (gameDate != null) {
				game.setData(gameDate);
				dateChanged = true;
			}
			if (!visitScore.equals(homeScore)) {
				game.setHomeScore(Integer.valueOf(homeScore));
				game.setVisitScore(Integer.valueOf(visitScore));
				game.setOverTime(Integer.valueOf(ot));
				scoreChanged = true;
			}
			if (dateChanged || scoreChanged) {
				Nba2016GameDao.getInstance().update(game);
			}
			if (!scoreChanged) {
				break;
			}
			Nba2016Series series = game.getSeries();

			int team1Wins = 0;
			int team2Wins = 0;
			for (Nba2016Game seriesGame : series.getGames()) {
				if (seriesGame.getHomeScore() > seriesGame.getVisitScore()) {
					if (series.getTeam1().equals(seriesGame.getHomeTeam())) {
						team1Wins++;
					} else {
						team2Wins++;
					}
				}
				if (seriesGame.getHomeScore() < seriesGame.getVisitScore()) {
					if (series.getTeam1().equals(seriesGame.getHomeTeam())) {
						team2Wins++;
					} else {
						team1Wins++;
					}
				}
			}
			if (team1Wins == 4 || team2Wins == 4) {
				series.setWinner(((team1Wins > team2Wins) ? series.getTeam1() : series.getTeam2()));
				series.setNbrGames(team1Wins + team2Wins);
				Nba2016SeriesDao.getInstance().update(series);
				for (Nba2016Series upcomingSerie : (ArrayList<Nba2016Series>) session.getAttribute(Nba2016SessionUtil.ALL_SERIES)) {
					if (upcomingSerie.getPrevId1() != series.getId() && upcomingSerie.getPrevId2() != series.getId()) {
						continue;
					}
					if (upcomingSerie.getPrevId1() == series.getId()) {
						upcomingSerie.setTeam1(series.getWinner());
					} else {
						upcomingSerie.setTeam2(series.getWinner());
					}
					Nba2016SeriesDao.getInstance().update(upcomingSerie);
				}
			} else {
				series.setWinner("");
				series.setNbrGames(0);
				Nba2016SeriesDao.getInstance().update(series);
			}
			break;
		}

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
