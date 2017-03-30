import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.TreeSet;
class NodeD
{
	public NodeD(int indexi,HashSet<Integer> set,int dist )
	{
		i=indexi;
		j=set;
		distance=dist;
	}
	public NodeD(){}
	int i;
	int path;
	HashSet<Integer> j= new HashSet<Integer>();
	int distance;
	public NodeD nextNode;
}
class LinkedList_self
{
	public NodeD first=null;
	public NodeD getNode(int i,HashSet<Integer> j)
	{
		NodeD tem=first;
		if(tem.i==i&&tem.j.equals(j))
			return tem;
		while(tem.nextNode!=null)
		{
			tem=tem.nextNode;
			if(tem.i==i&&tem.j.equals(j))
				return tem;
		}
		return null;
	}
	public int getDistance(int i,HashSet<Integer> j)
	{
		NodeD tem=first;
		if(tem.i==i&&tem.j.equals(j))
			return tem.distance;
		while(tem.nextNode!=null)
		{
			tem=tem.nextNode;
			if(tem.i==i&&tem.j.equals(j))
				return tem.distance;
		}
		return 0;
	}
	public void add(NodeD nodeD)
	{
		if(first==null)
		{
			first=nodeD;
			//System.out.println("add first");
		}
		else
		{
			//System.out.println("add next");
			NodeD tem=first;
			while(tem.nextNode!=null)
			{
				tem=tem.nextNode;
			}
			tem.nextNode=nodeD;
		}
	}
	public void print()
	{
		NodeD tem=first;
		System.out.print(tem.distance+" ");
		while(tem.nextNode!=null)
		{
			tem=tem.nextNode;
			System.out.print(tem.distance+" ");
		}
		System.out.println();
	}
}
public class DynamicTSP {
	
	static final int maxvalue=99999;
	static int[][] W={{0,16,16,7,13,6},{16,0,9,5,19,7},{16,9,0,7,9,6},{7,5,7,0,7,7},{13,19,9,7,0,13},{6,17,6,7,13,0}};
//	static int[][] W={{0,6,6,10,8},{3,0,12,7,6},{8,7,0,14,20},{5,13,9,0,8},{9,8,10,6,0}};
//	static int[][] W={{0,2,9,maxvalue},{1,0,6,4},{maxvalue,7,0,8},{6,3,maxvalue,0}};
	static LinkedList<HashSet<Integer>> combin=new LinkedList<HashSet<Integer>>();

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
	
	
	
	static ArrayList<Integer> initArrayList(int[] array,ArrayList<Integer> arrayList)
	{
		for(int i=0;i<array.length;i++)
		{
			arrayList.add(array[i]);
		}
		return arrayList;
	}
	public static void combination(Integer record[], Integer info[], int n, int r, int k1,int k2) 
	{
	if (k1 == r) { 
		HashSet<Integer> tem=new HashSet<Integer>();
		for (int i = 0; i < r; ++i)
		{
			tem.add(info[record[i] - 1]);
//			System.out.print(info[record[i] - 1] + " ");
		}
//		System.out.println();
		combin.add(tem);
	} 
	else
		for (int i = k2; i < n; ++i) {
			record[k1] = i + 1; 
			combination(record, info, n, r, k1 + 1, i + 1); 
		}
	}
	public static void combination(Integer info[], int r) {
		Integer record[] = new Integer[r];
		int n = info.length;
		combination(record, info, n, r, 0, 0);
	}
	
	
	
	
	static void TSP()
	{
		int n=W[0].length;
		HashSet<Integer> V=new HashSet<Integer>();
		HashSet<Integer> set=new HashSet<Integer>();
		for(int i=0;i<n-1;i++)
		{
			set.add(i+1);
			V.add(i+1);
		}
		int k;
		LinkedList_self D=new LinkedList_self();
		for(int i=0;i<n;i++)
		{
			NodeD node=new NodeD(i,new HashSet<Integer>(),W[i][0]);
			D.add(node);
		}
		for(k=1;k<n-1;k++)
		{
			combin.clear();
			Integer[] temo=new Integer[V.size()];
			V.toArray(temo);
			combination(temo,k);
			for(HashSet<Integer> A:combin)
				for(Integer i:set)
				{
					if(!A.contains(i)){
						int min=99999;
						int minj=0;
						HashSet<Integer> tem=(HashSet<Integer>)A.clone();
						for(Integer j:A)
						{
							tem.remove(j);
							if((W[i][j]+D.getDistance(j,tem))<min)
							{
								min=(W[i][j]+D.getDistance(j,tem));
								minj=j;
							}
							tem.add(j);
						}
						System.out.println("D["+i+"]["+A.toString()+"]"+"="+min+",,,from "+minj);
						NodeD node=new NodeD(i,A,min);
						node.path=minj;
						D.add(node);
					}
				}		
		}
		int min=99999;
		int minj=0;
		HashSet<Integer> tem=(HashSet<Integer>)V.clone();
		for(Integer j:V)
		{
			tem.remove(j);
			int x=(W[0][j]+D.getDistance(j,tem));
			if((W[0][j]+D.getDistance(j,tem))<min)
			{
				min=(W[0][j]+D.getDistance(j,tem));
				minj=j;
			}
			tem.add(j);
		}
		NodeD node=new NodeD(0,V,min);
		node.path=minj;
		D.add(node);
		int minilength=D.getDistance(0, V);
//		System.out.println();
		System.out.println("minilength="+minilength);
		System.out.println("shortest path:");
		int start=0;
		while(!V.isEmpty())
		{
			System.out.println("D["+start+"]["+V.toString()+"]"+"="+D.getDistance(start, V)+",,,from "+D.getNode(start, V).path);
			start=D.getNode(start, V).path;
			V.remove(start);
		}
		System.out.println("D["+start+"]["+V.toString()+"]"+"="+D.getDistance(start, V)+",,,from "+D.getNode(start, V).path);
		
	}
	static int D(int i,HashSet<Integer> A)
	{
		HashSet<Integer> tem=(HashSet<Integer>)A.clone();
		TreeSet<Integer> findMin=new TreeSet<Integer>();
		if(A.isEmpty())
		{
			return W[i][0];
		}
		else
		{
			for(Integer q:A)
			{
				tem.remove(q);
				findMin.add((W[i][q]+D(q,tem)));
				tem.add(q);
			}
			return findMin.first();
		}
			
	}
	public static void main(String[] args) {
		
		TSP();
//		
//		long startTime;
//		long endTime;
//		long[] time=new long[16];
//		int[] timeseries={5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,30,40,50,60,70,80,90,100};
//		for(int i=0;i<timeseries.length;i++)
//		{
//			W=CreatCompleteGraph(timeseries[i]);
//			startTime=System.currentTimeMillis();
//			TSP();
//			endTime=System.currentTimeMillis();
//			time[i]=endTime-startTime;
//			System.out.println(time[i]);
//		}
//		W=CreatCompleteGraph(10);
//		for(int i=0;i<W.length;i++)
//		{
//			System.out.println(Arrays.toString(W[i]));
//		}

		
	}

}


//HashSet<Integer> x=new HashSet<Integer>();
//x.add(1);
//x.add(1);
//x.add(1);
//HashSet<Integer> y=new HashSet<Integer>();
//y.add(1);
//y.add(3);
//y.add(2);
//Integer[] in=new Integer[y.size()];
//y.toArray(in);
//System.out.println();
//LinkedList_self ll=new LinkedList_self();
//NodeD node=new NodeD();
//node.i=4;
//node.j=x;
//node.distance=10;
//ll.add(node);
//node=new NodeD();
//node.i=3;
//node.j=new HashSet<Integer>();
//node.distance=2;
//ll.add(node);
//ll.print();
//System.out.println(ll.getDistance(4, new HashSet<Integer>(1)));
//combination(new Integer[] { 1,2,3,4,5}, 1);
//HashSet<Integer> A=new HashSet<Integer>();
//for(int i=1;i<W.length;i++)
//{
//	A.add(i);
//}
//System.out.println(D(0,A));
