package ua.net.hj;

/**
 * Описывает функцию в общем.
 * @author Hobbit Jedi
 */
abstract public class Function implements Expression {
	protected int mNumOfArguments; // Количество принимаемых функцией аргументов (-1 - любое).
	
	/**
	 * Создать новую функцию.
	 * @param aNumOfArguments - Количество принимаемых функцией аргументов.
	 */
	protected Function(int aNumOfArguments)
	{
		mNumOfArguments = aNumOfArguments;
	}
	
	/**
	 * Получить количество аргументов функции.
	 * @return - Количество используемых функцией аргументов.
	 *           -1, если количество аргументов может быть любым.
	 */
	public int getNumOfArguments()
	{
		return mNumOfArguments;
	}
	
	/**
	 * Получить аргументы функции.
	 * @return - Массив с аргументами функции.
	 *           Порядок аргументов в массиве соответствует порядку аргументов функции.
	 */
	abstract public Expression[] getArguments();
	
}
