import java.util.HashSet;
import java.util.LinkedList;
import java.util.Arrays;

public class MonteCarlo {

	static int[] col={0,-1,-1,-1,-1,-1,-1,-1};
	static int SumNumnodes=0;
	static int estimate_n_queens(int n)
	{
		int i,j;
		int m,mprod,numnodes;
		LinkedList<Integer> prom_schildren=new LinkedList<Integer>();
		i=0;
		numnodes=1;
		m=1;
		mprod=1;
		while(m!=0&&i!=n)
		{
			mprod=mprod*m;
			numnodes=numnodes+mprod*n;
			m=0;
			prom_schildren.clear();
			for(j=0;j<n;j++)
			{
				col[i]=j;
				if(promising(i))
				{
					m=m+1;
					prom_schildren.add(j);
				}
			}
			if(m!=0)
			{
				j=prom_schildren.get((int)Math.floor(prom_schildren.size()*Math.random()));
				col[i]=j;
			}
			i=i+1;
		}
		return numnodes;
	}
	
	static boolean promising(int i)
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
	
	static void run(int len)
	{
		int numnodes=0;
		for(int k=0;k<len;k++)
		{

			// TODO Auto-generated method stub
			numnodes=estimate_n_queens(col.length);
			boolean havemax=false;
			boolean havemaxtwice=false;
			for(int i=0;i<col.length;i++)
			{
				if(havemax)
					if(col[i]==col.length-1)
						havemaxtwice=true;
				if(col[i]==col.length-1)
					havemax=true;
			}
			if((col[col.length-1])==-1||havemaxtwice)
				System.out.println("unsuccessful");
			else
				System.out.println("col="+Arrays.toString(col));
			System.out.println("numnodes="+numnodes);
			SumNumnodes=SumNumnodes+numnodes;
		}
		System.out.println("average numnodes="+SumNumnodes/20);
	}
	public static void main(String[] args) {
		run(20);
	}

}
