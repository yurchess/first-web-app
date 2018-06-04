package mitroshin.util;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

/**
 * A utility class which encapsulates a Cookie retrieving operations.
 */
public class CookieUtil {

    private CookieUtil() {}

    /**
     * Get {@code Cookie} object from {@code HttpServletRequest} by cookie name.
     * @param request
     * @param cookieName
     * @return A {@code Cookie} object with required name if one exists,
     * otherwise returns {@code null}
     */
    public static Cookie getCookie(HttpServletRequest request, String cookieName) {
        Cookie[] cookies = request.getCookies();
        for (Cookie cookie : cookies) {
            if (cookie.getName().equals(cookieName)) {
                return cookie;
            }
        }
        return null;
    }

    public static String getCookieValue(Cookie[] cookies, String cookieName) {
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals(cookieName)) {
                    return cookie.getValue();
                }
            }
        }
        return "";
    }
}
