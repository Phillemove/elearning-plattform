package servlets;

import java.io.IOException;

import javax.inject.Inject;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.User;
import model.UserEJB;

/**
 * Servlet implementation class UpdateUser
 */
@WebServlet("/UpdateUserPassword")
public class UpdateUserPassword extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	
	@Inject
	private UserEJB userEJB;
	
	private User user;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateUserPassword() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String email = request.getParameter("useremail");
		String password = request.getParameter("password");
		
		String role = request.getParameter("role");
		request.setAttribute("role", role);
		request.setAttribute("email", email);
		
		this.user = userEJB.findUserById(email);
		
		this.user = userEJB.updatePassword(user, password);
		
		RequestDispatcher dispatch = request.getRequestDispatcher("/portal/admin/admin.jsp");
		dispatch.forward(request, response);
		
	}
}
