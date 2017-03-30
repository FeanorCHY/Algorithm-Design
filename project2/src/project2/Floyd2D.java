package project2;
import java.util.Arrays;
import java.lang.Math;

public class Floyd2D {
	static int max=99999999;
	public static int[][] CreatCompleteGraph(int n)
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
		return array;
		
	}
	
	public static int[][] CreatSparseGraph(int n)
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
		return array;
	}
	static int[][] Floyd_2D(int[][] array)
	{
		
		int[][] P = new int[array.length][array.length];
		int[][] Distance = new int[array.length][array.length];
		for(int i = 0;i<array.length;i++){		
			for(int j = 0;j<array[i].length;j++){
				P[i][j] = 0;
			}
		}
		Distance = array.clone();
		for(int k = 0;k<array.length;k++){
			for(int i = 0; i<array.length;i++){
				for(int j = 0;j<array[i].length;j++){
//					System.out.println("Distance[i][k]:"+Distance[i][k]);
//					System.out.println("Distance[k][j]:"+Distance[k][j]);
//					System.out.println("Distance[i][j]:"+Distance[i][j]);
					
					if(Distance[i][k]+Distance[k][j]<Distance[i][j]){
						P[i][j] = k+1;
						Distance[i][j] = Distance[i][k]+Distance[k][j];
					}
				
				}
			
			}
		
		}
//		for (int i=0;i<array.length;i++)
//		{
//			System.out.println(Arrays.toString(Distance[i]));
//		}
		return P;
	}

	public static void main(String[] args) {//100, 1000, 2000, 3000, 4000, 5000 and 6000 nodes
//		long startTime;
//		long endTime;
//		long[] time=new long[16];
//		int[] timeseries={10,100,1000,2000,3000,4000,5000,6000,7000,8000,9000,10000,11000,12000,13000};
//		for(int i=0;i<10;i++)
//		{
//			int[][] array=CreatSparseGraph(timeseries[i]);
//			startTime=System.currentTimeMillis();
//			Floyd_2D(array);
//			endTime=System.currentTimeMillis();
//			time[i]=endTime-startTime;
//			System.out.println(time[i]);
//		}
//		int[][] array8=CreatSparseGraph(10);
		int max=9999;
		int[][] array={{0,4,max,max,max,10,max},{4,0,6,18,max,max,max},{max,6,0,15,12,max,max},{max,18,15,0,2,19,8},{max,max,12,2,0,max,max},{10,max,max,19,max,0,10},{max,max,max,8,max,10,0}};
		
		int[][] P=Floyd_2D(array);
		for(int i=0;i<P[0].length;i++)
		{
			System.out.println(Arrays.toString(P[i]));
		}
		
		//int[][] W = {{0,4,max,max,max,10,max},{4,0,6,18,max,max,max},{max,6,0,15,12,max,max},{max,18,15,0,2,19,8},{max,max,12,2,0,max,max},{10,max,max,19,max,0,10},{max,max,max,8,max,10,0}};
		
	}

}
