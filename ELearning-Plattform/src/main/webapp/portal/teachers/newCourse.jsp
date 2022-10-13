<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Neuen Kurs anlegen</title>
</head>
<body>
<%@ include file="../../header.jsp" %>
<p>${error}</p>
<form method="post" action="newCourse">
	<label for="courseName">Kursname:</label>
	<input type="text" id="courseName" name="courseName"><br>
	
	<label for="description">Beschreibung:</label>
	<input type="text" id="description" name="description"><br>
	
	<label for="thema">Thema:</label>
	<input type="text" id="thema" name="thema">
	
	<label for="price">Preis</label>
	<input type="number" id="price" name="price">
	<input type="hidden" name="role" value="${role }">
	<input type="hidden" name="email" value="${email }">
	
	<button type="submit">Kurs anlegen</button> 
</form>
<%@ include file="../../footer.jsp" %> 
</body>
</html>