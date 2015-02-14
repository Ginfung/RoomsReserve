package servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entities.StudyGroup;

/**
 * Servlet implementation class UpdateGroupServlet
 */
public class UpdateGroupServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateGroupServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		StudyGroup.updateStudyGroup(Integer.parseInt(request.getParameter("gidU")),request.getParameter("keywordU"),request.getParameter("groupNameU"),Integer.parseInt(request.getParameter("roomNumberU")),request.getParameter("dayOfStudyU"),request.getParameter("startTimeU"),request.getParameter("endTimeU"),false);
		response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {

            out.println("Study group has been updated successfully!");


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
