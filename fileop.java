import java.io.*;
import java.util.*;
public class fileop
{
	public static void main(String args[])throws IOException
	{
		Scanner in=new Scanner(System.in);
		int choice;
		do
		{
			System.out.println("1.Creation\n2.Deletion\n3.Modification\n4.Print\n5.Exit");
			System.out.print(:Choice: ");
			choice=Integer.parseInt(in.nextLine());
			switch(choice)
			{
				case 1: create();
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
	public static void create()throws IOException
	{
		Scanner in=new Scanner(System.in);
		System.out.print("Enter file name: ");
		String fname=in.nextLine();
		File f1=new File(fname);
		if(!f1.exists())
		{
			f1.createNewFile();
			System.out.println("File created.");
			System.out.println("Enter the text you want to enter:-");
			String str=in.nextLine();
			FileWriter fw=new FileWriter(f1);
			BufferedWriter BW=new BufferedWriter(fw);
			BW.write(str);
			BW.close();
			System.out.println("Text inserted");
		}
		else
			System.out.println("File already exists");
	}
	public static void del()
	{
		Scanner in=new Scanner(System.in);
		System.out.print("Enter file name: ");
		String fname=in.nextLine();
		File f1=new File(fname);
		if(f1.exists())
		{
			f1.delete();
			System.out.println("File deleted");
		}
		else
			System.out.println("File does not exists");
	}
	public static void mod()throws IOException
	{
		Scanner in=new Scanner(System.in);
		System.out.print("Enter file name: ");
		String fname=in.nextLine();
		File f1=new File(fname);
		if(f1.exists())
		{
			System.out.println("Eneter the text to append:-");
			String str=in.nextLine();
			FileWriter fw=new FileWriter(f1,true);
			BufferedWriter BW=new BufferedWriter(fw);
			BW.write("\n"+str);
			BW.close();
		}
		else
			System.out.println("File doesn't exist");
	}
	public static void disp()throws IOException
	{
		Scanner in=new Scanner(System.in);
		System.out.print("Enter file name: ");
		String fname=in.nextLine();
		File f1=new File(fname);
		if(f1.exists())
		{
		FileReader fr=new FileReader(f1);
		BufferedReader BR=new BufferedReader(fr);
		String content;
		while((content=BR.readLine())!=null)
		{
			System.out.println(content);
		}
		}
	}
}