import java.util.Arrays;

public class BackT {
	static int W=9;
	static int maxprofit=0;
	static int numbest=0;
	static String[] bestset=new String[5];
	static String[] include=new String[5];
	static int[] p={0,20,30,35,12,3};
	static int[] w={0,2,5,7,3,1};
	static int sumweight=0;
	static int sumprofit=0;
	static void knapsack(int i,int profit,int weight)
	{
		System.out.println("profit="+profit);
		System.out.println("weight="+weight);
		System.out.println("include:"+Arrays.toString(include));
		System.out.println();
		if(weight <= W &&profit>maxprofit)
		{
			maxprofit=profit;
			numbest=i;
			bestset=include.clone();
			System.out.println("maxprofit="+maxprofit);
			System.out.println("bestset:"+Arrays.toString(bestset));
			System.out.println();
		}
		sumweight=weight;
		sumprofit=profit;
		if(promissing(i))
		{
			include[i]="yes";
			knapsack(i+1,profit+p[i+1],weight+w[i+1]);
			include[i]="no";
			knapsack(i+1,profit,weight);
		}
//		else{
//			sumweight=sumweight-w[i];
//			sumprofit=sumprofit-p[i];
//		}
	}
	static boolean promissing(int i)
	{
		int n=5;
		int j,k;
		int totweight;
		float bound;
		if(sumweight>=W)
			return false;
		else
		{
			j=i+1;
			bound=sumprofit;
			totweight=sumweight;
			while(j<=n && totweight+w[j]<=W)
			{
				totweight=totweight+w[j];
				bound=bound+p[j];
				j=j+1;
			}
			k=j;
			if(k<=n)
				bound=bound+(W-totweight)*p[k]/w[k];
			return bound>maxprofit;
		}
	}
	
	public static void main(String[] args) {
		knapsack(0,0,0);
		System.out.println(maxprofit);
		System.out.println(Arrays.toString(bestset));
	}

}
