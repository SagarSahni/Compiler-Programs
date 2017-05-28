import java.io.*;
import java.util.*;
public class LF
{
	public static void main(String args[])
	{
		Scanner in=new Scanner(System.in);
		System.out.print("No. of prods:");
		int n=Integer.parseInt(in.nextLine());
		String prod[]=new String[100];
		System.out.print("Enter prods:");
		for(int i=0;i<n;i++)
			prod[i]=in.nextLine();
		int count=n;
		char newchar='N';
		for(int i=0;i<count;i++)
		{
			char producer=prod[i].charAt(0);
			String pro=producer+"->";
			String rhs[]=prod[i].substring(prod[i].indexOf('>')+1,prod[i].length()).split("/");
			Arrays.sort(rhs);
			for(int j=0;j<rhs.length;j++)
			{
				String prodash=newchar+"->";
				char c=rhs[j].charAt(0);
				int k;
				for(k=j+1;k<rhs.length;k++)
					if(rhs[k].charAt(0)!=c)
						break;
				k=k-1;
				if(k==j)
				{
					pro=pro+rhs[k]+"/";
					continue;
				}
				int smallest=rhs[j].length();
				for(int l=j+1;l<=k;l++)
					if(smallest>rhs[l].length())
						smallest=rhs[l].length();
				outer: for(int m=smallest;m>0;m--)
				{
					String comp=rhs[j].substring(0,m);
					for(int l=j+1;l<=k;l++)
						if(!rhs[l].substring(0,m).equals(comp))
							continue outer;
					pro=pro+comp+newchar+"/";
					for(int l=j;l<=k;l++)
					{
						if(m==rhs[l].length())
							prodash=prodash+"#/";
						else
							prodash=prodash+rhs[l].substring(m,rhs[l].length())+"/";
					}
					break;
				}
				j=k;
				prod[count++]=prodash;
				newchar=(char)(newchar+1);
			}
			prod[i]=pro;
		}
		for(int i=0;i<count;i++)
		{
			if(prod[i].charAt(prod[i].length()-1)=='/')
				prod[i]=prod[i].substring(0,prod[i].length()-1);
			System.out.println(prod[i]);
		}
	}
}