package rech.bolao.util;

import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.http.HttpSession;

import rech.bolao.bean.Bolao;
import rech.bolao.bean.User;
import rech.bolao.bean.UserBolao;
import rech.bolao.dao.BolaoDao;
import rech.bolao.dao.UserBolaoDao;
import rech.bolao.dao.UserDao;

public class BaseSessionUtil {

	public static final String MESSAGE = "message";

	public static final String LOGGED_USER = "loggedUser";
	public static final String ALL_USERS = "allUsers";
	public static final String ALL_BALLOTS = "allBallots";
	public static final String ALL_USER_BALLOTS = "allUserBallots";

	public static void udpateSessionInfo(HttpSession session, User u) {

		clearSessionInfo(session);

		// FULL INFO
		ArrayList<User> allUsers;
		try {
			allUsers = UserDao.getInstance().listAll();
		} catch (SQLException e1) {
			allUsers = new ArrayList<User>();
		}
		ArrayList<Bolao> allBallots;
		try {
			allBallots = BolaoDao.getInstance().listAll();
		} catch (SQLException e1) {
			allBallots = new ArrayList<Bolao>();
		}
		ArrayList<UserBolao> allUserBallots;
		try {
			allUserBallots = UserBolaoDao.getInstance().listAll();
		} catch (SQLException e1) {
			allUserBallots = new ArrayList<UserBolao>();
		}
		session.setAttribute(ALL_USERS, allUsers);
		session.setAttribute(ALL_BALLOTS, allBallots);
		session.setAttribute(ALL_USER_BALLOTS, allUserBallots);

		if (u != null) {
			for (User user : allUsers) {
				if (user.getId() == u.getId()) {
					u = user;
				}
			}
		}

		for (Bolao bolao : allBallots) {
			for (UserBolao userBolao : allUserBallots) {
				if (userBolao.getBolaoId() == bolao.getId()) {
					bolao.addRegisteredUser(userBolao);
					userBolao.setBolao(bolao);
					for (User user : allUsers) {
						if (user.getId() == userBolao.getUserId()) {
							userBolao.setUser(user);
							user.addBallot(userBolao);
						}
					}
				}
			}
		}

		// USER INFO
		session.setAttribute(LOGGED_USER, u);
		try {
			nbaplayoffs2016.util.Nba2016SessionUtil.udpateSessionInfo(session, u);
		} catch (Exception e) {
			e.printStackTrace();
		}
		try {
			rio2016.util.Rio2016SessionUtil.udpateSessionInfo(session, u);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void clearSessionInfo(HttpSession session) {
		session.setAttribute(LOGGED_USER, null);
		session.setAttribute(ALL_USERS, null);
		session.setAttribute(ALL_BALLOTS, null);
		try {
			nbaplayoffs2016.util.Nba2016SessionUtil.clearSessionInfo(session);
		} catch (Exception e) {
		}
	}

	public static User getLoggedUser(HttpSession session) {
		return (User) session.getAttribute(LOGGED_USER);
	}

	public static void setSessionMessage(HttpSession session, String message) {
		session.setAttribute(MESSAGE, message);
	}

	public static void clearSessionMessage(HttpSession session) {
		session.setAttribute(MESSAGE, null);
	}

	public static boolean isUserEnrolled(HttpSession session, String ballotName) {
		User loggedUser = BaseSessionUtil.getLoggedUser(session);
		if (loggedUser == null) {
			return false;
		}
		for (UserBolao bolao : loggedUser.getBallots()) {
			if (bolao.getBolao().getName().equalsIgnoreCase(ballotName)) {
				return true;
			}
		}
		return false;
	}

}
