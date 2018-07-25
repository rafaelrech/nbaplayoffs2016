package rech.bolao.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import rech.bolao.bean.User;
import rech.bolao.dao.UserDao;
import rech.bolao.util.BaseSessionUtil;

/**
 * Servlet implementation class ChangeUserInfo
 */
@WebServlet("/ChangeUserInfo")
public class ChangeUserInfo extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ChangeUserInfo() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String source = request.getParameter("source");
		String target = request.getParameter("target");
		String newPwd = request.getParameter("password");
		String newUser = request.getParameter("username");
		HttpSession session = request.getSession();
		User u = (User) session.getAttribute("loggedUser");
		boolean changed = false;
		if (!(newUser == null) && !newUser.isEmpty()) {
			u.setUsername(newUser);
			changed = true;
		}
		if (!(newPwd == null) && !newPwd.isEmpty()) {
			u.setPassword(newPwd);
			changed = true;
		}
		if (changed) {
			UserDao.getInstance().update(u);
		}
		BaseSessionUtil.udpateSessionInfo(session, u);
		response.sendRedirect(getRedirectInfo(source));
	}


	private String getRedirectInfo(String page) {
		return WebUtils.getRedirectInfo(page, WebUtils.HOME_JSP);
	}


}
