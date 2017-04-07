package ua.net.hj;

import java.util.ArrayList;

/**
 * Описывает преобразователь строки с математическим выражением в дерево вычисления этого выражения.
 * @author Hobbit Jedi
 */
public class ExpressionParser {
	private int mIndex;               // Индекс текущей позиции в строке.
	private String mExpressionString; // Разбираемая строка с математическим выражением (очищена от пробельных символов и приведена к нижнему регистру).
	private String mPrefix;           // Указанный в выражении префикс отображения (используется при формировании сообщений об ошибках).
	private int mPrefixLen;           // Длина префикса с двоеточием (используется при формировании сообщений об ошибках).
	
	/**
	 * Создать парсер математических выражений.
	 */
	public ExpressionParser()
	{
		mIndex = 0;
		mPrefixLen = 0;
		mExpressionString = null;
		mPrefix = null;
	}
	
	/**
	 * Разобрать строковое выражение в рендерер с деревом вычисления выражения.
	 * @param aExpressionString - Строка с математическим выражением, которое нужно разобрать.
	 * @return - Объект отображения (рендерер) результата вычисления указанного выражения.
	 * @throws ua.net.hj.CalculatorException - Если выражение для вычисления задано некорректно,
	 *                                         то вызвает это исключение.
	 */
	public ExpressionRenderer parse(String aExpressionString) throws CalculatorException
	{
			String input = aExpressionString.trim().toLowerCase().replaceAll("\\s+", ""); // Очистим строку от пробельных символов.
			int colonIndex = input.indexOf(":");
			if (colonIndex >= 0)
			{
				mPrefix = input.substring(0, colonIndex).trim();
				mPrefixLen = mPrefix.length() + 1;
				mExpressionString = input.substring(colonIndex + 1, input.length());
			}
			else
			{
				mPrefix = "";
				mPrefixLen = 0;
				mExpressionString = input;
			}
			NumberSystems numSys;
			switch(mPrefix)
			{
				case "bin":
					numSys = NumberSystems.BIN;
					break;
				case "tri":
					numSys = NumberSystems.TRI;
					break;
				case "oct":
					numSys = NumberSystems.OCT;
					break;
				case "hex":
					numSys = NumberSystems.HEX;
					break;
				case "36":
					numSys = NumberSystems.R36;
					break;
				case "dec":
				case "":
					numSys = NumberSystems.DEC;
					break;
				default:
					StringBuilder errorMessage = new StringBuilder();
					errorMessage.append("Указан некорректный префикс отображения '");
					errorMessage.append(mPrefix);
					errorMessage.append("' в позиции ");
					mIndex = -mPrefixLen; // Костыль, чтобы корректно отображалась позиция префикса.
					errorMessagePositionAndSourceExpressionAppend(errorMessage);
					throw new CalculatorException(errorMessage.toString());
			}
			mIndex = 0;
			ExpressionRenderer result = new ExpressionRenderer(numSys, parseExpression());
			if (mIndex < mExpressionString.length())
			{
				StringBuilder errorMessage = new StringBuilder();
				errorMessage.append("Указан некорректный символ '");
				errorMessage.append(mExpressionString.charAt(mIndex));
				errorMessage.append("' в позиции ");
				errorMessagePositionAndSourceExpressionAppend(errorMessage);
				throw new CalculatorException(errorMessage.toString());
			}
			return result;
	}
	
	/**
	 * Выделить из текущего места строки выражение.
	 * @return - Узел дерева вычисления разобранного выражения, соответствующий выделенному выражению.
	 * @throws ua.net.hj.CalculatorException - Если выражение для вычисления задано некорректно,
	 *                                         то вызвает это исключение.
	 */
	private Expression parseExpression() throws CalculatorException
	{
		Expression result;
		Expression rightOperand;
		if (mIndex < mExpressionString.length())
		{
			result = parseOperand1();
			while (mIndex < mExpressionString.length() && isOperatorBinary1(mExpressionString.charAt(mIndex)))
			{
				switch(mExpressionString.charAt(mIndex++))
				{
					case '+':
						rightOperand = parseOperand1();
						result = new OperatorAdd(result, rightOperand);
						break;
					case '-':
						rightOperand = parseOperand1();
						result = new OperatorSub(result, rightOperand);
						break;
				}
			}
			return result;
		}
		else
		{
			StringBuilder errorMessage = new StringBuilder();
			errorMessage.append("Неожиданный конец выражения при разборе операндов в позиции ");
			errorMessagePositionAndSourceExpressionAppend(errorMessage);
			throw new CalculatorException(errorMessage.toString());
		}
	}
	
	/**
	 * Выделить из текущего места строки операнды операторов с наинизшим первым приоритетом.
	 * @return - Узел дерева вычисления разобранного выражения, соответствующий выделенному операнду.
	 * @throws ua.net.hj.CalculatorException - Если выражение для вычисления задано некорректно,
	 *                                         то вызвает это исключение.
	 */
	private Expression parseOperand1() throws CalculatorException
	{
		Expression result;
		Expression rightOperand;
		if (mIndex < mExpressionString.length())
		{
			result = parseOperand2();
			while (mIndex < mExpressionString.length() && isOperatorBinary2(mExpressionString.charAt(mIndex)))
			{
				switch(mExpressionString.charAt(mIndex++))
				{
					case '*':
						rightOperand = parseOperand2();
						result = new OperatorMult(result, rightOperand);
						break;
					case '/':
						rightOperand = parseOperand2();
						result = new OperatorDiv(result, rightOperand);
						break;
				}
			}
			return result;
		}
		else
		{
			StringBuilder errorMessage = new StringBuilder();
			errorMessage.append("Неожиданный конец выражения при разборе операндов в позиции ");
			errorMessagePositionAndSourceExpressionAppend(errorMessage);
			throw new CalculatorException(errorMessage.toString());
		}
	}
	
	/**
	 * Выделить из текущего места строки операнды операторов со вторым приоритетом.
	 * @return - Узел дерева вычисления разобранного выражения, соответствующий выделенному операнду.
	 * @throws ua.net.hj.CalculatorException - Если выражение для вычисления задано некорректно,
	 *                                         то вызвает это исключение.
	 */
	private Expression parseOperand2() throws CalculatorException
	{
		Expression result;
		Expression rightOperand;
		if (mIndex < mExpressionString.length())
		{
			result = parseOperand3();
			while (mIndex < mExpressionString.length() && isOperatorBinary3(mExpressionString.charAt(mIndex)))
			{
				switch(mExpressionString.charAt(mIndex++))
				{
					case '^':
						rightOperand = parseOperand3();
						result = new OperatorPow(result, rightOperand);
						break;
				}
			}
			return result;
		}
		else
		{
			StringBuilder errorMessage = new StringBuilder();
			errorMessage.append("Неожиданный конец выражения при разборе операндов в позиции ");
			errorMessagePositionAndSourceExpressionAppend(errorMessage);
			throw new CalculatorException(errorMessage.toString());
		}
	}
	
	/**
	 * Выделить из текущего места строки операнды операторов с наивысшим третьим приоритетом.
	 * @return - Узел дерева вычисления разобранного выражения, соответствующий выделенному операнду.
	 * @throws ua.net.hj.CalculatorException - Если выражение для вычисления задано некорректно,
	 *                                         то вызвает это исключение.
	 */
	private Expression parseOperand3() throws CalculatorException
	{
		Expression result;
		if (mIndex < mExpressionString.length())
		{
			if (isOperatorUnary(mExpressionString.charAt(mIndex)))
			{
				mIndex++;
				result = new OperatorNegative(parseOperand3());
				return result;
			}
			else if (NumberSystems.DEC.isDigit(mExpressionString.charAt(mIndex)))
			{
				result = parseConst();
				return result;
			}
			else if (mExpressionString.charAt(mIndex) == '(')
			{
				mIndex++;
				result = parseExpression();
				if (mIndex < mExpressionString.length() && mExpressionString.charAt(mIndex) == ')')
				{
					mIndex++;
					return result;
				}
				else
				{
					StringBuilder errorMessage = new StringBuilder();
					errorMessage.append("Ожидается закрывающая круглая скобка в позиции ");
					errorMessagePositionAndSourceExpressionAppend(errorMessage);
					throw new CalculatorException(errorMessage.toString());
				}
			}
			else if (isLetter(mExpressionString.charAt(mIndex)))
			{
				result = parseFunction();
				return result;
			}
			else
			{
				StringBuilder errorMessage = new StringBuilder();
				errorMessage.append("Недопустимый символ '");
				errorMessage.append(mExpressionString.charAt(mIndex));
				errorMessage.append("' в позиции ");
				errorMessagePositionAndSourceExpressionAppend(errorMessage);
				throw new CalculatorException(errorMessage.toString());
			}
		}
		else
		{
			StringBuilder errorMessage = new StringBuilder();
			errorMessage.append("Неожиданный конец выражения при разборе операндов в позиции ");
			errorMessagePositionAndSourceExpressionAppend(errorMessage);
			throw new CalculatorException(errorMessage.toString());
		}
	}
	
	/**
	 * Выделить из текущего места строки функцию.
	 * @return - Узел дерева вычисления разобранного выражения, соответствующий выделенной функции.
	 * @throws ua.net.hj.CalculatorException - Если выражение для вычисления задано некорректно,
	 *                                         то вызвает это исключение.
	 */
	private Expression parseFunction() throws CalculatorException
	{
		int startPosition = mIndex;
		if (mIndex < mExpressionString.length() && isLetter(mExpressionString.charAt(mIndex)))
		{
			do
			{
				mIndex++;
			} while (mIndex < mExpressionString.length() && isLetter(mExpressionString.charAt(mIndex)));
			if (mIndex < mExpressionString.length() && mExpressionString.charAt(mIndex) == '(')
			{
				String functionName = mExpressionString.substring(startPosition, mIndex);
				mIndex++;
				Expression result = null;
				ArrayList<Expression> arguments;
				switch (functionName)
				{
					case "sqrt":
						arguments = parseArguments(functionName, 1);
						if (arguments.size() == 1)
						{
							result = new FunctionSqrt(arguments.get(0));
						}
						else
						{
							StringBuilder errorMessage = new StringBuilder();
							errorMessage.append("Недостаточно аргументов передано в функцию \"");
							errorMessage.append(functionName);
							errorMessage.append("\". Позиция ");
							errorMessagePositionAndSourceExpressionAppend(errorMessage);
							throw new CalculatorException(errorMessage.toString());
						}
						break;
					case "sin":
						arguments = parseArguments(functionName, 1);
						if (arguments.size() == 1)
						{
							result = new FunctionSin(arguments.get(0));
						}
						else
						{
							StringBuilder errorMessage = new StringBuilder();
							errorMessage.append("Недостаточно аргументов передано в функцию \"");
							errorMessage.append(functionName);
							errorMessage.append("\". Позиция ");
							errorMessagePositionAndSourceExpressionAppend(errorMessage);
							throw new CalculatorException(errorMessage.toString());
						}
						break;
					case "cos":
						arguments = parseArguments(functionName, 1);
						if (arguments.size() == 1)
						{
							result = new FunctionCos(arguments.get(0));
						}
						else
						{
							StringBuilder errorMessage = new StringBuilder();
							errorMessage.append("Недостаточно аргументов передано в функцию \"");
							errorMessage.append(functionName);
							errorMessage.append("\". Позиция ");
							errorMessagePositionAndSourceExpressionAppend(errorMessage);
							throw new CalculatorException(errorMessage.toString());
						}
						break;
					case "tg":
						arguments = parseArguments(functionName, 1);
						if (arguments.size() == 1)
						{
							result = new FunctionTg(arguments.get(0));
						}
						else
						{
							StringBuilder errorMessage = new StringBuilder();
							errorMessage.append("Недостаточно аргументов передано в функцию \"");
							errorMessage.append(functionName);
							errorMessage.append("\". Позиция ");
							errorMessagePositionAndSourceExpressionAppend(errorMessage);
							throw new CalculatorException(errorMessage.toString());
						}
						break;
					case "lg":
						arguments = parseArguments(functionName, 1);
						if (arguments.size() == 1)
						{
							result = new FunctionLg(arguments.get(0));
						}
						else
						{
							StringBuilder errorMessage = new StringBuilder();
							errorMessage.append("Недостаточно аргументов передано в функцию \"");
							errorMessage.append(functionName);
							errorMessage.append("\". Позиция ");
							errorMessagePositionAndSourceExpressionAppend(errorMessage);
							throw new CalculatorException(errorMessage.toString());
						}
						break;
					case "ln":
						arguments = parseArguments(functionName, 1);
						if (arguments.size() == 1)
						{
							result = new FunctionLn(arguments.get(0));
						}
						else
						{
							StringBuilder errorMessage = new StringBuilder();
							errorMessage.append("Недостаточно аргументов передано в функцию \"");
							errorMessage.append(functionName);
							errorMessage.append("\". Позиция ");
							errorMessagePositionAndSourceExpressionAppend(errorMessage);
							throw new CalculatorException(errorMessage.toString());
						}
						break;
					case "log":
						arguments = parseArguments(functionName, 2);
						if (arguments.size() == 2)
						{
							result = new FunctionLog(arguments.get(0), arguments.get(1));
						}
						else
						{
							StringBuilder errorMessage = new StringBuilder();
							errorMessage.append("Недостаточно аргументов передано в функцию \"");
							errorMessage.append(functionName);
							errorMessage.append("\". Позиция ");
							errorMessagePositionAndSourceExpressionAppend(errorMessage);
							throw new CalculatorException(errorMessage.toString());
						}
						break;
					case "min":
						arguments = parseArguments(functionName, -1);
						if (arguments.size() > 0)
						{
							result = new FunctionMin(arguments.toArray(new Expression[0]));
						}
						else
						{
							StringBuilder errorMessage = new StringBuilder();
							errorMessage.append("Недостаточно аргументов передано в функцию \"");
							errorMessage.append(functionName);
							errorMessage.append("\". Позиция ");
							errorMessagePositionAndSourceExpressionAppend(errorMessage);
							throw new CalculatorException(errorMessage.toString());
						}
						break;
					case "max":
						arguments = parseArguments(functionName, -1);
						if (arguments.size() > 0)
						{
							result = new FunctionMax(arguments.toArray(new Expression[0]));
						}
						else
						{
							StringBuilder errorMessage = new StringBuilder();
							errorMessage.append("Недостаточно аргументов передано в функцию \"");
							errorMessage.append(functionName);
							errorMessage.append("\". Позиция ");
							errorMessagePositionAndSourceExpressionAppend(errorMessage);
							throw new CalculatorException(errorMessage.toString());
						}
						break;
					default:
						StringBuilder errorMessage = new StringBuilder();
						errorMessage.append("Неизвестное имя функции \"");
						errorMessage.append(functionName);
						errorMessage.append("\". Позиция ");
						errorMessagePositionAndSourceExpressionAppend(errorMessage);
						throw new CalculatorException(errorMessage.toString());
				}
				if (mIndex < mExpressionString.length() && mExpressionString.charAt(mIndex) == ')')
				{
					mIndex++;
					return result;
				}
				else
				{
					StringBuilder errorMessage = new StringBuilder();
					errorMessage.append("Ожидается закрывающая круглая скобка при вызове функции \"");
					errorMessage.append(functionName);
					errorMessage.append("\" в позиции ");
					errorMessagePositionAndSourceExpressionAppend(errorMessage);
					throw new CalculatorException(errorMessage.toString());
				}
			}
			else
			{
				StringBuilder errorMessage = new StringBuilder();
				errorMessage.append("Ожидается открывающая круглая скобка при вызове функции \"");
				errorMessage.append(mExpressionString.substring(startPosition, mIndex));
				errorMessage.append("\" в позиции ");
				errorMessagePositionAndSourceExpressionAppend(errorMessage);
				throw new CalculatorException(errorMessage.toString());
			}
		}
		else
		{
			StringBuilder errorMessage = new StringBuilder();
			errorMessage.append("Недопустимый символ '");
			errorMessage.append(mExpressionString.charAt(mIndex));
			errorMessage.append("' в имени функции. Позиция ");
			errorMessagePositionAndSourceExpressionAppend(errorMessage);
			throw new CalculatorException(errorMessage.toString());
		}
	}
	
	/**
	 * Выделить из текущего места строки список аргументов функции.
	 * @param aFunctionName - Имя функции, аргументы которой читаются.
	 * @param aNumOfArguments - Ожидаемое количество аргументов.
	 *                          -1, если количество аргументов заранее не известно.
	 * @return - Массив узлов дерева вычисления разобранного выражения, соответствующих аргументам функции.
	 * @throws ua.net.hj.CalculatorException - Если выражение для вычисления задано некорректно,
	 *                                         то вызвает это исключение.
	 */
	private ArrayList<Expression> parseArguments(String aFunctionName, int aNumOfArguments) throws CalculatorException
	{
		ArrayList<Expression> result = new ArrayList<>();
		if (aNumOfArguments == 0)
		{
			if (mIndex < mExpressionString.length() && mExpressionString.charAt(mIndex) == ')')
			{
				// Дошли до конца списка аргументов - выходим из рекурсии.
			}
			else
			{
				StringBuilder errorMessage = new StringBuilder();
				errorMessage.append("Ожидается закрывающая круглая скобка при вызове функции \"");
				errorMessage.append(aFunctionName);
				errorMessage.append("\" в позиции ");
				errorMessagePositionAndSourceExpressionAppend(errorMessage);
				throw new CalculatorException(errorMessage.toString());
			}
		}
		else
		{
			Expression nextArgument = parseExpression();
			if	(mIndex < mExpressionString.length())
			{
				switch (mExpressionString.charAt(mIndex))
				{
					case ')':
						result.add(nextArgument);
						break;
					case ',':
						mIndex++;
						result.add(nextArgument);
						ArrayList<Expression> lastArguments = parseArguments(aFunctionName, (aNumOfArguments > 0) ? aNumOfArguments - 1 : aNumOfArguments);
						result.addAll(lastArguments);
						break;
					default:
						StringBuilder errorMessage = new StringBuilder();
						errorMessage.append("Недопустимый символ '");
						errorMessage.append(mExpressionString.charAt(mIndex));
						errorMessage.append("' при разборе аргументов функции \"");
						errorMessage.append(aFunctionName);
						errorMessage.append("\" в позиции ");
						errorMessagePositionAndSourceExpressionAppend(errorMessage);
						throw new CalculatorException(errorMessage.toString());
				}
			}
			else
			{
				StringBuilder errorMessage = new StringBuilder();
				errorMessage.append("Неожиданный конец выражения при разборе аргументов функции \"");
				errorMessage.append(aFunctionName);
				errorMessage.append("\" в позиции ");
				errorMessagePositionAndSourceExpressionAppend(errorMessage);
				throw new CalculatorException(errorMessage.toString());
			}
		}
		return result;
	}
	
	/**
	 * Выделить из текущего места строки константу.
	 * @return - Узел дерева вычисления разобранного выражения, соответствующий выделенной константе.
	 * @throws ua.net.hj.CalculatorException - Если выражение для вычисления задано некорректно,
	 *                                         то вызвает это исключение.
	 */
	private Expression parseConst() throws CalculatorException
	{
		NumberSystems numSys = parseNumSys();
		int startPosition = mIndex;
		if (mIndex < mExpressionString.length() && numSys.isDigit(mExpressionString.charAt(mIndex)))
		{
			do
			{
				mIndex++;
			} while (mIndex < mExpressionString.length() && numSys.isDigit(mExpressionString.charAt(mIndex)));
			if (mIndex < mExpressionString.length() && numSys.isSeparator(mExpressionString.charAt(mIndex)))
			{
				mIndex++;
				if (mIndex < mExpressionString.length() && numSys.isDigit(mExpressionString.charAt(mIndex)))
				{
					do
					{
						mIndex++;
					} while (mIndex < mExpressionString.length() && numSys.isDigit(mExpressionString.charAt(mIndex)));
				}
			}
			Expression result = new Const(numSys.convertStringToNum(mExpressionString.substring(startPosition, mIndex)));
			return result;
		}
		else
		{
			StringBuilder errorMessage = new StringBuilder();
			errorMessage.append("Недопустимая цифра '");
			errorMessage.append(mExpressionString.charAt(mIndex));
			errorMessage.append("' в позиции ");
			errorMessagePositionAndSourceExpressionAppend(errorMessage);
			throw new CalculatorException(errorMessage.toString());
		}
	}
	
	/**
	 * Выделить из текущего места строки маркер системы счисления (если он указан).
	 * @return - Соответствующая маркеру система счисления.
	 */
	private NumberSystems parseNumSys()
	{
		NumberSystems result = NumberSystems.DEC;
		if (mIndex < mExpressionString.length() && mExpressionString.charAt(mIndex) == '0')
		{
			if (mIndex + 1 < mExpressionString.length())
			{
				switch(mExpressionString.charAt(mIndex + 1))
				{
					case 'b':
						result = NumberSystems.BIN;
						mIndex += 2;
						break;
					case 't':
						result = NumberSystems.TRI;
						mIndex += 2;
						break;
					case 'x':
						result = NumberSystems.HEX;
						mIndex += 2;
						break;
					case 'z':
						result = NumberSystems.R36;
						mIndex += 2;
						break;
					case '0':
					case '1':
					case '2':
					case '3':
					case '4':
					case '5':
					case '6':
					case '7':
						result = NumberSystems.OCT;
						mIndex += 1;
						break;
				}
			}
		}
		return result;
	}
	
	/**
	 * Проверить, что указанный символ относится к унарным операторам.
	 * @return - Признак того, что указанный символ является унарным оператором.
	 */
	private boolean isOperatorUnary(char aChar)
	{
		return (aChar == '+' || aChar == '-');
	}
	
	/**
	 * Проверить, что указанный символ относится к бинарным операторам с приоритетом 1.
	 * @return - Признак того, что указанный символ является бинарным оператором с приоритетом 1.
	 */
	private boolean isOperatorBinary1(char aChar)
	{
		return (aChar == '+' || aChar == '-');
	}
	
	/**
	 * Проверить, что указанный символ относится к бинарным операторам с приоритетом 2.
	 * @return - Признак того, что указанный символ является бинарным оператором с приоритетом 2.
	 */
	private boolean isOperatorBinary2(char aChar)
	{
		return (aChar == '*' || aChar == '/');
	}
	
	/**
	 * Проверить, что указанный символ относится к бинарным операторам с приоритетом 3.
	 * @return - Признак того, что указанный символ является бинарным оператором с приоритетом 3.
	 */
	private boolean isOperatorBinary3(char aChar)
	{
		return (aChar == '^');
	}
	
	/**
	 * Проверить, что указанный символ относится к буквам.
	 * @return - Признак того, что указанный символ является буквой.
	 */
	private boolean isLetter(char aChar)
	{
		return (aChar >= 'a' && aChar <= 'z');
	}
	
	/**
	 * Добавить к указанному сообщению об ошибке номер позиции и исходное выражение.
	 * @param aErrorMessage - Сообщение об ошибке, к которому добавляем информацию.
	 */
	private void errorMessagePositionAndSourceExpressionAppend(StringBuilder aErrorMessage)
	{
		aErrorMessage.append(mIndex + mPrefixLen + 1);
		aErrorMessage.append(":\n");
		if (mPrefixLen > 0)
		{
			aErrorMessage.append(mPrefix);
			aErrorMessage.append(":");
		}
		aErrorMessage.append(mExpressionString);
	}
	
}
