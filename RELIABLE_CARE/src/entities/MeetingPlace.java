package entities;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class MeetingPlace {

	public int meetingPlaceId;
	public String startTime;// Eg : "10"
	public String endTime;
	public String meetingDay; // Eg :"Monday"
	public Integer roomNumber;
	public Boolean recurring;
	static int numberofMeetings = 0;

	static ArrayList<MeetingPlace> MeetingList;
	static HashMap<Integer, String> roomAvailability;
	static ArrayList<Integer> currentEndTimes = new ArrayList<Integer>();

	public static void initializeMeetingPlace() {

		MeetingList = new ArrayList<MeetingPlace>();
		roomAvailability = new HashMap<Integer, String>();
		roomAvailability
				.put(1000,
						"MONDAY-10,11,12,13,14,15,16|TUESDAY-10,11,12,13,14,15,16|WEDNESDAY-10,11,12,13,14,15,16|FRIDAY-10,11,12,13,14,15,16");
		roomAvailability
				.put(2000,
						"MONDAY-10,11,12,13,14,15,16|TUESDAY-10,11,12,13,14,15,16|WEDNESDAY-10,11,12,13,14,15,16|THURSDAY-10,11,12,13,14,15,16");
		roomAvailability
				.put(3000,
						"MONDAY-10,11,12,13,14,15,16|TUESDAY-10,11,12,13,14,15,16|WEDNESDAY-10,11,12,13,14,15,16|SATURDAY-10,11,12,13,14,15,16");
		roomAvailability.put(4000,
				"MONDAY-10,11,12,13,14,15,16|TUESDAY-10,11,12,13,14,15,16");
		roomAvailability
				.put(5000,
						"MONDAY-10,11,12,13,14,15,16|TUESDAY-10,11,12,13,14,15,16|WEDNESDAY-10,11,12,13,14,15,16");
		roomAvailability
				.put(6000,
						"MONDAY-10,11,12,13,14,15,16|TUESDAY-10,11,12,13,14,15,16|WEDNESDAY-10,11,12,13,14,15,16");

	}

	public static String get24Hr(String time12) {
		SimpleDateFormat displayFormat = new SimpleDateFormat("HH");
		SimpleDateFormat parseFormat = new SimpleDateFormat("hh:mm a");
		Date date;
		try {
			date = parseFormat.parse(time12);
			// System.out.println(parseFormat.format(date) + " = " +
			// displayFormat.format(date));
			return displayFormat.format(date);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;

	}

	public static String get12Hr(String time24) {
		SimpleDateFormat displayFormat = new SimpleDateFormat("HH");
		SimpleDateFormat parseFormat = new SimpleDateFormat("hh:mm a");
		Date date;
		try {
			date = displayFormat.parse(time24);
			// System.out.println(parseFormat.format(date) + " = " +
			// displayFormat.format(date));
			return parseFormat.format(date);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;

	}

	public static ArrayList<String> getAllDays() {
		ArrayList<String> allDays = new ArrayList<String>();
		allDays.add("Monday");
		allDays.add("Tuesday");
		allDays.add("Wednesday");
		allDays.add("Thursday");
		allDays.add("Friday");
		allDays.add("Saturday");
		allDays.add("Sunday");
		return allDays;
	}

	public static ArrayList<String> getRoomAvailableOnOneDay(String weekday) {
		ArrayList<String> availableRoom = new ArrayList<String>();
		Iterator it = roomAvailability.entrySet().iterator();
		while (it.hasNext()) {
			Map.Entry pairs = (Map.Entry) it.next();
			if (MeetingPlace.getDays(pairs.getKey().toString()).contains(
					weekday.toUpperCase()))
				availableRoom.add(pairs.getKey().toString());
		}
		return availableRoom;
	}

	public static void removeMeetingRoomAvailability(int roomNumber,
			String day, String startTime, String endTime) {
		String dayAndTime = roomAvailability.get(roomNumber);
		String[] days = dayAndTime.split("\\|");
		Integer sTime = Integer.parseInt(startTime);
		Integer eTime = Integer.parseInt(endTime);
		int index = 0;
		for (String tempday : days) {

			if (tempday.contains((day.toUpperCase()))) {
				String[] temp = tempday.split("-");
				String[] curtimes = temp[1].split(",");
				String newTimes = "";
				for (String t : curtimes) {
					Integer cTime = Integer.parseInt(t);
					if (sTime <= cTime && cTime < eTime) {
						continue;
					}
					newTimes += t + ",";

				}
				String newString = "";
				for (int i = 0; i < days.length; i++) {
					if (i == index) {
						newString += day.toUpperCase() + "-"
								+ newTimes.substring(0, newTimes.length() - 1)
								+ "|";
					} else {

						newString += days[i] + "|";

					}
				}
				if (newString.charAt(0) == '|') {
					newString = newString.substring(1, newString.length());
				}
				if (newString.charAt(newString.length() - 1) == '|') {
					newString = newString.substring(0, newString.length() - 1);
				}
				roomAvailability.put(roomNumber, newString);

			}
			index++;
		}
	}

	public static void addMeetingRoomAvailability(Integer roomNumber,
			String day, String startTime, String endTime) {
		String dayAndTime = roomAvailability.get(roomNumber);
		String[] days = dayAndTime.split("\\|");
		Integer sTime = Integer.parseInt(startTime);
		Integer eTime = Integer.parseInt(endTime);
		String newString = "";
		int index = 0;
		for (String tempdayStr : days) {
			if (tempdayStr.contains((day.toUpperCase()))) {

				String[] temp = tempdayStr.split("-");
				String[] curtimes = temp[1].split(",");
				ArrayList<Integer> curTimesInInt = new ArrayList<Integer>();
				for (int i = 0; i < curtimes.length; i++) {
					curTimesInInt.add(Integer.parseInt(curtimes[i]));
				}
				for (int i = sTime; i < eTime; i++) {
					curTimesInInt.add(i);
				}
				Collections.sort(curTimesInInt);
				for (Integer i = 0; i < curTimesInInt.size(); i++) {
					newString += curTimesInInt.get(i) + ",";

				}
				newString = newString.substring(0, newString.length() - 1);
				String tempStr = "";
				for (int i = 0; i < days.length; i++) {
					if (i == index) {
						tempStr += day.toUpperCase() + "-" + newString + "|";
					} else
						tempStr += days[i] + "|";
				}
				if (tempStr.charAt(0) == '|') {
					tempStr = tempStr.substring(1, tempStr.length());
				}
				if (tempStr.charAt(tempStr.length() - 1) == '|') {
					tempStr = tempStr.substring(0, tempStr.length() - 1);
				}
				roomAvailability.put(roomNumber, tempStr);

			}
			index++;
		}
	}

	public static int createMeeting(int roomNumber, String meetingDay,
			String startTime, String endTime, Boolean recurring) {
		MeetingPlace mp = new MeetingPlace();
		mp.meetingPlaceId = numberofMeetings++;
		mp.meetingDay = meetingDay;
		mp.startTime = get24Hr(startTime);
		mp.endTime = get24Hr(endTime);
		mp.roomNumber = roomNumber;
		// remove availability of the room;
		mp.recurring = recurring;
		// handle recurring???
		removeMeetingRoomAvailability(roomNumber, meetingDay, mp.startTime,
				mp.endTime);
		MeetingList.add(mp);
		return mp.meetingPlaceId;
	}

	public static Boolean cancelMeeting(int meetingPlaceId) {

		Iterator<MeetingPlace> iter = MeetingList.iterator();
		while (iter.hasNext()) {
			MeetingPlace mp = (MeetingPlace) iter.next();
			if (mp.meetingPlaceId == meetingPlaceId) {
				addMeetingRoomAvailability(mp.roomNumber, mp.meetingDay,
						mp.startTime, mp.endTime);

				iter.remove();
				return true;
			}

		}

		return false;
	}

	public static MeetingPlace getMeetingPlace(int meetingPlaceId) {

		for (MeetingPlace mp : MeetingList) {
			if (mp.meetingPlaceId == meetingPlaceId) {
				return mp;
			}
		}
		return null;
	}

	public String toString() {
		return "meetingPlaceId: " + this.meetingPlaceId + "\n StartTime: "
				+ this.startTime + "\n EndTime: " + this.endTime
				+ "\n meetingDay: " + this.meetingDay + "\n roomNumber: "
				+ this.roomNumber;
	}

	public static void printMeetingPlaces() {
		for (MeetingPlace mp : MeetingList) {
			System.out.println(mp);
		}
	}

	public static void printMap() {
		Iterator it = roomAvailability.entrySet().iterator();
		while (it.hasNext()) {
			Map.Entry pairs = (Map.Entry) it.next();
			System.out.println(pairs.getKey() + " = " + pairs.getValue());
			// it.remove();
		}
	}

	public static ArrayList<Integer> getRoomNumbers() {
		return new ArrayList<Integer>(roomAvailability.keySet());

	}

	public static ArrayList<String> getEndTime(String startTime) {

		Integer startTimein24 = Integer.parseInt(get24Hr(startTime));
		ArrayList<String> EndTmesTimesin12 = new ArrayList<String>();

		for (Integer j = startTimein24 + 1; j < 24; j++) {
			if (currentEndTimes.contains(j)) {
				EndTmesTimesin12.add(get12Hr(j.toString()));
			} else {

				break;
			}

		}

		return EndTmesTimesin12;
	}

	public static ArrayList<ArrayList<String>> getStartandEndTime(
			String roomNumber, String day) {
		currentEndTimes.clear();
		int rNo = Integer.parseInt(roomNumber);
		String str = roomAvailability.get(rNo);
		String[] days = str.split("\\|");
		ArrayList<ArrayList<String>> res = new ArrayList<ArrayList<String>>();
		for (String tempdayStr : days) {

			if (tempdayStr.contains((day.toUpperCase()))) {

				String[] temp = tempdayStr.split("-");
				String[] curtimes = temp[1].split(",");
				ArrayList<Integer> curTimesInInt = new ArrayList<Integer>();
				for (int i = 0; i < curtimes.length; i++) {
					curTimesInInt.add(Integer.parseInt(curtimes[i]));
				}
				ArrayList<String> StartTimesin12 = new ArrayList<String>();
				ArrayList<String> EndTmesTimesin12 = new ArrayList<String>();

				for (int i = 0; i < curTimesInInt.size(); i++) {
					StartTimesin12.add(get12Hr((curTimesInInt.get(i))
							.toString()));
					Integer result = curTimesInInt.get(i) + 1;
					EndTmesTimesin12.add(get12Hr(result.toString()));
					currentEndTimes.add(result);
				}

				res.add(StartTimesin12);
				res.add(EndTmesTimesin12);
				return res;

			}
		}
		return null;

	}

	public static ArrayList<ArrayList<String>> getStartandEndTimeU(Integer gid,
			String roomNumber, String day) {
		currentEndTimes.clear();
		Iterator<StudyGroup> iter = StudyGroup.getStudyGroupList().iterator();
		Integer oStartTime = 0, oEndTime = 0;

		while (iter.hasNext()) {
			StudyGroup sg = (StudyGroup) iter.next();
			if (sg.groupId == gid) {
				MeetingPlace mp = MeetingPlace
						.getMeetingPlace(sg.meetingPlaceId);
				if (mp.roomNumber == Integer.parseInt(roomNumber)) {
					oStartTime = Integer.parseInt(mp.startTime);
					oEndTime = Integer.parseInt(mp.endTime);
				}
			}

		}
		int rNo = Integer.parseInt(roomNumber);
		String str = roomAvailability.get(rNo);
		String[] days = str.split("\\|");
		ArrayList<ArrayList<String>> res = new ArrayList<ArrayList<String>>();
		for (String tempdayStr : days) {

			if (tempdayStr.contains((day.toUpperCase()))) {

				String[] temp = tempdayStr.split("-");
				String[] curtimes = temp[1].split(",");
				ArrayList<Integer> curTimesInInt = new ArrayList<Integer>();
				for (int i = 0; i < curtimes.length; i++) {
					curTimesInInt.add(Integer.parseInt(curtimes[i]));
				}
				for (int i = oStartTime; i < oEndTime; i++) {
					curTimesInInt.add(i);
				}

				Collections.sort(curTimesInInt);
				ArrayList<String> StartTimesin12 = new ArrayList<String>();
				ArrayList<String> EndTmesTimesin12 = new ArrayList<String>();

				for (int i = 0; i < curTimesInInt.size(); i++) {
					StartTimesin12.add(get12Hr((curTimesInInt.get(i))
							.toString()));
					Integer result = curTimesInInt.get(i) + 1;
					EndTmesTimesin12.add(get12Hr(result.toString()));
					currentEndTimes.add(result);
				}

				res.add(StartTimesin12);
				res.add(EndTmesTimesin12);
				return res;

			}
		}
		return null;

	}

	public static ArrayList<String> getDays(String roomNumber) {
		int rNo = Integer.parseInt(roomNumber);
		String str = roomAvailability.get(rNo);
		ArrayList<String> dayOfWeek = new ArrayList<String>();
		String[] days = str.split("\\|");
		for (String tempdayStr : days) {
			String[] temp = tempdayStr.split("-");
			dayOfWeek.add(temp[0]);

		}
		return dayOfWeek;

	}
}
