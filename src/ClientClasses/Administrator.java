package ClientClasses;

import java.sql.*;
import java.util.ArrayList;

public class Administrator extends User {

	public Administrator (String loginUser) {
		searchUserForLogin(loginUser);
	}

	public void addToGroupDB (String groupName) {
		try {
			Class.forName("org.sqlite.JDBC");
			String urlDataBase = "jdbc:sqlite:c:\\DataBases/Users.db";
			Connection dataBase = DriverManager.getConnection(urlDataBase);
			dataBase.setAutoCommit(false);
			Statement statement = dataBase.createStatement();
			String sqlite = 
					"INSERT INTO Groups (GroupName) " +
					"VALUES ('" + groupName +  "' );";
			statement.executeUpdate(sqlite);
			statement.close();
			dataBase.commit();
			dataBase.close();
		}
		catch (Exception e) {
			System.err.println(e.getMessage());
		}
	}
	
	public boolean addStudent (Student newStudent, String personNum) {
		if (!newStudent.searchUserForName(newStudent.getRoleUser(), newStudent.getFirstName(),
				newStudent.getSecondName(), newStudent.getLastName())) {
			addToUsersDB (newStudent, personNum);
			addToStudentGroupDB (newStudent);
			return true;
		}
		else {
			return false;
		}
	}

	public boolean addTeacher (Teacher newTeacher, String personNum, 
			ArrayList<String> eduObjectNames) {
		if (!newTeacher.searchUserForName(newTeacher.getRoleUser(), newTeacher.getFirstName(),
				newTeacher.getSecondName(), newTeacher.getLastName())) {
			addToUsersDB (newTeacher, personNum);
			addToTeacherObjectsDB (newTeacher, eduObjectNames);
			return true;
		}
		else {
			return false;
		}
	}
	
	private void addToUsersDB (User addUser, String personNum) {
		try {
			Class.forName("org.sqlite.JDBC");
			String urlDataBase = "jdbc:sqlite:c:\\DataBases/Users.db";
			Connection dataBase = DriverManager.getConnection(urlDataBase);
			dataBase.setAutoCommit(false);
			Statement statement = dataBase.createStatement();
			String sqlite = 
					"INSERT INTO Users (Login,Password,Role,FirstName,SecondName,LastName) " +
					"VALUES ('" + addUser.getLoginUser() + "', '" + personNum + "', '" + 
					addUser.getRoleUser() + "', '" + addUser.getFirstName() + "', '" +
					addUser.getSecondName() + "', '" + addUser.getLastName() + "' );";
			statement.executeUpdate(sqlite);
			statement.close();
			dataBase.commit();
			dataBase.close();
		}
		catch (Exception e) {
			System.err.println(e.getMessage());
		}
	}
	
	private void addToStudentGroupDB (Student newStudent) {
		try {
			Class.forName("org.sqlite.JDBC");
			String urlDataBase = "jdbc:sqlite:c:\\DataBases/Users.db";
			Connection dataBase = DriverManager.getConnection(urlDataBase);
			dataBase.setAutoCommit(false);
			Statement statement = dataBase.createStatement();
			String sqlite = 
					"INSERT INTO StudentGroup (Login,GroupName) " +
					"VALUES ('" + newStudent.getLoginUser() + "', '" 
					+ newStudent.getGroupName() + "' );";
			statement.executeUpdate(sqlite);
			statement.close();
			dataBase.commit();
			dataBase.close();
		}
		catch (Exception e) {
			System.err.println(e.getMessage());
		}
	}
	
	private void addToTeacherObjectsDB (Teacher newTeacher, ArrayList<String> eduObjectNames) {
		try {
			Class.forName("org.sqlite.JDBC");
			String urlDataBase = "jdbc:sqlite:c:\\DataBases/Users.db";
			Connection dataBase = DriverManager.getConnection(urlDataBase);
			dataBase.setAutoCommit(false);
			Statement statement = dataBase.createStatement();
			for (String eduObject : eduObjectNames) {
				String sqlite = 
						"INSERT INTO StudentGroup (Login,ObjectName) " +
						"VALUES ('" + newTeacher.getLoginUser() + "', '" 
						+ eduObject + "' );";
				statement.executeUpdate(sqlite);
			}
			statement.close();
			dataBase.commit();
			dataBase.close();
		}
		catch (Exception e) {
			System.err.println(e.getMessage());
		}
	}

	public void deleteGroup (String group) {
		Group deleteGroup = new Group (group);
		ArrayList<Student> delStudents = deleteGroup.getStudents();
		for (Student student : delStudents) {
			deleteStudent (student);
		}
		deleteData (group, "Users", "Groups", "GroupName");
		deleteData (group, "Users", "GroupEduObjs", "GroupName");
		deleteData (group, "Users", "TeacherGroups", "GroupName");
	}
	
	public void deleteStudent (Student student) {
		deleteData (student.getLoginUser(), "Users", "Users", "Login");
		deleteData (student.getLoginUser(), "Users", "StudentGroup", "Login");
		deleteData (student.getLoginUser(), "Tests", "StudentTestMarks", "StudentLogin");
	}

	public void deleteTeacher (Teacher teacher) {
		deleteData (teacher.getLoginUser(), "Users", "Users", "Login");
		deleteData (teacher.getLoginUser(), "Users", "GroupEduObjs", "TeacherLogin");
		deleteData (teacher.getLoginUser(), "Users", "TeacherGroups", "Login");
		deleteData (teacher.getLoginUser(), "Users", "TeacherObjects", "Login");
		deleteData (teacher.getLoginUser(), "Tests", "UnVarQuest", "TeacherLogin");
		deleteData (teacher.getLoginUser(), "Tests", "OneVarQuest", "TeacherLogin");
		deleteData (teacher.getLoginUser(), "Tests", "SomeVarQuest", "TeacherLogin");
		deleteData (teacher.getLoginUser(), "Tests", "TestNames", "TeacherLogin");
	}
	
	private void deleteData (String element, String dataBaseName,
			String tableName, String colElement) {
		try {
			Class.forName("org.sqlite.JDBC");
			String urlDataBase = "jdbc:sqlite:c:\\DataBases/" + dataBaseName + ".db";
			Connection dataBase = DriverManager.getConnection(urlDataBase);
			Statement statement = dataBase.createStatement();
			String sqlite = 
					"DELETE FROM " + tableName + " WHERE " + 
					colElement + " = '" + element + "';";
			statement.executeUpdate(sqlite);
			statement.close();
			dataBase.close();
		}
		catch (Exception e) {
			System.err.println(e.getMessage());
		}
	}
}