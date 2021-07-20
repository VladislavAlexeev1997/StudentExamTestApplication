package ClientClasses;

public class UnVaribleQuestion extends Question {

	// Правильный ответ на вопрос
	private String rightAnswear;
	// Ответ пользователя на вопрос
	public String userAnswear;
	
	// Конструктор без параметров
	public UnVaribleQuestion () {
		super();
	}
	
	// Конструктор с параметрами
	public UnVaribleQuestion (String question, String rightAnswear, String userAnswear) {
		setQuestion(question);
		this.rightAnswear = rightAnswear;
		this.userAnswear = userAnswear;
	}
	
	// Методы get и set для поля rightAnswear класса 
	public String getRightAnswear() {
		return rightAnswear;
	}

	public void setRightAnswear (String rightAnswear) {
		this.rightAnswear = rightAnswear;
	}
	
	// Расчет оценки ответа на данный вопрос
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