package ua.net.hj;

/**
 * Описывает оператор изменения знака.
 * -x - знак x меняется на противоположный.
 * @author Hobbit Jedi
 */
public class OperatorNegative extends OperatorUnary {
	
	/**
	 * Создать оператор изменения знака.
	 * @param aOperand - Выражение, соответствующее операнду оператора.
	 */
	public OperatorNegative(Expression aOperand)
	{
		super(aOperand);
	}
	
	/**
	 * Выполняет вычисление числового значения выражения.
	 * @return - Вычисленное знаение выражения.
	 */
	@Override
	public double interptet()
	{
		double result = -mOperand.interptet();
		return result;
	}
	
}
