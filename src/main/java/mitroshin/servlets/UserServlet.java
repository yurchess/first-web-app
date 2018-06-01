package mitroshin.servlets;

import mitroshin.data.UserDB;
import mitroshin.entities.User;
import mitroshin.util.CookieUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet(urlPatterns = "/auth")
public class UserServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Boolean logining = request.getParameter("logining") != null;
        String url;
        String action = request.getParameter("action");
        if (action != null && action.equals("logout")) {
            url = logout(request, response);
        } else if (logining) {
            url = loginUser(request, response);
        } else {
            url = registerUser(request, response);
        }

        getServletContext()
                .getRequestDispatcher(url)
                .forward(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    private String loginUser(HttpServletRequest request, HttpServletResponse response) {
        String resultUrl;
        String loginMessage;
        String login = request.getParameter("login");
        String password = request.getParameter("password");
        User user = new User(login, password);

        if (UserDB.loginExists(login)) {
            if (UserDB.isAuthOK(login, password)) {
                rememberUser(user, request, response);
                return "/index.jsp";
            } else {
                loginMessage = "Wrong loginUser or password";
                resultUrl = "/user.jsp";
            }
        } else {
            loginMessage = "There is no user with this login";
            resultUrl = "/user.jsp";
        }

        request.setAttribute("loginMessage", loginMessage);
        request.setAttribute("user", user);

        return resultUrl;
    }

    private void rememberUser(User user, HttpServletRequest request, HttpServletResponse response) {
        Cookie cookie = new Cookie("login", user.getLogin());
        cookie.setMaxAge(60 * 60 * 24 * 10);
        response.addCookie(cookie);
        HttpSession session = request.getSession();
        session.setAttribute("user", user);
    }

    private String registerUser(HttpServletRequest request, HttpServletResponse response) {
        String resultUrl;
        String loginMessage;
        String login = request.getParameter("login");
        String password = request.getParameter("password");
        User user = new User(login, password);

        if (UserDB.loginExists(login)) {
            loginMessage = "Sorry, this loginUser is already taken";
            request.setAttribute("loginMessage", loginMessage);
            request.setAttribute("user", user);
            return "/user.jsp";
        } else {
            boolean succeded = UserDB.insert(user);
            if (succeded) {
                rememberUser(user, request, response);
                return "/index.jsp";
            } else {
                loginMessage = "Registration error";
                request.setAttribute("loginMessage", loginMessage);
                request.setAttribute("user", user);
                return "/user.jsp";
            }
        }
    }

    private String logout(HttpServletRequest request, HttpServletResponse response) {
        Cookie cookie = CookieUtil.getCookie(request, "login");
        if (cookie!=null){
            cookie.setMaxAge(0);
            response.addCookie(cookie);
        }

        HttpSession session = request.getSession();
        session.removeAttribute("user");
        return "/user.jsp";
    }
}
