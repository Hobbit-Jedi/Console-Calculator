package ua.net.hj;

/**
 * Описывает функцию sqrt(x).
 * Корень квадратный из x.
 * @author Hobbit Jedi
 */
public class FunctionSqrt extends FunctionUnary {
	
	/**
	 * Создать функцию квадратного корня.
	 * @param aArgument - Выражение, описывающее аргумент функции.
	 */
	public FunctionSqrt(Expression aArgument)
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
		double result = Math.sqrt(mArgument.interptet());
		return result;
	}
	
}
