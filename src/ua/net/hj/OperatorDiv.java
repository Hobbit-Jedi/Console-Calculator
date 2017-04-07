package ua.net.hj;

/**
 * Описывает оператор деления.
 * x/y - x делится на y.
 * @author Hobbit Jedi
 */
public class OperatorDiv extends OperatorBinary {
	
	/**
	 * Создать оператор деления.
	 * @param aOperand1 - Выражение, соответствующее первому (левому) операнду оператора.
	 * @param aOperand2 - Выражение, соответствующее второму (правому) операнду оператора.
	 */
	public OperatorDiv(Expression aOperand1, Expression aOperand2)
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
		double result = mOperands[0].interptet() / mOperands[1].interptet();
		return result;
	}
	
}
