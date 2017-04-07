package ua.net.hj;

/**
 * Описывает оператор возведения в степень.
 * x^y - x возводится в степень y.
 * @author Hobbit Jedi
 */
public class OperatorPow extends OperatorBinary {
	
	/**
	 * Создать оператор возведения в степень.
	 * @param aOperand1 - Выражение, соответствующее первому (левому) операнду оператора.
	 * @param aOperand2 - Выражение, соответствующее второму (правому) операнду оператора.
	 */
	public OperatorPow(Expression aOperand1, Expression aOperand2)
	{
		super(aOperand1, aOperand2);
	}
	
	/**
	 * Выполняет вычисление числового значения выражения.
	 * @return - Вычисленное знаение выражения.
	 */
	@Override
	public double interptet()
	{
		double result = Math.pow(mOperands[0].interptet(), mOperands[1].interptet());
		return result;
	}
	
}
