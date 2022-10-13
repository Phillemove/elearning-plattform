<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<%@ include file="../../header.jsp" %>
   
<h1> Markierte Kurse </h1>


<table>
	<tr>
		<th>Kursname</th>
		<th>Beschreibung</th>
		<th>Thema</th>
		<th>Preis</th>	
	</tr>
	<c:forEach items="${courses}" var="element">
		<tr>
			<td>${element.coursename }</td>
			<td>${element.description }</td>
			<td>${element.thema }</td>
			<td>${element.price }</td>		
		</tr>
	</c:forEach>
</table>
<br>

<%@ include file="../../footer.jsp" %> 
</body>
</html>