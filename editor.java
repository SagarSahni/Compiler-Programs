import java.io.*;
import java.util.*;
public class editor
{
	static String clipboard="";
	public static void main(String args[])
	{
		Scanner in=new Scanner(System.in);
		int choice;
		do
		{
			System.out.println("1.New\n2.Open\n3.Cut\n4.Copy\n5.Paste\n6.Exit");
			choice=Integer.parseInt(in.nextLine());
			switch(choice)
			{
				case 1: newfile();
						break;
				case 2: System.out.print("Name of the file to open: ");
						String str=in.nextLine();
						String s1=open(str);
						System.out.println("File Contents:-\n"+s1);
						break;
				case 3: cut();
						break;
				case 4: copy();
						break;
				case 5: paste();
						break;
			}
		}while(choice<6);
	}
	public static void newfile()
	{
		try
		{
			Scanner in=new Scanner(System.in);
			System.out.print("Name of new file: ");
			String str=in.nextLine();
			File f=new File(str);
			if(f.exists())
				System.out.println("File with same name already exists");
			else
			{
				f.createNewFile();
				System.out.println("File created");
				System.out.println("Enter the text you want to write");
				String str1=in.nextLine();
				FileWriter fw=new FileWriter(f);
				BufferedWriter BW=new BufferedWriter(fw);
				BW.write(str1);
				BW.close();
				System.out.println("Text inserted");
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
	}
	public static String open(String str)
	{
		try
		{
			Scanner in=new Scanner(System.in);
			FileReader fis=new FileReader(str);
			BufferedReader BR=new BufferedReader(fis);
			StringBuffer buffer =new StringBuffer();
			String content;
			while((content=BR.readLine())!=null)
			{
				buffer.append(content);
				buffer.append("\n");
			}
			fis.close();
			return buffer.toString();
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return "";
		}
	}
	public static void cut()
	{
		try
		{
			Scanner in=new Scanner(System.in);
			System.out.print("Name of the file to open: ");
			String str=in.nextLine();
			String s1=open(str);
			System.out.println("File Contents:-\n"+s1);
			System.out.print("Indexes for cut: ");
			int a=in.nextInt();
			int b=in.nextInt();
			clipboard=s1.substring(a,b+1);
			s1=s1.substring(0,a)+s1.substring(b+1,s1.length());
			File f=new File(str);
			FileWriter fw=new FileWriter(f);
				BufferedWriter BW=new BufferedWriter(fw);
				BW.write(s1);
				BW.close();
				s1=open(str);
			System.out.println("File Contents:-\n"+s1);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
	}
	public static void copy()
	{
		try
		{
			Scanner in=new Scanner(System.in);
			System.out.print("Name of the file to open: ");
			String str=in.nextLine();
			String s1=open(str);
			System.out.println("File Contents:-\n"+s1);
			System.out.print("Indexes for copy:");
			int a=in.nextInt();
			int b=in.nextInt();
			clipboard=s1.substring(a,b+1);
			System.out.println(clipboard+" Copied");
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
	}
	public static void paste()
	{
		try
		{
			Scanner in=new Scanner(System.in);
			System.out.print("Name of the file to open: ");
			String str=in.nextLine();
			String s1=open(str);
			System.out.println("File Contents:-\n"+s1);
			System.out.print("Index for paste:");
			int a=in.nextInt();
			s1=s1.substring(0,a)+clipboard+s1.substring(a,s1.length());
			File f=new File(str);
			FileWriter fw=new FileWriter(f);
				BufferedWriter BW=new BufferedWriter(fw);
				BW.write(s1);
				BW.close();
				s1=open(str);
			System.out.println("File Contents:-\n"+s1);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
	}
}