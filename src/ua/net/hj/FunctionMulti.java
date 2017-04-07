package ua.net.hj;

/**
 * Описывает функцию с неизвестным количеством аргументов.
 * @author Hobbit Jedi
 */
abstract public class FunctionMulti extends Function {
	protected Expression[] mArguments; // Аргументы функции.
	
	/**
	 * Создать функцию с любым количеством аргументов.
	 * @param aArguments - Выражения, соответствующее аргументам функции.
	 */
	protected FunctionMulti(Expression... aArguments)
	{
		super(aArguments.length);
		mArguments = new Expression[mNumOfArguments];
		System.arraycopy(aArguments, 0, mArguments, 0, mNumOfArguments);
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
