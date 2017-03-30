package project2;
import java.util.Arrays;
import java.lang.Math;

public class Floyd1D {
	static int max=99999999;
	public static int[] CreatSparseGraph(int n)
	{
		int array[][]=new int[n][n];
		for (int i=0;i<n;i++)
		{
			int[] count=new int[n];
			int index=0;
			for(int j=0;j<n;j++)
			{
				if(array[j][i]==0&&i!=j)
				{
					count[index]=j;
					index=index+1;
				}
			}
			if(index>0)
			{
				int tem=count[(int)(Math.random()*index)];
				int distance=(int)(Math.random()*100)+1;
				array[i][tem]=distance;
				array[tem][i]=distance;
				
			}
		}
//		for (int i=0;i<array.length;i++)
//		{
//			for(int j=0;j<array.length;j++)
//			{
//				System.out.print(array[i][j]+",");	
//			}
//			System.out.println();
//			
//		}
		int[] removable=new int[n];
		int Rindex=0;
		for (int i=0;i<n;i++)
		{
			int[] count=new int[n];
			int index=0;
			for(int j=0;j<n;j++)
			{
				if(array[j][i]>0)
				{
					count[index]=j;
					index=index+1;
				}
			}
			if(index>=2)
			{
				removable[Rindex]=i;
				Rindex=Rindex+1;
			}
		}
		int removedX=removable[(int)(Math.random()*Rindex)];
		int[] count=new int[n];
		int index=0;
		for(int i=0;i<n;i++)
		{
			if(array[removedX][i]>0)
			{
				count[index]=i;
				index=index+1;
			}
		}
		int removedY=count[(int)(Math.random()*index)];
		array[removedX][removedY]=0;
		array[removedY][removedX]=0;
		for(int i=0;i<n;i++)
		{
			for(int j=0;j<n;j++)
			{
				if(array[i][j]==0)
				{
					array[i][j]=max;
				}
				if(i==j)
				{
					array[i][j]=0;
				}
			}
		}
		int[] D1=new int[(n-1)*n/2];
		//test
		//int[][] testarray={{0,4,max,max,max,10,max},{4,0,6,18,max,max,max},{max,6,0,15,12,max,max},{max,18,15,0,2,19,8},{max,max,12,2,0,max,max},{10,max,max,19,max,0,10},{max,max,max,8,max,10,0}};
		for(int i=0;i<n;i++)
		{
			for(int j=0;j<i;j++)
			{
				D1[(i-1)*i/2+j]=array[i][j];
			}
		}
		return(D1);
	}
	static void Floyd_1D(int[] array)
	{
		int len=(int)(Math.sqrt(2*array.length+0.25)+0.5);
		int[][] P = new int[len][len];
		int[] Distance = new int[array.length];
		for(int i = 0;i<len;i++){		
			for(int j = 0;j<len;j++){
				P[i][j] = 0;
			}
		}
		Distance = array.clone();
		for(int k = 0;k<len;k++){
			for(int i = 0; i<len;i++){
				for(int j = 0;j<len;j++){
					
//					if(i==5&&j==1)
//					{
//						System.out.println();
//					}
//					System.out.println("i:"+i+",j:"+j+",k:"+k);
//					System.out.println("((i)*(i-1)/2+k):"+((i)*(i-1)/2+k));
//					System.out.println("((k)*(k-1)/2+j):"+((k)*(k-1)/2+j));
//					System.out.println("((i)*(i-1)/2+j):"+((i)*(i-1)/2+j));
//					System.out.println("Distance[((i)*(i-1)/2+k)]:"+Distance[((i)*(i-1)/2+k)]);
//					System.out.println("Distance[((k)*(k-1)/2+j)]:"+Distance[((k)*(k-1)/2+j)]);
//					System.out.println("Distance[((i)*(i-1)/2+j)]:"+Distance[((i)*(i-1)/2+j)]);
					if((transer(i,k,Distance)+transer(k,j,Distance))<transer(i,j,Distance)){
						P[i][j] = k+1;
						if(i>j)
							Distance[((i)*(i-1)/2+j)] = (transer(i,k,Distance)+transer(k,j,Distance));
						else
							Distance[((j)*(j-1)/2+i)] = (transer(i,k,Distance)+transer(k,j,Distance));
					}
				}
				
			
			}
		
		}
//		for (int i=0;i<Distance.length;i++)
//		{
//			System.out.println(Distance[i]);
//		}
	}
	public static int transer(int i,int j,int[] Distance)
	{
		int result;
		if(i<j)
			result=Distance[((j)*(j-1)/2+i)];
		else if(i>j)
			result=Distance[((i)*(i-1)/2+j)];
		else
			result=0;
		return result;
					
	}
	public static int[] CreatCompleteGraph(int n)
	{
		int array[][]=new int[n][n];
		for (int i=0;i<n;i++)
		{
			for(int j=i;j<n;j++)
			{
				array[i][j]=(int)(Math.random()*100)+1;
				array[j][i]=array[i][j];
				if(i==j)
				{
					array[i][j]=0;
				}
			}
			
		}
		int[] D1=new int[(n-1)*n/2];
		//test
		int[][] testarray={{0,4,max,max,max,10,max},{4,0,6,18,max,max,max},{max,6,0,15,12,max,max},{max,18,15,0,2,19,8},{max,max,12,2,0,max,max},{10,max,max,19,max,0,10},{max,max,max,8,max,10,0}};
		for(int i=0;i<n;i++)
		{
			for(int j=0;j<i;j++)
			{
				D1[(i-1)*i/2+j]=array[i][j];
			}
		}
		return(D1);
	}
	public static void main(String[] args) {
		//int[][] W = {{0,4,max,max,max,10,max},{4,0,6,18,max,max,max},{max,6,0,15,12,max,max},{max,18,15,0,2,19,8},{max,max,12,2,0,max,max},{10,max,max,19,max,0,10},{max,max,max,8,max,10,0}};
		long startTime;
		long endTime;
		long[] time=new long[16];
		int[] timeseries={10,100,1000,2000,3000,4000,5000,6000,7000,8000,9000,10000,11000,12000,13000};
		for(int i=0;i<10;i++)
		{
			int[] array=CreatSparseGraph(timeseries[i]);
			startTime=System.currentTimeMillis();
			Floyd_1D(array);
			endTime=System.currentTimeMillis();
			time[i]=endTime-startTime;
			System.out.println(time[i]);
		}
		int[] array8=CreatSparseGraph(10);
	}

}
