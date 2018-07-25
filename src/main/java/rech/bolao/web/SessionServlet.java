package rech.bolao.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import rech.bolao.bean.User;
import rech.bolao.util.BaseSessionUtil;

/**
 * Servlet implementation class SessionServlet
 */
@WebServlet("/SessionServlet")
public class SessionServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public SessionServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String action = request.getParameter("action");
		String target = request.getParameter("target");

		HttpSession session = request.getSession();
		User u = (User) session.getAttribute("loggedUser");
		if (u == null) {
			BaseSessionUtil.setSessionMessage(session, "SESSION EXPIRED!");
			response.sendRedirect("login.jsp");
			return;
		}

		if (action == null || action.isEmpty()) {
			fail(response, session, "ACTION IS EMPTY");
			return;
		}

		if (!u.getRole().equalsIgnoreCase("admin")) {
			fail(response, session, "You are not an admin");
			return;
		}

		if (action.equalsIgnoreCase("FLUSH")) {
			BaseSessionUtil.udpateSessionInfo(session, u);
			BaseSessionUtil.setSessionMessage(session, String.format("Sesion cache flush"));
		}

		response.sendRedirect((target == null || target.isEmpty()) ? "dashboard.jsp" : target);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

	private void fail(HttpServletResponse response, HttpSession session, String msg) throws IOException {
		BaseSessionUtil.setSessionMessage(session, "ERROU FEIO! ERROU RUDE! " + msg);
		response.sendRedirect("dashboard.jsp");
	}
}
