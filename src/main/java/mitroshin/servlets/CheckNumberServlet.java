package mitroshin.servlets;

import mitroshin.business.Attempt;
import mitroshin.business.GuessedNumber;
import mitroshin.business.GuessedNumberHint;
import mitroshin.data.ScoreDB;
import mitroshin.entities.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

@WebServlet(urlPatterns = "/checkGuess")
public class CheckNumberServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();

        GuessedNumber guessedNumber = getGuessedNumber(request);
        String clientNumber = request.getParameter("guess");
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
            request.setAttribute("message", message);
            getServletContext()
                    .getRequestDispatcher("/play.jsp")
                    .forward(request, response);
            return;
        }

        GuessedNumberHint guessedNumberHint = new GuessedNumberHint(guessedNumber.getGuessedNumber());
        String hint = guessedNumberHint.hint(clientNumber);
        List<Attempt> attempts = getAttempts(request);
        attempts.add(attempts.size(), new Attempt(clientNumber, hint));

        if (clientNumber.equals(guessedNumber.toString())) {
            updateScores(request);
            dropGameData(request);
            getServletContext()
                    .getRequestDispatcher("/win.jsp")
                    .forward(request, response);
            return;
        } else {

            request.setAttribute("attempts", attempts);
            getServletContext()
                    .getRequestDispatcher("/play.jsp")
                    .forward(request, response);
        }
    }

    private void updateScores(HttpServletRequest request) {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        List<Attempt> attempts = (List) session.getAttribute("attempts");
        ScoreDB.updateScore(user.getLogin(), attempts.size());
    }

    private void dropGameData(HttpServletRequest request) {
        HttpSession session = request.getSession();
        session.removeAttribute("guessedNumber");
        session.removeAttribute("attempts");
    }

    private GuessedNumber getGuessedNumber(HttpServletRequest request) {
        GuessedNumber guessedNumber;

        HttpSession session = request.getSession();
        if ((guessedNumber = (GuessedNumber) session.getAttribute("guessedNumber")) == null) {
            guessedNumber = new GuessedNumber();
            session.setAttribute("guessedNumber", guessedNumber);
        }
        return guessedNumber;
    }

    private List<Attempt> getAttempts(HttpServletRequest request) {
        List<Attempt> attempts;

        HttpSession session = request.getSession();
        if ((attempts = (List<Attempt>) session.getAttribute("attempts")) == null) {
            attempts = new LinkedList<>();
            session.setAttribute("attempts", attempts);
        }
        return attempts;
    }
}
