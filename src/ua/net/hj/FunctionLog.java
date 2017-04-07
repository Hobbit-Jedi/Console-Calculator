package ua.net.hj;

/**
 * Описывает функцию log(x,y).
 * Логарифм из x по основанию y.
 * @author Hobbit Jedi
 */
public class FunctionLog extends FunctionBinary {
	
	/**
	 * Создать функцию логарифма по указанному основанию.
	 * @param aArgument1 - Выражение, соответствующее первому аргументу функции.
	 * @param aArgument2 - Выражение, соответствующее второму аргументу функции.
	 */
	public FunctionLog(Expression aArgument1, Expression aArgument2)
	{
		super(aArgument1, aArgument2);
	}
	
	/**
	 * Выполняет вычисление числового значения выражения.
	 * @return - Вычисленное знаение выражения.
	 */
	@Override
	public double interptet()
	{
		double result = Math.log(mArguments[0].interptet()) / Math.log(mArguments[1].interptet());
		return result;
	}
	
}
