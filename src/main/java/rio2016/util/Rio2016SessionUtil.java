package rio2016.util;

import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.http.HttpSession;

import rech.bolao.bean.User;
import rech.bolao.util.BaseSessionUtil;
import rio2016.bean.Rio2016Competitor;
import rio2016.bean.Rio2016MedalBet;
import rio2016.bean.Rio2016Participation;
import rio2016.bean.Rio2016Sport;
import rio2016.bean.Rio2016User;
import rio2016.dao.Rio2016CompetitorDao;
import rio2016.dao.Rio2016MedalBetDao;
import rio2016.dao.Rio2016ParticipationDao;
import rio2016.dao.Rio2016SportDao;
import rio2016.dao.Rio2016UserDao;

public class Rio2016SessionUtil {

	public static final String ALL_USERS = "allRio2016Users";
	public static final String ALL_SPORTS = "allRio2016Sports";
	public static final String ALL_COMPETITORS = "allRio2016Competitors";
	public static final String ALL_PARTICIPATION = "allRio2016Participations";
	public static final String ALL_MEDAL_BETS = "allRio2016MedalBets";

	public static void udpateSessionInfo(HttpSession session, User u) {

		clearSessionInfo(session);

		// FULL INFO
		ArrayList<Rio2016User> allRio2016Users;
		try {
			allRio2016Users = Rio2016UserDao.getInstance().listAll();
		} catch (SQLException e5) {
			allRio2016Users = new ArrayList<Rio2016User>();
		}

		ArrayList<Rio2016Sport> allSports;
		try {
			allSports = Rio2016SportDao.getInstance().listAll();
		} catch (SQLException e2) {
			allSports = new ArrayList<Rio2016Sport>();
		}

		ArrayList<Rio2016Competitor> allCompetitors;
		try {
			allCompetitors = Rio2016CompetitorDao.getInstance().listAll();
		} catch (SQLException e4) {
			allCompetitors = new ArrayList<Rio2016Competitor>();
		}

		ArrayList<Rio2016MedalBet> allMedalBets;
		try {
			allMedalBets = Rio2016MedalBetDao.getInstance().listAll();
		} catch (SQLException e3) {
			allMedalBets = new ArrayList<Rio2016MedalBet>();
		}

		ArrayList<Rio2016Participation> allParticipations;
		try {
			allParticipations = Rio2016ParticipationDao.getInstance().listAll();
		} catch (SQLException e1) {
			allParticipations = new ArrayList<Rio2016Participation>();
		}

		ArrayList<User> baseUsers = (ArrayList<User>) session.getAttribute(BaseSessionUtil.ALL_USERS);
		for (User baseUser : baseUsers) {
			for (Rio2016User user : allRio2016Users) {
				if (user.getUserId() == baseUser.getId()) {
					user.setUser(baseUser);
				}
				for (Rio2016MedalBet medalBet : allMedalBets) {
					if (medalBet.getUserId() == user.getUserId()) {
						medalBet.setUser(user);
						user.addBet(medalBet);
					}
				}
			}
		}

		for (Rio2016Sport sport : allSports) {
			for (Rio2016MedalBet medalBet : allMedalBets) {
				if (medalBet.getSportId() == sport.getId()) {
					medalBet.setSport(sport);
					sport.addBet(medalBet);
				}
			}
			for (Rio2016Participation participation : allParticipations) {
				if (participation.getSportId() == sport.getId()) {
					participation.setSport(sport);
					sport.addParticipation(participation);
				}
			}
		}

		for (Rio2016Competitor competitor : allCompetitors) {
			for (Rio2016MedalBet medalBet : allMedalBets) {
				if (medalBet.getCompetitorId() == competitor.getId()) {
					medalBet.setCompetitor(competitor);
					competitor.addBet(medalBet);
				}
			}
			for (Rio2016Participation participation : allParticipations) {
				if (participation.getCompetitorId() == competitor.getId()) {
					participation.setCompetitor(competitor);
					competitor.addParticipation(participation);
				}
			}
		}

		session.setAttribute(ALL_USERS, allRio2016Users);
		session.setAttribute(ALL_SPORTS, allSports);
		session.setAttribute(ALL_COMPETITORS, allCompetitors);
		session.setAttribute(ALL_PARTICIPATION, allParticipations);
		session.setAttribute(ALL_MEDAL_BETS, allMedalBets);
	}

	public static void clearSessionInfo(HttpSession session) {
		session.setAttribute(ALL_USERS, null);
		session.setAttribute(ALL_SPORTS, null);
		session.setAttribute(ALL_COMPETITORS, null);
		session.setAttribute(ALL_PARTICIPATION, null);
		session.setAttribute(ALL_MEDAL_BETS, null);
	}

}
