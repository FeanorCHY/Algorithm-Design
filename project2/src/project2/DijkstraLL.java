package project2;
import java.lang.Math;
class DNode
{
	public int indX;
	public int indY;
	public int distance;
	public DNode nextDNode;
	public DNode(int i,int j,int d)
	{
		indX=i;
		indY=j;
		distance=d;
	}
}
class DLinkedList
{
	//get distance given the index of this distance in the 2-D array
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
		DNode tem=first;
		if(tem.indX==i&&tem.indY==j)
			return tem.distance;
		while(tem.nextDNode!=null)
		{
			tem=tem.nextDNode;
			if(tem.indX==i&&tem.indY==j)
				return tem.distance;
		}
		return 0;
	}
	//get node given the index of this distance in the 2-D array
	public DNode find(int i,int j)
	{
		if (i<j)
		{
			int tem=i;
			i=j;
			j=tem;	
		}
		DNode tem=first;
		if(tem.indX==i&&tem.indY==j)
			return tem;
		while(tem.nextDNode!=null)
		{
			tem=tem.nextDNode;
			if(tem.indX==i&&tem.indY==j)
				return tem;
		}
		return tem;
	}
	public DNode first=null;
	//get the length of the linkedlist
	public int getLength()
	{
		DNode tem=first;
		int length=0;
		while(tem.nextDNode!=null)
		{
			length=length+1;
			tem=tem.nextDNode;
		}
		if(first!=null)
			length=length+1;
		return length;
	}
	//add a new node
	public void add(DNode node)
	{
		if(first==null)
		{
			first=node;
			//System.out.println("add first");
		}
		else
		{
			//System.out.println("add next");
			DNode tem=first;
			while(tem.nextDNode!=null)
			{
				tem=tem.nextDNode;
			}
			tem.nextDNode=node;
		}
	}
	//print all distance stored in the linked list
	public void print()
	{
		DNode tem=first;
		System.out.print(tem.distance+" ");
		while(tem.nextDNode!=null)
		{
			tem=tem.nextDNode;
			System.out.print(tem.distance+" ");
		}
		System.out.println();
	}
}

public  class DijkstraLL{
	private static int  dist[] ;
	private static int  prve[];
	static int  max=99999999;
	
	public static DLinkedList CreatSparseGraph(int n)
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
		DLinkedList ll=new DLinkedList();
		//test
		int[][] testarray={{0,4,max,max,max,10,max},{4,0,6,18,max,max,max},{max,6,0,15,12,max,max},{max,18,15,0,2,19,8},{max,max,12,2,0,max,max},{10,max,max,19,max,0,10},{max,max,max,8,max,10,0}};
		for(int i=0;i<n;i++)
		{
			for(int j=0;j<i;j++)
			{
				int tem=array[i][j];
				ll.add(new DNode(i,j,tem));
			}
		}
		return(ll);
	}
	public static DLinkedList CreatCompleteGraph(int n)
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
		DLinkedList ll=new DLinkedList();
		//test
		int[][] testarray={{0,4,max,max,max,10,max},{4,0,6,18,max,max,max},{max,6,0,15,12,max,max},{max,18,15,0,2,19,8},{max,max,12,2,0,max,max},{10,max,max,19,max,0,10},{max,max,max,8,max,10,0}};
		for(int i=0;i<n;i++)
		{
			for(int j=0;j<i;j++)
			{
				int tem=array[i][j];
				ll.add(new DNode(i,j,tem));
			}
		}
		return(ll);
	}
	
	public static void dijkstra(int  v,DLinkedList a,int  dist[],int  prve[]){
		int  n=dist.length-1;
		boolean[]s=new boolean[n+1];
		for(int i=0;i<=n;i++){
			dist[i]=a.getDistance(v, i);
			s[i]=false;if(dist[i]<Integer.MAX_VALUE)
				prve[i]=v;
			else
				prve[i]=-1;
		}
			
		dist[v]=0;
		s[v]=true;
		for(int i=0;i<=n;i++){
			int temp=Integer.MAX_VALUE;
			int u=v;
			for(int j=0;j<=n;j++){
				if((!s[j])&&dist[j]<temp){
					u=j;
					temp=dist[j];
				}
			}
			s[u]=true;
			for(int  j=0;j<=n;j++){
				if((!s[j])&&a.getDistance(u, j)<Integer.MAX_VALUE){
					int newDist=dist[u]+a.getDistance(u, j);
					if(newDist<dist[j]){
						dist[j]=newDist;
						prve[j]=u;
					}
				}
			}
			//System.out.print("s");
		}
	}
	public static void outPath(int  m,int [] p,int [] d){
		for(int i=0;i<dist.length;i++){
			if(d[i]<Integer.MAX_VALUE&&i!=m){
				System.out.print ("v"+i+"<--");
				int next=p[i];
				while(next!=m){
					System.out.print ("v"+next+"<--");
					next=p[next];
				}
				System.out.println("v"+m+":"+d[i]);
			}
			else
				if(i!=m)
					System.out.println("v"+i+"<--"+"v"+m+":nopath");
		}
	}
	
	public static void main(String[] args){
		//int array[][]={{0,4,max,max,max,10,max},{4,0,6,18,max,max,max},{max,6,0,15,12,max,max},{max,18,15,0,2,19,8},{max,max,12,2,0,max,max},{10,max,max,19,max,0,10},{max,max,max,8,max,10,0}};
				//int[] k=CreatCompleteGraph(7);
				//int len=(int)(Math.sqrt(2*array.length+0.25)+0.5);
				long startTime;
				long endTime;
				long[] time=new long[16];
				int[] timeseries={10,30,50,80,100,120,160,200};//create the array of the number of nodes used for testing
				for(int i=0;i<8;i++)
				{
					dist=new int[timeseries[i]];
					prve=new int[timeseries[i]];
					DLinkedList array=CreatSparseGraph(timeseries[i]);
					startTime=System.currentTimeMillis();
					dijkstra(3,array,prve,prve);
					endTime=System.currentTimeMillis();
					time[i]=endTime-startTime;
					System.out.println(time[i]);
				}
	}
}