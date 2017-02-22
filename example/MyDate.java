package by.epam.l05.example;

import java.util.Date;

public class MyDate
{
	@SuppressWarnings("deprecation")
	public static void main(String[] args)
	{
		// 1
		Date myDate1, myDate2;
		myDate1 = new Date(2008 - 1900, 4, 2);
		myDate2 = new Date(1996 - 1900, 1, 22);
		System.out.println("1-----------------------");
		System.out.println("myDate1 = " + myDate1);
		System.out.println("myDate2 = " + myDate2);
		// 2
		myDate1 = new Date(2089 - 1900, 6, 12);
		System.out.println("2-----------------------");
		System.out.println("myDate1 = " + myDate1);
		System.out.println("myDate2 = " + myDate2);
		// 3
		myDate2 = myDate1;
		System.out.println("3-----------------------");
		System.out.println("myDate1 = " + myDate1);
		System.out.println("myDate2 = " + myDate2);
		// 4
		myDate2 = null;
		System.out.println("4------------------------");
		System.out.println("myDate1 = " + myDate1);
		System.out.println("myDate2 = " + myDate2);
	}
}