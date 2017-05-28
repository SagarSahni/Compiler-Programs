import java.io.*;
import java.util.*;
public class LR
{
	public static void main(String args[])
	{
		Scanner in=new Scanner(System.in);
		System.out.print("No. of prods:");
		int n=Integer.parseInt(in.nextLine());
		String prod[]=new String[n*2];
		System.out.print("Enter prods:");
		for(int i=0;i<n;i++)
			prod[i]=in.nextLine();
		int count=n-1;
		String prodA,prodAdash;
		for(int i=0;i<n;i++)
		{
			char producer=prod[i].charAt(0);
			prodA=producer+"->";
			prodAdash = producer+"'->";
			StringTokenizer sr=new StringTokenizer(prod[i].substring(prod[i].indexOf('>')+1,prod[i].length()),"/");
			int flag=0;
			while(sr.hasMoreTokens())
			{
				String a=sr.nextToken();
				if(a.charAt(0)==producer)
				{
					flag=1;
					break;
				}
			}
			if(flag==1)
			{
				sr=new StringTokenizer(prod[i].substring(prod[i].indexOf('>')+1,prod[i].length()),"/");
				while(sr.hasMoreTokens())
				{
					String str=sr.nextToken();
					if(str.charAt(0)!=producer)
						prodA=prodA+str+producer+"'/";
					else
						prodAdash=prodAdash+str.substring(1,str.length())+producer+"'/";
				}
				prodAdash=prodAdash+"#";
				prod[++count]=prodAdash;
				if(prodA.length()==3)
					prodA=prodA+producer+"'";
				prod[i]=prodA.substring(0,prodA.length()-1);
			}
		}
		for(int i=0;i<=count;i++)
			System.out.println(prod[i]);
	}
}	