package rech.bolao.web;

import java.io.IOException;
import java.util.StringTokenizer;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import rech.bolao.bean.User;
import rech.bolao.util.BaseDBUtil;
import rech.bolao.util.BaseSessionUtil;

/**
 * Servlet implementation class DBServlet
 */
@WebServlet("/DBServlet")
public class DBServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public DBServlet() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String action = request.getParameter("action");
		String sql = request.getParameter("sql");
		String object = request.getParameter("object");
		String source = request.getParameter("source");
		String target = request.getParameter("target");

		HttpSession session = request.getSession();
		User u = (User) session.getAttribute("loggedUser");

		if (u==null) {
			fail(response, session, "You are not logged in", getRedirectInfo(source));
			return;
		}
		if (u.getId() != -1 && !(u.getRole().equalsIgnoreCase("admin"))) {
			fail(response, session, "You are not an admin", getRedirectInfo(source));
			return;
		}
		if (action == null || action.isEmpty()) {
			fail(response, session, "'action' is null or empty", getRedirectInfo(source));
			return;
		}

		if (action.equalsIgnoreCase("EXECUTE")) {
			StringTokenizer st = new StringTokenizer(sql, ";");
			int q = 0;
			while (st.hasMoreElements()) {
				String sql2exec = (String) st.nextElement();
				if (sql2exec.isEmpty()) {
					fail(response, session, "sql is empty", getRedirectInfo(source));
					return;
				}
				if ((sql2exec.toUpperCase().indexOf("UPDATE") > -1 || sql2exec.toUpperCase().indexOf("UPDATE") > -1)
						&& (sql2exec.toUpperCase().indexOf("WHERE") < 0)) {
					fail(response, session, "missing WHERE clause", getRedirectInfo(source));
					return;
				}
				System.out.println(sql2exec);
				q += BaseDBUtil.executeDML(sql2exec);
			}
			BaseSessionUtil.udpateSessionInfo(session, u);
			BaseSessionUtil.setSessionMessage(session, String.format("%d commands successfully executed!", q));
		}

		if (action.equalsIgnoreCase("CREATE")) {
			if (object == null || object.isEmpty()) {
				fail(response, session, "'object' is null or empty", getRedirectInfo(source));
				return;
			}
			BaseDBUtil.createTable(object.toUpperCase());
			BaseSessionUtil.udpateSessionInfo(session, u);
			BaseSessionUtil.setSessionMessage(session, String.format("Table %s created", object));
		}

		if (action.equalsIgnoreCase("DROP")) {
			if (object == null || object.isEmpty()) {
				fail(response, session, "'object' is null or empty", getRedirectInfo(source));
				return;
			}
			BaseDBUtil.dropTable(object.toUpperCase());
			BaseSessionUtil.udpateSessionInfo(session, u);
			BaseSessionUtil.setSessionMessage(session, String.format("Table %s dropped", object));
		}

		if (action.equalsIgnoreCase("CLEAN")) {
			if (object == null || object.isEmpty()) {
				fail(response, session, "'object' is null or empty", getRedirectInfo(source));
				return;
			}
			BaseDBUtil.dropTable(object.toUpperCase());
			BaseDBUtil.createTable(object.toUpperCase());
			BaseSessionUtil.udpateSessionInfo(session, u);
			BaseSessionUtil.setSessionMessage(session, String.format("Table %s created", object));
		}
		
		if (action.equalsIgnoreCase("LOAD")) {
			if (object == null || object.isEmpty()) {
				fail(response, session, "'object' is null or empty", getRedirectInfo(source));
				return;
			}
			if (object.equalsIgnoreCase("USER")) {
				BaseDBUtil.loadUsers();
			}
			if (object.equalsIgnoreCase("BOLAO")) {
				BaseDBUtil.loadBallots();;
			}
			if (object.equalsIgnoreCase("USER-BOLAO")) {
				BaseDBUtil.loadUserBallots();
			}
			BaseSessionUtil.udpateSessionInfo(session, u);
			BaseSessionUtil.setSessionMessage(session, String.format("Table %s loaded", object));
		}

		response.sendRedirect(getRedirectInfo(target));

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

	private void fail(HttpServletResponse response, HttpSession session, String msg, String targetPage)
			throws IOException {
		BaseSessionUtil.setSessionMessage(session, "FAIL! " + msg);
		response.sendRedirect(targetPage);
	}

	private String getRedirectInfo(String source) {
		// TODO which jsp?
		return WebUtils.getRedirectInfo(source, "index.jsp");
	}
}
