package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import entities.MeetingPlace;
import entities.Student;
import entities.StudyGroup;

/**
 * Servlet implementation class CreateStudyGroupServlet
 */
public class CreateStudyGroupServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public CreateStudyGroupServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		String operationType = request.getParameter("opType");
		PrintWriter out = response.getWriter();

		

		if (operationType == null) {
			/*ArrayList<Integer> roomNumbers = MeetingPlace.getRoomNumbers();
			 out.print(roomNumbers);
			out.close();*/
			ArrayList<String> allDays = MeetingPlace.getAllDays();
			 out.print(allDays);
			out.close();
			
		}
		else if(operationType.equals("getRooms"))
		{
			String thisDay=request.getParameter("dayChanged");
			ArrayList<String>days=MeetingPlace.getRoomAvailableOnOneDay(thisDay);
			 out.print(days);
				out.close();
		}
		else if(operationType.equals("getTimes"))
		{
			String roomNumber=request.getParameter("roomNumberChanged");
			String day=request.getParameter("forDay");
			ArrayList<ArrayList<String>>days=MeetingPlace.getStartandEndTime(roomNumber, day);
			 out.print(days);
				out.close();
		}
		else if(operationType.equals("getTimesU"))
		{
			String roomNumber=request.getParameter("roomNumberChanged");
			String day=request.getParameter("forDay");
			String gid=request.getParameter("gid");
			ArrayList<ArrayList<String>>days=MeetingPlace.getStartandEndTimeU(Integer.parseInt(gid),roomNumber, day);
			 out.print(days);
				out.close();
		}
		
		else if(operationType.equals("getEndTimes"))
		{
			String roomNumber=request.getParameter("roomNumberChanged");
			String day=request.getParameter("forDay");
			String startTime=request.getParameter("startTime");
			ArrayList<String> endTimes=MeetingPlace.getEndTime(startTime);
			 out.print(endTimes);
				out.close();
		}
		
		
		else if(operationType.equals("Create"))
		{
			String roomNumber=request.getParameter("roomNumber");
			String day=request.getParameter("dayOfStudy");
			String startTime=request.getParameter("startTime");
			String endTime=request.getParameter("endTime");
			String keyword=request.getParameter("keyword");
			String groupName=request.getParameter("groupName");
			HttpSession session = request.getSession();
			
			Student s=(Student)session.getAttribute("student");
			String id=s.getId();
			StudyGroup.createStudyGroup(groupName, id, keyword, Integer.parseInt(roomNumber), day, startTime, endTime, false);
			 response.setContentType("text/html;charset=UTF-8");
	        
	         try {

	             out.println("Study Group \""+groupName+"\" has been created successfully!");


	         } finally {
	             out.close();
	         }
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
