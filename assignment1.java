import java.lang.Math;
import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.io.Reader;
import java.lang.reflect.Array;

public class assignment1 {
		static PrintStream p ;
	
	
	public static void dividetwo(int[] origin)
	{
		int n=origin.length;
		int setA[]=new int[n/2];
		int setB[]=new int[n/2];
		int sumdiff=0;
		int locA=0;
		int locB=0;
		int t=0;
		float tem;
		float diffmin=9999999;
	
		for (int i=0;i<n/2;i++)
			setA[i]=origin[i];
		for (int i=n/2;i<n;i++)
			setB[i-n/2]=origin[i];
		for (int i=0;i<(n/2+1);i++)
		{
			sumdiff=0;
			for (int m=0;m<n/2;m++)
				sumdiff=setA[m]-setB[m]+sumdiff;
			
			for(int m=0;m<n/2;m++)
			{
				for (int k=0;k<n/2;k++)
				{
					tem=Math.abs((float)sumdiff/2-((float)setA[m]-(float)setB[k]));
					if (tem<diffmin)
					{
						locA=m;
						locB=k;
						diffmin=tem;
					}
				}
			}
			if (Math.abs(diffmin*2)<Math.abs(sumdiff))
			{
				t=setA[locA];
				setA[locA]=setB[locB];
				setB[locB]=t;
			}
			diffmin=9999999;
		}
		System.out.print("Origin:");
		for(int p=0;p<origin.length;p++)
			System.out.print(" "+origin[p]);
		System.out.println();
		
		System.out.print("SetA:");
		for(int p=0;p<setA.length;p++)
			System.out.print(" "+setA[p]);
		System.out.println();
		
		System.out.print("SetB:");
		for(int p=0;p<setB.length;p++)
			System.out.print(" "+setB[p]);
		System.out.println();
		
		System.out.println("diff:"+sumdiff);
		
		try
		{
		//FileOutputStream fs = new FileOutputStream(new File("/Users/FeanorCui/Documents/Algorithm Design/assignment1/answers.txt"));
		//PrintStream p = new PrintStream(fs);
		String str=""+Math.abs(sumdiff);
//		for(int i=0;i<n/2;i++)
//		{
//			str=str+setA[i]+" ";
//		}
//		str=str.substring(0,str.length()-1);
//		p.println(str);
//		//str=str.substring(0,str.length()-1);
//		//str=str+",";
//		str="";
//		for(int i=0;i<n/2;i++)
//		{
//			str=str+setB[i]+" ";
//		}
//		str=str.substring(0,str.length()-1);
		p.println(str);
		}catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	

	public static void main(String[] args) {
		
		
        try {
        	p= new PrintStream(new FileOutputStream(new File("/Users/FeanorCui/Documents/Algorithm Design/assignment1/answers.txt")));
			String filePath = "/Users/FeanorCui/Documents/Algorithm Design/assignment1/test_cases.txt";
			BufferedReader br = new BufferedReader(new InputStreamReader(
			        new FileInputStream(filePath)));
			        for (String line = br.readLine(); line != null; line = br.readLine()) {
		        		String [] strArray = line.split(" ");
		        		int[] t=new int[strArray.length];
		        		for(int i=0;i<strArray.length;i++)
		        		{
			        		t[i]=Integer.parseInt(strArray[i]);
		        		}
	        			dividetwo(t);
	    				//System.out.println(line);               
			        }
			        br.close();
        }
        catch(Exception e)
        {
        	e.printStackTrace();
        }
		
		
		
	}

}
