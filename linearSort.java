import java.util.Arrays;

public class linearSort {

	static void Sort(int[] W)
	{
		int NW[]=new int[W.length];
		for(int i=0;i<W.length;i++)
		{
			NW[W[i]-1]=W[i];
		}
		System.out.println(Arrays.toString(NW));
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int W[]={5,9,1,6,10,7,2,4,8,3};
		Sort(W);
	}

}
