# Console-Calculator
Консольный калькулятор (дополнительная практическая задача на Java курсе Prometheus)

В параметрах программы нужно передать выражение для вычисления.
Регистр символов и наличие\отсутствие пробельных символов - значения не имеет.
------------------------------------
Допустимые лексемы:

Представление чисел:
	XXX - Десятичное целое число (например, 123).
	XXX.XXX - Десятичное дробное число (например, 123.4567).
	0xXXXXXXXX - Шестрадцатеричное число (начинается с нуля за которым идет латинская буква X) (например, 0x1A).
	0bXXXXXXXX - Двоичное число (начинается с нуля за которым идет латинская буква B) (например, 0b0110).
	0XXX - Восьмеричное число (начинается с нуля) (например, 0123).
	0tXXXXXXXX - Троичное число (начинается с нуля за которым идет латинская буква T) (например, 0t0120).
	0zXXX - Тридцатишестиричное число (начинается с нуля за которым идет латинская буква Z) (например, 0z1q8x).

Операторы:
	+ - Сложение двух операндов.
	- - Вычитание правого операнда из левого операнда.
	* - Умножение двух операндов.
	/ - Деление левого операнда на правый операнд.
	^ - Возведение левого операнда в степень правого операнда.

Математические функции:
	sqrt(x) - Извлечение из x квадратного корня.
	sin(x) - Синус x.
	cos(x) - Косинус x.
	tg(x) - Тангенс x.
	lg(x) - Логарифм десятичный от x.
	ln(x) - Логарифм натуральный от x.
	log(x,y) - Логарифм от x по основанию y.
	min(x1,x2,...,xn) - Минимальное из множества значений.
	max(x1,x2,...,xn) - Максимальное из множества значений.

Префиксы отображения (допускаются только в начале выражения):
	dec:expression - [по умолчанию] Отобразить результат в десятичной системе счисления.
	hex:expression - Отобразить результат в шестнадцатеричной системе счисления.
	oct:expression - Отобразить результат в восьмеричной системе счисления.
	bin:expression - Отобразить результат в двоичной системе счисления.
	tri:expression - Отобразить результат в троичной системе счисления.
	36:expression - Отобразить результат в тридцатишестиричной системе счисления.

Примеры:
	1000+358 => 1358.0
	dec: 1000+358 => 1358.0
	bin: 1000+358 => 10101001110
	hex: 1000+358 => 54e
	oct: 1000+358 => 2516
	tri: 1000+358 => 1212022
	36 : 1000+358 => 11q
	sqrt((10 + 20)*30) => 30.0
	sqrt((10 + 20)*30) + sqrt(sqrt((10 + 20)*30)/(1+2)+6) => 34.0
	10 + 3 - (8+4)*((15-6) - 13*sin(8^2)) => 48.52406195869935
	-3*-5 => 15.0