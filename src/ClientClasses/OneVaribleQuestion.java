package ClientClasses;

import java.util.ArrayList;

public class OneVaribleQuestion extends Question {

	// Список вариантов ответа на вопрос
	private ArrayList<String> varibleAnswears;
	// Правильный ответ
	private String rightAnswear;
	// Ответ пользователя
	public String userAnswear;
	
	// Конструктор без параметров
	public OneVaribleQuestion () {
		super();
	}
	
	// Конструктор с параметрами
	public OneVaribleQuestion (String question, String rightAnswear, String userAnswear) {
		setQuestion(question);
		this.rightAnswear = rightAnswear;
		this.userAnswear = userAnswear;
	}
	
	// Методы get и set для полей данного класса
	public ArrayList<String> getVaribleAnswears() {
		return varibleAnswears;
	}
	
	public void setVaribleAnswears(ArrayList<String> varibleAnswears) {
		this.varibleAnswears = varibleAnswears;
	}

	public String getRightAnswear() {
		return rightAnswear;
	}

	public void setRightAnswear(String rightAnswear) {
		this.rightAnswear = rightAnswear;
	}
	
	// Расчет набранных баллов пользователем за данный вопрос
	@Override
	public double calculateMark () {
		if (userAnswear.equals(rightAnswear)) {
			return 1;
		}
		else {
			return 0;
		}
	}
}