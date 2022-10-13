<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Kurse</title>
</head>
<body>
<%@ include file="../header.jsp" %>


<!-- CHECK TODO: Das Button Einschreiben ruf doPost() in servlets/MyCourseServlet.java auf.  -->
<!-- CHECK TODO: die Meldung anzeigen (message) siehe doPost() in servlets/MyCourseServlet.java -->
<!--TODO: Eine Search Funktion implementieren, wo man ein Name oder subname eines Kures eingibt,
           die Seite wird aktuallisiert und du bekommst eine List von Courses als Ergebnis. Siehe doPost() in Servlets/SearchCourses.java -->
           
<!-- CHECK TODO: Ein Button neben dem Kurs einfügen. Somit kann der man den Kurs markieren. Siehe doPost() in MarkedCoursesServlet  --> 
<!--TODO: Ein Button zum nicht merkeiren. Siehe doDelete() in MarkedCoursesServlet.   --> 
<!--Die markierte Kurse werden in markedCourses.jsp angezeigt. siehe doGet() in MarkedCoursesServlet.   --> 

<!-- Derzeit benoetigt die courseid noch eine Ruecksprache. Denn es fehlt noch die Info ueber das korrekt uebergebene ojbekt. -->


<h1>Hallo ${User.name }, hier sind die moglichen Kurse:</h1>


<p>${message }</p>

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
			<c:if test="${!myCourses.contains(element.courseid) }">		
			<td>
				<form action="myCourses" method="post">
					<input type="hidden" name="email" value="${User.email }">
					<input type="hidden" name="buy" value="True">
					<input type="hidden" name="courseId" value="${element.courseid }">
					<button action="submit">Einschreiben</button>
				</form>
			</td>
			</c:if>
			<c:if test="${myCourses.contains(element.courseid) }">
			<td>
				<form action="myCourses" method="post">
					<input type="hidden" name="email" value="${User.email }">
					<input type="hidden" name="buy" value="False">
					<input type="hidden" name="courseId" value="${element.courseid }">
					<button action="submit">Austragen</button>
				</form>
			</td>
			</c:if>
			<c:if test="${!markedCourses.contains(element.courseid) }">
			<td>
				<form action="MarkedCoursesServlet" method="post">
					<input type="hidden" name="email" value="${User.email }">
					<input type="hidden" name="shouldMarke" value="True">
					<input type="hidden" name="courseId" value="${element.courseid }">
					<button action="submit">Markieren</button>
				</form>
			</td>
			</c:if>
			<c:if test="${markedCourses.contains(element.courseid) }">
			<td>
				<form action="MarkedCoursesServlet" method="post">
					<input type="hidden" name="email" value="${User.email }">
					<input type="hidden" name="shouldMarke" value="False">
					<input type="hidden" name="courseId" value="${element.courseid }">
					<button action="submit">nicht mehr markieren</button>
				</form>
			</td>
			</c:if>			
		</tr>
	</c:forEach>
</table>
<br>

<h3>Kurs suchen</h3>
<br>
<form method="post" action="searchCourses">
	<label for="searchTxt">Kurs:</label>
	<input type="text" id="searchTxt" name="searchTxt"><br>
	<input type="hidden" id="email" name="email" value="${email }">	
	<button type="submit">Suchen</button>
</form>
<br>

<%@ include file="../footer.jsp" %> 
</body>
</html>