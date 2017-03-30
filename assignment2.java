
public class assignment2 {

	static int fib(int n)
	{
		if(n<=1)
			return n;
		else 
			return fib(n-1)+fib(n-2);
	}
	static int fib2(int n)
	{
		int[] f=new int[n];
		f[0]=0;
		if(n>0)
		{
			f[1]=1;
			for(int i=2;i<n;i++)
				f[i]=f[i-1]+f[i-2];
		}
		return f[n-1];
	}
	public static void main(String[] args) {
		long startTime;
		long endTime;
//		for (int i=0;i<100;i++)
//		{
//			startTime=System.currentTimeMillis();
//			int tem=fib(i);
//			endTime=System.currentTimeMillis();
//			System.out.println("fib("+i+")= "+tem+"   cost time="+(endTime-startTime)+"ms");
//		}
		for (int i=2;i<100;i++)
		{
			startTime=System.currentTimeMillis();
			int tem=fib2(i);
			endTime=System.currentTimeMillis();
			System.out.println("fib2("+i+")= "+tem+"   cost time="+(endTime-startTime)+"ms");
		}

	}

}
