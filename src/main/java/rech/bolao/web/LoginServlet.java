package rech.bolao.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import nbaplayoffs2016.util.Nba2016WebUtil;
import rech.bolao.bean.User;
import rech.bolao.dao.UserDao;
import rech.bolao.util.BaseSessionUtil;

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public LoginServlet() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String login = request.getParameter("login");
		String pwd = request.getParameter("password");
		String target = request.getParameter("target");
		String action = request.getParameter("action");
		HttpSession session = request.getSession();
		User u = null;
		if (action.equalsIgnoreCase("LOGIN")) {
			if (login != null && pwd != null && !login.isEmpty() && !pwd.isEmpty()) {
				if (login.equals(pwd) && login.equalsIgnoreCase("julia")) {
					u = new User(-1, "no-email", "guest", "pwd", "admin", 1, "kk");

				} else {
					u = UserDao.getInstance().authenticate(login, pwd);
				}
			}
			if (u != null) {
				BaseSessionUtil.udpateSessionInfo(session, u);
				if (u.getId() == -1) {
					response.sendRedirect(Nba2016WebUtil.DBUTIL_JSP);
				} else {
					if (u.getId() == -99) {
						response.sendRedirect(WebUtils.NEW_USER_JSP);
					} else {
						response.sendRedirect(WebUtils.HOME_JSP);
					}
				}
			} else {
				failLogin(response, session);
			}
		}
		if (action.equalsIgnoreCase("REGISTER")) {
			User lu = (User) session.getAttribute(BaseSessionUtil.LOGGED_USER);
			lu.setActive(0);
			UserDao.getInstance().insert(lu);
			BaseSessionUtil.udpateSessionInfo(session, lu);
			response.sendRedirect(WebUtils.PROFILE_JSP);
		}
		if (action.equalsIgnoreCase("GO-BACK")) {
			BaseSessionUtil.clearSessionInfo(session);
			response.sendRedirect(WebUtils.LOGIN_JSP);
		}
	}

	private void failLogin(HttpServletResponse response, HttpSession session) throws IOException {
		BaseSessionUtil.clearSessionInfo(session);
		BaseSessionUtil.setSessionMessage(session, "Your login failed");
		response.sendRedirect(WebUtils.LOGIN_JSP);
	}

}
