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
import entities.SearchList;
import entities.Student;
import entities.StudyGroup;
import entities.StudyGroupList;

/**
 * Servlet implementation class SearchGroupsServlet
 */
public class SearchGroupsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public SearchGroupsServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		Student s = (Student) session.getAttribute("student");
		String searckKey = request.getParameter("searchBox");
		ArrayList<SearchList> searchResults = new ArrayList<SearchList>();
		
			ArrayList<StudyGroup> sg = StudyGroup
					.listAllStudyGroupsOfAStudent(s,searckKey);
			ArrayList<StudyGroupList> sgl = new ArrayList<StudyGroupList>();
			for (StudyGroup st : sg) {
				Student temp = Student.getStudentFromId(st.getModeratorId());
				MeetingPlace mp = MeetingPlace.getMeetingPlace(st
						.getMeetingPlaceId());
				SearchList sList = new SearchList(st.getGroupId().toString(),
						st.getGroupName(), mp.roomNumber.toString(), temp.name,
						mp.meetingDay, MeetingPlace.get12Hr(mp.startTime),
						MeetingPlace.get12Hr(mp.endTime), true,st.getKeyword());
				searchResults.add(sList);
			}
			int found = 0;
			ArrayList<StudyGroup> sg1 = StudyGroup.getStudyGroupList(searckKey);
			for (StudyGroup st : sg1) {
				for (SearchList temp : searchResults) {
					if (st.getGroupId() == Integer.parseInt(temp.groupId)) {
						found = 1;
						break;
					}
				}
				if (found == 1) {
					found = 0;
					continue;
				}

				Student temp = Student.getStudentFromId(st.getModeratorId());
				MeetingPlace mp = MeetingPlace.getMeetingPlace(st
						.getMeetingPlaceId());
				SearchList sList = new SearchList(st.getGroupId().toString(),
						st.getGroupName(), mp.roomNumber.toString(), temp.name,
						mp.meetingDay, MeetingPlace.get12Hr(mp.startTime),
						MeetingPlace.get12Hr(mp.endTime), false,st.getKeyword());
				searchResults.add(sList);

			}

		
		session.setAttribute("searchGroupList",searchResults);
		 PrintWriter out = response.getWriter();
        try {

            out.println("Done");


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
