import java.io.*;
import java.util.*;
public class student implements Serializable
{
	int regno;
	String name;
	long phone;
	public void setdetails()
	{
		Scanner in=new Scanner(System.in);
		System.out.print("Reg.No.:");
		this.regno=Integer.parseInt(in.nextLine());
		System.out.print("Name:");
		this.name=in.nextLine();
		System.out.print("Phone:");
		this.phone=Long.parseLong(in.nextLine());
	}
	public static void ins()
	{
		try
		{
			Scanner in=new Scanner(System.in);
			File f1=new File("record.txt");
			if(f1.exists()==false)
			f1.createNewFile();
			FileOutputStream f=new FileOutputStream("record.txt");
			ObjectOutputStream oos=new ObjectOutputStream(f);
			List<student> l=new ArrayList<student>();
			int choice=1;
			while(choice==1)
			{
				student s=new student();
				s.setdetails();
				l.add(s);
				System.out.print("Enter 1 for more insertions:");
				choice=Integer.parseInt(in.nextLine());
			}
			oos.writeObject(l);
			oos.flush();
			oos.close();
			f.close();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	public static void del()
	{
		try
		{
			Scanner in=new Scanner(System.in);
			FileInputStream f=new FileInputStream("record.txt");
			ObjectInputStream ois=new ObjectInputStream(f);
			List<student> l=(ArrayList<student>)ois.readObject();
			System.out.print("reg no of record to be del:");
			int a=in.nextInt();
			Iterator i=l.iterator();
			int flag=0;
			while(i.hasNext())
			{
				student s1=(student)i.next();
				if(s1.regno==a)
				{
					System.out.println("Removed-"+s1.name+" "+s1.phone);
					l.remove(s1);
					FileOutputStream f1=new FileOutputStream("record.txt");
					ObjectOutputStream oos=new ObjectOutputStream(f1);
					oos.writeObject(l);
					oos.flush();
					oos.close();
					f1.close();
					flag=1;
					break;
				}
			}
			ois.close();
			f.close();
			if(flag==0)
				System.out.println("Element not Found.");
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	public static void mod()
	{
		try
		{
			Scanner in=new Scanner(System.in);
			FileInputStream f=new FileInputStream("record.txt");
			ObjectInputStream ois=new ObjectInputStream(f);
			List<student> l=(ArrayList<student>)ois.readObject();
			System.out.print("reg no of record to be modified:");
			int a=in.nextInt();
			Iterator i=l.iterator();
			int flag=0;
			while(i.hasNext())
			{
				student s1=(student)i.next();
				if(s1.regno==a)
				{
					l.remove(s1);
					student s2=new student();
					s2.setdetails();
					l.add(s2);
					FileOutputStream f1=new FileOutputStream("record.txt");
					ObjectOutputStream oos=new ObjectOutputStream(f1);
					oos.writeObject(l);
					oos.flush();
					oos.close();
					f1.close();
					flag=1;
					break;
				}
			}
			ois.close();
			f.close();
			if(flag==0)
				System.out.println("Element not Found.");
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	public static void disp()
	{
		try
		{
			Scanner in=new Scanner(System.in);
			FileInputStream f=new FileInputStream("record.txt");
			ObjectInputStream ois=new ObjectInputStream(f);
			List<student> l=(ArrayList<student>)ois.readObject();
			Iterator i=l.iterator();
			while(i.hasNext())
			{
				student s1=(student)i.next();
				System.out.println("Reg No.: "+s1.regno+"\nName: "+s1.name+"\nPhone: "+s1.phone+"\n");
			}
			ois.close();
			f.close();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	public static void main(String args[])
	{
		Scanner in=new Scanner(System.in);
		int choice;
		do
		{
			System.out.println("1.Insertion\n2.Deletion\n3.Modification\n4.Print\n5.Exit");
			choice=Integer.parseInt(in.nextLine());
			switch(choice)
			{
				case 1: ins();
						break;
				case 2: del();
						break;
				case 3: mod();
						break;
				case 4: disp();
						break;
			}
		}while(choice<5);
	}
}