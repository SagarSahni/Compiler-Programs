import java.io.*;
import java.util.*;
public class lexanalyser
{
	static ArrayList<String> id=new ArrayList<String>();
	static ArrayList<String> num=new ArrayList<String>();
	static ArrayList<String> key=new ArrayList<String>();
	static ArrayList<String> op=new ArrayList<String>();
	public static void main(String args[])
	{
		Scanner in=new Scanner(System.in);
		String str=in.nextLine();
		analyse(str);
		Iterator itr;
		if(!id.isEmpty())
		{
			itr=id.iterator();
			System.out.print("Identifiers:\t");
			while(itr.hasNext())
				System.out.print(itr.next()+"\t");
			System.out.println();
		}
		if(!num.isEmpty())
		{
			itr=num.iterator();
			System.out.print("Constants:\t");
			while(itr.hasNext())
				System.out.print(itr.next()+"\t");
			System.out.println();
		}
		if(!op.isEmpty())
		{
			itr=op.iterator();
			System.out.print("Operators:\t");
			while(itr.hasNext())
				System.out.print(itr.next()+"\t");
			System.out.println();
		}
		if(!key.isEmpty())
		{
			itr=key.iterator();
			System.out.print("Keywords:\t");
			while(itr.hasNext())
				System.out.print(itr.next()+"\t");
			System.out.println();
		}
	}
	public static void analyse(String str)
	{
		String delim=" +-*/=%(){}[]";
		StringTokenizer st=new StringTokenizer(str,delim,true);
		String t;
		while(st.hasMoreTokens())
		{
			t=st.nextToken();
			if(t.equals(" ")==true)
				continue;
			else if(IsNumeral(t)){}
			else if(IsOperator(t)){}
			else if(IsKey(t)){}
			else
			{
				if(!id.contains(t))
					id.add(t);
			}
		}
	}
	public static boolean IsNumeral(String s)
	{
		try
		{
			double d=Double.parseDouble(s);
			if(!num.contains(s))
				num.add(s);
			return true;
		}
		catch(NumberFormatException e)
		{
			return false;
		}
	}
	public static boolean IsOperator(String s)
	{
		String OP[]={"+","-","*","/","%","=","(",")","{","}","[","]","<",">"};
		for(int i=0;i<OP.length;i++)
		{
			if(s.equals(OP[i]))
			{
				if(!op.contains(s))
					op.add(s);
				return true;
			}
		}
		return false;
	}
	public static boolean IsKey(String s)
	{
		s=s.toLowerCase();
		String KEY[]={"true","false","int","float","double","char","string","class","main"};
		for(int i=0;i<KEY.length;i++)
		{
			if(s.equals(KEY[i]))
			{
				if(!key.contains(s))
					key.add(s);
				return true;
			}
		}
		return false;
	}
}