package ua.net.hj;

/**
 * Описывает функцию с одним аргументом.
 * @author Hobbit Jedi
 */
abstract public class FunctionUnary extends Function {
	protected Expression mArgument; // Аргумент функции.
	
	/**
	 * Создать функцию с одним аргументом.
	 * @param aArgument - Выражение, соответствующее аргументу функции.
	 */
	protected FunctionUnary(Expression aArgument)
	{
		super(1);
		mArgument = aArgument;
	}
	
	/**
	 * Получить аргумент.
	 * @return - Единственный аргумент функции.
	 */
	public Expression getArgument()
	{
		return mArgument;
	}

	/**
	 * Получить аргументы функции.
	 * @return - Массив с аргументами функции.
	 *           Порядок аргументов в массиве соответствует порядку аргументов функции.
	 */
	@Override
	public Expression[] getArguments()
	{
		Expression[] result = new Expression[1];
		result[0] = getArgument();
		return result;
	}
}
