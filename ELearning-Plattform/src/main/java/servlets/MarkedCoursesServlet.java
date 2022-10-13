package servlets;

import java.io.IOException;
import java.util.List;

import javax.inject.Inject;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Cours;
import model.DBManager;
import model.Markedcours;
import model.Mycours;
import model.User;

/**
 * Servlet implementation class MarkedCoursesServlet
 */
@WebServlet("/MarkedCoursesServlet")
public class MarkedCoursesServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	@Inject
	private DBManager dbManager;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public MarkedCoursesServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	// get all marked Courses
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("the GET request has been made to /markedCoursesServlet");
		//System.out.println("Parameter: " + request.getParameter("email"));
		//request.setAttribute("email", request.getParameter("email"));
		User user = (User) request.getSession().getAttribute("User");
		System.out.println(user.getEmail());

		List<Cours> fromMeMarkedCourses = dbManager.getFromMeMarkedCourses(user.getEmail());
		request.setAttribute("courses", fromMeMarkedCourses);

		String destination = "portal/students/markedCourses.jsp";
		RequestDispatcher dispatch = request.getRequestDispatcher(destination);
		dispatch.forward(request, response);
	}

	// Marke Course
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		User user = (User) request.getSession().getAttribute("User");
		boolean shouldMarke =Boolean.valueOf( request.getParameter("shouldMarke")); 
		String courseid = request.getParameter("courseId");
	
		String message = "";
		
		if(shouldMarke) {
				boolean success = dbManager.markCourse(user.getEmail(), courseid);
			if (success) {
				message = "Der Kurse wurde gemerkt ";
			} else {
				message = "Ein Fehler ist aufgetreten, bitte erneut versuchen";
			}
		} else {
			boolean success = dbManager.unmarkCourse(user.getEmail(), courseid);

			if (success) {
				message = "Der Kurs ist nicht mehr gemerkt.";
			} else {
				message = "Ein Fehler ist aufgetreten, bitte erneut versuchen";
			}
		}
	
		request.setAttribute("message", message);

		String destination = "courseOverview";
		request.getRequestDispatcher(destination).forward(request, response);
	}

}
