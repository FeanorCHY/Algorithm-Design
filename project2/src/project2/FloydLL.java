package project2;
import java.util.Arrays;
import java.lang.Math;

class Node
{
	public int indX;
	public int indY;
	public int distance;
	public Node nextNode;
	public Node(int i,int j,int d)
	{
		indX=i;
		indY=j;
		distance=d;
	}
}
class LinkedList
{
	public int getDistance(int i,int j)
	{
		if (i<j)
		{
			int tem=i;
			i=j;
			j=tem;	
		}
		else if(i==j)
			return 0;
		Node tem=first;
		if(tem.indX==i&&tem.indY==j)
			return tem.distance;
		while(tem.nextNode!=null)
		{
			tem=tem.nextNode;
			if(tem.indX==i&&tem.indY==j)
				return tem.distance;
		}
		return 0;
	}
	public Node find(int i,int j)
	{
		if (i<j)
		{
			int tem=i;
			i=j;
			j=tem;	
		}
		Node tem=first;
		if(tem.indX==i&&tem.indY==j)
			return tem;
		while(tem.nextNode!=null)
		{
			tem=tem.nextNode;
			if(tem.indX==i&&tem.indY==j)
				return tem;
		}
		return tem;
	}
	public Node first=null;
	public int getLength()
	{
		Node tem=first;
		int length=0;
		while(tem.nextNode!=null)
		{
			length=length+1;
			tem=tem.nextNode;
		}
		if(first!=null)
			length=length+1;
		return length;
	}
	public void add(Node node)
	{
		if(first==null)
		{
			first=node;
			//System.out.println("add first");
		}
		else
		{
			//System.out.println("add next");
			Node tem=first;
			while(tem.nextNode!=null)
			{
				tem=tem.nextNode;
			}
			tem.nextNode=node;
		}
	}
	public void print()
	{
		Node tem=first;
		System.out.print(tem.distance+" ");
		while(tem.nextNode!=null)
		{
			tem=tem.nextNode;
			System.out.print(tem.distance+" ");
		}
		System.out.println();
	}
}
public class FloydLL {
	static int max=99999999;
	public static LinkedList CreatSparseGraph(int n)
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
		LinkedList ll=new LinkedList();
		//test
		int[][] testarray={{0,4,max,max,max,10,max},{4,0,6,18,max,max,max},{max,6,0,15,12,max,max},{max,18,15,0,2,19,8},{max,max,12,2,0,max,max},{10,max,max,19,max,0,10},{max,max,max,8,max,10,0}};
		for(int i=0;i<n;i++)
		{
			for(int j=0;j<i;j++)
			{
				int tem=array[i][j];
				ll.add(new Node(i,j,tem));
			}
		}
		return(ll);
	}
	static void Floyd_LL(LinkedList ll)
	{
		int len=(int)(Math.sqrt(2*ll.getLength()+0.25)+0.5);
		int[][] P = new int[len][len];
		LinkedList Distance = ll;
		for(int i = 0;i<len;i++){		
			for(int j = 0;j<len;j++){
				P[i][j] = 0;
			}
		}
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
					//Distance.print();
					if((Distance.getDistance(i, k)+Distance.getDistance(k, j))<Distance.getDistance(i, j)){
						P[i][j] = k+1;
						Distance.find(i, j).distance = Distance.find(i, k).distance+Distance.find(k, j).distance;
					}
				}
				
			
			}
		
		}
		//Distance.print();
		
	}
//	public static int transer(int i,int j,LinkedList Distance)
//	{
//		int result;
//		if(i<j)
//			result=Distance[((j)*(j-1)/2+i)];
//		else if(i>j)
//			result=Distance[((i)*(i-1)/2+j)];
//		else
//			result=0;
//		return result;
//					
//	}
	public static LinkedList CreatCompleteGraph(int n)
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
		LinkedList ll=new LinkedList();
		//test
		int[][] testarray={{0,4,max,max,max,10,max},{4,0,6,18,max,max,max},{max,6,0,15,12,max,max},{max,18,15,0,2,19,8},{max,max,12,2,0,max,max},{10,max,max,19,max,0,10},{max,max,max,8,max,10,0}};
		for(int i=0;i<n;i++)
		{
			for(int j=0;j<i;j++)
			{
				int tem=array[i][j];
				ll.add(new Node(i,j,tem));
			}
		}
		return(ll);
	}
	public static void main(String[] args) {
		long startTime;
		long endTime;
		long[] time=new long[16];
		int[] timeseries={10,30,50,80,100,120,160};
		for(int i=0;i<7;i++)
		{
			LinkedList array=CreatCompleteGraph(timeseries[i]);
			startTime=System.currentTimeMillis();
			Floyd_LL(array);
			endTime=System.currentTimeMillis();
			time[i]=endTime-startTime;
			System.out.println(time[i]);
		}
		
	}

}
