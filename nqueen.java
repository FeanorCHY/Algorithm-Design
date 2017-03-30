
public class nqueen {
	static int[] col={-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1};
	static int n;
	static int count=0;
	static void queens(int i)
	{
		int j;
		if(promissing(i)){
			if(i==n-1){
				for(int m=0;m<col.length;m++)
					System.out.print(col[m]+" ");
				System.out.println();
				count=count+1;
			}
			else
				for(j=0;j<n;j++)
				{
					col[i+1]=j;
					queens(i+1);
				}
		}
	}
	static boolean promissing(int i)
	{
		int k;
		boolean swit;
		k=0;
		swit=true;
		while(k<i&&swit)
		{
			if((col[i]==col[k]||Math.abs(col[i]-col[k])==i-k)&&col[k]!=-1)
				swit=false;
			k=k+1;
		}
		return swit;
	}
	
	
	public static void main(String[] args) {
		n=col.length;
		for(int i=0;i<col.length;i++){
			for(int j=0;j<col.length;j++){
				col[j]=-1;
			}
			col[0]=i;
			queens(0);
		}
		System.out.println("count="+count);
	}

}
