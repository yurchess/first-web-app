<html>
	<head>
	<!--meta http-equiv="content-type" content="text/html; charset=utf-8" /-->
	<title>ГГ</title>
	<style>
	#button {
	  width: 30px;
	  height: 30px;
	}
	</style>
	</head>
	<body>

	<% for (int i = 0; i < 10; i++) { %>
	<button id="button" onclick="clicker(<%= i %>)"><%= i %></button>
    <% } %>

    <script>
    function clicker(digit){
      var input = document.querySelector('.clientGuess')
      input.value += digit;
    }
    </script>

	</body>
</html>