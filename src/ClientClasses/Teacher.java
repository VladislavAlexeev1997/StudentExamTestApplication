package ClientClasses;

import java.sql.*;
import java.util.ArrayList;

public class Teacher extends User {
	
	// Конструктор без параметров
	public Teacher () {
		setRoleUser ("преподаватель");
	}
	
	//  Конструктор  параметрами
	public Teacher (String loginUser) {
		searchUserForLogin(loginUser);
	}
	
	// Подкрепить группу к преподавателю
	public boolean addGroup (String addGroup, String connectEduObj) {
		SQLConnection sql = new SQLConnection();
		return sql.addTeacherElement(getLoginUser(), addGroup, "TeacherGroups", "GroupName") && 
				connectGroupToEduObj(addGroup, connectEduObj);
	}
	
	// Добавить дисциплину к преподавателю
	public boolean addEducationObgect (String addEduObg) {
		SQLConnection sql = new SQLConnection();
		return sql.addTeacherElement(getLoginUser(), addEduObg, "TeacherObjects", "ObjectName");
	}
	
	//Добавить новый тест
	public boolean addNewTeacherTest (Test newTest) {
		SQLConnection sql = new SQLConnection ();
		boolean adding = sql.addTestDB (getLoginUser(), newTest);
		if (adding) {
			for (Question question : newTest.getQuestions()) {
				newTest.addQuestion(getLoginUser(), question);
			}
		}
		return adding;
	}
	
	//
	public boolean connectGroupToEduObj (String group, String connectEduObj) {
		boolean connect = false;
		try {
			Class.forName("org.sqlite.JDBC");
			String urlDataBase = "jdbc:sqlite:c:\\DataBases/Users.db";
			Connection dataBase = DriverManager.getConnection(urlDataBase);
			dataBase.setAutoCommit(false);
			Statement statement = dataBase.createStatement();
			String sqlite = 
					"INSERT INTO GroupEduObjs (GroupName,TeacherLogin,ObjectName) " +
					"VALUES ('" + group + "', '" + getLoginUser() + "', '" + connectEduObj + "' );";
			statement.executeUpdate(sqlite);
			connect = true;
			statement.close();
			dataBase.commit();
			dataBase.close();
		}
		catch (Exception e) {
			System.err.println(e.getMessage());
		}
		return connect;
	}
	//
	private boolean deleteConnectGroup (String delGroup) {
		boolean deleting = false;
		try {
			Class.forName("org.sqlite.JDBC");
			String urlDataBase = "jdbc:sqlite:c:\\DataBases/Users.db";
			Connection dataBase = DriverManager.getConnection(urlDataBase);
			Statement statement = dataBase.createStatement();
			String sqlite = 
					"DELETE FROM GroupEduObjs WHERE " + 
					"GroupName = '" + delGroup  + "' AND " +
					"TeacherLogin = '" + getLoginUser()+ "';";
			statement.executeUpdate(sqlite);
			statement.close();
			dataBase.close();
			deleting = true;
		}
		catch (Exception e) {
			System.err.println(e.getMessage());
		}
		return deleting;
	}
	
	// Открепить группу от преподавателя
	public boolean deleteGroup (String delGroup) {
		SQLConnection sql = new SQLConnection();
		return sql.deleteTeacherElement(getLoginUser(), delGroup, "TeacherGroups", "GroupName") && 
				deleteConnectGroup (delGroup);
	}
	
	// Удалить дисциплину у преподавателя
	public boolean deleteEducationObgect (String delEduObg) {
		SQLConnection sql = new SQLConnection();
		return sql.deleteTeacherElement(getLoginUser(), delEduObg, "TeacherObjects", "ObjectName");
	}
	
	// Удалить тест у преподавателя
	public boolean deleteTeacherTest (Test delTest) {
		SQLConnection sql = new SQLConnection();
		boolean deleting = sql.deleteTestDB (getLoginUser(), delTest);
		if (deleting) {
			for (Question question : delTest.getQuestions()) {
				delTest.deleteQuestion(getLoginUser(), question);
			}
		}
		return deleting;
	}

	//
	public ArrayList<String> getGroupEduObjects (String group) {
		ArrayList<String> educationObjects = new ArrayList<String>();
		try {
			Class.forName("org.sqlite.JDBC");
			String urlDataBase = "jdbc:sqlite:c:\\DataBases/Users.db";
			Connection dataBase = DriverManager.getConnection(urlDataBase);
			Statement statement = dataBase.createStatement();
			ResultSet result = statement.executeQuery("SELECT * FROM GroupEduObjs");
			while (result.next()) {
				if (result.getString("GroupName").equals(group) &&
						result.getString("TeacherLogin").equals(getLoginUser())) {
					educationObjects.add(result.getString("ObjectName"));
				}
			}
			result.close();
			statement.close();
			dataBase.close();
		}
		catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return educationObjects;
	}
	//
	public ArrayList<String> getObjectTests (String eduObject) {
		ArrayList<String> tests = new ArrayList<String>();
		try {
			Class.forName("org.sqlite.JDBC");
			String urlDataBase = "jdbc:sqlite:c:\\DataBases/Tests.db";
			Connection dataBase = DriverManager.getConnection(urlDataBase);
			Statement statement = dataBase.createStatement();
			ResultSet result = statement.executeQuery("SELECT * FROM TestNames");
			while (result.next()) {
				if (result.getString("TeacherLogin").equals(getLoginUser()) &&
						result.getString("ObjectName").equals(eduObject)) {
					tests.add(result.getString("TestName"));
				}
			}
			result.close();
			statement.close();
			dataBase.close();
		}
		catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return tests;
	}
	
	//Выбрать тест преподавателя по дисциплине и его наименованию
	public Test getTeacherTest (String nameEducationObject, String nameTest) {
		Test teacherTest = null;
		SQLConnection sql = new SQLConnection ();
		if (sql.searchTeacherTest (getLoginUser(), nameEducationObject, nameTest)) {
			teacherTest = new Test(getLoginUser(), nameEducationObject, nameTest);
		}
		return teacherTest;
	}
	
	// Список прикрепленных групп
	public ArrayList<String> groupList () {
		SQLConnection sql = new SQLConnection ();
		return sql.teacherElementList (getLoginUser(), "TeacherGroups", "GroupName");
	}
	//
	public Group getGroup (String nameGroup) {
		return new Group (nameGroup);
	}
	
	// Список преподаваемых предметов
	public ArrayList<String> educationObgectList () {
		SQLConnection sql = new SQLConnection ();
		return sql.teacherElementList (getLoginUser(), "TeacherObjects", "ObjectName");
	}
}