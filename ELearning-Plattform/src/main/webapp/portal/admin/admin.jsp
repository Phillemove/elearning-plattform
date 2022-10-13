<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Admin Page</title>
<style type="text/css" scoped>
	ul {
	  list-style-type: none;
	  margin: 0;
	  padding: 0;
	  overflow: hidden;
	}
	
	li {
	  float: left;
	}
	
	li a {
	  display: block;
	  padding: 8px;
	  background-color: #dddddd;
	}
</style>
</head>
<body>

<%@ include file="../../header.jsp" %>
<div id="admin-page">
	<ul>
		<li>
			<form method="get" action="ShowAllUsers">
				<button type="submit">Alle Nutzer Zeigen</button>
			</form>
		</li>
	</ul>
	
</div>
<br>
<div id="Userlist">
	<table>
	 <tr>
		<th>Email</th>
		<th>Name</th>
	 </tr>
	 <c:forEach items="${userlist}" var="userlist" varStatus="status">
		<tr>
			<td>${userlist.email } </td>
			<td>${userlist.name }</td>
			<td>
				<form method="post" action="RedirectAdmin">
					<input type="hidden" name="location" value="password">
					<input type="hidden" name="useremail" value="${userlist.email}">
					<button type="submit">Passwort zurücksetzen</button>
				</form>
			</td>
		</tr>
	 </c:forEach>
	</table>
</div>
<br>
<div id="Userlist">
	<table>
	 <tr>
		<th>Email</th>
		<th>Gruppe</th>
	 </tr>
	 <c:forEach items="${grouplist}" var="grouplist" varStatus="status">
		<tr>
			<td>${grouplist.email } </td>
			<td>${grouplist.groupname }</td>
			<td>
				<form method="post" action="RedirectAdmin">
					<input type="hidden" name="location" value="group">
					<input type="hidden" name="useremail" value="${grouplist.email}">
					<button type="submit">Gruppe ändern</button>
				</form>
			</td>
		</tr>
	 </c:forEach>
	</table>
</div>
<br>
<%@ include file="../../footer.jsp" %> 
</body>
</html>