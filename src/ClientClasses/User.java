package ClientClasses;

import java.sql.*;
import java.util.ArrayList;

public class User {

	private String loginUser;
	private String roleUser;
	private String firstName;
	private String secondName;
	private String lastName;
	
	public String getLoginUser () {
		return loginUser;
	}
	
	public void setLoginUser (String loginUser) {
		this.loginUser = loginUser;
	}
	
	public String getRoleUser () {
		return roleUser;
	}
	
	public void setRoleUser (String roleUser) {
		this.roleUser = roleUser;
	}
	
	public String getNames () {
		return firstName + " " + secondName + " " + lastName;
	}
	
	public String getFirstName () {
		return firstName;
	}
	
	public String getSecondName () {
		return secondName;
	}
	
	public String getLastName () {
		return lastName;
	}
	
	public void setFirstName (String firstName) {
		this.firstName = firstName;
	}
	
	public void setSecondName (String secondName) {
		this.secondName = secondName;
	}
	
	public void setLastName (String lastName) {
		this.lastName = lastName;
	}
	
	public boolean getUserData (String login, String password) {
		boolean getting = false;
		try {
			Class.forName("org.sqlite.JDBC");
			String urlDataBase = "jdbc:sqlite:c:\\DataBases/Users.db";
			Connection dataBase = DriverManager.getConnection(urlDataBase);
			Statement statement = dataBase.createStatement();
			ResultSet result = statement.executeQuery("SELECT * FROM Users");
			while (result.next()) {
				if (result.getString("Login").equals(login) && 
						result.getString("Password").equals(password)) {
					loginUser = result.getString("Login");
					roleUser = result.getString("Role");
					firstName = result.getString("FirstName");
					secondName = result.getString("SecondName");
					lastName = result.getString("LastName");
					getting = true;
					break;
				}
			}
			result.close();
			statement.close();
			dataBase.close();
		}
		catch (Exception e) {
			System.err.println(e.getMessage());
		}
		return getting;
	}
	
	public boolean searchUserForLogin (String login) {
		boolean searching = false;
		try {
			Class.forName("org.sqlite.JDBC");
			String urlDataBase = "jdbc:sqlite:c:\\DataBases/Users.db";
			Connection dataBase = DriverManager.getConnection(urlDataBase);
			Statement statement = dataBase.createStatement();
			ResultSet result = statement.executeQuery("SELECT * FROM Users");
			while (result.next()) {
				if (result.getString("Login").equals(login)) {
					loginUser = result.getString("Login");
					roleUser = result.getString("Role");
					firstName = result.getString("FirstName");;
					secondName = result.getString("SecondName");
					lastName = result.getString("LastName");
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

	public boolean searchUserForName (String role, 
			String firstName, String secondName, String lastName) {
		boolean searching = false;
		try {
			Class.forName("org.sqlite.JDBC");
			String urlDataBase = "jdbc:sqlite:c:\\DataBases/Users.db";
			Connection dataBase = DriverManager.getConnection(urlDataBase);
			Statement statement = dataBase.createStatement();
			ResultSet result = statement.executeQuery("SELECT * FROM Users");
			while (result.next()) {
				if (result.getString("Role").equals(role) && 
						result.getString("FirstName").equals(firstName) &&
						result.getString("SecondName").equals(secondName) &&
						result.getString("LastName").equals(lastName)) {
					loginUser = result.getString("Login");
					roleUser = result.getString("Role");
					this.firstName = result.getString("FirstName");;
					this.secondName = result.getString("SecondName");
					this.lastName = result.getString("LastName");
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
	
	public static ArrayList<User> userList (String userRole) {
		ArrayList<User> userList = new ArrayList<User>();
		try {
			Class.forName("org.sqlite.JDBC");
			String urlDataBase = "jdbc:sqlite:c:\\DataBases/Users.db";
			Connection dataBase = DriverManager.getConnection(urlDataBase);
			Statement statement = dataBase.createStatement();
			ResultSet result = statement.executeQuery("SELECT * FROM Users");
			while (result.next()) {
				if (result.getString("Role").equals(userRole)) {
					User getUser = new User();
					getUser.setLoginUser(result.getString("Login"));
					getUser.setRoleUser(result.getString("Role"));
					getUser.setFirstName(result.getString("FirstName"));
					getUser.setSecondName(result.getString("SecondName"));
					getUser.setLastName(result.getString("LastName"));
					userList.add(getUser);
				}
			}
			result.close();
			statement.close();
			dataBase.close();
		}
		catch (Exception e) {
			System.err.println(e.getMessage());
		}
		return userList;
	}
}