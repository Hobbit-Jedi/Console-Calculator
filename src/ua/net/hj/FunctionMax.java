package ua.net.hj;

/**
 * Описывает функцию max(x1,x2,...,xn).
 * Максимальное из значений.
 * @author Hobbit Jedi
 */
public class FunctionMax extends FunctionMulti {
	
	/**
	 * Создать функцию максимума.
	 * @param aArguments - Выражения, соответствующее аргументам функции.
	 */
	public FunctionMax(Expression... aArguments)
	{
		super(aArguments);
	}
	
	/**
	 * Выполняет вычисление числового значения выражения.
	 * @return - Вычисленное знаение выражения.
	 */
	@Override
	public double interptet()
	{
		double result = Double.NEGATIVE_INFINITY;
		for (Expression arg: mArguments)
		{
			double argValue = arg.interptet();
			result = Math.max(result, argValue);
		}
		return result;
	}
	
}
