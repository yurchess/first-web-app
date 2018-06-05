<html>
    <head>
        <meta charset="utf-8">
        <title>Guess the number</title>
        <link rel="stylesheet" href="styles/main.css" type="text/css"/>
    </head>
    <body>
        <jsp:include page="/includes/header.jsp"/>

        <p>Authentication</p>

        <form action="auth" method="post">
            <input type="hidden" name="action" value="login">
            <label>Login:</label>
            <input type="text" name="login" value="${user.login}"
                   pattern="[a-zA-Z0-9]{1,}"
                   title="Login must contain only letters and digits"
                   maxlength="15"
                   required><br>
            <text><i>${loginMessage}</i></text><br>
            <label>Password:</label>
            <input type="password" name="password"
                   maxlength="15"
                   required><br>
            <input type="submit" name="logining" value="Log In">
            <input type="submit" name="registering" value="Register">
        </form>
    </body>
</html>