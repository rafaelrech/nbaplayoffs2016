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
import nbaplayoffs2016.bean.Nba2016SeriesBet;
import nbaplayoffs2016.dao.Nba2016SeriesBetDao;
import nbaplayoffs2016.dao.Nba2016SeriesDao;
import nbaplayoffs2016.util.Nba2016SessionUtil;
import rech.bolao.bean.LogEntry;
import rech.bolao.bean.User;
import rech.bolao.dao.LoggingDao;
import rech.bolao.util.BaseSessionUtil;

/**
 * Servlet implementation class BetServlet
 */
@WebServlet("/SeriesBetServlet")
public class SeriesBetServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public SeriesBetServlet() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String seriesId = request.getParameter("seriesId");
		String winner = request.getParameter("winner");
		String nbrGames = request.getParameter("games");

		HttpSession session = request.getSession();
		User u = BaseSessionUtil.getLoggedUser(session);

		if (u == null) {
			fail(response, session, "NOT LOGGED IN");
			return;
		}
		if (seriesId == null || seriesId.isEmpty()) {
			fail(response, session, "SERIES NOT INFORMED");
			return;
		}

		Nba2016Series serie = Nba2016SeriesDao.getInstance().get(Integer.valueOf(seriesId));

		ArrayList<Nba2016Game> games = serie.getGames();
		for (Nba2016Game game : games) {
			if (game.getHomeScore() != game.getVisitScore()) {
				LoggingDao.getInstance().insert(new LogEntry(0, Calendar.getInstance(), u.getUsername(),
						String.format("User %s tentou apostar em uma série em andamento", u.getUsername())));
				fail(response, session, "SERIES ALREADY IN PROGRESS");
				return;
			}
		}

		String prev1BetWinner = new String();
		String prev2BetWinner = new String();
		ArrayList<Nba2016SeriesBet> bracketBets = (ArrayList<Nba2016SeriesBet>) session.getAttribute(Nba2016SessionUtil.ALL_SERIES_BETS);
		for (Nba2016SeriesBet bet : bracketBets) {
			if (bet.getUserId() != u.getId()) {
				continue;
			}
			if (bet.getBracketId() == serie.getPrevId1()) {
				prev1BetWinner = bet.getWinner();
			}
			if (bet.getBracketId() == serie.getPrevId2()) {
				prev2BetWinner = bet.getWinner();
			}
		}
		if (!winner.equalsIgnoreCase(prev1BetWinner) && !winner.equalsIgnoreCase(prev2BetWinner)) {
			LoggingDao.getInstance()
					.insert(new LogEntry(0, Calendar.getInstance(), u.getUsername(),
							String.format(
									"User %s tentou apostar em um time (%s) no qual nao havia apostado na fase anterior",
									u.getUsername(), winner)));
			fail(response, session, "YOU DIDN'T BET ON THIS TEAM IN THE PREVIOUS SERIES");
			return;
		}

		Nba2016SeriesBet bet = Nba2016SeriesBetDao.getInstance().get(new Integer(seriesId), new Integer(u.getId()));
		if (bet == null) {
			bet = new Nba2016SeriesBet(new Integer(seriesId).intValue(), u.getId(), winner, new Integer(nbrGames), 0);
			Nba2016SeriesBetDao.getInstance().insert(bet);
		} else {
			bet.setWinner(winner);
			bet.setGames(new Integer(nbrGames));
			Nba2016SeriesBetDao.getInstance().update(bet);
		}
		LoggingDao.getInstance()
				.insert(new LogEntry(0, Calendar.getInstance(), u.getUsername(),
						String.format("User %s apostou na serie %s-%s : %s %d", u.getUsername(), serie.getTeam1(),
								serie.getTeam2(), bet.getWinner(), bet.getGames())));

		BaseSessionUtil.udpateSessionInfo(session, u);
		BaseSessionUtil.setSessionMessage(session, "BET SAVED!");
		response.sendRedirect("main.jsp");
	}

	private void fail(HttpServletResponse response, HttpSession session, String msg) throws IOException {
		BaseSessionUtil.setSessionMessage(session, "ERROU FEIO! ERROU RUDE! " + msg);
		response.sendRedirect("main.jsp");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
