package servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.Servlet;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import entities.MeetingPlace;
import entities.Student;

/**
 * Servlet implementation class LoginServlet
 */

public class LoginPageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	

    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginPageServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Servlet#init(ServletConfig)
	 */
	public void init(ServletConfig config) throws ServletException {
		System.out.println("***In init***");
		Student.initializeStudentList();
		MeetingPlace.initializeMeetingPlace();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException,IOException {
		System.out.println("***In get***");
		
		//ApplicationContext context = new FileSystemXmlApplicationContext("C:\\Users\\karthik\\Desktop\\Beans.xml");
		//System.out.println("***In init***");
		/*
		TruncateTables truncateTables = (TruncateTables) context
				.getBean("truncate");
		// truncateTables.truncate();
		studentCRUDoperations = (StudentCRUDoperations) context
				.getBean("studentEntity");
		doctorCRUDoperations = (DoctorCRUDoperations) context
				.getBean("doctorEntity");
		nurseCRUDoperations = (NurseCRUDoperations) context
				.getBean("nurseEntity");
				*/
		String userId=request.getParameter("loginId");
		String password=request.getParameter("password");
		if(userId==null || password==null)return;
		
		Student s= Student.getStudentFromId(userId, password);
		String msg;
		if(s==null)
		{
			RequestDispatcher requestDispatcher; 
			requestDispatcher = request.getRequestDispatcher("/WEB-INF/Index1.jsp");
			requestDispatcher.forward(request, response);
			

		}
		else
		{
			msg="Welcome, "+s.name+"!";
			HttpSession session = request.getSession();
			session.setAttribute("student", s);
			session.setAttribute("message", msg);
			
			request.getRequestDispatcher("/WEB-INF/test.jsp").include(request, response);
			

		}
		/*
		Student stu=studentCRUDoperations.getStudent(userId);
		if(stu==null)
		{
			Doctor doc=doctorCRUDoperations.getDoctor(userId);
			if(doc==null)
			{
				Nurse nurse=nurseCRUDoperations.getNurse(userId);
			}
			
		}*/
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException {
		// TODO Auto-generated method stub
	}

}
