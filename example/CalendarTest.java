package by.epam.l05.example;


import java.util.Calendar;
//import java.text.DateFormatSymbols;

public class CalendarTest
{
	public static void main(String[] args)
	{
		//GregorianCalendar d = new GregorianCalendar();
		String r="";
		
		Calendar d = Calendar.getInstance();
		
		int today = d.get(Calendar.DAY_OF_MONTH);
		int month = d.get(Calendar.MONTH);
		
		int FirstDayOfWeek = d.getFirstDayOfWeek();
		
		d.set(Calendar.DAY_OF_WEEK, FirstDayOfWeek);
		d.add(Calendar.DAY_OF_MONTH, 6);
		
		int LastDayOfWeek=d.get(Calendar.DAY_OF_WEEK);
		 		
		d.set(Calendar.DAY_OF_MONTH, 1);		
			
		int weekday = d.get(Calendar.DAY_OF_WEEK);

		System.out.println("Пн Вт Ср Чт Пт Сб Вс");
		r+="Пн Вт Ср Чт Пт Сб Вс";
		r+="\n";

		for (int i = FirstDayOfWeek; i < weekday; i++)
		{
			System.out.print("   ");
			r+="   ";
		}

		do
		{
			int day = d.get(Calendar.DAY_OF_MONTH);
			if (day < 10)
			{
				System.out.print(" ");
				r+=" ";
			}

			System.out.print(day);
			r+=day;

			if (day == today)
			{
				System.out.print("*");
				r+="*";
			}
			else
			{
				System.out.print(" ");
				r+=" ";
			}

			if (weekday == LastDayOfWeek)
			{
				System.out.println();
				r+="\n";
			}

			d.add(Calendar.DAY_OF_MONTH, 1);
			weekday = d.get(Calendar.DAY_OF_WEEK);
		}
		while (d.get(Calendar.MONTH) == month);

		if (weekday != Calendar.SUNDAY)
		{
			System.out.println();
			r+="\n";
		}
		System.out.println();
		System.out.println(r);
	}
}
