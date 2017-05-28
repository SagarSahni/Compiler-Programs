import java.io.*;
import java.util.*;
public class LandT
{
	static String PROD[];
	public static void main(String args[])
	{
		Scanner in=new Scanner(System.in);
		System.out.print("No. of productions:");
		int n=Integer.parseInt(in.nextLine());
		PROD=new String[n];
		System.out.print("Enter the productions:");
		for(int i=0;i<n;i++)
			PROD[i]=in.nextLine();
		for(int i=0;i<n;i++)
		{
			System.out.print("LEADING("+PROD[i].charAt(0)+") = ");
			System.out.println(leading(PROD[i]));
		}
		for(int i=0;i<n;i++)
		{
			System.out.print("TRAILING("+PROD[i].charAt(0)+") = ");
			System.out.println(trailing(PROD[i]));
		}
	}
	public static String leading(String prod)
	{
		StringTokenizer sr= new StringTokenizer(prod.substring(prod.indexOf('>')+1,prod.length()),"/");
		ArrayList<Character> A=new ArrayList<Character>(20);
		while(sr.hasMoreTokens())
		{
			String s=sr.nextToken();
			if(!Character.isUpperCase(s.charAt(0)))
			{
				if(!A.contains(s.charAt(0)))
					A.add(s.charAt(0));
			}
			else
			{
				if(s.charAt(0)!=prod.charAt(0))
				{
					String nestlead=leading(findprod(s.charAt(0)));
					for(int j=0;j<nestlead.length();j+=2)
					{
						if(!A.contains(nestlead.charAt(j)))
							A.add(nestlead.charAt(j));
					}
				}
				if(s.length()>1)
					if(!A.contains(s.charAt(1)))
						A.add(s.charAt(1));
			}
		}
		Iterator it=A.iterator();
		String str="";
		while(it.hasNext())
			str=str+","+it.next();
		return str.substring(1,str.length());
	}
	public static String findprod(char c)
	{
		for(int i=0;i<PROD.length;i++)
			if(PROD[i].charAt(0)==c)
				return PROD[i];
		return c+"";
	}
	public static String trailing(String prod)
	{
		StringTokenizer sr= new StringTokenizer(prod.substring(prod.indexOf('>')+1,prod.length()),"/");
		ArrayList<Character> A=new ArrayList<Character>(20);
		while(sr.hasMoreTokens())
		{
			String s=sr.nextToken();
			if(!Character.isUpperCase(s.charAt(s.length()-1)))
			{
				if(!A.contains(s.charAt(s.length()-1)))
					A.add(s.charAt(s.length()-1));
			}
			else
			{
				if(s.charAt(s.length()-1)!=prod.charAt(0))
				{
					String nesttrail=trailing(findprod(s.charAt(s.length()-1)));
					for(int j=0;j<nesttrail.length();j+=2)
					{
						if(!A.contains(nesttrail.charAt(j)))
							A.add(nesttrail.charAt(j));
					}
				}
				if(s.length()>1)
					if(!A.contains(s.charAt(s.length()-2)))
						A.add(s.charAt(s.length()-2));
			}
		}
		Iterator it=A.iterator();
		String str="";
		while(it.hasNext())
			str=str+","+it.next();
		return str.substring(1,str.length());
	}  
}