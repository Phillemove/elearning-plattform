<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Update User Group</title>
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

<div id="updateUserGroup">
	<form method="post" action="UpdateUserGroup">
	
		<label for="useremail">User:</label>
		<input type="text" name="useremail" value="${param.useremail}" readonly>
		
		<label for="group">Gruppe:</label>
		<select id="group" name="group">
			<option value="teachers">Teachers</option>
			<option value="students">Students</option>
			<option value="admin">admin</option>
		</select>
		
		<button type="submit">Gruppe ändern</button>
	</form>
</div>
<br>
<div>
	<form method="get" action="RedirectAdmin">
		<button type="submit">Zurück</button>
	</form>
</div>
<br>
<%@ include file="../../footer.jsp" %> 

</body>
</html>