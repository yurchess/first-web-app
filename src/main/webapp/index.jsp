<html>
    <head>
        <meta charset="utf-8">
        <title>Guess the number</title>
        <link rel="stylesheet" href="styles/main.css" type="text/css"/>
    </head>
    <body>
        <p>Guess the number (Угадайте число!)</p>
        <h2>XXXX</h2>

        <form action="checkGuess" method="post">
            <label>Enter the number:</label>
            <input type="text" name="guess" required>
            <input type="submit" value="Check"><br>
        </form>
        <text class="validationMessage"><i>${message}</i></text>

        <c:if test="${guess != null}">
            <p>${guess}: ${hint}</p>
        </c:if>
    </body>
</html>