package mitroshin.servlets;

import mitroshin.data.UserDB;
import mitroshin.entities.User;
import mitroshin.util.CookieUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet("/start")
public class StartServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String url = checkUserLogged(request, response);
        getServletContext()
                .getRequestDispatcher(url)
                .forward(request, response);
    }

    private String checkUserLogged(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        session.removeAttribute("guessedNumber");
        session.removeAttribute("attempts");
        User user = (User) session.getAttribute("user");
        String url = "/play.jsp";

        if (user == null) {
            Cookie cookie = CookieUtil.getCookie(request, "login");
            if (cookie != null && UserDB.loginExists(cookie.getValue())) {
                user = new User(cookie.getValue(), null);
                session.setAttribute("user", user);
                url = "/play.jsp";
            } else {
                url = "/user.jsp";
            }
        }
        return url;
    }
}
