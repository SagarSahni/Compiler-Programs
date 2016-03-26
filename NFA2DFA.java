import java.io.*;
import java.util.*;
public class NFA2DFA
{
	static String A[][];
	static String op[];
	static int count;
    public static void main(String args[])
    {
        Scanner in=new Scanner(System.in);
        System.out.print("No. of states:");
        int n=Integer.parseInt(in.nextLine());
		count=n;
        for(int i=0;i<n;i++)
            System.out.print("Q"+i+"\t");
        System.out.println("#\nInputs: 0\t1");
        A=new String[((int)Math.pow(2,n))][3];
		for(int i=0;i<n;i++)
		{
			A[i][0]=("Q"+i);
			System.out.print("F(Q"+i+",0)=");
			A[i][1]=in.nextLine();
			System.out.print("F(Q"+i+",1)=");
			A[i][2]=in.nextLine();
		}
		System.out.print("Output State(s):");
		op=(in.nextLine()).split(",");
		for(int i=0;i<count;i++)
            for(int j=1;j<3;j++)
				if(find(A[i][j])==-1)
					add(A[i][j],n);
		System.out.println("Q\\inp\t\t 0 \t\t 1");
		reachable();	
    }
	public static void add(String s,int n)
	{
		if(s.equals("#"))
		{
			A[count][0]="#";
			A[count][1]="#";
			A[count][2]="#";
			count++;
			return;
		}
		A[count][0]=s;
		String T[]=s.replace("Q","").split(",");
		ArrayList<String> B=new ArrayList<String>(n);
		ArrayList<String> C=new ArrayList<String>(n);
		int m;
		String U[],V[];
		for(int i=0;i<T.length;i++)
		{
			m=Integer.parseInt(T[i]);
			U=A[m][1].split(",");
			V=A[m][2].split(",");
			for(int j=0;j<U.length;j++)
				if(!U[j].equals("#") && !B.contains(U[j]))
					B.add(U[j]);
			for(int j=0;j<V.length;j++)
				if(!V[j].equals("#") && !C.contains(V[j]))
					C.add(V[j]);
		}
		Iterator b=B.iterator();
		String s1="";
		while(b.hasNext())
		{
			s1+=b.next();
			if(b.hasNext())
				s1+=",";
		}
		A[count][1]=s1;
		b=C.iterator();
		s1="";
		while(b.hasNext())
		{
			s1+=b.next();
			if(b.hasNext())
				s1+=",";
		}
		A[count][2]=s1;
		count++;
	}
	public static int find(String s)
	{
		int flag=0,i;
		for(i=0;i<count;i++)
			if(s.equals(A[i][0]))
			{	
				flag=1;
				break;
			}
		if(flag==1)
			return i;
		else
			return -1;
	}
	public static void reachable()
	{
		int C[]=new int[count];
		for(int i=0;i<count;i++)
			C[i]=0;
		String store[]=new String[count];
		int cc=0,marker=0;
		store[cc++]="Q0";
		while(marker<cc)
		{
			int a=find(store[marker]);
			C[a]=1;
			if(C[find(A[a][1])]==0)
			{
				C[find(A[a][1])]=1;
				store[cc++]=A[a][1];
			}
			if(C[find(A[a][2])]==0)
			{
				C[find(A[a][2])]=1;
				store[cc++]=A[a][2];
			}
			marker++;
		}
		for(int i=0;i<op.length;i++)
			for(int j=0;j<count;j++)
			{
				if(A[j][0].charAt(0)=='*')
				continue;
				else if(A[j][0].indexOf(op[i])>=0)
				A[j][0]="*"+A[j][0];
			}
		A[0][0]="->"+A[0][0];
		for(int i=0;i<count;i++)
		{
			if(C[i]==1)
			{
				for(int j=0;j<3;j++)
					System.out.print(A[i][j]+"\t\t");
				System.out.println();
			}
		}
	}	
}