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
import model.Mycours;
import model.User;

/**
 * Servlet implementation class FinshedCoursesServlet
 */
@WebServlet("/finshedCourses")
public class FinshedCoursesServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	@Inject
	private DBManager dbManager;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FinshedCoursesServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	// liefert alle fertige Course (angeschaute)! 
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		User user = (User) request.getSession().getAttribute("User");
		List<Cours> myFinschedCourses = dbManager.getMyFinshedCourses(user.getEmail());
		
		request.setAttribute("courses", myFinschedCourses);

		String destination = "portal/finishedCourses.jsp";
		request.getRequestDispatcher(destination).forward(request, response);

	}

	  // macht einen Course fertig 
		protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			boolean isDone =Boolean.valueOf( request.getParameter("isDone")); 
			User user = (User) request.getSession().getAttribute("User");
			String courseid = request.getParameter("courseId");
			String message = "";
			
			if(isDone) {
				boolean success = dbManager.makeCourseDone(user.getEmail(), courseid);
			

			if (success) {
				message = "Der Kurse wurde als fertig gespeichert!";
			} else {
				message = "Fehler ist aufgetreten";
			}

			}else {
				boolean success = dbManager.makeCourseStillNotDone(user.getEmail(), courseid);
			

				if (success) {
					message = "Der Kurse wurde als 'noch nicht fertig' gespeichert!";
				} else {
					message = "Fehler ist aufgetreten";
				}
			}
			request.setAttribute("message", message);
			List<Mycours> myCourses =dbManager.getMyCourses(user.getEmail()); 
			request.setAttribute("myCourses", myCourses);

			double totalPrice = dbManager.getMyTotalPrice(user.getEmail());
			request.setAttribute("totalPrice", totalPrice);
			
			RequestDispatcher dispatch = request.getRequestDispatcher("/portal/students/myCourses.jsp");
			dispatch.forward(request, response);
			
			
			
		}

}
