package ClientClasses;

import java.util.ArrayList;

public class SomeVaribleQuestion extends Question{

	// Список вариантов ответа на вопрос
	private ArrayList<String> varibleAnswears;
	// Список правильных ответов
	private ArrayList<String> rightAnswear;
	// Список ответов пользователя на данный вопрос
	public ArrayList<String> userAnswear;
	
	// Конструктор без параметров
	public SomeVaribleQuestion () {
		super();
	}
	
	// Конструктор с параметрами
	public SomeVaribleQuestion (String question, ArrayList<String> rightAnswear, ArrayList<String> userAnswear) {
		setQuestion(question);
		this.rightAnswear = rightAnswear;
		this.userAnswear = userAnswear;
	}
	
	// Методы set и get полей данного класса
	public ArrayList<String> getVaribleAnswears() {
		return varibleAnswears;
	}
	
	public void setVaribleAnswears(ArrayList<String> varibleAnswears) {
		this.varibleAnswears = varibleAnswears;
	}

	public ArrayList<String> getRightAnswears() {
		return rightAnswear;
	}

	public void setRightAnswears(ArrayList<String> rightAnswears) {
		this.rightAnswear= rightAnswears;
	}
	
	// Расчет оценки ответа на данный вопрос
	@Override
	public double calculateMark () {
		double result = 0;
		for (String userAnswear : userAnswear) {
			for (String rightAnswear : rightAnswear) {
				if (userAnswear.equals(rightAnswear)) {
					result += 0.5;
				}
			}
		}
		return result;
	}
}