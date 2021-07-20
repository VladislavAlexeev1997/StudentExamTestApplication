package ClientClasses;

import java.sql.*;
import java.util.ArrayList;

public class Group {
	
	private String nameGroup;
	private ArrayList<Student> students;
	
	public Group (String nameGroup) {
		this.nameGroup = nameGroup;
		students = searchStudents(nameGroup);
	}
	
	public String getNameGroup () {
		return nameGroup;
	}
	
	public ArrayList<Student> getStudents () {
		return students;
	}
	
	public ArrayList<Student> searchStudents (String nameGroup) {
		ArrayList<Student> searchStudents = new ArrayList<Student>();
		ArrayList<String> loginStudents = getLoginStudents (nameGroup);
		for (String login : loginStudents) {
			searchStudents.add(new Student (login));
		}
		return searchStudents;
	}
	
	private ArrayList<String> getLoginStudents (String nameGroup) {
		ArrayList<String> searching = new ArrayList<String> ();
		try {
			Class.forName("org.sqlite.JDBC");
			String urlDataBase = "jdbc:sqlite:c:\\DataBases/Users.db";
			Connection dataBase = DriverManager.getConnection(urlDataBase);
			Statement statement = dataBase.createStatement();
			ResultSet result = statement.executeQuery("SELECT * FROM StudentGroup");
			while (result.next()) {
				if (result.getString("GroupName").equals(nameGroup)) {
					searching.add(result.getString("Login"));
				}
			}
			result.close();
			statement.close();
			dataBase.close();
		}
		catch (Exception e) {
			System.err.println(e.getMessage());
		}
		return searching;
	}
}