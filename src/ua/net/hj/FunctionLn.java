package ua.net.hj;

/**
 * Описывает функцию ln(x).
 * Логарифм натуральный из x.
 * @author Hobbit Jedi
 */
public class FunctionLn extends FunctionUnary {
	
	/**
	 * Создать функцию натурального логарифма.
	 * @param aArgument - Выражение, описывающее аргумент функции.
	 */
	public FunctionLn(Expression aArgument)
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
		double result = Math.log(mArgument.interptet());
		return result;
	}
	
}
