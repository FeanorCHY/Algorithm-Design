package project2;
public  class Dijkstra1D{
	private static int  dist[] ;
	private static int  prve[];
	static int  max=99999999;
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
	//use the index in the 2-D array to get the distance in the 1-D array 
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
	public static void dijkstra(int  v,int[] a,int  dist[],int  prve[]){
		int  n=dist.length-1;
		boolean[]s=new boolean[n+1];
		for(int i=0;i<=n;i++){
			dist[i]=transer(v,i,a);
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
				if((!s[j])&&transer(u,j,a)<Integer.MAX_VALUE){
					int newDist=dist[u]+transer(u,j,a);
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
		int[] timeseries={10,100,1000,2000,3000,4000,5000,6000,7000,8000,9000,10000,11000,12000,13000};
		for(int i=0;i<15;i++)
		{
			dist=new int[timeseries[i]];
			prve=new int[timeseries[i]];
			int[] array=CreatSparseGraph(timeseries[i]);
			startTime=System.currentTimeMillis();
			dijkstra(3,array,prve,prve);
			endTime=System.currentTimeMillis();
			time[i]=endTime-startTime;
			System.out.println(time[i]);
		}
	}
}