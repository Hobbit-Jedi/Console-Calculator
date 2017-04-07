package ua.net.hj;

/**
 * Описывает оператор вообще.
 * @author Hobbit Jedi
 */
abstract public class Operator implements Expression {
	protected int mNumOfOperands; // Количество принимаемых оператором операндов (-1 - любое).
	
	/**
	 * Создать новый оператор.
	 * @param aNumOfOperands - Количество принимаемых оператором операндов.
	 */
	protected Operator(int aNumOfOperands)
	{
		mNumOfOperands = aNumOfOperands;
	}
	
	/**
	 * Получить количество операндов оператора.
	 * @return - Количество используемых оператором операндов.
	 *           -1, если количество операндов может быть любым.
	 */
	public int getNumOfOperands()
	{
		return mNumOfOperands;
	}
	
	/**
	 * Получить операнды оператора.
	 * @return - Массив с операндами оператора.
	 */
	abstract public Expression[] getOperands();
	
}
