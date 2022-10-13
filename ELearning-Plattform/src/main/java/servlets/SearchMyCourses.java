package servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
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
 * Servlet implementation class SearchMyCourses
 */
@WebServlet("/searchMyCourses")
public class SearchMyCourses extends HttpServlet {
	private static final long serialVersionUID = 1L;
	@Inject
	private DBManager dbManager;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public SearchMyCourses() {
		super();
		// TODO Auto-generated constructor stub
	}


	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String destination = "portal/students/myCourses";
		request.getRequestDispatcher(destination).forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		User user = (User) request.getSession().getAttribute("User");		
		String searchTxt = request.getParameter("searchTxt");

		List<Mycours> myCourses = searchMyCourses(user.getEmail(), searchTxt);

		String destination = "/portal/students/myCourses.jsp";
		request.setAttribute("courses", myCourses);
		request.getRequestDispatcher(destination).forward(request, response);
	}

	private List<Mycours> searchMyCourses(String studentEmail, String searchText) {
		List<Mycours> courses = dbManager.getMyCourses(studentEmail);
		String[] searchTexts = searchText.split(" ");
		List<Mycours> result = new ArrayList<Mycours>();

		for (Mycours course : courses) {
			boolean found = true;
			for (int i = 0; i < searchTexts.length && found; i++) {
				if (!course.toString().contains(searchTexts[i])) {
					found = false;

				}
			}

			if (found) {
				result.add(course);

			}

		}
		return result;
	}

}
