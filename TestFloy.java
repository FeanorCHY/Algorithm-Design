
public class TestFloy {

	void floyd2(int n,int[][] W,int[][] D,int[][] P)
	{
		int i,j,k;
		for(i=0;i<n;i++)
			for(j=0;j<n;j++)
				P[i][j]=0;
		D=W;
		for(k=0;k<n;k++)
			for(i=0;i<n;i++)
				for(j=0;j<n;j++)
				{
					if(D[i][k]+D[k][j]<D[i][j])
					{
						P[i][j]=k;
						D[i][j]=D[i][k]+D[k][j];
					}
				}
	}
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int

	}

}
