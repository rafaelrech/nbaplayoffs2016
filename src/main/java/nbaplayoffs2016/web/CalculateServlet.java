package nbaplayoffs2016.web;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import nbaplayoffs2016.bean.Nba2016Game;
import nbaplayoffs2016.bean.Nba2016GameBet;
import nbaplayoffs2016.bean.Nba2016User;
import nbaplayoffs2016.bean.Nba2016Series;
import nbaplayoffs2016.bean.Nba2016SeriesBet;
import nbaplayoffs2016.bean.Nba2016TieBreaker;
import nbaplayoffs2016.bean.Nba2016TieBreakerBet;
import nbaplayoffs2016.dao.Nba2016GameBetDao;
import nbaplayoffs2016.dao.Nba2016UserDao;
import nbaplayoffs2016.dao.Nba2016SeriesBetDao;
import nbaplayoffs2016.dao.Nba2016TieBreakerBetDao;
import nbaplayoffs2016.util.Nba2016ScoresUtil;
import nbaplayoffs2016.util.Nba2016SessionUtil;
import nbaplayoffs2016.util.Nba2016WebUtil;
import rech.bolao.bean.User;
import rech.bolao.util.BaseSessionUtil;
import rech.bolao.web.WebUtils;

/**
 * Servlet implementation class CalculateServlet
 */
@WebServlet("/CalculateServlet")
public class CalculateServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public CalculateServlet() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	@SuppressWarnings("unchecked")
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String source = request.getParameter("source");
		String target = request.getParameter("target");

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

		ArrayList<Nba2016User> users = (ArrayList<Nba2016User>) session.getAttribute(Nba2016SessionUtil.ALL_USERS);
		ArrayList<Nba2016Game> games = (ArrayList<Nba2016Game>) session.getAttribute(Nba2016SessionUtil.ALL_GAMES);
		ArrayList<Nba2016Series> series = (ArrayList<Nba2016Series>) session.getAttribute(Nba2016SessionUtil.ALL_SERIES);
		ArrayList<Nba2016TieBreaker> bonus = (ArrayList<Nba2016TieBreaker>) session.getAttribute(Nba2016SessionUtil.ALL_BONUS);

		for (Nba2016User user : users) {
			int gameBetScore = 0;
			int seriesBetScore = 0;
			int mvpBetScore = 0;
			for (Nba2016GameBet bet : user.getGameBets()) {
				for (Nba2016Game game : games) {
					if (bet.getGameId() != game.getId()) {
						continue;
					}
					// Calculate score
					int currScore = Nba2016ScoresUtil.calculateGameBetScore(bet, game);
					gameBetScore += currScore;
					bet.setUserScore(currScore);
					Nba2016GameBetDao.getInstance().update(bet);
					break;
				}
			}

			for (Nba2016SeriesBet bet : user.getSeriesBets()) {
				for (Nba2016Series serie : series) {
					if (bet.getBracketId() != serie.getId()) {
						continue;
					}
					// test whether series is over
					int currScore = 0;
					if (!serie.getWinner().isEmpty()) {
						if (bet.getWinner().equalsIgnoreCase(serie.getWinner())) {
							switch (serie.getFase()) {
							case 1:
								currScore = Nba2016ScoresUtil.SERIES_WINNER_SCORE_R1
										* ((bet.getGames() == serie.getNbrGames()) ? 2 : 1);
								break;
							case 2:
								currScore = Nba2016ScoresUtil.SERIES_WINNER_SCORE_R2
										* ((bet.getGames() == serie.getNbrGames()) ? 2 : 1);
								break;
							case 3:
								currScore = Nba2016ScoresUtil.SERIES_WINNER_SCORE_R3
										* ((bet.getGames() == serie.getNbrGames()) ? 2 : 1);
								break;
							case 4:
								currScore = Nba2016ScoresUtil.SERIES_WINNER_SCORE_R4
										* ((bet.getGames() == serie.getNbrGames()) ? 2 : 1);
								break;
							default:
								break;
							}

						}
					}
					seriesBetScore += currScore;
					bet.setUserScore(currScore);
					Nba2016SeriesBetDao.getInstance().update(bet);
					break;
				}
			}

			for (Nba2016TieBreakerBet bet : user.getTbBets()) {
				for (Nba2016TieBreaker tb : bonus) {
					if (bet.getTieBrackerId() != tb.getId()) {
						continue;
					}
					if (tb.getValue() != null && !tb.getValue().isEmpty()) {
						if (bet.getValue().equalsIgnoreCase(tb.getValue())) {
							mvpBetScore += Nba2016ScoresUtil.MVP_SCORE;
						}
					}
					bet.setUserScore(mvpBetScore);
					Nba2016TieBreakerBetDao.getInstance().update(bet);
					break;
				}
			}

			user.setGameBetsScore(gameBetScore);
			user.setSeriesBetsScore(seriesBetScore);
			user.setMvpBetScore(mvpBetScore);
			user.setScore(gameBetScore + seriesBetScore + mvpBetScore);
			Nba2016UserDao.getInstance().update(user);
		}

		BaseSessionUtil.udpateSessionInfo(session, u);
		BaseSessionUtil.setSessionMessage(session, "Successful score calculation");
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
