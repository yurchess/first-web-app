package mitroshin.servlets;

import mitroshin.data.UserDB;
import mitroshin.entities.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet(urlPatterns = "/auth")
public class UserServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String action = request.getParameter("action");
        String url = null;
        String loginMessage = null;
        String login = request.getParameter("login");
        String password = request.getParameter("password");

        User user = new User(login, password);

        if (action.equals("login")) {
            if (!UserDB.loginExists(login)) {
                loginMessage = "There is no user with this login";
            } else if (!UserDB.isAuthOK(login, password)) {
                loginMessage = "Wrong login or password";
            }
            url = "/user.jsp";
            request.setAttribute("loginMessage", loginMessage);
            request.setAttribute("user", user);
        }
        if (action.equals("register")) {
            if (UserDB.loginExists(login)) {
                loginMessage = "Sorry, this login is already taken";
                url = "/user.jsp";
                request.setAttribute("loginMessage", loginMessage);
                request.setAttribute("user", user);
            } else {
                UserDB.insert(user);
                Cookie cookie = new Cookie("login", login);
                cookie.setMaxAge(60 * 60 * 24 * 10);
                response.addCookie(cookie);
                HttpSession session = request.getSession();
                session.setAttribute("user", user);
                url = "/index.jsp";
            }
        }

        getServletContext()
                .getRequestDispatcher(url)
                .forward(request, response);
    }
}
