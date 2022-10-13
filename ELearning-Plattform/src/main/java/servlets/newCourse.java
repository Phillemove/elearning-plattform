package servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.inject.Inject;

import model.Cours;
import model.Course;
import model.DBManager;
import model.User;
import model.UserEJB;

/**
 * Servlet implementation class newCourse
 */
@WebServlet(name = "newCourse", urlPatterns = "/newCourse")
public class newCourse extends HttpServlet {
	private static final long serialVersionUID = 1L;
	@Inject
	private DBManager dbManager;
	@Inject
	private UserEJB userEJB;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public newCourse() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		User user = (User) request.getSession().getAttribute("User"); 
		getServletContext()
		.getRequestDispatcher("/portal/teachers/newCourse.jsp")
		.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String courseName = request.getParameter("courseName");
		String description = request.getParameter("description");
		String thema = request.getParameter("thema");
		String price = request.getParameter("price");
		User user = (User) request.getSession().getAttribute("User");

		
		if(courseName == null || description == null || thema == null || price == null) {
			request.setAttribute("error", "Leider fehlte etas bei der Eingabe");
			
			doGet(request, response);
		} else {
			User teacher = null;
			try {
				teacher = userEJB.findUserById(user.getEmail());
			} catch (Exception e) {
				e.printStackTrace();
			}

			Cours cours = new Cours();
			cours.setDescription(description);
			cours.setCoursename(courseName);
			cours.setPrice(Double.parseDouble(price));
			cours.setThema(thema);
			cours.setCourseid(3L);
			cours.setTeacher(teacher.getEmail());
			cours.setMarkedcourses(null);
			cours.setMycourses(null);
			dbManager.createCourse(cours);
			
			getServletContext()
			.getRequestDispatcher("/portal/courses.jsp")
			.forward(request, response);
		}
	}

}
