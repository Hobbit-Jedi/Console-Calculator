package ua.net.hj;

/**
 * Описывает оператор с одним операндом.
 * @author Hobbit Jedi
 */
abstract public class OperatorUnary extends Operator {
	protected Expression mOperand; // Операнд оператора.
	
	/**
	 * Создать оператор с одним операндом.
	 * @param aOperand - Выражение, соответствующее операнду оператора.
	 */
	protected OperatorUnary(Expression aOperand)
	{
		super(1);
		mOperand = aOperand;
	}
	
	/**
	 * Получить операнд.
	 * @return - Единственный операнд оператора.
	 */
	public Expression getOperand()
	{
		return mOperand;
	}

	/**
	 * Получить операнды оператора.
	 * @return - Массив с операндами оператора.
	 */
	@Override
	public Expression[] getOperands()
	{
		Expression[] result = new Expression[1];
		result[0] = getOperand();
		return result;
	}
}
