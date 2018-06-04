<html>
    <head>
        <meta charset="utf-8">
        <link rel="stylesheet" href="styles/main.css" type="text/css"/>
    </head>
    <body>
        <jsp:include page="/includes/header.jsp"/>

        <h1>Rankings</h1>
        <%@ page import="java.util.List, mitroshin.entities.Score, mitroshin.data.ScoreDB" %>
        <% List<Score> scores = ScoreDB.getScoresRank(10); %>
            <table>
              <tr>
                <th>Rank</th>
                <th>User</th>
                <th>Score</th>
                <th>Attempts</th>
              </tr>
            <% int i = 1;
               for (Score score : scores) {
            %>
              <tr>
                <td><%= i %></td>
                <td><%= score.getLogin() %></td>
                <td><%= score.getScore() %></td>
                <td><%= score.getAttempts() %></td>
              </tr>
           <%  i++;
              }
           %>
           </table>
    </body>
</html>