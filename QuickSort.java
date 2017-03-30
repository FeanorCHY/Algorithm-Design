import java.util.Arrays;

public class QuickSort {
	static int[] S={1,2,3,4,5,6,7,8,9};
	static void quickSort(int low, int high)
	{
		int pivotpoint=0;
		if(high>low){
			pivotpoint=partition(low,high,pivotpoint);
			quickSort(low,pivotpoint-1);
			quickSort(pivotpoint+1,high);
		}
	}

	static int partition(int low,int high, int pivotpoint)
	{
		int i=0;
		int j=0;
		int pivotitem=S[low];
		j=low;
		for(i=low+1;i<=high;i++)
		{
			if(S[i]<pivotitem){
				j=j+1;
				int tem=S[i];
				S[i]=S[j];
				S[j]=tem;
			}
		}
		pivotpoint=j;
		int tem=S[low];
		S[low]=S[pivotpoint];
		S[pivotpoint]=tem;
		return pivotpoint;
	}
	public static int[] RandomArray(int n)
	{
		int array[]=new int[n];
		
		for (int i=0;i<n;i++)
		{
			array[i]=(int)(Math.random()*100000);
		}
		return(array);
	}
	public static void main(String[] args) {
		quickSort(0,S.length-1);
		System.out.println(Arrays.toString(S));

//		int[] timeseries={1000,5000,1000,1500,2000,2500,3000,3500,4000,4500};
		int[] timeseries={1000,3000,5000,7000,10000,13000,15000,17000};//,20000};//,25000,30000,35000,40000,45000};
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
			S=testarr.clone();
			startTime=System.currentTimeMillis();
			endTime=System.currentTimeMillis();
			best[i]=endTime-startTime;
			
			
			for(int j=0;j<testarr.length;j++)
			{
				testarr[j]=testarr.length-j;
			}
			S=testarr.clone();
			startTime=System.currentTimeMillis();
			quickSort(0,testarr.length-1);
			endTime=System.currentTimeMillis();
			worst[i]=endTime-startTime;


			long sumtime=0;
			for(int j=0;j<20;j++)
			{
				testarr=RandomArray(timeseries[i]);
				S=testarr.clone();
				startTime=System.currentTimeMillis();
				quickSort(0,testarr.length-1);
				endTime=System.currentTimeMillis();
				System.out.println("Run time: "+(endTime-startTime));
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
