package ClientClasses;

public abstract class Question {

	// Поле, хранящее формулировку вопроса
	private String question;
	
	// get и set методы для поля question
	public String getQuestion () {
		return question;
	}
	
	public void setQuestion (String question) {
		this.question = question;
	}
	
	// Метод для расчета набранных баллов по данному вопросу
	public abstract double calculateMark ();
}