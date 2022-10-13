package servlets;

import java.io.IOException;
import java.security.Principal;

import javax.inject.Inject;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.User;
import model.UserEJB;

/**
 * Servlet implementation class Login
 */
@WebServlet("/Login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	
	@Inject
	private UserEJB userEJB;
	
	private User user;
    
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Login() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		
		if (email.isEmpty() || password.isEmpty()) {
			System.out.println("1");
			session.setAttribute("msg", "Email oder Passwort nicht eingegeben");
			response.sendRedirect("login.jsp");
		} else {
			try {
				request.login(email, password);
			} catch (ServletException e) {
				System.out.println("2");
				session.setAttribute("msg", "Login failed");
				response.sendRedirect("login.jsp");
			}
			Principal principal = request.getUserPrincipal();
			
			this.user = userEJB.findUserById(principal.getName());
			session.setAttribute("User", user);
			request.setAttribute("email",this.user.getEmail());
			if(request.isUserInRole("teachers")) {
				System.out.println("3");
				
				RequestDispatcher dispatch = request.getRequestDispatcher("/portal/teachers/newCourse.jsp");
				dispatch.forward(request, response);
			} else if (request.isUserInRole("students")) {
				System.out.println("4");
				RequestDispatcher dispatch = request.getRequestDispatcher("courseOverview");
				dispatch.forward(request, response);
			} else if (request.isUserInRole("admin")) {
				System.out.println("5");
				RequestDispatcher dispatch = request.getRequestDispatcher("/portal/admin/admin.jsp");
				dispatch.forward(request, response);
			} else {
				System.out.println("6");
				session.setAttribute("msg", "Unauthorised");
				response.sendRedirect("login.jsp");
			}
		}
	}

}
