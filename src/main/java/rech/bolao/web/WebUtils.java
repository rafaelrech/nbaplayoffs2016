package rech.bolao.web;

import java.util.Calendar;

import com.mysql.jdbc.StringUtils;

public class WebUtils {

	public static final String LOGIN_JSP = "login.jsp";
	public static final String HOME_JSP = "home.jsp";
	public static final String NEW_USER_JSP = "new_user.jsp";
	public static final String PROFILE_JSP = "profile.jsp";

	public static String getRedirectInfo(String source, String defaultPage) {
		return StringUtils.isNullOrEmpty(source) ? defaultPage : source;
	}

	public static Calendar truncate(Calendar cal) {
		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MILLISECOND, 0);
		return cal;
	}

	public static String getBetJSLink(int sportId, int competitorId, int betValue, boolean pending,
			boolean alreadyStarted) {
		String retString = new String();
		if (pending && !alreadyStarted) {
			retString = String.format("onclick=\"javascript: bet('BET', '%d', '%d', '%d', 'sport%d')\"", sportId,
					competitorId, betValue, sportId);
		}
		return retString;
	}
}
