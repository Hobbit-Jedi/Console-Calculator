package ua.net.hj;

/**
 * Системы счисления.
 * Позволяет преобразовывать числа, представленные в разных системах счисления.
 * @author Hobbit Jedi
 */
public enum NumberSystems {
	BIN(2),  // Двоичная
	TRI(3),  // Троичная
	OCT(8),  // Восьмеричная
	DEC(10), // Десятичная
	HEX(16), // Шестнадцатеричная
	R36(36)  // Тридцатишестиричная
	;
	
	private final int mRadix; // Основание счистемы счисления.
	
	private NumberSystems(int aRadix)
	{
		if (aRadix >= 2 && aRadix <= 36)
		{
			mRadix = aRadix;
		}
		else
		{
			throw new UnknownError("При создании системы счисления указаное значение основания не попадает в диапазон [2..36].");
		}
	}
	
	/**
	 * Преобразовать число в строковое представление в данной системе счисления.
	 * @param aNumber - Число, которое нужно преобразовать в строку.
	 * @return - Строковое представление указанного числа в данной системе счисления.
	 */
	public String convertNumToString(double aNumber)
	{
		String result;
		if (mRadix == 10)
		{
			result = Double.toString(aNumber);
		}
		else
		{
			result = Integer.toString((int)aNumber, mRadix);
		}
		return result;
	}
	
	/**
	 * Преобразовать строковое представление числа в данной системе счисления в числовое значение.
	 * @param aString - Строковое представление числа в данной системе счисления.
	 * @return - Соответствующее числовое значение.
	 */
	public double convertStringToNum(String aString)
	{
		double result;
		if (mRadix == 10)
		{
			result = Double.valueOf(aString);
		}
		else
		{
			String clearString = aString.trim().toLowerCase();
			int length = clearString.length();
			if (length > 0)
			{
				result = 0d;
				for (int i = 0; i < length; i++)
				{
					char current = clearString.charAt(i);
					if (isDigit(current))
					{
						if (current >= '0' && current <= '9')
						{
							result += (current - '0') * Math.pow(mRadix, length-1-i);
						}
						else
						{
							result += (10 + current - 'a') * Math.pow(mRadix, length-1-i);
						}
					}
					else
					{
						throw new NumberFormatException("Некорректный символ в позиции " + i + ": " + clearString + ".");
					}
				}
			}
			else
			{
				throw new NumberFormatException("Попытка получить число из пустой строки.");
			}
		}
		return result;
	}
	
	/**
	 * Проверить, является ли указанный символ допустимым цифровым символом в данной системе счисления.
	 * @param aChar - Проверяемый символ.
	 * @return - Признак того, что указанный символ допустим при указании чисел в данной системе счисления.
	 */
	public boolean isDigit(char aChar)
	{
		boolean result;
		if (mRadix <= 10)
		{
			result = (aChar >= '0') && (aChar - '0' < mRadix);
		}
		else
		{
			result = (aChar >= '0' && aChar <= '9') || (aChar >= 'a' && aChar - 'a' < mRadix - 10);
		}
		return result;
	}
	
	/**
	 * Проверить, является ли указанный символ разделителем целой и дробной частей в данной системе счисления.
	 * @param aChar - Проверяемый символ.
	 * @return - Признак того, что указанный символ является разделителем целой и дробной частей в данной системе счисления.
	 */
	public boolean isSeparator(char aChar)
	{
		boolean result = (mRadix == 10 && aChar == '.');
		return result;
	}
}
