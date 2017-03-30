import java.util.Arrays;

public class InsersionSort {

	static void InsersionSort(int[] S)

	{
		int x=0;
		int j=0;
		for(int i=0;i<S.length;i++)
		{
			x=S[i];
			j=i-1;
			while(j>=0&&S[j]>x)
			{
				S[j+1]=S[j];
				j=j-1;
			}
			S[j+1]=x;
		}
	}
	public static int[] RandomArray(int n)
	{
		int array[]=new int[n];
		
		for (int i=0;i<n;i++)
		{
			array[i]=(int)(Math.random()*1000);
		}
		return(array);
	}
	public static void main(String[] args) {
		int[] timeseries={1000,5000,10000,15000,20000,25000,30000,35000,40000,45000};
		long[] best=new long[timeseries.length];
		long[] worst=new long[timeseries.length];
		long[] average=new long[timeseries.length];
		long startTime=0;
		long endTime=0;

//		System.out.println(Arrays.toString(RandomArray(20)));

//		int[] testarr=RandomArray(10000);
//		System.out.println(Arrays.toString(testarr));
//		startTime=System.currentTimeMillis();
//		InsersionSort(testarr);
//		endTime=System.currentTimeMillis();
//		System.out.println((endTime-startTime));
		
		
		
		for(int i=0;i<timeseries.length;i++)
		{
			int[] testarr=new int[timeseries[i]];
			for(int j=0;j<testarr.length;j++)
			{
				testarr[j]=j;
			}
			startTime=System.currentTimeMillis();
			InsersionSort(testarr);
			endTime=System.currentTimeMillis();
			best[i]=endTime-startTime;
			
			
			for(int j=0;j<testarr.length;j++)
			{
				testarr[j]=testarr.length-j;
			}
//			System.out.println(Arrays.toString(testarr));
			startTime=System.currentTimeMillis();
			InsersionSort(testarr);
			endTime=System.currentTimeMillis();
			worst[i]=endTime-startTime;


			long sumtime=0;
			for(int j=0;j<20;j++)
			{
				testarr=RandomArray(timeseries[i]);
				startTime=System.currentTimeMillis();
				InsersionSort(testarr);
				endTime=System.currentTimeMillis();
				sumtime=sumtime+endTime-startTime;
			}
			average[i]=sumtime/20;
		}
		
		System.out.print("best: ");
		for(int i=0;i<best.length;i++)
		{
			System.out.print(best[i]+" ");
		}
		System.out.println();


		System.out.print("average: ");
		for(int i=0;i<average.length;i++)
		{
			System.out.print(average[i]+" ");
		}
		System.out.println();
		

		System.out.print("worst: ");
		for(int i=0;i<worst.length;i++)
		{
			System.out.print(worst[i]+" ");
		}
		System.out.println();
		
	}

}