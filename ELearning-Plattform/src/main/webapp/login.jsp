<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Login</title>
</head>
<body>

	<%
		String msg = (String)session.getAttribute("msg");
		if (msg!=null && !msg.isEmpty()) {
			%>
			<hr> <% out.print(msg); %> <hr>
			<%
		}
		session.setAttribute("msg", "");
	%>
<form method="post" action="Login">
	<label for="email">Email:</label>
	<input type="text" id="email" name="email"><br>
	
	<label for="password">Password:</label>
	<input type="password" id="password" name="password">
	
	<button type="submit">Login</button>
</form>

<h3>Noch nicht Registriert?</h3>
<form method="get" action="register.jsp">
	<button type="submit">Registrieren</button>
</form>
<br>

</body>
</html>