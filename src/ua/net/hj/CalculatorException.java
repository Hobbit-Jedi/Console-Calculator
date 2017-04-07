package ua.net.hj;

/**
 * Исключение обработки переданного в калькулятор выражения.
 * @author Hobbit Jedi
 */
public class CalculatorException extends Exception {

	/**
	 * Создать исключение без детализации.
	 */
	public CalculatorException()
	{
		super();
	}
	
	/**
	 * Создать исключение с описанием подробностей.
	 * @param aMsg - Текст с описанием подробностей исключения.
	 */
	public CalculatorException(String aMsg)
	{
		super(aMsg);
	}
}
