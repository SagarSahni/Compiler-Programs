import java.io.*;
import java.util.*;
public class RegexToNfa
{
    static String regex;
    static int plus[]=new int[50];
    static int plushold[]=new int[50];
    static int popped[]=new int[50];
    static int top=-1,tophold=-1,toppop=-1;
    public static void pushhold(int num)
    {
    plushold[++tophold]=num;
    }
    public static void push()
    {
    for(int i=top;i>=0;i--)
        plus[i+tophold+1]=plus[i];
    for(int i=0;i<=tophold;i++)
        plus[i]=plushold[i];
    top+=tophold+1;
    tophold=-1;
    }
    public static int pop()
    {
        int c=plus[0];
        for(int i=0;i<top;i++)
            plus[i]=plus[i+1];
        top--;
        popped[++toppop]=c;
        return c;
    }
    public static void main(String args[])
    {
        Scanner in=new Scanner(System.in);
        System.out.print("Regular Expression:");
        regex=in.nextLine();
        String A[][]=new String[50][3];
        for(int i=0;i<50;i++)
            for(int j=0;j<3;j++)
                A[i][j]="";
        int star[]=new int[50];
        int ctrS=0,ctrP=0,current=0,plusCount;
        for(int i=0;i<regex.length();i++)
        {
            switch(regex.charAt(i))
            {
                case '(':
                    int bracPos=CorrBrac(i);
					int nState=CountStates(regex.substring(i,bracPos+1));
                    if(bracPos!=regex.length()-1 && regex.charAt(bracPos+1)=='*')
                    {    
                        star[ctrS++]=current;
                        A[current][2]=A[current][2]+" "+Integer.toString(current+1);
                        popreplace(current,current+nState+1);
						current+=1;
                    }
                    plusCount=CountPlus(i+1,bracPos);
                    if(plusCount>0)
                    {
						popreplace(current,current+nState);
                        int next;
                        pushhold(current+1);
                        int prev=current+1;
                        String str=Integer.toString(prev);
                        for(int x=i+1;x<bracPos;x++)
                        {
                            next=NextPlus(x,bracPos);
                            int n=CountStates(regex.substring(x,next));
                            x=next;
                            if(x!=bracPos)
                            {
                                pushhold(prev+n);
                                prev+=n;
                                str=str+","+Integer.toString(prev);
                            }
                        }
                        A[current][2]=str;
                        push();
                        current=pop();
                    }
                    break;
                case 'a':
                    A[current][0]=A[current][0]+" "+Integer.toString(current+1);
                    popreplace(current,current+1);
                    current+=1;
                    break;
                case 'b':
                    A[current][1]=A[current][1]+" "+Integer.toString(current+1);
                    popreplace(current,current+1);
                    current+=1;
                    break;
                case '+':
                    current=pop();
                    break;
                case '.':
                    A[current][2]=A[current][2]+" "+Integer.toString(current+1);
                    popreplace(current,current+1);
                    current+=1;
                    break;
                case ')':
                    bracPos=CorrBrac(i);
                    plusCount=CountPlus(bracPos+1,i);
                    if(plusCount>0)
                    {
                        for(int f=plusCount;f>=0;f--)
                        {
                            A[popped[toppop]][2]=A[popped[toppop]][2]+" "+Integer.toString(current+1);
							toppop-=1;
                        }
                    current+=1;
                    }
                    break;
                case '*':
                    int ss=star[--ctrS];
                    A[current][2]=Integer.toString(ss+1)+" "+Integer.toString(current+1);
                    A[ss][2]=A[ss][2]+" "+Integer.toString(++current);
                    break;
            }
        }
        System.out.println("Q\\ip \t a \t b \t E");
        for(int i=0;i<=current;i++)
        {
            System.out.print(i);
            for(int j=0;j<3;j++)
                System.out.print("\t"+A[i][j].trim().replace(" ",","));
            System.out.println();
        }
    }
    public static int CountStates(String str)
    {
        int count =0;
        for(int i=0;i<str.length();i++)
            switch(str.charAt(i))
            {
                case 'a':
                    count+=2;
                    break;
                case 'b':
                    count+=2;
                    break;
                case '+':
                    count+=2;
                    break;
                case '*':
                    count+=2;
                    break;
            }
        return count;
    }
    public static int NextPlus(int a,int b)
    {
        int i,count=0;
        for(i=a;i<b;i++)
        {
            if(regex.charAt(i)=='(')
                i=CorrBrac(i);
            else if(regex.charAt(i)=='+')
                break;
        }
        return i;
    }
    public static int CountPlus(int a,int b)
    {
        int count=0;
        for(int i=a;i<=b;i++)
        {
            if(regex.charAt(i)=='(')
                i=CorrBrac(i);
            else if(regex.charAt(i)=='+')
                count+=1;
        }
        return count;
    }
    public static int CorrBrac(int pos)
    {
        if(regex.charAt(pos)=='(')
        {
           int count=0,i;
           for(i=pos;i<regex.length();i++)
            {
                if(regex.charAt(i)=='(')
                    count++;
               else if(regex.charAt(i)==')')
                {
                    count--;
                    if(count==0)
                    break;
                }
            }
            return i; 
        }
        else if(regex.charAt(pos)==')')
        {
           int count=0,i;
           for(i=pos;i>=0;i--)
            {
                if(regex.charAt(i)==')')
                    count++;
               else if(regex.charAt(i)=='(')
                {
                    count--;
                    if(count==0)
                    break;
                }
            }
           return i;
        }
        return -1;
    }
    public static void popreplace(int a,int b)
    {
        for(int i=0;i<=toppop;i++)
            if(popped[i]==a)
                popped[i]=b;
    }
}