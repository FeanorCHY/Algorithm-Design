import java.util.Arrays;
import java.util.Random;

public class FindKSmall {
	static int recTimes=0;
	static int[] S={7,4,9,2,5,1,8,6,3};
	static void InsersionSort(int[] W,int first,int last)

	{
		int x=0;
		int j=first;
		for(int i=first;i<(last+1);i++)
		{
			x=W[i];
			j=i-1;
			while(j>=first&&W[j]>x)
			{
				W[j+1]=W[j];
				j=j-1;
			}
			W[j+1]=x;
		}
	}
	static int selection2(int[] W,int low,int high,int k)
	{
		int pivotpoint=0;
		if(low==high)
			return S[low];
		else{
			pivotpoint=partition2(W,low,high,pivotpoint);
			if(k==pivotpoint)
				return S[pivotpoint];
			else if(k<pivotpoint)
				return selection2(W,low,pivotpoint-1,k);
			else
				return selection2(W,pivotpoint+1,high,k);
		}
		
	}
	static int selection(int low,int high,int k)
	{
		int pivotpoint=0;
		if(low==high)
			return S[low];
		else{
			pivotpoint=partition(low,high,pivotpoint);
			if(k==pivotpoint)
				return S[pivotpoint];
			else if(k<pivotpoint)
				return selection(low,pivotpoint-1,k);
			else
				return selection(pivotpoint+1,high,k);
		}
		
	}
	static int select(int n,int[] W,int k)
	{
		return selection2(W,0,n,k);
	}
	public static int[] RandomArray(int n,int max)
	{
		int array[]=new int[n];
		
		for (int i=0;i<n;i++)
		{
			array[i]=(int)(Math.random()*max);
		}
		return(array);
	}
	
	static int partition(int low,int high,int pivotpoint)
	{
		int i=0;
		int j=0;
//		System.out.println("low: "+low);
		int pivotitem=S[low];
		j=low;
		for(i=low+1;i<=high;i++)
			if(S[i]<pivotitem){
				j=j+1;
				int tem=S[i];
				S[i]=S[j];
				S[j]=tem;
				recTimes=recTimes+1;
//				System.out.println(Arrays.toString(S));
			}
		pivotpoint=j;
		int tem=S[low];
		S[low]=S[pivotpoint];
		S[pivotpoint]=tem;
		recTimes=recTimes+1;
		return pivotpoint;
	}
	static int partition2(int[] W,int low, int high,int pivotpoint)
	{
		int arraysize= high-low+1;
		int r=(int)Math.ceil((double)arraysize/5);
		int i=0;
		int j=0;
		int mark=0;
		int first=0;
		int last=0;
		int pivotitem=0;
		int[] T=new int[r];
		for(i=1;i<=r;i++)
		{
			first = low+5*i-5;
			last=Integer.min((low+5*i-1), arraysize-1);
			T[i-1]=median(S,first,last);
		}
		pivotitem=select(r-1,T,(int)Math.floor(((double)r+1)/2)-1);
		j=low;
		for(i=low;i<=high;i++)
		{
			if(S[i]==pivotitem)
			{
				int tem=S[i];
				S[i]=S[j];
				S[j]=tem;
				mark=j;
				j=j+1;
			}
			else if(S[i]<pivotitem){
				int tem=S[i];
				S[i]=S[j];
				S[j]=tem;
				j=j+1;
			}
		}
		pivotpoint=j-1;
		int tem=S[mark];
		S[mark]=S[pivotpoint];
		S[pivotpoint]=tem;
		return pivotpoint;
	}
	
	static int median(int[] S,int first,int last)
	{
		int[] arr=S.clone();
		InsersionSort(arr,first,last);
		return(arr[(first+last)/2]);
		
	}
	static void test1()
	{
		int[] S1={10,1,2,3,4,5,6,7,8,9};
		S=S1.clone();
		// TODO Auto-generated method stub
//		for(int i=0;i<S.length;i++)
//		{
//			S[i]=S.length-i;
//		}
//		S=RandomArray(10,20);
//		partition(S,1,S.length-1,2);
//		System.out.println(Arrays.toString(S));
		selection(0,S.length-1,0);
		System.out.println(recTimes);
		
		
		int[] timeseries={10,20,50,100,200,500,1000};
		long[] best=new long[timeseries.length];
		long[] worst=new long[timeseries.length];
		long[] average=new long[timeseries.length];

		
		for(int i=0;i<timeseries.length;i++)
		{
			System.out.println("The "+i+"th iteration");
			int[] testarr=new int[timeseries[i]];
//			int[] testarr=RandomArray(timeseries[i],1000000);
//			int[] randomArray=new int[timeseries[i]];
			long sumtime=0;
			int besttime=99999;
			
			for(int j=0;j<testarr.length-1;j++)
			{
				testarr[j+1]=j;
			}
			testarr[0]=testarr.length;
			recTimes=0;
			S=testarr.clone();
			selection(0,testarr.length-1,0);
			worst[i]=recTimes;

			for(int j=0;j<testarr.length;j++)
			{
				testarr[j]=j;
			}
			recTimes=0;
			S=testarr.clone();
			selection(0,testarr.length-1,0);
			best[i]=recTimes;
			
			
			for(int k=0;k<20;k++)
			{
				testarr=RandomArray(timeseries[i],1000000);
				for(int j=0;j<testarr.length;j++)
				{
					recTimes=0;
					if(j==testarr.length-1)
						System.out.println();
					S=testarr.clone();
					selection(0,testarr.length-1,j);
	//				System.out.println("time period to find: "+(endTime-startTime));
					sumtime=sumtime+recTimes;
				}
			}
			average[i]=sumtime/(20*testarr.length);
		}
		System.out.println("best: "+Arrays.toString(best));
		System.out.println("average: "+Arrays.toString(average));
		System.out.println("worst: "+Arrays.toString(worst));
		
		
		

	}
	static void test2()
	{	
	}
	static int selection3(int[] W,int low,int high,int k)
	{
		int pivotpoint=0;
		if(low==high)
			return S[low];
		else{
//	        if((high - low + 1)<=0)
//	        	System.out.println();
			pivotpoint=partition3(W,low,high,pivotpoint);
//	        if(pivotpoint==999)
//	        	System.out.println();
			if(k==pivotpoint)
				return S[pivotpoint];
			else if(k<pivotpoint)
				return selection3(W,low,pivotpoint-1,k);
			else
				return selection3(W,pivotpoint+1,high,k);
		}
		
	}
	 static int partition3(int [] S, int left, int right, int pivotpoint) {
        Random r = new Random();
//        if((right - left + 1)<=0)
//        	System.out.println();
        int randSpot = r.nextInt(right - left + 1) + left;
        int pivotItem = S[randSpot];
    	int j=left;
    	int tem=0;
//    	int pivotValue = S[index];
		tem=S[randSpot];
		S[randSpot]=S[left];
		S[left]=tem;
        for (int i = left+1; i <=right; i++) {
            if (S[i] < pivotItem) {
            	recTimes=recTimes+1;
                j=j+1;
        		tem=S[i];
        		S[i]=S[j];
        		S[j]=tem;
            }
        }
        pivotpoint=j;
		tem=S[left];
		S[left]=S[pivotpoint];
		S[pivotpoint]=tem;
    	recTimes=recTimes+1;
        return pivotpoint;
    }
	public static void main(String[] args) {
		int[] timeseries={1000,5000,10000,15000,20000,25000};
		long startTime=0;
		long endTime=0;
		int[] best=new int[timeseries.length];
		int[] worst=new int[timeseries.length];
		int[] average=new int[timeseries.length];
//		test1();
//		System.out.println(median(S,1,7));

		for(int i=0;i<timeseries.length;i++)
		{
			System.out.println("The "+i+"th iteration");
			int sumtime=0;
			int besttime=999999;
			int worsttime=-1;
			int[] T=RandomArray(timeseries[i],10000);
			for(int k=0;k<S.length;k++)
			{
				S=T.clone();
//				startTime=System.currentTimeMillis();
//				System.out.println(selection3(S,0,S.length-1,k));
            	recTimes=0;
				selection3(S,0,S.length-1,k);
//				endTime=System.currentTimeMillis();
//				recTimes=(int)(endTime-startTime);
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
			average[i]=sumtime/S.length;
			System.out.println("average: "+average[i]);
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
//		for(int i=0;i<20;i++)
//		{
//	        Random r = new Random();
//			System.out.println(r.nextInt(10));
//		}
		
	}

}
