package rio2016.web;

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
import rech.bolao.web.WebUtils;
import rio2016.util.Rio2016DBUtil;
import rio2016.util.Rio2016WebUtil;

@WebServlet("/rio2016/DBServlet")
public class Rio2016DBServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public Rio2016DBServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String sql = request.getParameter("sql");
		String source = request.getParameter("source");
		String target = request.getParameter("target");
		String action = request.getParameter("action");
		String object = request.getParameter("object");

		HttpSession session = request.getSession();
		User u = (User) session.getAttribute("loggedUser");

		if (!u.getRole().equalsIgnoreCase("admin")) {
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
		}		
		if (action.equalsIgnoreCase("CREATE")) {
			if (object == null || object.isEmpty()) {
				fail(response, session, "'object' is null or empty", getRedirectInfo(source));
				return;
			}
			Rio2016DBUtil.getDaoFromBean(object).createTable();
			BaseSessionUtil.udpateSessionInfo(session, u);
			BaseSessionUtil.setSessionMessage(session, String.format("Table %s created", object));
		}

		if (action.equalsIgnoreCase("DROP")) {
			if (object == null || object.isEmpty()) {
				fail(response, session, "'object' is null or empty", getRedirectInfo(source));
				return;
			}
			Rio2016DBUtil.getDaoFromBean(object).dropTable();
			BaseSessionUtil.udpateSessionInfo(session, u);
			BaseSessionUtil.setSessionMessage(session, String.format("Table %s dropped", object));
		}

		if (action.equalsIgnoreCase("CLEAN")) {
			if (object == null || object.isEmpty()) {
				fail(response, session, "'object' is null or empty", getRedirectInfo(source));
				return;
			}
			Rio2016DBUtil.getDaoFromBean(object).dropTable();
			Rio2016DBUtil.getDaoFromBean(object).createTable();
			BaseSessionUtil.udpateSessionInfo(session, u);
			BaseSessionUtil.setSessionMessage(session, String.format("Table %s created", object));
		}

		if (action.equalsIgnoreCase("LOAD")) {
			if (object == null || object.isEmpty()) {
				fail(response, session, "'object' is null or empty", getRedirectInfo(source));
				return;
			}
			if (object.equalsIgnoreCase(Rio2016DBUtil.USER_BEAN)) {
				Rio2016DBUtil.loadUsers();
			}
			if (object.equalsIgnoreCase(Rio2016DBUtil.SPORT_BEAN)) {
				Rio2016DBUtil.loadSports();
			}
			if (object.equalsIgnoreCase(Rio2016DBUtil.COMPETITOR_BEAN)) {
				Rio2016DBUtil.loadCompetitors();
			}
			if (object.equalsIgnoreCase(Rio2016DBUtil.PARTICIPATION_BEAN)) {
				Rio2016DBUtil.loadParticipations();
			}
			if (object.equalsIgnoreCase(Rio2016DBUtil.MEDAL_BET_BEAN)) {
				// Rio2016DBUtil.load;
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
		System.out.println(msg);
		BaseSessionUtil.setSessionMessage(session, "FAIL! " + msg);
		response.sendRedirect(targetPage);
	}

	private String getRedirectInfo(String page) {
		return WebUtils.getRedirectInfo(page, Rio2016WebUtil.DBUTIL_JSP);
	}

}
