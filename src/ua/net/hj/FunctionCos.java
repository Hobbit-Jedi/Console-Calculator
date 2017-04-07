package ua.net.hj;

/**
 * Описывает функцию cos(x).
 * Косинус из x.
 * @author Hobbit Jedi
 */
public class FunctionCos extends FunctionUnary {
	
	/**
	 * Создать функцию косинуса.
	 * @param aArgument - Выражение, описывающее аргумент функции.
	 */
	public FunctionCos(Expression aArgument)
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
		double result = Math.cos(mArgument.interptet());
		return result;
	}
	
}
