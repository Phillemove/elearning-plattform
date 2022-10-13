<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<%@ include file="../../header.jsp" %>
   
<h1> Abgeschlossene Kurse </h1>

<table>
	<tr>
		<th>Kursname</th>
		<th>Beschreibung</th>
		<th>Thema</th>
		<th>Preis</th>	
	</tr>
	<c:forEach items="${courses}" var="element">
		<tr>
			<td>${element.name }</td>
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