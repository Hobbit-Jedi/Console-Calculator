package ua.net.hj;

/**
 * Описывает функцию sin(x).
 * Синус из x.
 * @author Hobbit Jedi
 */
public class FunctionSin extends FunctionUnary {
	
	/**
	 * Создать функцию синуса.
	 * @param aArgument - Выражение, описывающее аргумент функции.
	 */
	public FunctionSin(Expression aArgument)
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
		double result = Math.sin(mArgument.interptet());
		return result;
	}
	
}
