package servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class RedirectAdminPage
 */
@WebServlet("/RedirectAdmin")
public class RedirectAdmin extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RedirectAdmin() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String role = request.getParameter("role");
		String studentEmail = request.getParameter("email");
		request.setAttribute("role", role);
		request.setAttribute("email", studentEmail);
		RequestDispatcher dispatch = request.getRequestDispatcher("/portal/admin/admin.jsp");
		dispatch.forward(request, response);
	}
	
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String location = request.getParameter("location");
		String role = request.getParameter("role");
		String studentEmail = request.getParameter("email");
		request.setAttribute("role", role);
		request.setAttribute("email", studentEmail);
		
		if(location.equals("password")) {
			RequestDispatcher dispatch = request.getRequestDispatcher("/portal/admin/updateUserPassword.jsp");
			dispatch.forward(request, response);
		} else if(location.equals("group")) {
			RequestDispatcher dispatch = request.getRequestDispatcher("/portal/admin/updateUserGroup.jsp");
			dispatch.forward(request, response);
		}
	}

}
