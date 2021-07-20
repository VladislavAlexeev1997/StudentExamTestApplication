package ClientClasses;

import java.sql.*;
import java.util.ArrayList;

public class SQLConnection {

	// Добавить тест в базу данных
	public boolean addTestDB (String loginTeacher, Test newTest) {
		boolean adding = false;
		try {
			Class.forName("org.sqlite.JDBC");
			String urlDataBase = "jdbc:sqlite:c:\\DataBases/Tests.db";
			Connection dataBase = DriverManager.getConnection(urlDataBase);
			dataBase.setAutoCommit(false);
			Statement statement = dataBase.createStatement();
			if (!searchTeacherTest (loginTeacher, newTest.getNameEducationObject(), newTest.getNameTest())) {
				String sqlite = 
						"INSERT INTO TestNames (TeacherLogin, ObjectName, TestName) " +
						"VALUES ('" + loginTeacher + "', '" + newTest.getNameEducationObject() + "', '" +
						newTest.getNameTest() + "' );";
				statement.executeUpdate(sqlite);
				adding = true;
			}
			statement.close();
			dataBase.commit();
			dataBase.close();
		}
		catch (Exception e) {
			System.err.println(e.getMessage());
		}
		return adding;
	}
	
	// Добавить преподавателю дисциплину, подсоединить к нему группу в базе данных
	public boolean addTeacherElement (String loginTeacher, String addElem, String tableName, String addName) {
		boolean adding = false;
		try {
			Class.forName("org.sqlite.JDBC");
			String urlDataBase = "jdbc:sqlite:c:\\DataBases/Users.db";
			Connection dataBase = DriverManager.getConnection(urlDataBase);
			dataBase.setAutoCommit(false);
			Statement statement = dataBase.createStatement();
			if (!searchTeacherElement(loginTeacher, addElem, tableName, addName)) {
				String sqlite = 
						"INSERT INTO " + tableName + " (Login," + addName + ") " +
						"VALUES ('" + loginTeacher + "', '" + addElem + "' );";
				statement.executeUpdate(sqlite);
				adding = true;
			}
			statement.close();
			dataBase.commit();
			dataBase.close();
		}
		catch (Exception e) {
			System.err.println(e.getMessage());
		}
		return adding;
	}

	// Добавить вопрос в базу данных
	public boolean addQuestionDB (String teacherLogin, String sqlite) {
		boolean adding = false;
		try {
			Class.forName("org.sqlite.JDBC");
			String urlDataBase = "jdbc:sqlite:c:\\DataBases/Tests.db";
			Connection dataBase = DriverManager.getConnection(urlDataBase);
			dataBase.setAutoCommit(false);
			Statement statement = dataBase.createStatement();
			statement.executeUpdate(sqlite);
			adding = true;
			statement.close();
			dataBase.commit();
			dataBase.close();
		}
		catch (Exception e) {
			System.err.println(e.getMessage());
		}
		return adding;
	}
	
	// Удалить у преподавателя дисциплину, отсоединить от него группу из базы данных
	public boolean deleteTeacherElement (String loginTeacher, String delElem, String tableName, String delName) {
		boolean deleting = false;
		try {
			Class.forName("org.sqlite.JDBC");
			String urlDataBase = "jdbc:sqlite:c:\\DataBases/Users.db";
			Connection dataBase = DriverManager.getConnection(urlDataBase);
			Statement statement = dataBase.createStatement();
			String sqlite = 
					"DELETE FROM " + tableName + " WHERE " + 
					"Login = '" + loginTeacher + "' AND " +
					delName + " = '" + delElem + "';";
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
	
	// Удалить тест преподавателя из базы данных
	public boolean deleteTestDB (String loginTeacher, Test delTest) {
		boolean deleting = false;
		try {
			Class.forName("org.sqlite.JDBC");
			String urlDataBase = "jdbc:sqlite:c:\\DataBases/Tests.db";
			Connection dataBase = DriverManager.getConnection(urlDataBase);
			Statement statement = dataBase.createStatement();
			String sqlite = 
					"DELETE FROM TestNames WHERE TeacherLogin = '" + loginTeacher + "' AND " +
					"ObjectName = '" + delTest.getNameEducationObject() + "' AND " +
					"TestName = '" + delTest.getNameTest() + "';";
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
	
	// Удалить вопрос теста из базы данных 
	public boolean deleteQuestionDB (Test delTest, String teacherLogin, String tableName, String questName) {
		boolean deleting = false;
		try {
			Class.forName("org.sqlite.JDBC");
			String urlDataBase = "jdbc:sqlite:c:\\DataBases/Tests.db";
			Connection dataBase = DriverManager.getConnection(urlDataBase);
			Statement statement = dataBase.createStatement();
			String sqlite = 
					"DELETE FROM " + tableName + " WHERE TeacherLogin = '" + teacherLogin + "' AND " +
					"ObjectName = '" + delTest.getNameEducationObject() + "' AND TestName = '" 
					+ delTest.getNameTest() + "' AND " + "Question = " + questName + ";";
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
	
	//Поиск по базе данных дисциплины, группы
	public boolean searchTeacherElement (String loginTeacher, String srhElem, String tableName, String srhName) {
		boolean searching = false;
		try {
			Class.forName("org.sqlite.JDBC");
			String urlDataBase = "jdbc:sqlite:c:\\DataBases/Users.db";
			Connection dataBase = DriverManager.getConnection(urlDataBase);
			Statement statement = dataBase.createStatement();
			ResultSet result = statement.executeQuery("SELECT * FROM " + tableName);
			while (result.next()) {
				if (result.getString("Login").equals(loginTeacher) &&
						result.getString(srhName).equals(srhElem)) {
					searching = true;
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
		return searching;
	}
	
	// Поиск теста преподавателя в базе данных
	public boolean searchTeacherTest (String loginTeacher, String nameEducationObject, String nameTest) {
		boolean searching = false;
		try {
			Class.forName("org.sqlite.JDBC");
			String urlDataBase = "jdbc:sqlite:c:\\DataBases/Tests.db";
			Connection dataBase = DriverManager.getConnection(urlDataBase);
			Statement statement = dataBase.createStatement();
			ResultSet result = statement.executeQuery("SELECT * FROM TestNames");
			while (result.next()) {
				if (result.getString("TeacherLogin").equals(loginTeacher) &&
						result.getString("ObjectName").equals(nameEducationObject) &&
						result.getString("TestName").equals(nameTest)) {
					searching = true;
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
		return searching;
	}
	
	// Список дисциплин, групп, прикрепленных преподавателю
	public ArrayList<String> teacherElementList (String loginTeacher, String tableName, String searchElem) {
		ArrayList<String> element = new ArrayList<String>();
		try {
			Class.forName("org.sqlite.JDBC");
			String urlDataBase = "jdbc:sqlite:c:\\DataBases/Users.db";
			Connection dataBase = DriverManager.getConnection(urlDataBase);
			Statement statement = dataBase.createStatement();
			ResultSet result = statement.executeQuery("SELECT * FROM " + tableName);
			while (result.next()) {
				if (result.getString("Login").equals(loginTeacher)) {
					element.add(result.getString(searchElem));
				}
			}
			result.close();
			statement.close();
			dataBase.close();
		}
		catch (Exception e) {
			System.err.println(e.getMessage());
		}
		return element;
	}
}
