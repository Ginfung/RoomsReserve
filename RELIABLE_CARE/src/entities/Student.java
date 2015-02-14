package entities;

import java.util.ArrayList;

public class Student {
	public String name, email, id, password, phoneNumber;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public static ArrayList<Student> getStudentList() {
		return studentList;
	}

	public static void setStudentList(ArrayList<Student> studentList) {
		Student.studentList = studentList;
	}

	static ArrayList<Student> studentList;

	public Student(String name, String email, String id, String pass, String pno) {
		this.name = name;
		this.email = email;
		this.id = id;
		this.password = pass;
		this.phoneNumber = pno;
	}

	public static void initializeStudentList() {
		studentList = new ArrayList<Student>();
		studentList.add(new Student("Karthik", "karthik@ncsu.edu", "201400001",
				"abcd", "1234"));
		studentList.add(new Student("Jianfeng", "jianfeng@ncsu.edu",
				"201400002", "abcd", "1234"));
		studentList.add(new Student("Sihan", "sihan@ncsu.edu", "20140003",
				"abcd", "1234"));
		studentList.add(new Student("Tom", "tom@ncsu.edu", "201400004", "abcd",
				"1234"));
		studentList.add(new Student("Bob", "bob@ncsu.edu", "201400005", "abcd",
				"1234"));
		studentList.add(new Student("Tonny", "tonny@ncsu.edu", "201400006",
				"abcd", "1234"));
		studentList.add(new Student("Jimmy", "jimmy@ncsu.edu", "201400007",
				"abcd", "1234"));
		studentList.add(new Student("Machel","machel@ncsu.edu","20140008","abcd","1234"));
	}

	public static Student getStudentFromId(String id) {
		for (Student s : studentList) {
			if (s.id == id) {
				return s;
			}
		}
		return null;
	}

	public static Student getStudentFromId(String id, String pass) {
		for (Student s : studentList) {
			if (s.id.equals(id) && s.password.equals(pass)) {
				return s;
			}
		}
		return null;
	}

	public static void updateStudentDetails(Student s) {
		for (Student s1 : studentList) {
			if (s1.id.equals(s.id)) {
				s1.phoneNumber = s.phoneNumber;
				s1.email = s.email;
				return;
			}
		}
	}

	public String toString() {
		return "name: " + this.name;
		// + "\n email: " + this.email + "\n id" + this.id;
	}
}
