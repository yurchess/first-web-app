package mitroshin.servlets;

import mitroshin.business.GuessedNumber;
import mitroshin.business.GuessedNumberHint;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(urlPatterns = "/checkGuess")
public class CheckNumberServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws ServletException, IOException {
        HttpSession session = httpServletRequest.getSession();

        GuessedNumber guessedNumber = (GuessedNumber) session.getAttribute("guessedNumber");
        if (guessedNumber == null) {
            guessedNumber = new GuessedNumber();
            session.setAttribute("guessedNumber", guessedNumber);
        }

        String clientNumber = httpServletRequest.getParameter("guess");
        String message = "";

        boolean isClientNumberValid = true;
        if (clientNumber.length() != guessedNumber.toString().length()) {
            message = String.format("Digits count must be equal to %d", guessedNumber.toString().length());
            isClientNumberValid = false;
        }
        try {
            Integer.parseInt(clientNumber);
        } catch (NumberFormatException e) {
            message = "Only digits are available";
            isClientNumberValid = false;
        }
        if (!isClientNumberValid) {
            httpServletRequest.setAttribute("message", message);
            getServletContext()
                    .getRequestDispatcher("/index.jsp")
                    .forward(httpServletRequest, httpServletResponse);
            return;
        }

        if (clientNumber.equals(guessedNumber.toString())) {
            PrintWriter pw = httpServletResponse.getWriter();
            pw.println("<h1>You WIN</h1>");
            pw.close();
        } else {
            GuessedNumberHint guessedNumberHint = new GuessedNumberHint(guessedNumber.getGuessedNumber());
            String hint = guessedNumberHint.hint(clientNumber);

            httpServletRequest.setAttribute("guess", clientNumber.toString());
            httpServletRequest.setAttribute("hint", hint);
            getServletContext()
                    .getRequestDispatcher("/index.jsp")
                    .forward(httpServletRequest, httpServletResponse);
        }
    }
}
