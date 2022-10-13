package servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.HttpConstraint;
import javax.servlet.annotation.ServletSecurity;
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
 * Servlet implementation class ByCourse
 */

@WebServlet("/myCourses")
public class MyCourseServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	@Inject
	private DBManager dbManager;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public MyCourseServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 * 
	 *      diese doGet sorget f�r das Einlesen der eingekaufte Kurse
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("the GET request has been made to /myCoursesServlet");
		//System.out.println("Parameter: " + request.getParameter("email"));
		//request.setAttribute("email", request.getParameter("email"));
		User user = (User) request.getSession().getAttribute("User");
		List<Mycours> myCourses =dbManager.getMyCourses(user.getEmail()); 
		request.setAttribute("myCourses", myCourses);

		double totalPrice = dbManager.getMyTotalPrice(user.getEmail());
		request.setAttribute("totalPrice", totalPrice);
		
		RequestDispatcher dispatch = request.getRequestDispatcher("/portal/students/myCourses.jsp");
		dispatch.forward(request, response);

		//request.getRequestDispatcher("portal/students/myCourses.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 * 
	 *      diese doPost sorgt f�r das Einkaufen der Kurse
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		User user = (User) request.getSession().getAttribute("User");
		String message = "";
		boolean buy =Boolean.valueOf( request.getParameter("buy")); 
		String courseId = request.getParameter("courseId");

		if (buy) {
			boolean success2 = dbManager.buyCourse(user.getEmail(), courseId);
		} else {
			boolean success2 = dbManager.unBuyCourse(user.getEmail(), courseId);
		}

		boolean success = true;
		if (success) {
			message = "Der Kuse wurde erfolgreich eingekauft!";
		} else {
			message = "Der Kurs konnte nicht gekauft werden..";
		}

		request.setAttribute("message", message);
		
		
		List<Long> markedCourses = new ArrayList();
		List<Long> myCourses = new ArrayList();
		List<Cours> cours = dbManager.getAllCourses();
		List<Mycours> myc = dbManager.getMyCourses(user.getEmail());
		List<Cours> markc = dbManager.getFromMeMarkedCourses(user.getEmail());
		for (Mycours mc : myc) {
			myCourses.add(mc.getCours().getCourseid());
		}
		for (Cours mc : markc) {
			markedCourses.add(mc.getCourseid());
		}
		request.setAttribute("markedCourses", markedCourses);
		request.setAttribute("myCourses", myCourses);
		request.setAttribute("courses", cours);
		RequestDispatcher dispatch = request.getRequestDispatcher("/portal/courses.jsp");
		dispatch.forward(request, response);

	}

}
