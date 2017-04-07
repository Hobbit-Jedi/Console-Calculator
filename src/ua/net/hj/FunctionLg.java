package ua.net.hj;

/**
 * Описывает функцию lg(x).
 * Логарифм десятичный из x.
 * @author Hobbit Jedi
 */
public class FunctionLg extends FunctionUnary {
	
	/**
	 * Создать функцию десятичного логарифма.
	 * @param aArgument - Выражение, описывающее аргумент функции.
	 */
	public FunctionLg(Expression aArgument)
	{
		super(aArgument);
	}
	
	/**
	 * Выполняет вычисление числового значения выражения.
	 * @return - Вычисленное знаение выражения.
	 */
	@Override
	public double interptet()
	{
		double result = Math.log10(mArgument.interptet());
		return result;
	}
	
}
