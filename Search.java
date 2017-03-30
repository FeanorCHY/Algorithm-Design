import java.util.Arrays;

public class Search {
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
	
	
	static int RobustInterpolationSearch(int S[], int t)
	{
			int low=0;
			int high=S.length-1;
			int i=-1;
			long deno=0;
			long mid=0;
			int gap = (int)Math.sqrt((high - low + 1));
			double gap3=(double)high - (double)low + (double)1;
			double gap2 = Math.sqrt((double)high - (double)low + (double)1);
			recTimes=0;
			if(S[low]<=t && S[high]>=t)
				while(low <= high&&i==-1)
				{
//					System.out.println("mid: "+mid);
					recTimes=recTimes+1;
					deno=S[high]-S[low];
					if(deno==0)
						mid=low;
					else{
						gap = (int)Math.sqrt((high - low + 1));
						mid = Integer.min(high - gap,Integer.max((int)mid,low + gap));
					}
					if(mid<0)
						System.out.println("mid: "+mid);
					if(t==S[(int)mid])
						i=(int)mid;
					else if(t<S[(int)mid])
						high=(int)mid-1;
					else
						low = (int)mid+1;
				}
			return i;
	}
	
	static int recTimes=0;
	public static int BinarySearch (int[] a, int low, int high, int searchValue)
	{
		recTimes=recTimes+1;
		int mid;
		if (high <= low)
		return -1;
			mid = (low + high) /2; 
		if (a [mid] > searchValue)
		{
			return BinarySearch (a, low, mid, searchValue);
		}
		else if (a [mid] < searchValue)
		{
			return BinarySearch (a, mid + 1, high, searchValue);
		}
		else 
		{
			return mid;
		}
	}
	static int InterpolationSearch(int S[], int t)
	{
//	        int low = 0;
//    		int high = a.length - 1;
//	        int pos;
//	        while(low <= high){
//	        		recTimes=recTimes+1;
//	                pos = (t - a[low])/(a[high] - a[low])*(high - low) + low;
//	                if(a[pos] == t){
//	                        return pos;
//	                }
//	                if(a[pos] > t){
//	                        high = pos - 1;
//	                }else{
//	                        low = pos + 1;
//	                }
//	        }
//	        return -1;

			int low=0;
			int high=S.length-1;
			int i=-1;
			long deno=0;
			long mid=0;
			recTimes=0;
			if(S[low]<=t && S[high]>=t)
				while(low <= high&&i==-1)
				{
//					System.out.println("mid: "+mid);
					recTimes=recTimes+1;
					deno=S[high]-S[low];
					if(deno==0)
						mid=low;
					else
						mid=(long)low+(((long)t-(long)S[low])*((long)high-(long)low))/(long)deno;
//					if(mid<0)
//						System.out.println("mid: "+mid);
					if(t==S[(int)mid])
						i=(int)mid;
					else if(t<S[(int)mid])
						high=(int)mid-1;
					else
						low = (int)mid+1;
				}
			return i;
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
	static void test1()
	{

//		int[] test=RandomArray(20);
//		InsersionSort(test);
////		for(int i=0;i<test.length;i++)
////		{
////			test[i]=i;
////		}
//		for(int i=0;i<test.length;i++)
//		{
//			recTimes=0;
////			BinarySearch(test,0,test.length,i);
//			InterpolationSearch(test,test[i]);
//			System.out.println("Search Times for "+test[i]+" : "+recTimes);
//		}
//		System.out.println(BinarySearch(test,0,test.length,6));
//		System.out.println(InterpolationSearch(test,9));
		
		
		
		

		int[] timeseries={10000,50000,100000,150000,200000,250000,300000,350000,400000};
//		int[] timeseries={10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28,29,30,31,32,33,34};
		long[] best=new long[timeseries.length];
		long[] worst=new long[timeseries.length];
		long[] average=new long[timeseries.length];
		long[] best2=new long[timeseries.length];
		long[] worst2=new long[timeseries.length];
		long[] average2=new long[timeseries.length];
		long startTime=0;
		long endTime=0;

		
		
		
		for(int i=0;i<timeseries.length;i++)
		{
			System.out.println("The "+i+"th iteration");
			int[] testarr=new int[timeseries[i]];
			int[] testarr2=RandomArray(timeseries[i]);
			InsersionSort(testarr2);
			for(int j=0;j<testarr.length;j++)
			{
				testarr[j]=j;
			}
//			System.out.println(Arrays.toString(testarr));
			recTimes=0;
			BinarySearch(testarr,0,testarr.length,testarr[timeseries[i]/2]);
//			System.out.println("Search Times for "+(timeseries[i]/2)+" : "+recTimes);
			best[i]=recTimes;
			
			recTimes=0;
			BinarySearch(testarr,0,testarr.length,testarr[0]);
//			System.out.println("Search Times for 0"+" : "+recTimes);
			worst[i]=recTimes;


			long sumtime=0;
			for(int j=0;j<1000;j++)
			{
				recTimes=0;
				BinarySearch(testarr,0,testarr.length,(int)(Math.random()*testarr.length));
				sumtime=sumtime+recTimes;
			}
			average[i]=sumtime/1000;
			
			
			
			
			
			

			recTimes=0;
			sumtime=0;
			int besttime=999999;
			int worsttime=-1;
//			System.out.println("Search Times for "+(timeseries[i]/2)+" : "+recTimes);
			

			for(int k=0;k<testarr2.length;k++)
			{
				recTimes=0;
				InterpolationSearch(testarr2,testarr2[k]);
				sumtime=sumtime+recTimes;
				if(worsttime<recTimes){
					worst2[i]=recTimes;
					worsttime=recTimes;
				}
				if(besttime>recTimes){
					best2[i]=recTimes;
					besttime=recTimes;
				}
			}
			average2[i]=sumtime/testarr2.length;
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
		
		
		System.out.print("best2: ");
		for(int i=0;i<best2.length;i++)
		{
			System.out.print(best2[i]+" ");
		}
		System.out.println();


		System.out.print("average2: ");
		for(int i=0;i<average2.length;i++)
		{
			System.out.print(average2[i]+" ");
		}
		System.out.println();
		

		System.out.print("worst2: ");
		for(int i=0;i<worst2.length;i++)
		{
			System.out.print(worst2[i]+" ");
		}
		System.out.println();
		
	}
	public static void main(String[] args) {

		int[] timeseries={1000,5000,10000,15000,20000,25000};//
//		int[] timeseries={10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28,29,30,31,32,33,34};
		long[] best=new long[timeseries.length];
		long[] worst=new long[timeseries.length];
		long[] average=new long[timeseries.length];
		long startTime=0;
		long endTime=0;
		long sumtime=0;
		recTimes=0;
		sumtime=0;
		int besttime=999999;
		int worsttime=-1;
//		System.out.println("Search Times for "+(timeseries[i]/2)+" : "+recTimes);
		

		for(int i=0;i<timeseries.length;i++)
		{
			System.out.println("The "+i+"th iteration");
			int[] testarr=RandomArray(timeseries[i]);
			InsersionSort(testarr);
			for(int k=0;k<testarr.length;k++)
			{
				recTimes=0;
				InterpolationSearch(testarr,testarr[k]);
				sumtime=sumtime+recTimes;
				if(worsttime<recTimes){
					worst[i]=recTimes;
					worsttime=recTimes;
				}
				if(besttime>recTimes){
					best[i]=recTimes;
					besttime=recTimes;
				}
			}
			average[i]=sumtime/testarr.length;
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
