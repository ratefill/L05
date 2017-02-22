package by.epam.l05.example;

public class MyString
{
	public static void main(String[] args)
	{
		String str = "MINSK";
		System.out.println("In main - before call function -str=" + str);
		changeString(str);
		System.out.println("In main - after call function - str=" + str);
	}

	public static void changeString(String s)
	{
		System.out.println("In changeString - before change -s=" + s);
		s = s + " end.";
		System.out.println("In changeString - before change -s=" + s);
	}
}
