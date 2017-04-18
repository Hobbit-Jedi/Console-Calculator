package ua.net.hj;

/**
 * Выполняет тестирование класса Calculator.
 * @author Hobbit Jedi
 */
public class CalculatorTester {
	
	public static void main(String[] args) {
		doTest(null, "Подсказка по пользованию программой.");
		doTest("10", "10.0");
		doTest("dec:1358", "1358.0");
		doTest("bin : 1358", "10101001110");
		doTest(" hex: 1358", "54e");
		doTest("oct :1358", "2516");
		doTest(" tri: 1358", "1212022");
		doTest(" 36 : 1358", "11q");
		doTest("15.2698", "15.2698");
		doTest("33.", "33.0");
		doTest("0b10101001110", "1358.0");
		doTest("0t1212022", "1358.0");
		doTest("02516", "1358.0");
		doTest("0x54e", "1358.0");
		doTest("0z11q", "1358.0");
		doTest("sqrt(25)", "5.0");
		doTest("sin(1.5707963267948966192313216916398)", "1.0"); // Pi/2
		doTest("lg(10)", "1.0");
		doTest("ln(148.41315910257660342111558004055)", "5.0");
		doTest("log(256,2)", "8.0");
		doTest("min(156,2,14,835)", "2.0");
		doTest("max(156,2,14,835)", "835.0");
		doTest("(((10)))", "10");
		doTest("asdf", "Ошибка вычисления");
		doTest("as:df", "Ошибка вычисления");
		doTest("10+20", "30.0");
		doTest("1000+358", "1358.0");
		doTest("dec:1000+358", "1358.0");
		doTest("bin : \t1000\n\n\n\r+358", "10101001110");
		doTest(" hex: 1000+358", "54e");
		doTest("oct :1000+358", "2516");
		doTest(" tri: 1000+358", "1212022");
		doTest(" 36 : 1000+358", "11q");
		doTest("sqrt((10 + 20)*30)", "30.0");
		doTest("sqrt((10 + 20)*30) + sqrt(sqrt((10 + 20)*30)/(1+2)+6)", "34.0");
		doTest("10 + 3 - (8+4)*((15-6) - 13*sin(8^2))", "48.524061958699346602839710189251");
		doTest("2^3^4", "4096.0");
		doTest("(2^3)^4", "4096.0");
		doTest("2^(3^4)", "2417851639229258349412352.0");
		doTest("-3*-5", "15.0");
		doTest("max((6 - 3)^4, sqrt(64 * 64), 56 + 48 / 0.333)", "200.14414414414415");
		doTest("min(9, 4, 7, sqrt(64), log(8,2), 6)", "3.0");
	}
	
	public static void doTest(String aInputExpression, String aResult)
	{
		System.out.println();
		Calculator.main(new String[]{aInputExpression});
		System.out.println("Ожидаемый результат = " + aResult);
	}
}
