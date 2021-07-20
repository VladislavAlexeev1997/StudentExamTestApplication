package ClientClasses;

import java.sql.*;
import java.util.*;

public class Test {

	// Наименование дисциплины
	private String nameEducationObject;
	//Наименование теста
	private String nameTest;
	// Список вопросов, содержащихся в тесте
	private ArrayList<Question> questions;
	
	// Конструктор без параметров
	public Test () {
		questions = new ArrayList<Question> ();
	}
	
	// Конструктор с параметрами
	public Test (String nameEducationObject, String nameTest) {
		this.nameEducationObject = nameEducationObject;
		this.nameTest = nameTest;
	}
	//
	public Test (String teacherLogin, String nameEducationObject, String nameTest) {
		this.nameEducationObject = nameEducationObject;
		this.nameTest = nameTest;
		getQuestionsDB (teacherLogin);
	}

	// Методы get и set для полей класса
	public String getNameEducationObject() {
		return nameEducationObject;
	}

	public void setNameEducationObject(String nameEducationObject) {
		this.nameEducationObject = nameEducationObject;
	}

	public String getNameTest() {
		return nameTest;
	}

	public void setNameTest(String nameTest) {
		this.nameTest = nameTest;
	}

	public ArrayList<Question> getQuestions() {
		return questions;
	}

	public void setQuestions(ArrayList<Question> questions) {
		this.questions = questions;
	}
	
	// Добавить вопрос к тесту
	public boolean addQuestion (String teacherLogin, Question addQuestion) {
		String sqlite = "";
		SQLConnection sql = new SQLConnection ();
		if (addQuestion instanceof UnVaribleQuestion) {
			sqlite = "INSERT INTO UnVarQuest (TeacherLogin, ObjectName, " +
					 "TestName, Question, Answear) " +
					 "VALUES ('" + teacherLogin + "', '" + nameEducationObject + "', '" +
					 nameTest + "', " + ", '" + addQuestion.getQuestion () + "', '" + 
					 ((UnVaribleQuestion)addQuestion).getRightAnswear() + "' );";
		} 
		else if (addQuestion instanceof OneVaribleQuestion) {
			sqlite = "INSERT INTO OneVarQuest (TeacherLogin, ObjectName, " +
					 "TestName, Question, Answear1, Answear2, Answear3, RightAnswear) " +
					 "VALUES ('" + teacherLogin + "', '" + nameEducationObject + "', '" +
					 nameTest + "', " + ", '" + addQuestion.getQuestion () + "', '" + 
					 ((OneVaribleQuestion)addQuestion).getVaribleAnswears().get(0) + "', '" +
					 ((OneVaribleQuestion)addQuestion).getVaribleAnswears().get(1) + "', '" +
					 ((OneVaribleQuestion)addQuestion).getVaribleAnswears().get(2) + "', '" +
					 ((OneVaribleQuestion)addQuestion).getRightAnswear() + "' );";
		} 
		else if (addQuestion instanceof SomeVaribleQuestion) {
			sqlite = "INSERT INTO SomeVarQuest (TeacherLogin, ObjectName, TestName, Question, " +
					 "Answear1, Answear2, Answear3, Answear4, RightAns1, RightAns2) " + 
					 "VALUES ('" + teacherLogin + "', '" + nameEducationObject + "', '" +
					 nameTest + "', " + ", '" + addQuestion.getQuestion () + "', '" + 
					 ((SomeVaribleQuestion)addQuestion).getVaribleAnswears().get(0) + "', '" +
					 ((SomeVaribleQuestion)addQuestion).getVaribleAnswears().get(1) + "', '" +
					 ((SomeVaribleQuestion)addQuestion).getVaribleAnswears().get(2) + "', '" +
					 ((SomeVaribleQuestion)addQuestion).getVaribleAnswears().get(3) + "', '" +
					 ((SomeVaribleQuestion)addQuestion).getRightAnswears().get(0) + "', '" +
					 ((SomeVaribleQuestion)addQuestion).getRightAnswears().get(1) + "' );";
		}
		return sql.addQuestionDB (teacherLogin, sqlite);
	}
	
	// Подсчитать результат тестирования
	public double calculateResult () {
		double result = 0;
		for (Question question : questions) {
			if (question instanceof UnVaribleQuestion) {
				result += ((UnVaribleQuestion) question).calculateMark();
			}
			else if (question instanceof OneVaribleQuestion) {
				result += ((OneVaribleQuestion) question).calculateMark();
			} 
			else {
				result += ((SomeVaribleQuestion) question).calculateMark();
			}
		}	
		return result;
	}
	
	// Удалить вопрос из теста
	public boolean deleteQuestion (String teacherLogin, Question deleteQuestion) {
		String tableName = "";
		SQLConnection sql = new SQLConnection();
		if (deleteQuestion instanceof UnVaribleQuestion) {
			tableName = "UnVarQuest";
		} 
		else if (deleteQuestion instanceof OneVaribleQuestion) {
			tableName = "OneVarQuest";
		} 
		else if (deleteQuestion instanceof SomeVaribleQuestion) {
			tableName = "SomeVarQuest";
		}
		return sql.deleteQuestionDB (this, teacherLogin, tableName, deleteQuestion.getQuestion());
	}
	
	//
	private void getQuestionsDB (String teacherLogin) {
		questions = new ArrayList<Question> ();
		questions.addAll(getOneVaribleQuestions (teacherLogin));
		questions.addAll(getSomeVaribleQuestions (teacherLogin));
		questions.addAll(getUnVaribleQuestions (teacherLogin));
	}
	
	public ArrayList<UnVaribleQuestion> getUnVaribleQuestions (String teacherLogin) {
		ArrayList<UnVaribleQuestion> unVarQuest = new ArrayList<UnVaribleQuestion>();
		try {
			Class.forName("org.sqlite.JDBC");
			String urlDataBase = "jdbc:sqlite:c:\\DataBases/Tests.db";
			Connection dataBase = DriverManager.getConnection(urlDataBase);
			Statement statement = dataBase.createStatement();
			ResultSet result = statement.executeQuery("SELECT * FROM UnVarQuest");
			while (result.next()) {
				if (result.getString("TeacherLogin").equals(teacherLogin) && 
						result.getString("ObjectName").equals(nameEducationObject) &&
						result.getString("TestName").equals(nameTest)) {
					UnVaribleQuestion question = new UnVaribleQuestion();
					question.setQuestion(result.getString("Question"));
					question.setRightAnswear(result.getString("Answear"));
					unVarQuest.add(question);
				}
			}
			result.close();
			statement.close();
			dataBase.close();
		}
		catch (Exception e) {
			System.err.println(e.getMessage());
		}
		return unVarQuest;
	}
	
	public ArrayList<OneVaribleQuestion> getOneVaribleQuestions (String teacherLogin) {
		ArrayList<OneVaribleQuestion> oneVarQuest = new ArrayList<OneVaribleQuestion>();
		try {
			Class.forName("org.sqlite.JDBC");
			String urlDataBase = "jdbc:sqlite:c:\\DataBases/Tests.db";
			Connection dataBase = DriverManager.getConnection(urlDataBase);
			Statement statement = dataBase.createStatement();
			ResultSet result = statement.executeQuery("SELECT * FROM OneVarQuest");
			while (result.next()) {
				if (result.getString("TeacherLogin").equals(teacherLogin) && 
						result.getString("ObjectName").equals(nameEducationObject) &&
						result.getString("TestName").equals(nameTest)) {
					OneVaribleQuestion question = new OneVaribleQuestion();
					question.setQuestion(result.getString("Question"));
					ArrayList<String> answearList = new ArrayList<String> ();
					answearList.add(result.getString("Answear1"));
					answearList.add(result.getString("Answear2"));
					answearList.add(result.getString("Answear3"));
					question.setVaribleAnswears(answearList);
					question.setRightAnswear(result.getString("RightAnswear"));
					oneVarQuest.add(question);
				}
			}
			result.close();
			statement.close();
			dataBase.close();
		}
		catch (Exception e) {
			System.err.println(e.getMessage());
		}
		return oneVarQuest;
	}
	
	public ArrayList<SomeVaribleQuestion> getSomeVaribleQuestions (String teacherLogin) {
		ArrayList<SomeVaribleQuestion> someVarQuest = new ArrayList<SomeVaribleQuestion>();
		try {
			Class.forName("org.sqlite.JDBC");
			String urlDataBase = "jdbc:sqlite:c:\\DataBases/Tests.db";
			Connection dataBase = DriverManager.getConnection(urlDataBase);
			Statement statement = dataBase.createStatement();
			ResultSet result = statement.executeQuery("SELECT * FROM SomeVarQuest");
			while (result.next()) {
				if (result.getString("TeacherLogin").equals(teacherLogin) && 
						result.getString("ObjectName").equals(nameEducationObject) &&
						result.getString("TestName").equals(nameTest)) {
					SomeVaribleQuestion question = new SomeVaribleQuestion();
					question.setQuestion(result.getString("Question"));
					ArrayList<String> answearList = new ArrayList<String> ();
					answearList.add(result.getString("Answear1"));
					answearList.add(result.getString("Answear2"));
					answearList.add(result.getString("Answear3"));
					answearList.add(result.getString("Answear4"));
					question.setVaribleAnswears(answearList);
					ArrayList<String> rightAnswearList = new ArrayList<String> ();
					rightAnswearList.add(result.getString("RightAns1"));
					rightAnswearList.add(result.getString("RightAns2"));
					question.setRightAnswears(rightAnswearList);
					someVarQuest.add(question);
				}
			}
			result.close();
			statement.close();
			dataBase.close();
		}
		catch (Exception e) {
			System.err.println(e.getMessage());
		}
		return someVarQuest;
	}
}