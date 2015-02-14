package Test;

import java.util.ArrayList;

import entities.MeetingPlace;

public class TestData {

	public static void main(String[] args) {
		// MeetingPlace
		/*
		 * MeetingPlace.initializeMeetingPlace(); Integer
		 * id=MeetingPlace.createMeeting(2000, "Wednesday", "12", "13", false);
		 * Integer id2=MeetingPlace.createMeeting(2000, "Wednesday", "13", "14",
		 * false); MeetingPlace.printMeetingPlaces(); MeetingPlace.printMap();
		 * MeetingPlace.cancelMeeting(id); MeetingPlace.printMeetingPlaces();
		 * MeetingPlace.printMap();
		 */
		MeetingPlace.initializeMeetingPlace();
		ArrayList<String> temp = MeetingPlace.getRoomAvailableOnOneDay("Thursday");
		System.out.println(temp);
//		ArrayList<ArrayList<String>> ds = MeetingPlace.getStartandEndTime("1000", "MONDAY");
//		System.out.println(ds);
//		Student.initializeStudentList();
//		Integer id1 = StudyGroup.createStudyGroup("CSC541", "201400001",
//				"abcd", 2000, "Monday", "10", "11", false);
//		Integer id2 = StudyGroup.createStudyGroup("CSC5421", "201400001",
//				"efgh", 2000, "Monday", "11", "12", false);
//		StudyGroup.searchByGroupId(id1).addStudentToGroup(Student.getStudentFromId("201400002"));
		 //MeetingPlace.printMeetingPlaces(); MeetingPlace.printMap();
		// StudyGroup.updateStudyGroup(id1, "201400001", "aaaaa", 2000, "TUESDAY", "10", "11", false);
		// MeetingPlace.printMeetingPlaces(); MeetingPlace.printMap();
		 
//		StudyGroup.printStudyGroupList();
//		StudyGroup.searchByGroupId(id1).deleteStudentFromGroup(Student.getStudentFromId("201400002"));
//		StudyGroup.printStudyGroupList();
	//	StudyGroup.deleteStudyGroup(id1);
	//	StudyGroup.printStudyGroupList();
//		ArrayList<StudyGroup>list=StudyGroup.listAllStudyGroupsOfAStudent(Student.getStudentFromId("201400001"));
//		for(StudyGroup sg:list)
//			System.out.println(sg);
		

	}

}
