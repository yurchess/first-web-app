<html>
     <head>
         <meta charset="utf-8">
         <title>Guess the number</title>
         <link rel="stylesheet" href="styles/main.css" type="text/css"/>
     </head>
     <body>
        <% String url= request.getContextPath() + "/start";
           response.sendRedirect(url);
        %>
     </body>
 </html>