package mitroshin.servlets;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet(urlPatterns = "/num")
public class NumPadServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws ServletException, IOException {

        String digit = httpServletRequest.getParameter("digit");

        httpServletRequest.setAttribute("digit", digit);
        this.getServletContext()
                .getRequestDispatcher("/index.jsp")
                .forward(httpServletRequest, httpServletResponse);
    }
}
