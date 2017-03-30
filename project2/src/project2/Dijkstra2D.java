package project2;
public  class Dijkstra2D{
	private static int  dist[] ;//shortest path weight
	private static int  prve[];//passing nodes
	static int  max=99999999;
	//test case
	static int testcase1[][] = {{max, max, 19, max, max, max, max, max, 7, max}, 
			{max, max, 11, max, max, max, max, 11, max, max}, 
			{19, 11, max, max, 9, max, max, max, max, max}, 
			{max, max, max, max, max, max, 11, max, max, 6}, 
			{max, max, 9, max, max, max, max, max, max, 13}, 
			{max, max, max, max, max, max, 17, max, max, 10}, 
			{max, max, max, 11, max, 17, max, max, max, max}, 
			{max, 11, max, max, max, max, max, max, max, max}, 
			{7, max, max, max, max, max, max, max, max, 6}, 
			{max, max, max, 6, 13, 10, max, max, 6, max}};
	
	
	static int testcase2[][] = {{max, max, max, max, max, max, max, max, max, 8, max, max,},
			{max, max, 17, 13, max, max, max, 17, 12, max, max, max,},
			{max, 17, max, max, max, max, max, max, max, 7, max, 9},
			{max, 13, max, max, max, max, 15, max, max, 13, 5, 13},
			{max, max, max, max, max, 18, max, max, 5, max, max, max,},
			{max, max, max, max, 18, max, max, 17, max, max, max, max,},
			{max, max, max, 15, max, max, max, 17, max, max, 8, 11},
			{max, 17, max, max, max, 17, 17, max, max, max, max, max,},
			{max, 12, max, max, 5, max, max, max, max, max, 15, max,},
			{8, max, 7, 13, max, max, max, max, max, max, max, 14},
			{max, max, max, 5, max, max, 8, max, 15, max, max, max,},
			{max, max, 9, 13, max, max, 11, max, max, 14, max, max,}};
	//create comple graph with random weight
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
	
	//create Sparse graph with random weight
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
		}//create n edges for each node to the node not connecting to 
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
		}//find all nodes which has more than 2 edges
		int removedX=removable[(int)(Math.random()*Rindex)];//randomly choose a node removable for one of its edges
		int[] count=new int[n];
		int index=0;
		for(int i=0;i<n;i++)
		{
			if(array[removedX][i]>0)
			{
				count[index]=i;
				index=index+1;
			}
		}//find all edges for chosen nodes
		int removedY=count[(int)(Math.random()*index)];//randomly choose an edge to remove
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
		}//fill other weights
		return array;
	}
	public static void dijkstra(int  v,int [][]a,int  dist[],int  prve[]){
		int  n=dist.length-1;
		boolean[]s=new boolean[n+1];
		for(int i=0;i<=n;i++){
			dist[i]=a[v][i];
			s[i]=false;
			if(dist[i]<Integer.MAX_VALUE)
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
				if((!s[j])&&a[u][j]<Integer.MAX_VALUE){
					int newDist=dist[u]+a[u][j];
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
				System.out.print ("v"+(i+1)+"<--");
				int next=p[i];
				while(next!=m){
					System.out.print ("v"+(next+1)+"<--");
					next=p[next];
				}
				System.out.println("v"+(m+1)+":"+d[i]);
			}
			else
				if(i!=m)
					System.out.println("v"+(i+1)+"<--"+"v"+(m+1)+":nopath");
		}
	}
	
	public static void main(String[] args){
		//int array[][]={{0,4,max,max,max,10,max},{4,0,6,18,max,max,max},{max,6,0,15,12,max,max},{max,18,15,0,2,19,8},{max,max,12,2,0,max,max},{10,max,max,19,max,0,10},{max,max,max,8,max,10,0}};
		//int[][] k=CreatCompleteGraph(7);
//		dijkstra(1,k,dist,prve);
//		outPath(1,prve,dist);
		dist=new int[testcase2[0].length];
		prve=new int[testcase2[0].length];
		dijkstra(4,testcase2,dist,prve);
		outPath(4,prve,dist);
//		long startTime;
//		long endTime;
//		long[] time=new long[16];
		int[] timeseries={10,100,1000,2000,3000,4000,5000,6000,7000,8000,9000,10000,11000,12000,13000};//create the array of the number of nodes used for testing
//		for(int i=0;i<15;i++)
//		{
//			dist=new int[timeseries[i]];
//			prve=new int[timeseries[i]];
//			int[][] array=CreatSparseGraph(timeseries[i]);
//			startTime=System.currentTimeMillis();
//			dijkstra(3,array,prve,prve);
//			endTime=System.currentTimeMillis();
//			time[i]=endTime-startTime;
//			System.out.println(time[i]);
//		}
	}
}