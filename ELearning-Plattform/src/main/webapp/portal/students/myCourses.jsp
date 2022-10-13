<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Meine Kurse</title>
</head>
<body>
<%@ include file="../../header.jsp" %>

     
<h1>Hallo, hier ist die Liste aller deiner Kurse</h1>
         
<table>
	<tr>
		<th>Kursname</th>
		<th>Beschreibung</th>
		<th>Thema</th>
		<th>Erledigtstatus wechseln</th>
	</tr>
	<c:forEach items="${myCourses }" var="element">
		<tr>
			<td>${element.cours.coursename }</td>
			<td>${element.cours.description }</td>
			<td>${element.cours.thema }</td>
			<c:if test="${!element.isdone}">
			<td>
				<form action="finshedCourses" method="post">
					<input type="hidden" name="email" value="${email }">
					<input type="hidden" name="isDone" value="True">
					<input type="hidden" name="courseId" value="${element.cours.courseid }">
					<button action="submit">Erledigen</button>
				</form>
			</td>
			</c:if>
			<c:if test="${element.isdone }">
			<td>
				<form action="finshedCourses" method="post">
					<input type="hidden" name="email" value="${email }">
					<input type="hidden" name="isDone" value="False">
					<input type="hidden" name="courseId" value="${element.cours.courseid }">
					<button action="submit">Noch nicht erledigt</button>
				</form>
			</td>
			</c:if>
			

		</tr>
	</c:forEach>
</table>

<p>Preis: ${totalPrice }</p>

<h3>Kurs suchen</h3>
<form method="post" action="searchMyCourses">
	<label for="searchTxt">Kurs:</label>
	<input type="text" id="searchTxt" name="searchTxt"><br>
	<input type="hidden" id="email" name="email" value="${email }">	
	<button type="submit">Suchen</button>
</form>
<br>
           
<%@ include file="../../footer.jsp" %> 
</body>
</html>