package by.epam.l05.homework;

import java.time.Month;
import java.time.format.TextStyle;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/*Используя код листинга 4 напишите приложение, выводящее на
консоль календарь на год.*/

// корректно эту задачу решать отдельным классом, т.к. решение можно будет переиспользовать
public class HW01calendar
{

	public static void main(String[] args)
	{
		int Columns = 3;// кол-во колонок для вывода календаря
		int year = 2017;// Год
		String indent = "  ";// отступ между месяцами

		String y[][] = new String[12][];// 12 месяцев

		// Заполняем массив
		for (int i = 0; i < y.length; i++)
		{
			y[i] = getMonthText(year, i);
		}

		int offset = 0;// итерационная переменная для вывода столбцами

		// выводим на экран с нужным форматированием
		do
		{
			for (int i = 0; i < y[1].length; i++)
			{
				for (int j = offset; j < Columns + offset; j++)
				{
					System.out.print(y[j][i]);
					System.out.print(indent);
				}
				System.out.println();

			}
			offset += Columns;
			System.out.println();
		}
		while (offset < 12);
	};

	/*
	 * исправляем код из примера, что б работал правильно, убираем захардкоданые
	 * начала и конец недели (т.к. это зависит от локали) - используем
	 * FirstDayOfWeek/LastDayOfWeek делаем вывод не в System.out, а в массив
	 * String[] для последующего форматирования
	 * 
	 */

	private static String[] getMonthText(int year, int month)
	{
		final int LineLength = 21;// длина строки, нужна для выравнивания
		// GregorianCalendar d = new GregorianCalendar();

		// в месяце может быть максимум 6 недель (4 полных и 2 неполных)
		// +1 строка заголовок, +1 имя месяца
		String[] result = { "", "", "", "", "", "", "", "" };

		int result_item = 0;// текущий элемент

		// типа бест практиз, скорее всего вернет GregorianCalendar
		Calendar d = Calendar.getInstance();

		// текущий день и месяц, нужно что б отметить * в календаре
		int current_day = d.get(Calendar.DAY_OF_MONTH);
		int current_month = d.get(Calendar.MONTH);
		// int month = d.get(Calendar.MONTH);

		// код первого дня недели
		int FirstDayOfWeek = d.getFirstDayOfWeek();

		// сбрасываем дату на начало года и сохраняем
		d.set(year, month, 1);
		Date FirstDateOfYear = d.getTime();

		// сохраняем код текущего дня недели
		int weekday = d.get(Calendar.DAY_OF_WEEK);

		// устанавливаем первый день текущей недели и сохраняем дату
		d.set(Calendar.DAY_OF_WEEK, FirstDayOfWeek);
		Date FirstDateOfFirstWeek = d.getTime();

		// добавляем 6 дней для получения последнего дня недели и
		// сохраняем его код
		d.add(Calendar.DAY_OF_MONTH, 6);

		int LastDayOfWeek = d.get(Calendar.DAY_OF_WEEK);

		// разница в днях между первым днем недели и текущим
		// сложность обусловлена дурацкой нумерацией дней в JAVA
		// (суббота=6,воскресение=1, понедельник=2)
		int diff = (int) (FirstDateOfYear.getTime() - FirstDateOfFirstWeek.getTime()) / 1000 / 3600 / 24;

		// получаем имя месяца на русском

		Locale loc = Locale.forLanguageTag("ru");
		String m = Month.of(month + 1).getDisplayName(TextStyle.FULL_STANDALONE, loc);

		result[result_item]=expand(m, LineLength - m.length());
		
		result_item++;

		// System.out.println("Пн Вт Ср Чт Пт Сб Вс");
		result[result_item] = "Пн Вт Ср Чт Пт Сб Вс ";
		result_item++;

		// все константы подсчитали, ставим дату на начало месяца и погнали!
		d.set(year, month, 1);

		// вместо цикла юзаем такую вот красоту для определения отступа
		// каждая колонка у нас 3 пробела, поэтому diff*3
		String prefix = new String(new char[diff * 3]).replace('\0', ' ');
		result[result_item] += prefix;

		/*
		 * for (int i = 0; i < diff; i++) { // System.out.print(" ");
		 * result[result_item] += "   "; }
		 */

		do
		{
			int day = d.get(Calendar.DAY_OF_MONTH);
			if (day < 10)
			{
				// System.out.print(" ");
				result[result_item] += " ";
			}

			// System.out.print(day);
			result[result_item] += day;

			if (current_month == month && day == current_day)
			{
				// System.out.print("*");
				result[result_item] += "*";
			}
			else
			{
				// System.out.print(" ");
				result[result_item] += " ";
			}

			if (weekday == LastDayOfWeek)
			{
				// System.out.println();

				result_item++;
			}

			d.add(Calendar.DAY_OF_MONTH, 1);
			weekday = d.get(Calendar.DAY_OF_WEEK);
		}
		while (d.get(Calendar.MONTH) == month);

		// дополняем строку пробелами
		int length = LineLength - result[result_item].length();

		if (length > 0)
		{
			result[result_item] = expand(result[result_item], length);
			
		}

		// две последние строки забиваем пробелами, потому что они могут быть
		// пустыми

		if (result[6].length() == 0)
		{
			result[6] = expand("", LineLength);
		}
		if (result[7].length() == 0)
		{
			result[7] = expand("", LineLength);
		}

		/*
		 * if (weekday != Calendar.SUNDAY) { System.out.println();
		 * result_item++; }
		 */

		return result;
	}

	private static String expand(String str, int len)
	{
		String result;
		// няшный код на генерацию пробелов через чар-массив-реплейс ♥
		String tail = new String(new char[len]).replace('\0', ' ');
		result = str + tail;

		return result;
	}
}
