package servlets;

import java.io.IOException;

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
 * Servlet implementation class Register
 */
@WebServlet("/Register")
public class Register extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@Inject
	private UserEJB userEJB;

       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Register() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		
		String email = request.getParameter("email");
		String name = request.getParameter("name");
		String group = request.getParameter("group");
		String password = request.getParameter("password");
		String confirmPassword = request.getParameter("confirmPassword");
		
		if(password.isEmpty() || confirmPassword.isEmpty()) {
			session.setAttribute("msg", "Kein Passwort eigegeben");
			response.sendRedirect("register.jsp");
		} else if (!password.equals(confirmPassword)) {
			session.setAttribute("msg", "Die Passwörter stimmen nicht überein");
			response.sendRedirect("register.jsp");
		} else if(userEJB.findUserById(email) != null) {
			session.setAttribute("msg", "Ein Nutzer mit der E-Mail existiert bereits");
			response.sendRedirect("register.jsp");
		} else {
			User user = new User(email, password, name);
			userEJB.createUser(user, group);
			session.setAttribute("User", user);
			if(group.equals("teachers")) {
				RequestDispatcher dispatch = request.getRequestDispatcher("/portal/teachers/newCourse.jsp");
				dispatch.forward(request, response);
			} else if (group.equals("students")) {
				RequestDispatcher dispatch = request.getRequestDispatcher("courseOverview");
				dispatch.forward(request, response);
			} else if (group.equals("admin")) {
				RequestDispatcher dispatch = request.getRequestDispatcher("/portal/admin/admin.jsp");
				dispatch.forward(request, response);
			}
		}
		
	}

}
