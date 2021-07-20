package ClientClasses;

import java.sql.*;
import java.util.ArrayList;

public class Student extends User {

	private String groupName;
	
	public Student () {
		setRoleUser ("студент");
	}
	
	public Student (String loginUser) {
		searchUserForLogin(loginUser);
		groupName = getStudentGroupDB ();
	}

	public String getGroupName() {
		return groupName;
	}

	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}
	
	public void addResultTest (Test test) {
		try {
			Class.forName("org.sqlite.JDBC");
			String urlDataBase = "jdbc:sqlite:c:\\DataBases/Tests.db";
			Connection dataBase = DriverManager.getConnection(urlDataBase);
			dataBase.setAutoCommit(false);
			Statement statement = dataBase.createStatement();
			String sqlite = 
					"INSERT INTO StudentTestMarks (StudentLogin,ObjectName,TestName,Mark) " +
					"VALUES ('" + getLoginUser() + "', '" + test.getNameEducationObject() + 
					"', '" + test.getNameTest() + "', " + test.calculateResult() + " );";
			statement.executeUpdate(sqlite);
			statement.close();
			dataBase.commit();
			dataBase.close();
		}
		catch (Exception e) {
			System.err.println(e.getMessage());
		}
	}
	
	private String getStudentGroupDB () {
		String group = null;
		try {
			Class.forName("org.sqlite.JDBC");
			String urlDataBase = "jdbc:sqlite:c:\\DataBases/Users.db";
			Connection dataBase = DriverManager.getConnection(urlDataBase);
			Statement statement = dataBase.createStatement();
			ResultSet result = statement.executeQuery("SELECT * FROM StudentGroup");
			while (result.next()) {
				if (result.getString("Login").equals(getLoginUser())) {
					group = result.getString("GroupName");
					break;
				}
			}
			result.close();
			statement.close();
			dataBase.close();
		}
		catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return group;
	}
	
	public double getMaxResultTest (String EduObject, String testName) {
		double resultTest = 0;
		try {
			Class.forName("org.sqlite.JDBC");
			String urlDataBase = "jdbc:sqlite:c:\\DataBases/Tests.db";
			Connection dataBase = DriverManager.getConnection(urlDataBase);
			Statement statement = dataBase.createStatement();
			ResultSet result = statement.executeQuery("SELECT * FROM StudentTestMarks");
			while (result.next()) {
				if (result.getString("StudentLogin").equals(getLoginUser()) &&
						result.getString("ObjectName").equals(EduObject) &&
						result.getString("TestName").equals(testName) &&
						result.getDouble("Mark") > resultTest) {
					resultTest = result.getDouble("Mark");
				}
			}
			result.close();
			statement.close();
			dataBase.close();
		}
		catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return resultTest;
	}
	
	public ArrayList<Teacher> getStudentTeacher () {
		ArrayList<String> teacherLogins = getStudentTeacherLoginDB ();
		ArrayList<Teacher> teachers = new ArrayList<Teacher> ();
		if (teacherLogins.size() > 0) {
			for (String login : teacherLogins) {
				teachers.add(new Teacher (login));
			}
			return teachers;
		}
		else {
			return null;
		}
	}
	
	private ArrayList<String> getStudentTeacherLoginDB () {
		ArrayList<String> logins = new ArrayList<String> ();
		try {
			Class.forName("org.sqlite.JDBC");
			String urlDataBase = "jdbc:sqlite:c:\\DataBases/Users.db";
			Connection dataBase = DriverManager.getConnection(urlDataBase);
			Statement statement = dataBase.createStatement();
			ResultSet result = statement.executeQuery("SELECT * FROM TeacherGroups");
			while (result.next()) {
				if (result.getString("GroupName").equals(groupName)) {
					logins.add(result.getString("Login"));
				}
			}
			result.close();
			statement.close();
			dataBase.close();
		}
		catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return logins;
	}
}