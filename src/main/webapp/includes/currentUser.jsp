<html>
    <head>
        <meta charset="utf-8">
        <link rel="stylesheet" href="styles/main.css" type="text/css"/>
    </head>
    <body>
        <% if (session.getAttribute("user") != null) { %>
            <text>User: ${user.login}</text>
            <a href="auth?action=logout">Log out</a>
        <% } %>
    </body>
</html>