import java.io.*;
import java.util.*;
public class FF
{
	static String PROD[];
	static char start;
	public static void main(String args[])
	{
		Scanner in=new Scanner(System.in);
		System.out.print("No. of productions:");
		int n=Integer.parseInt(in.nextLine());
		PROD=new String[n];
		System.out.print("Enter the productions:");
		for(int i=0;i<n;i++)
			PROD[i]=in.nextLine();
		System.out.print("Start Symbol:");
		start=in.nextLine().charAt(0);
		for(int i=0;i<n;i++)
		{
			System.out.print("FIRST("+PROD[i].charAt(0)+") = ");
			System.out.println(first(PROD[i]));
		}
		for(int i=0;i<n;i++)
		{
			System.out.print("FOLLOW("+PROD[i].charAt(0)+") = ");
			System.out.println(follow(PROD[i].charAt(0)));
		}
	}
	public static String first(String prod)
	{
		StringTokenizer sr= new StringTokenizer(prod.substring(prod.indexOf('>')+1,prod.length()),"/");
		ArrayList<Character> A=new ArrayList<Character>(20);
		while(sr.hasMoreTokens())
		{
			String s=sr.nextToken();
			for(int i=0;i<s.length();i++)
			{	
				if(!Character.isUpperCase(s.charAt(i)))
				{
					if(!A.contains(s.charAt(i)))
						A.add(s.charAt(i));
					break;
				}
				else
				{
					if(s.charAt(i)==prod.charAt(0))
						continue;
					String firstpp=first(findprod(s.charAt(i)));
					int flag=0;
					for(int j=0;j<firstpp.length();j+=2)
					{
						if(firstpp.charAt(j)=='#')
						{
							if(i==s.length()-1)
								if(!A.contains(firstpp.charAt(j)))
									A.add(firstpp.charAt(j));
							flag=1;
						}
						else
						{
							if(!A.contains(firstpp.charAt(j)))
								A.add(firstpp.charAt(j));
						}
					}
					if(flag==0)
						break;
				}
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
	public static String follow(char C)
	{
		ArrayList<Character> A=new ArrayList<Character>(20);
		if(C==start)
			A.add('$');
		for(int i=0;i<PROD.length;i++)
		{
			char producer=PROD[i].charAt(0);
			String str[]=PROD[i].substring(PROD[i].indexOf('>')+1,PROD[i].length()).split("/");
			for(int j=0;j<str.length;j++)
			{
				if(str[j].indexOf(C)>=0)
				{
					String st="";
					for(int k=str[j].indexOf(C)+1;k<str[j].length();k++)
					{
						st=st+first(findprod(str[j].charAt(k)))+",";
						if(st.indexOf('#')>=0)
						{
							st=st.replace("#,","").replace("#","");
							if(k==str[j].length()-1)
								st+=follow(producer)+",";
						}
						else
							break;
					}
					if(str[j].indexOf(C)==str[j].length()-1)
						st+=follow(producer);
					for(int k=0;k<st.length();k+=2)
						if(!A.contains(st.charAt(k)))
							A.add(st.charAt(k));
				}
			}
		}
		Iterator it=A.iterator();
		String str1="";
		while(it.hasNext())
			str1+=it.next()+",";
		return str1.substring(0,str1.length()-1);
	}
}