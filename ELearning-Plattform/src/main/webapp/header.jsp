<div id="scoped-content">
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
	<ul>
		<li>		
			<form method="get" action="courseOverview">
				<input type="hidden" name="email" value="${email }">
				<button type="submit">Kurse anzeigen</button>
			</form>
		</li>
			<li>
			<form method="get" action="newCourse">
				<input type="hidden" name="email" value="${email }">
				<button type="submit">Kurs erstellen</button>
			</form>
		</li>
		<li>
			<form method="get" action="myCourses">
				<input type="hidden" name="email" value="${email }">
				<button type="submit">Meine Kurse</button>
			</form>
		</li>
		<li>
			<form method="get" action="MarkedCoursesServlet">
				<input type="hidden" name="email" value="${email }">
				<button type="submit">Markierte Kurse</button>
			</form>
		</li>
			<li>
			<form method="get" action="portal/admin/admin.jsp">
				<input type="hidden" name="email" value="${email }">
				<button type="submit">Admin</button>
			</form>
		</li>

	</ul>
	<br>
</div>
