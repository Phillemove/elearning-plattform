<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Registrieren</title>
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
<form method="post" action="Register">
	<label for="email">Email:</label>
	<input type="text" id="email" name="email"><br>
	
	<label for="name">Name:</label>
	<input type="text" id="name" name="name"><br>
	
	<label for="group">Gruppe:</label>
	<select id="group" name="group">
		<option value="teachers">Teachers</option>
		<option value="students">Students</option>
		<option value="admin">admin</option>
	</select><br>
	
	<label for="password">Password:</label>
	<input type="password" id="password" name="password">

	<label for="confirmPassword">Password:</label>
	<input type="password" id="confirmPassword" name="confirmPassword">
	
	
	<button type="submit">Registrieren</button>
</form>

<h3>Schon registriert?</h3>
<form method="get" action="login.jsp">
	<button type="submit">Einloggen</button>
</form>
</body>
</html>