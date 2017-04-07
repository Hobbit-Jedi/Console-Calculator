package ua.net.hj;

/**
 * Описывает функцию с двумя аргументами.
 * @author Hobbit Jedi
 */
abstract public class FunctionBinary extends Function {
	protected Expression[] mArguments; // Аргументы функции.
	
	/**
	 * Создать функцию с двумя аргументами.
	 * @param aArgument1 - Выражение, соответствующее первому аргументу функции.
	 * @param aArgument2 - Выражение, соответствующее второму аргументу функции.
	 */
	protected FunctionBinary(Expression aArgument1, Expression aArgument2)
	{
		super(2);
		mArguments = new Expression[mNumOfArguments];
		mArguments[0] = aArgument1;
		mArguments[1] = aArgument2;
	}
	
	/**
	 * Получить аргументы функции.
	 * @return - Массив с аргументами функции.
	 *           Порядок аргументов в массиве соответствует порядку аргументов функции.
	 */
	@Override
	public Expression[] getArguments()
	{
		Expression[] result = new Expression[mNumOfArguments];
		System.arraycopy(mArguments, 0, result, 0, mNumOfArguments);
		return result;
	}
}
