package entities;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.ListIterator;

public class StudyGroup {
	Integer groupId;
	String groupName;
	int meetingPlaceId;
	String keyword;
	String moderatorId;
	ArrayList<Student> studentList;
	public Integer getGroupId() {
		return groupId;
	}

	public void setGroupId(int groupId) {
		this.groupId = groupId;
	}

	public String getGroupName() {
		return groupName;
	}

	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}

	public int getMeetingPlaceId() {
		return meetingPlaceId;
	}

	public void setMeetingPlaceId(int meetingPlaceId) {
		this.meetingPlaceId = meetingPlaceId;
	}

	public String getKeyword() {
		return keyword;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}

	public String getModeratorId() {
		return moderatorId;
	}

	public void setModeratorId(String moderatorId) {
		this.moderatorId = moderatorId;
	}

	public ArrayList<Student> getStudentList() {
		return studentList;
	}

	public void setStudentList(ArrayList<Student> studentList) {
		this.studentList = studentList;
	}

	public static ArrayList<StudyGroup> getStudyGroupList() {
		return studyGroupList;
	}

	public static void setStudyGroupList(ArrayList<StudyGroup> studyGroupList) {
		StudyGroup.studyGroupList = studyGroupList;
	}

	public static int getNumberofStudyGroups() {
		return numberofStudyGroups;
	}

	public static void setNumberofStudyGroups(int numberofStudyGroups) {
		StudyGroup.numberofStudyGroups = numberofStudyGroups;
	}

	static ArrayList<StudyGroup> studyGroupList = new ArrayList<StudyGroup>();
	static int numberofStudyGroups = 0;

	public static int createStudyGroup(String groupName, String moderatorId,
			String keyword, int roomNumber, String day, String startTime,
			String endTime, Boolean recurring) {
		StudyGroup sg = new StudyGroup();
		sg.groupId = StudyGroup.numberofStudyGroups++;
		sg.groupName = groupName;
		sg.moderatorId = moderatorId;
		sg.keyword = keyword;
		sg.meetingPlaceId = MeetingPlace.createMeeting(roomNumber, day,
				startTime, endTime, recurring);
		sg.studentList = new ArrayList<Student>();
		sg.studentList.add(Student.getStudentFromId(moderatorId));
		studyGroupList.add(sg);

		return sg.groupId;
	}

	public static boolean deleteStudyGroup(int groupId) {

		Iterator<StudyGroup> iter = studyGroupList.iterator();
		while (iter.hasNext()) {
			StudyGroup sg = (StudyGroup) iter.next();
			if (sg.groupId == groupId) {
				MeetingPlace.cancelMeeting(sg.meetingPlaceId);
				iter.remove();
				return true;
			}

		}

		return false;
	}

	public static boolean updateStudyGroup(int groupId,
			String keyword,String groupName, int roomNumber, String day, String startTime,
			String endTime, Boolean recurring) {
		ListIterator<StudyGroup> iter = studyGroupList.listIterator();
		while (iter.hasNext()) {
			StudyGroup sg = (StudyGroup) iter.next();
			if (sg.groupId == groupId) {
				MeetingPlace mp = MeetingPlace
						.getMeetingPlace(sg.meetingPlaceId);
				if (!day.equals(mp.meetingDay)
						|| !startTime.equals(mp.startTime)
						|| !endTime.equals(mp.endTime)) {
					MeetingPlace.cancelMeeting(sg.meetingPlaceId);
					// iter.remove();
					sg.meetingPlaceId = MeetingPlace.createMeeting(roomNumber,
							day, startTime, endTime, recurring);
					
					sg.keyword=keyword;
					sg.groupName=groupName;
					
					iter.set(sg);
				}
				return true;

			}

		}
		return false;
	}

	public void addStudentToGroup(Student stu) {
		for (Student s : studentList) {
			if (s.id.equals(stu.id))
				return;
		}
		this.studentList.add(stu);
	}

	public void deleteStudentFromGroup(Student stu) {
		ListIterator<Student> iter = studentList.listIterator();
		while (iter.hasNext()) {
			Student s = (Student) iter.next();
			if (s.id.equals(stu.id)) {
				iter.remove();
			}
		}
	}

	public static ArrayList<StudyGroup> searchByKeyword(String keyword) {
		ArrayList<StudyGroup> returnlist = new ArrayList<StudyGroup>();
		for (StudyGroup sg : studyGroupList) {
			if (sg.keyword.contains(keyword)||sg.groupName.contains(keyword))
				returnlist.add(sg);
		}
		return returnlist;
	}

	public static ArrayList<StudyGroup> searchByGroupName(String name) {
		ArrayList<StudyGroup> returnlist = new ArrayList<StudyGroup>();
		for (StudyGroup sg : studyGroupList) {
			if (sg.groupName.contains(name))
				returnlist.add(sg);
		}
		return returnlist;
	}

	public static StudyGroup searchByGroupId(int groupid) {
		for (StudyGroup sg : studyGroupList) {
			if (sg.groupId == groupid)
				return sg;
		}
		return null;
	}

	public static ArrayList<StudyGroup> listAllStudyGroupsOfAStudent(Student stu) {
		ArrayList<StudyGroup> returnlist = new ArrayList<StudyGroup>();
		for (StudyGroup sg : studyGroupList) {
			ArrayList<Student> list = sg.studentList;
			for (Student s : list) {
				if (s.id.equalsIgnoreCase(stu.id)) {
					returnlist.add(sg);
					break;
				}
			}
		}
		return returnlist;

	}
	
	

	public String printStudentList() {
		String result = "";
		for (Student s : studentList)
			result += s+" ";
		return result;
	}

	public String toString() {
		return "groupName: " + this.groupName + "\n meetingPlace: "
				+ MeetingPlace.getMeetingPlace(this.meetingPlaceId).toString()
				+ "\n moderator: "
				+ Student.getStudentFromId(this.moderatorId).toString()
				+ "\n studentList: " + printStudentList();
	}

	public static void printStudyGroupList() {
		for (StudyGroup sg : studyGroupList)
			System.out.println(sg);
	}

	public static ArrayList<StudyGroup> listAllStudyGroupsOfAStudent(Student s,
			String searckKey) {
		
		if(searckKey=="")
		{
			
			return listAllStudyGroupsOfAStudent(s);
		}
		ArrayList<StudyGroup> returnlist = new ArrayList<StudyGroup>();
		for (StudyGroup sg : studyGroupList) {
			if(!sg.keyword.contains(searckKey)|| !sg.groupName.contains(searckKey))continue;
			ArrayList<Student> list = sg.studentList;
			for (Student s1 : list) {
				if (s1.id.equalsIgnoreCase(s.id)) {
					returnlist.add(sg);
					break;
				}
			}
		}
		return returnlist;
	}

	public static ArrayList<StudyGroup> getStudyGroupList(String searckKey) {
		if(searckKey=="")
		{
			
			return getStudyGroupList();
		}
		
		return searchByKeyword(searckKey);
		
	}
}
