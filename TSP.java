import java.util.ArrayList;
import java.util.Set;
import java.util.TreeSet;
import java.util.LinkedList;



class Node implements Comparable<Node>
{
	int level=0;
	LinkedList<Integer> path=new LinkedList<Integer>();;
	int bound;
	@Override
	public int compareTo(Node o)
	{
	if(this.bound==o.bound&&this.path.getLast()==o.path.getLast())
		return 0;
	else if(this.bound>o.bound)
		return 1;
	else if(this.bound<o.bound)
		return -1;
	else if(this.bound==o.bound&&this.path.getLast()>o.path.getLast())
		return 1;
	else if(this.bound==o.bound&&this.path.getLast()<o.path.getLast())
		return 0;
	else
		return 1;
	}	
}

public class TSP {
	
	
	
	
	
	static Node bestnode=new Node();
	static final int maxLen=99999;
//	static int[][] W={{0,16,16,7,13,6},{16,0,9,5,19,7},{16,9,0,7,9,6},{7,5,7,0,7,7},{13,19,9,7,0,13},{6,17,6,7,13,0}};
//	static int[][] W={{maxLen,18,13,19,maxLen},{28,maxLen,10,24,11},{25,7,maxLen,18,maxLen},{maxLen,maxLen,24,maxLen,6},{12,16,19,maxLen, maxLen}};
//	static int[][] W={{maxLen,maxLen,10,9,23,maxLen,12,maxLen},{25,maxLen,27,15,maxLen,24,16,6},{maxLen,10,maxLen,5,11,28,maxLen,maxLen},{20,6,maxLen,maxLen,23,maxLen,10,maxLen},{6,7,maxLen,maxLen,maxLen,maxLen,15,15},{11,22,maxLen,maxLen,maxLen,maxLen,28,maxLen},{15,9,16,14,7,maxLen,maxLen,26},{21,maxLen,maxLen,12,maxLen,8,28,maxLen}};
	static int[][] W={{maxLen,12,6,12,24,maxLen,maxLen,26,24,29,25,maxLen,maxLen,24,26,17},{maxLen,maxLen,maxLen,maxLen,maxLen,maxLen,11,5,maxLen,18,10,24,maxLen,28,maxLen,15},{maxLen,maxLen,maxLen,13,maxLen,19,28,25,25,maxLen,28,8,maxLen,maxLen,28,maxLen},{maxLen,maxLen,5,maxLen,22,maxLen,18,maxLen,23,11,maxLen,maxLen,14,27,7,25},{12,maxLen,maxLen,14,maxLen,10,21,27,11,maxLen,12,18,9,6,10,7},{15,7,12,maxLen,17,maxLen,maxLen,maxLen,maxLen,13,13,17,7,maxLen,maxLen,19},{13,12,25,10,10,maxLen,maxLen,29,9,13,11,17,18,21,23,maxLen},{maxLen,maxLen,maxLen,27,maxLen,11,22,maxLen,13,26,maxLen,7,11,17,14,22},{6,maxLen,maxLen,maxLen,maxLen,17,20,11,maxLen,19,maxLen,27,maxLen,26,maxLen,16},{maxLen,maxLen,8,maxLen,5,maxLen,24,maxLen,24,maxLen,maxLen,26,19,10,9,25},{16,maxLen,22,maxLen,maxLen,28,11,11,26,6,maxLen,17,26,27,17,22},{11,maxLen,maxLen,maxLen,23,12,6,29,24,12,12,maxLen,23,27,20,maxLen},{11,29,28,maxLen,12,14,8,28,26,maxLen,maxLen,8,maxLen,25,15,5},{15,24,19,maxLen,maxLen,maxLen,22,maxLen,maxLen,maxLen,maxLen,maxLen,maxLen,maxLen,8,maxLen},{24,maxLen,maxLen,14,7,25,maxLen,7,26,maxLen,maxLen,maxLen,10,7,maxLen,23},{maxLen,20,27,21,maxLen,10,17,19,22,maxLen,maxLen,13,28,9,10,maxLen}};
//	static int[][] W={{0,6,6,10,8},{3,0,12,7,6},{8,7,0,14,20},{5,13,9,0,8},{9,8,10,6,0}};
//	static int[][] W={{0,2,9,maxvalue},{1,0,6,4},{maxvalue,7,0,8},{6,3,maxvalue,0}};
	
	static TreeSet<Integer> all=new TreeSet<Integer>();
	
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
	
	
	
	
	static int minimum(int[] input)
	{
		int tem=Integer.MAX_VALUE;
		for(int i=0;i<input.length;i++)
		{
			if(input[i]<tem)
				tem=input[i];
		}
		return tem;
	}
	static int bound(Node o)
	{
		int sumBound=0;
		TreeSet<Integer> tem=(TreeSet)all.clone();
		for(int i=0;i<o.path.size()-1;i++)
		{
			sumBound=sumBound+W[o.path.get(i)][o.path.get(i+1)];
		}
		for(int i=0;i<o.path.size();i++)
		{
			tem.remove(o.path.get(i));
		}
		int[] remodedW=W[o.path.getLast()].clone();
		for(Integer i:o.path)
		{
			//if(i!=o.path.getFirst())
			remodedW[i]=Integer.MAX_VALUE;
		}
		sumBound=sumBound+minimum(remodedW);
		//sumBound=sumBound+W[o.path.getLast()];
		for(Integer i:tem)
		{
			remodedW=W[i].clone();
			remodedW[i]=Integer.MAX_VALUE;
			for(Integer j:o.path)
			{
				if(j!=o.path.getFirst())
					remodedW[j]=Integer.MAX_VALUE;
				
			}
			sumBound=sumBound+minimum(remodedW);
		}
//		if(!tem.isEmpty())
//		{
//			sumBound=sumBound+
//			while()
//		}
		return sumBound;
	}
	static void travel2()
	{
		ArrayList<Node> PQ=new ArrayList<Node>();
		Node u=new Node();
		Node v=new Node();
		v.level=0;
		v.path.add(0);
		int minlength=99999;
		PQ.add(v);
		while(!PQ.isEmpty())
		{
//			v=PQ.first();
			int minbound=99999;
			int mini=0;
			for(int i=0;i<PQ.size();i++)
			{
				if(PQ.get(i).bound<minbound)
				{
					mini=i;
					minbound=PQ.get(i).bound;
				}
//				System.out.print(i.bound+" ");
			}
			v=PQ.get(mini);
//			System.out.println();
			for(Node n:PQ)
			{
//				System.out.println("bound="+n.bound);
//				System.out.println("path="+n.path);
//				System.out.println();
			}
			PQ.remove(v);
//			System.out.println("size after remove:"+PQ.size());
//			for(Node i:PQ)
//			{
//				System.out.print(i.bound+" ");
//			}
//			System.out.println();
			if(v.bound<minlength)
			{
				TreeSet<Integer> tem=(TreeSet)all.clone();
//				u.level=v.level+1;
				for(int i=0;i<v.path.size();i++)
				{
					tem.remove(v.path.get(i));
				}
				for(Integer i:tem)
				{
					u=new Node();
					u.path=(LinkedList)v.path.clone();
					u.path.add(i);
//					System.out.println("Bound="+bound(u));
//					System.out.println("Path="+u.path.toString());
//					System.out.println();
					if(u.path.size()==W[0].length-1)
					{
						if(bound(u)<minlength)
						{
							TreeSet<Integer> tem2=(TreeSet)all.clone();
							for(int j=0;j<u.path.size();j++)
							{
								tem2.remove(u.path.get(j));
							}
							minlength=bound(u);
							u.bound=bound(u);
							System.out.println("minlength="+minlength);
							u.path.add(tem2.first());
							u.path.add(u.path.getFirst());
							System.out.println("BestPath="+u.path);
							System.out.println();
							bestnode=u;
//							opttour=(LinkedList)u.path.clone();
						}
					}
					else
					{
						u.bound=bound(u);
						if(u.bound<minlength){
							PQ.add(u);
							//System.out.println("size after add:"+PQ.size());
						}
					}
				}
			}
		}
	}
	public static void main(String[] args) {

		
//		long startTime;
//		long endTime;
//		long[] time=new long[16];
//		int[] timeseries={5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,30,40,50,60,70,80,90,100};
//		for(int i=0;i<timeseries.length;i++)
//		{
//			W=CreatCompleteGraph(timeseries[i]);
//			for(int j=0;j<W[1].length;j++)
//			{
//				all.add(j);
//			}
//			startTime=System.currentTimeMillis();
//			travel2();
//			endTime=System.currentTimeMillis();
//			time[i]=endTime-startTime;
//			System.out.println(time[i]);
//		}
		for(int j=0;j<W[1].length;j++)
		{
			all.add(j);
		}
		travel2();
		
		
		
//		travel2();
		

//		System.out.println("minlength="+bestnode.bound);
//		System.out.println("BestPath="+bestnode.path);
//		System.out.println();
		
//		Node u=new Node();
//		u.bound=6;
//		TreeSet<Node> set=new TreeSet<Node>();
//		set.add(u);
//		u=new Node();
//		u.bound=1;
//		set.add(u);
//		u=new Node();
//		u.bound=3;
//		set.add(u);
//		u=new Node();
//		u.bound=2;
//		set.add(u);
//		for(Node i:set)
//		{
//			System.out.println(i.bound+" ");
//		}
//		Node u=new Node();
//		//u.path.add(3);
//		u.path.add(0);
//		u.path.add(2);
//		u.path.add(1);
//		u.path.add(4);
//		u.bound=bound(u);
//		System.out.println(u.bound);
		

	}

}



