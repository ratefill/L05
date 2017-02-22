package by.epam.l05.homework;

import java.util.Date;


/*
Создать класс “Записная книжка”. Предусмотреть возможность работы
с произвольным числом записей, поиска записи по какому-било
признаку (например, по фамилии, дате рождения или номеру телефона),
добавления и удаления записей, сортировки по разным полям.
 */

public class HW02notebook
{
	public static void main(String[] args)
	{
		Notebook n = new Notebook();
		n.Add("Маша", "123");
		n.Add("Лена", "103");
		n.Add("Света", "023");
		n.Add("Оля", "120");
		n.Add("Юля", "163");
		n.Add("Катя");
		n.Add("Саша", "523");
		n.Add("Дима", "641");
		n.Add("Коля", "907");
		n.Add("Слава", "323");
		n.Add("Слава");
		n.print();// печать всей книжки
		n.del("Саша");
		n.del("Дима");
		n.del("Коля");
		n.del("Слава");
		System.out.println();
		n.print();// печать всей книжки после удаления
		System.out.println();
		n.findPhone("Юля");
		n.findPhone("Слава");// поиск несуществующего элемента
		System.out.println();
		System.out.println();
		System.out.println("Без сортировки");
		n.print();
		System.out.println();
		System.out.println("Сортировка по имени");
		n.sortByName();
		n.print();
		System.out.println();

	}
}

class Notebook
{
	private int extendSize = 3;
	private int lastfree = 0;
	private int length;
	private NotebookRecord[] list;

	public Notebook()
	{
		super();
		this.length = 0;
		this.list = new NotebookRecord[extendSize];
	}

	public NotebookRecord Add(String name, String phone)
	{
		NotebookRecord result;
		int index = find(name);
		if (index == -1)
		{
			result = new NotebookRecord(name, phone);
			resizeList();
			list[lastfree] = result;
			lastfree++;
			length++;
		}
		else
		{
			result = list[index];
		}
		return result;
	}

	public NotebookRecord Add(String name)
	{
		NotebookRecord result = Add(name, "");
		return result;
	}

	public void del(String name)
	{
		int index = find(name);
		if (index != -1)
		{
			list[index] = null;
			length--;
		}
	}

	private int find(String name)
	{
		int result = -1;
		for (int i = 0; i < list.length; i++)
		{
			if (list[i] != null && list[i].getName() == name)
			{
				result = i;
				break;
			}
		}
		return result;
	}

	public void findPhone(String name)
	{
		int index = find(name);
		if (index != -1)
		{
			print(list[index]);
		}
		else
		{
			System.out.println("Запись не найдена");
		}

	}

	public void print(NotebookRecord record)
	{
		System.out.println(record.toString());
	}

	public void print()
	{
		for (int i = 0; i < list.length; i++)
		{
			if (list[i] != null)
			{
				print(list[i]);
			}
		}
	}

	public void sortByName()
	{
		int len = length + getExtendSize();
		shrinkList(len);
		// пузырек
		for (int i = length - 1; i > 0; i--)
		{
			for (int j = 0; j < i; j++)
			{
				if (list[j].getName().compareToIgnoreCase(list[j + 1].getName()) > 0)
				{
					NotebookRecord tmp = list[j];
					list[j] = list[j + 1];
					list[j + 1] = tmp;
				}
			}
		}
	}

	private void resizeList()
	{
		int len = length + getExtendSize();
		if (list.length > len || lastfree == list.length)
		{
			list = shrinkList(len);
		}
	}

	private NotebookRecord[] shrinkList(int len)
	{
		NotebookRecord[] list_new = new NotebookRecord[len];
		lastfree = list.length;
		// копируем данные из старого массива в новый
		for (int i = 0, j = 0; i < list.length; i++)
		{
			if (list[i] != null)
			{
				list_new[j] = list[i];
				j++;
			}
		}
		return list_new;
	}

	private int getExtendSize()
	{
		int result;

		result = length / 10;
		if (result < extendSize)
		{
			result = extendSize;
		}

		return result;
	}
};

//////////////////////////////////////////////////////////////////////
//////////////////////////////////////////////////////////////////////
//////////////////////////////////////////////////////////////////////
//////////////////////////////////////////////////////////////////////

class NotebookRecord
{
	private String name;
	private String phone;
	private Date dateCreate;

	public NotebookRecord(String name, String phone)
	{
		super();
		this.name = name;
		this.phone = phone;
		this.dateCreate = new Date();
	}

	public String getName()
	{
		return name;
	}

	public String getPhone()
	{
		return phone;
	}

	public Date getDateCreate()
	{
		return dateCreate;
	}

	@Override
	public String toString()
	{
		String result;
		result = "Имя: " + getName() + ", телефон: " + getPhone() + ", внесен в базу: " + getDateCreate();
		return result;
	}
};