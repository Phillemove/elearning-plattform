package servlets;

import java.io.IOException;

import javax.inject.Inject;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.UserEJB;
import model.UserGroup;

/**
 * Servlet implementation class UpdateUserGroup
 */
@WebServlet("/UpdateUserGroup")
public class UpdateUserGroup extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@Inject
	private UserEJB userEJB;
	
	private UserGroup group;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateUserGroup() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String email = request.getParameter("useremail");
		String usergroup = request.getParameter("group");
		
		this.group = userEJB.findGroupById(email);
		
		this.group = userEJB.updateUserRole(group, usergroup);
		
		String role = request.getParameter("role");
		request.setAttribute("role", role);
		request.setAttribute("email", email);
		
		RequestDispatcher dispatch = request.getRequestDispatcher("/portal/admin/admin.jsp");
		dispatch.forward(request, response);
	}

}
