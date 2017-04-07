package ua.net.hj;

/**
 * Описывает функцию min(x1,x2,...,xn).
 * Минимальное из значений.
 * @author Hobbit Jedi
 */
public class FunctionMin extends FunctionMulti {
	
	/**
	 * Создать функцию минимума.
	 * @param aArguments - Выражения, соответствующее аргументам функции.
	 */
	public FunctionMin(Expression... aArguments)
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
		double result = Double.POSITIVE_INFINITY;
		for (Expression arg: mArguments)
		{
			double argValue = arg.interptet();
			result = Math.min(result, argValue);
		}
		return result;
	}
	
}
