package ua.net.hj;

/**
 * Описывает оператор с двумя операндами.
 * @author Hobbit Jedi
 */
abstract public class OperatorBinary extends Operator {
	protected Expression[] mOperands; // Операнды оператора.
	
	/**
	 * Создать оператор с двумя операндами.
	 * @param aOperand1 - Выражение, соответствующее первому аргументу функции.
	 * @param aOperand2 - Выражение, соответствующее второму аргументу функции.
	 */
	protected OperatorBinary(Expression aOperand1, Expression aOperand2)
	{
		super(2);
		mOperands = new Expression[mNumOfOperands];
		mOperands[0] = aOperand1;
		mOperands[1] = aOperand2;
	}
	
	/**
	 * Получить операнды оператора.
	 * @return - Массив с операндами оператора.
	 */
	@Override
	public Expression[] getOperands()
	{
		Expression[] result = new Expression[mNumOfOperands];
		System.arraycopy(mOperands, 0, result, 0, mNumOfOperands);
		return result;
	}
	
}
