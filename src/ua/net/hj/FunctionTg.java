package ua.net.hj;

/**
 * Описывает функцию tg(x).
 * Тангенс из x.
 * @author Hobbit Jedi
 */
public class FunctionTg extends FunctionUnary {
	
	/**
	 * Создать функцию тангенса.
	 * @param aArgument - Выражение, описывающее аргумент функции.
	 */
	public FunctionTg(Expression aArgument)
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
		double result = Math.tan(mArgument.interptet());
		return result;
	}
	
}
