package servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Cours;
import model.Course;
import model.DBManager;
import model.Markedcours;
import model.Mycours;
import model.User;
import model.UserEJB;

/**
 * Servlet implementation class CourseOverview
 */
@WebServlet("/courseOverview")
public class CourseOverview extends HttpServlet {
	private static final long serialVersionUID = 1L;
	@Inject
	private DBManager dbManager;
	@Inject
	private UserEJB userEJB;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CourseOverview() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		User user = (User) request.getSession().getAttribute("User");
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

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
