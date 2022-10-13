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

/**
 * Servlet implementation class SearchCourses
 */
@WebServlet("/searchCourses")
public class SearchCourses extends HttpServlet {
	private static final long serialVersionUID = 1L;
	@Inject
	private DBManager dbManager;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public SearchCourses() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
 		String destination = "portal/students/courseOverview";
		request.getRequestDispatcher(destination).forward(request, response);;
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String searchTxt = request.getParameter("searchTxt");

		List<Cours> courses = searchCourses(searchTxt);
		request.setAttribute("courses", courses);
		String destination = "portal/courses.jsp";
		request.getRequestDispatcher(destination).forward(request, response);
	}

	private List<Cours> searchCourses(String searchText) {
		List<Cours> courses = dbManager.getAllCourses(); 
		String[] searchTexts = searchText.split(" ");
		List<Cours> result = new ArrayList<Cours>();

		for (Cours address : courses) {
			boolean found = true;
			for (int i = 0; i < searchTexts.length && found; i++) {
				if (!address.toString().contains(searchTexts[i])) {
					found = false;
				}
			}

			if (found) {
				result.add(address);

			}

		}
		return result;
	}

}
