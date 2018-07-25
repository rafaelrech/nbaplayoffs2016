package nbaplayoffs2016.util;

import nbaplayoffs2016.bean.Nba2016Game;
import nbaplayoffs2016.bean.Nba2016GameBet;

public class Nba2016ScoresUtil {
	public static final int MVP_SCORE = 100;
	public static final int SERIES_WINNER_SCORE_R1 = 20;
	public static final int SERIES_WINNER_SCORE_R2 = 40;
	public static final int SERIES_WINNER_SCORE_R3 = 70;
	public static final int SERIES_WINNER_SCORE_R4 = 100;
	public static final int GAME_WINNER_SCORE_R1 = 10;
	public static final int GAME_WINNER_SCORE_R2 = 20;
	public static final int GAME_WINNER_SCORE_R3 = 40;
	public static final int GAME_WINNER_SCORE_R4 = 60;
	public static final int GAME_OT_BONUS_SCORE = 10;
	public static final int GAME_REG_BONUS_SCORE = 2;

	public static int calculateGameBetScore(Nba2016GameBet bet, Nba2016Game game) {
		int gameBetScore = 0;
		if (game.getHomeScore() > 0 && game.getVisitScore() > 0) {
			// game already finished
			if ((game.getHomeScore() > game.getVisitScore()) && (bet.getWinner().equalsIgnoreCase(game.getHomeTeam()))) {
				switch (game.getFase()) {
				case 1:
					gameBetScore += GAME_WINNER_SCORE_R1;
					break;
				case 2:
					gameBetScore += GAME_WINNER_SCORE_R2;
					break;
				case 3:
					gameBetScore += GAME_WINNER_SCORE_R3;
					break;
				case 4:
					gameBetScore += GAME_WINNER_SCORE_R4;
					break;
				default:
					break;
				}
			}
			if ((game.getHomeScore() < game.getVisitScore()) && (bet.getWinner().equalsIgnoreCase(game.getVisitTeam()))) {
				switch (game.getFase()) {
				case 1:
					gameBetScore += GAME_WINNER_SCORE_R1;
					break;
				case 2:
					gameBetScore += GAME_WINNER_SCORE_R2;
					break;
				case 3:
					gameBetScore += GAME_WINNER_SCORE_R3;
					break;
				case 4:
					gameBetScore += GAME_WINNER_SCORE_R4;
					break;
				default:
					break;
				}
			}
			if (gameBetScore > 0 && game.getOverTime() <= 0 && bet.getOverTime() <= 0) {
				gameBetScore += GAME_REG_BONUS_SCORE;
			}
			if (gameBetScore > 0 && game.getOverTime() > 0 && bet.getOverTime() > 0) {
				gameBetScore += GAME_OT_BONUS_SCORE;
			}
		}
		return gameBetScore;
	}

}
