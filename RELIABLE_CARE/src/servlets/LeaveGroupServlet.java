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
import entities.StudyGroupList;

/**
 * Servlet implementation class LeaveGroupServlet
 */
public class LeaveGroupServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public LeaveGroupServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String remove = request.getParameter("leaveGroup");
		if (remove != null) {
			// StudyGroup.deleteStudyGroup(Integer.parseInt(remove));
			HttpSession session = request.getSession();
			Student s = (Student) session.getAttribute("student");
			StudyGroup sg1 = StudyGroup.searchByGroupId(Integer.parseInt(remove));
			sg1.deleteStudentFromGroup(s);
			ArrayList<StudyGroup> sg = StudyGroup
					.listAllStudyGroupsOfAStudent(s);
			ArrayList<StudyGroupList> sgl = new ArrayList<StudyGroupList>();
			for (StudyGroup st : sg) {
				Student temp = Student.getStudentFromId(st.getModeratorId());
				MeetingPlace mp = MeetingPlace.getMeetingPlace(st
						.getMeetingPlaceId());
				StudyGroupList sgList = new StudyGroupList(st.getGroupId()
						.toString(), st.getGroupName(),
						mp.roomNumber.toString(), temp.name, mp.meetingDay,
						MeetingPlace.get12Hr(mp.startTime),
						MeetingPlace.get12Hr(mp.endTime),st.getKeyword());
				if(temp.id.equals(s.id))
					sgList.moderator="true";
				else
					sgList.moderator="false";
					
				sgl.add(sgList);
			}
			session.removeAttribute("studyGroupList");
			session.setAttribute("studyGroupList", sgl);
		}

		PrintWriter out = response.getWriter();
		try {

			out.println("Removed successfully!");

		} finally {
			out.close();
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
