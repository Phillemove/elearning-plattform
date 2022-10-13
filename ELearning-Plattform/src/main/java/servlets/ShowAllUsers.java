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

import model.User;
import model.UserEJB;
import model.UserGroup;

/**
 * Servlet implementation class ShowAllUsers
 */
@WebServlet("/ShowAllUsers")
public class ShowAllUsers extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@Inject
	private UserEJB userEJB;
	
	private List<User> userList;
	
	private List<UserGroup> groupList;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ShowAllUsers() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		userList = userEJB.findUsers();
		
		groupList = userEJB.findUserGroups();
		
		if(userList != null) {
			String role = request.getParameter("role");
			String studentEmail = request.getParameter("email");
			request.setAttribute("role", role);
			request.setAttribute("email", studentEmail);
			
			request.setAttribute("userlist", userList);
			request.setAttribute("grouplist", groupList);
			RequestDispatcher dispatch = request.getRequestDispatcher("/portal/admin/admin.jsp");
			dispatch.forward(request, response);
		}
	}

}
