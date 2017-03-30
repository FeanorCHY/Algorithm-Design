
public class Binomial {
	static void bin(int n,int k)
	{
		int[] a=new int[k+1];
		for(int i=0;i<a.length;i++)
			a[i]=0;
		a[0]=1;
		for(int i=0;i<n;i++)
		{
			for(int j=k;j>0;j--)
			{
				a[j]=a[j]+a[j-1];
			}
		}
		System.out.println(a[k]);
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		bin(5,3);
	}

}
