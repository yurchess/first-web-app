<html>
    <head>
        <meta charset="UTF-8">
        <title>Guess the number</title>
        <link rel="stylesheet" href="styles/main.css" type="text/css"/>
    </head>
    <body>
        <jsp:include page="/includes/header.jsp"/>
        <jsp:include page="/includes/currentUser.jsp"/>

        <p>Guess the number!</p>
        <h2>XXXX</h2>

        <form action="checkGuess" method="post">
            <label>Enter the number:</label>
            <input class="clientGuess" type="text" name="guess" required>
            <input type="submit" value="Check"><br>
        </form>
        <text class="validationMessage"><i>${message}</i></text>

        <jsp:include page="/includes/numpad.jsp"/>

        <%@ page import="java.util.List, mitroshin.business.Attempt" %>
        <% List<Attempt> attempts = (List) request.getAttribute("attempts");
            if (attempts != null) {
        %>
            <table>
              <tr>
                <th>Try</th>
                <th>Guess</th>
                <th>Hint</th>
              </tr>
            <% int i = 1;
               for (Attempt attempt : attempts) {
            %>
              <tr>
                <td><%= i %></td>
                <td><%= attempt.getGuess() %></td>
                <td><%= attempt.getHint() %></td>
              </tr>
            <%  i++;
               }
            %>
            </table>
            <% } %>
    </body>
</html>