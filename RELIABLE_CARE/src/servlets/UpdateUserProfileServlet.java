package servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import entities.Student;

/**
 * Servlet implementation class UpdateUserProfile
 */
public class UpdateUserProfileServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateUserProfileServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		Student s=(Student)session.getAttribute("student");
		s.setEmail(request.getParameter("email"));
		s.setPhoneNumber(request.getParameter("phoneNumber"));
		
		Student.updateStudentDetails(s);
		session.setAttribute("student", s);
		 response.setContentType("text/html;charset=UTF-8");
         PrintWriter out = response.getWriter();
         try {

             out.println("Your Profile has been updated successfully!");


         } finally {
             out.close();
         }
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
