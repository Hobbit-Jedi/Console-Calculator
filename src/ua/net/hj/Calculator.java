package ua.net.hj;

/**
 * Вычисление записанных в строку математических выражений.
 * @author Hobbit Jedi
 */
public class Calculator {
	
	/**
	 * @param args the command line arguments
	 */
	public static void main(String[] args) {
		if (args != null && args.length > 0 && args[0] != null && args[0].length() > 0)
		{
			try
			{
				ExpressionParser parser = new ExpressionParser();
				ExpressionRenderer result = parser.parse(args[0]);
				System.out.println(args[0] + " = " + result.render());
			}
			catch (CalculatorException e)
			{
				System.out.println("Ошибка вычисления: " + e.getMessage());
			}
		}
		else
		{
			System.out.println("В параметрах программы нужно передать выражение для вычисления.");
			System.out.println("Регистр символов значения не имеет.");
			System.out.println("------------------------------------");
			System.out.println("Допустимые лексемы:");
			System.out.println();
			System.out.println("Представление чисел:");
			System.out.println("\tXXX - Десятичное целое число (например, 123).");
			System.out.println("\tXXX.XXX - Десятичное дробное число (например, 123.4567).");
			System.out.println("\t0xXXXXXXXX - Шестрадцатеричное число (начинается с нуля за которым идет латинская буква X) (например, 0x1A).");
			System.out.println("\t0bXXXXXXXX - Двоичное число (начинается с нуля за которым идет латинская буква B) (например, 0b0110).");
			System.out.println("\t0XXX - Восьмеричное число (начинается с нуля) (например, 0123).");
			System.out.println("\t0tXXXXXXXX - Троичное число (начинается с нуля за которым идет латинская буква T) (например, 0t0120).");
			System.out.println("\t0zXXX - Тридцатишестиричное число (начинается с нуля за которым идет латинская буква Z) (например, 0z1q8x).");
			System.out.println();
			System.out.println("Операторы:");
			System.out.println("\t+ - Сложение двух операндов.");
			System.out.println("\t- - Вычитание правого операнда из левого операнда.");
			System.out.println("\t* - Умножение двух операндов.");
			System.out.println("\t/ - Деление левого операнда на правый операнд.");
			System.out.println("\t^ - Возведение левого операнда в степень правого операнда.");
			System.out.println();
			System.out.println("Математические функции:");
			System.out.println("\tsqrt(x) - Извлечение из x квадратного корня.");
			System.out.println("\tsin(x) - Синус x.");
			System.out.println("\tcos(x) - Косинус x.");
			System.out.println("\ttg(x) - Тангенс x.");
			System.out.println("\tlg(x) - Логарифм десятичный от x.");
			System.out.println("\tln(x) - Логарифм натуральный от x.");
			System.out.println("\tlog(x,y) - Логарифм от x по основанию y.");
			System.out.println("\tmin(x1,x2,...,xn) - Минимальное из множества значений.");
			System.out.println("\tmax(x1,x2,...,xn) - Максимальное из множества значений.");
			System.out.println();
			System.out.println("Префиксы отображения (допускаются только в начале выражения):");
			System.out.println("\tdec:expression - [по умолчанию] Отобразить результат в десятичной системе счисления.");
			System.out.println("\thex:expression - Отобразить результат в шестнадцатеричной системе счисления.");
			System.out.println("\toct:expression - Отобразить результат в восьмеричной системе счисления.");
			System.out.println("\tbin:expression - Отобразить результат в двоичной системе счисления.");
			System.out.println("\ttri:expression - Отобразить результат в троичной системе счисления.");
			System.out.println("\t36:expression - Отобразить результат в тридцатишестиричной системе счисления.");
			System.out.println();
			System.out.println("Примеры:");
			System.out.println("\t1000+358 => 1358.0");
			System.out.println("\tdec: 1000+358 => 1358.0");
			System.out.println("\tbin: 1000+358 => 10101001110");
			System.out.println("\thex: 1000+358 => 54e");
			System.out.println("\toct: 1000+358 => 2516");
			System.out.println("\ttri: 1000+358 => 1212022");
			System.out.println("\t36 : 1000+358 => 11q");
			System.out.println("\tsqrt((10 + 20)*30) => 30");
		}
	}
	
}
