<html>
    <head>
        <link rel="stylesheet" href="styles/numpad.css" type="text/css"/>
    </head>
    <body>
        <% for (int i = 0; i < 10; i++) { %>
            <a class="num" href="/num" name="digit" value="<%= i %>" method="post"><%= i %></a>
        <% } %>
    </body>
</html>