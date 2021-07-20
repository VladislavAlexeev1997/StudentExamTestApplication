package ClientClasses;

import static org.junit.Assert.*;
import java.util.*;
import org.junit.Test;

// Тестовый драйвер для отладки функции calculateMark 

public class TestingQuestion {

	/*
	 * Тестовый случай, когда пользователь, отвечающий 
	 * на вопрос с кратким ответом, дает правильный ответ
	 */
	@Test
	public void rightAnswearForUVQ() {
		Question quest = new UnVaribleQuestion("Кто такой заяц?", "животное", "животное");
		assertEquals(quest.calculateMark(), 1, 0.1);
	}
	
	/*
	 * Тестовый случай, когда пользователь, отвечающий 
	 * на вопрос с кратким ответом, дает неверный ответ
	 */
	@Test
	public void notRightAnswearForUVQ() {
		Question quest = new UnVaribleQuestion("Кто такой заяц?", "животное", "птица");
		assertEquals(quest.calculateMark(), 0, 0.1);
	}
	
	/*
	 * Пользователь, отвечающий на вопрос с одним вариантом
	 * правильного ответа, дает правильный ответ
	 */
	@Test
	public void rightAnswearForOVQ() {
		Question quest = new OneVaribleQuestion("Первая буква русского алфавита", "А", "А");
		assertEquals(quest.calculateMark(), 1, 0.1);
	}
	
	/*
	 * Пользователь, отвечающий на вопрос с одним вариантом 
	 * правильного ответа, дает неверный ответ
	 */
	@Test
	public void notRightAnswearForOVQ() {
		Question quest = new OneVaribleQuestion("Первая буква русского алфавита", "А", "Я");
		assertEquals(quest.calculateMark(), 0, 0.1);
	}
	
	/*
	 * Пользователь, отвечающий на вопрос с несколькими вариантами
	 * правильного ответа, дает правильный ответ
	 */
	@Test
	public void rightAnswearForSVQ() {
		Question quest = new SomeVaribleQuestion(
				"Четными числами являются: ", 
				new ArrayList<String>(Arrays.asList(new String[]{"2", "4"})), 
				new ArrayList<String>(Arrays.asList(new String[]{"2", "4"}))
				);
		assertEquals(quest.calculateMark(), 1, 0.1);
	}

	/*
	 * Пользователь, отвечающий на вопрос с несколькими вариантами 
	 * правильного ответа, дает частично правильный ответ
	 */
	@Test
	public void partyRightAnswearForSVQ() {
		Question quest = new SomeVaribleQuestion(
				"Четными числами являются: ", 
				new ArrayList<String>(Arrays.asList(new String[]{"2", "4"})), 
				new ArrayList<String>(Arrays.asList(new String[]{"2", "5"}))
				);
		assertEquals(quest.calculateMark(), 0.5, 0.1);
	}
	
	/*
	 * Пользователь, отвечающий на вопрос с несколькими вариантами 
	 * правильного ответа, дает неверный ответ
	 */
	@Test
	public void notRightAnswearForSVQ() {
		Question quest = new SomeVaribleQuestion(
				"Четными числами являются: ", 
				new ArrayList<String>(Arrays.asList(new String[]{"2", "4"})), 
				new ArrayList<String>(Arrays.asList(new String[]{"3", "5"}))
				);
		assertEquals(quest.calculateMark(), 0, 0.1);
	}
}
