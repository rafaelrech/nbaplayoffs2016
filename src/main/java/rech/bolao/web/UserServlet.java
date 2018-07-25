package rech.bolao.web;

import java.io.IOException;
import java.util.Random;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.mysql.jdbc.StringUtils;

import rech.bolao.bean.User;
import rech.bolao.dao.UserDao;
import rech.bolao.util.BaseSessionUtil;

/**
 * Servlet implementation class ChangeUserInfo
 */
@WebServlet("/UserServlet")
public class UserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public UserServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String action = request.getParameter("action");
		String source = request.getParameter("source");
		// String target = request.getParameter("target");
		String newPwd = request.getParameter("password");
		String newUser = request.getParameter("username");

		HttpSession session = request.getSession();
		User u = BaseSessionUtil.getLoggedUser(session);
		if (StringUtils.isNullOrEmpty(action)) {
			fail(response, session, "'action' is empty or null", getRedirectInfo(source));
			return;
		}

		if (action.equalsIgnoreCase("UPDATE")) {
			if (u == null) {
				fail(response, session, "Not logged in", getRedirectInfo(WebUtils.LOGIN_JSP));
				return;
			}
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
		}

		if (action.equalsIgnoreCase("PROFILE")) {
			if (u == null) {
				fail(response, session, "Not logged in", getRedirectInfo(WebUtils.LOGIN_JSP));
				return;
			}
			u.setActive((new Integer(request.getParameter("active"))).intValue());
			u.setFirstName(request.getParameter("firstName"));
			u.setLastName(request.getParameter("lastName"));
			u.setEmail(request.getParameter("email"));
			u.setSecondEmail(request.getParameter("secondEmail"));
			UserDao.getInstance().update(u);
			BaseSessionUtil.udpateSessionInfo(session, u);
		}

		if (action.equalsIgnoreCase("GEN_KEY")) {
			if (u == null) {
				fail(response, session, "Not logged in", getRedirectInfo(WebUtils.LOGIN_JSP));
				return;
			}
			String key = keyGenerator();
			u.setActive(0);
			u.setKey(key);

			// if (!(newUser == null) && !newUser.isEmpty()) {
			// u.setUsername(newUser);
			// changed = true;
			// }
			// if (!(newPwd == null) && !newPwd.isEmpty()) {
			// u.setPassword(newPwd);
			// changed = true;
			// }
			// if (changed) {
			// UserDao.getInstance().update(u);
			// }
			UserDao.getInstance().update(u);

			// String htmlBody = ""; // ...
			// byte[] attachmentData = null; // ...
			//
			// Multipart mp = new MimeMultipart();
			//
			// MimeBodyPart htmlPart = new MimeBodyPart();
			// htmlPart.setContent(htmlBody, "text/html");
			// mp.addBodyPart(htmlPart);
			//
			// MimeBodyPart attachment = new MimeBodyPart();
			// InputStream attachmentDataStream = new
			// ByteArrayInputStream(attachmentData);
			// attachment.setFileName("manual.pdf");
			// attachment.setContent(attachmentDataStream, "application/pdf");
			// mp.addBodyPart(attachment);
			//
			// msg.setContent(mp);
			BaseSessionUtil.setSessionMessage(session, "EMAIL SENT WITH VALIDATION KEY! ");
		}
		if (action.equalsIgnoreCase("VAL_KEY")) {
			if (StringUtils.isNullOrEmpty(newUser)) {
				fail(response, session, "'username' is empty or null", getRedirectInfo(WebUtils.LOGIN_JSP));
				return;
			}

			User user = UserDao.getInstance().getUserByUsernameOrEmail(newUser);
			if (user == null) {
				fail(response, session, "username does not exist", getRedirectInfo(WebUtils.LOGIN_JSP));
				return;
			}
			u.setActive(1);
			UserDao.getInstance().update(u);

			BaseSessionUtil.setSessionMessage(session, "USER ACTIVATED! ");
		}
		response.sendRedirect(getRedirectInfo(source));
	}

	private void fail(HttpServletResponse response, HttpSession session, String msg, String targetPage)
			throws IOException {
		BaseSessionUtil.setSessionMessage(session, "FAIL! " + msg);
		response.sendRedirect(targetPage);
	}

	private String getRedirectInfo(String page) {
		return WebUtils.getRedirectInfo(page, WebUtils.HOME_JSP);
	}

	public static String keyGenerator() {
		String ret = "";
		Random r = new Random();
		for (int x = 1; x <= 48; x++) {
			int rint = r.nextInt(16);
			ret += String.format("%x", rint);
		}
		return ret;
	}
}
