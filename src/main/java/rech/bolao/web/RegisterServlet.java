package rech.bolao.web;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import rech.bolao.bean.Bolao;
import rech.bolao.bean.User;
import rech.bolao.bean.UserBolao;
import rech.bolao.dao.UserBolaoDao;
import rech.bolao.util.BaseSessionUtil;
import rio2016.bean.Rio2016User;
import rio2016.dao.Rio2016UserDao;

/**
 * Servlet implementation class DBServlet
 */
@WebServlet("/RegisterServlet")
public class RegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public RegisterServlet() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String ballot = request.getParameter("ballot");
		// String sql = request.getParameter("sql");
		// String object = request.getParameter("object");
		String source = request.getParameter("source");
		// String target = request.getParameter("target");
		HttpSession session = request.getSession();
		User u = (User) session.getAttribute("loggedUser");

		@SuppressWarnings("unchecked")
		ArrayList<Bolao> ballots = (ArrayList<Bolao>) session.getAttribute(BaseSessionUtil.ALL_BALLOTS);
		int bolaoId = -1;
		UserBolao registration = null;
		for (Bolao bolao : ballots) {
			if (bolao.getName().equalsIgnoreCase(ballot)) {
				bolaoId = bolao.getId();
				for (UserBolao ub : bolao.getRegisteredUsers()) {
					if (ub.getUserId() == u.getId()) {
						registration = ub;
						break;
					}
				}
				break;
			}
		}

		if (bolaoId == -1) {
			fail(response, session, "Bolão inexistente", source);
			return;
		}

		if (registration == null) {
			// not registered yet
			UserBolaoDao.getInstance().insert(new UserBolao(bolaoId, u.getId()));
			Rio2016UserDao.getInstance().insert(new Rio2016User(u.getId(), 0, 0, 0, 0));
		} else {
			// already registered
			UserBolaoDao.getInstance().delete(bolaoId, u.getId());
			Rio2016UserDao.getInstance().delete(u.getId());
		}

		BaseSessionUtil.udpateSessionInfo(session, u);

		response.sendRedirect(getRedirectInfo(source));
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

	private void fail(HttpServletResponse response, HttpSession session, String msg, String targetPage)
			throws IOException {
		BaseSessionUtil.setSessionMessage(session, "FAIL! " + msg);
		System.err.println(msg);
		response.sendRedirect(targetPage);
	}

	private String getRedirectInfo(String source) {
		return WebUtils.getRedirectInfo(source, "rio2016/home.jsp");
	}
}
