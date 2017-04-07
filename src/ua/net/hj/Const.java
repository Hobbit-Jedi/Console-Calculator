package ua.net.hj;

/**
 * Описывает числовую константу.
 * @author Hobbit Jedi
 */
public class Const implements Expression {
	private final double mValue; // Значение константы.
	
	/**
	 * Создать числовую константу.
	 * @param aValue - Значение константы.
	 */
	public Const(double aValue)
	{
		mValue = aValue;
	}
	
	/**
	 * Выполняет вычисление числового значения выражения.
	 * @return - Вычисленное знаение выражения.
	 */
	@Override
	public double interptet()
	{
		return mValue;
	}
	
}
