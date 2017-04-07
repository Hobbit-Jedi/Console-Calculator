package ua.net.hj;

/**
 * Описывает объект отображения результата выражения.
 * @author Hobbit Jedi
 */
public class ExpressionRenderer {
	private final NumberSystems mNumSys;  // Система счисления, в которой необходимо отображать результат выражения.
	private final Expression mExpression; // Выражение, к результату которого нужно применить префикс.
	
	/**
	 * Создать рендерер (объект отображения результата).
	 * @param aNumSys - Система счисления, в которой необходимо отображать результат выражения.
	 * @param aExpression - Выражение, к результату которого нужно применить префикс.
	 */
	public ExpressionRenderer(NumberSystems aNumSys, Expression aExpression)
	{
		mNumSys = aNumSys;
		mExpression = aExpression;
	}
		
	/**
	 * Рассчитать и отобразить результат.
	 * @return - Строковое представление результата хранимого в рендерере выражения,
	 *           в соответствии с указанными в рендерере параметрами отображения.
	 */
	public String render()
	{
		double evaluationResult = mExpression.interptet();
		return mNumSys.convertNumToString(evaluationResult);
	}
	
	/**
	 * Отобразить результат любого указанного выражения,
	 * в соответствии с указанными в рендерере параметрами.
	 * @param aExpression - Выражение, результат которого нужно отобразить.
	 * @return - Строковое представление результата указанного выражения,
	 *           в соответствии с указанными в рендерере параметрами отображения.
	 */
	public String render(Expression aExpression)
	{
		double evaluationResult = aExpression.interptet();
		return mNumSys.convertNumToString(evaluationResult);
	}
	
	/**
	 * Получить хранимое в рендерере выражение.
	 * @return - Хранимое в рендерере выражение.
	 */
	public Expression getExpression()
	{
		return mExpression;
	}
	
}
