import java.io.*;
import java.util.*;
public class leftfr
{
	public static List<List> prod=new ArrayList<List>();
	public static List<String> finalprod=new ArrayList<String>();
	public static void leftrecursion()
	{
		int c=64;	
		for(int i=0;i<prod.size();i++)
		{
			int f=0;
			for(int j=1;j<prod.get(i).size();j++)
				if(prod.get(i).get(0).toString().charAt(0)==prod.get(i).get(j).toString().charAt(0))
				{
					String temp="";
					char t=(char)++c;
					if(prod.get(i).size()>2)
					{
						for(int k=1;k<prod.get(i).size();k++)
							if(prod.get(i).get(0).toString().charAt(0)!=prod.get(i).get(k).toString().charAt(0))
								temp=temp+prod.get(i).get(k).toString()+t+"'/";
						finalprod.add(prod.get(i).get(0).toString().charAt(0)+"->"+temp.substring(0,temp.length()-1));
					}
					else
						finalprod.add(prod.get(i).get(0).toString().charAt(0)+"->#");
					finalprod.add(t+"'->"+prod.get(i).get(j).toString().substring(1,prod.get(i).get(j).toString().length())+t+"'/#");
					f=1;
					break;
				}
			if(f==0)
			{
				String s="";
				for(int l=1;l<prod.get(i).size();l++)
					s=s+prod.get(i).get(l).toString()+"/";
				finalprod.add(prod.get(i).get(0).toString().charAt(0)+"->"+s.substring(0,s.length()-1));
			}
		}
		System.out.println("After Removing Left Recursion:- ");
		for(int i=0;i<finalprod.size();i++)
			System.out.println(finalprod.get(i));
	}
	public static void leftfactoring()
	{
		finalprod.clear();
		for(int i=0;i<prod.size();i++)
		{
			String t="";
			int c=64,f=0;
			if(prod.get(i).size()>2)
			{
				String temp=prod.get(i).get(1).toString();
				String temp1=prod.get(i).get(2).toString();
				for(int j=0;j<temp1.length();j++)
					if(temp.charAt(j)==temp1.charAt(j))
						t=t+temp.charAt(j);
				if(!t.equals(""))
					f=1;
				if(f==1)
				{
					if(prod.get(i).size()>3)
					{
						String le="";
						for(int j=3;j<prod.get(i).size();j++)
							if(!prod.get(i).get(j).toString().contains(t))
								le=le+prod.get(i).get(j).toString()+"/";
						finalprod.add(prod.get(i).get(0).toString().charAt(0)+"->"+t+(char)++c+"'/"+le.substring(0,le.length()-1));
					}
					else
					{
						finalprod.add(prod.get(i).get(0).toString().charAt(0)+"->"+t+(char)++c+"'");
					}
					String el="";
					for(int k=1;k<prod.get(i).size();k++)
						if(prod.get(i).get(k).toString().contains(t))
							el=el+prod.get(i).get(k).toString().replace(t,"")+"/";
					finalprod.add((char)c+"'->"+el.substring(0,el.length()-1));
				}
				else
				{
					String s="";
					for(int l=1;l<prod.get(i).size();l++)
						s=s+prod.get(i).get(l).toString()+"/";
					finalprod.add(prod.get(i).get(0).toString().charAt(0)+"->"+s.substring(0,s.length()-1));
				}
			}
			else
			{	
				String s="";
				for(int l=1;l<prod.get(i).size();l++)
					s=s+prod.get(i).get(l).toString()+"/";
				finalprod.add(prod.get(i).get(0).toString().charAt(0)+"->"+s.substring(0,s.length()-1));
			}
		}
		System.out.println("After Removing Left Factoring:- ");
		for(int i=0;i<finalprod.size();i++)
			System.out.println(finalprod.get(i));
	}
	public static void main(String args[]) throws IOException
	{
		int nop;
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		System.out.print("No. of Productions:- ");
		nop=Integer.parseInt(br.readLine());
		System.out.println("Enter Productions");
		for(int i=0;i<nop;i++)
		{
			String in=br.readLine();
			List<String> temp=new ArrayList<String>(Arrays.asList(in.split("->")));
			List<String> temp1=new ArrayList<String>(Arrays.asList(temp.get(1).split("/")));
			temp.remove(temp.get(1));
			for(int j=0;j<temp1.size();j++)
				temp.add(temp1.get(j));
			prod.add(temp);
		}
		leftrecursion();
		leftfactoring();
	}
}