import java.util.Arrays;

public class MergeSort {

	public static void iterativeMergesortWithoutCopy(int[] a) {
	     int[] from = a, to = new int[a.length];
		 for (int blockSize=1; blockSize<a.length; blockSize*=2) {
			for (int start=0; start<a.length; start+=2*blockSize)
			{
				int mid=start+blockSize;
				int hi=start+2*blockSize;
				int lo=start;
				if (mid > from.length) 
					mid = from.length;
				if (hi > from.length) 
					hi = from.length;
				int i = lo, j = mid;
				for (int k = lo; k < hi; k++) {
				if      (i == mid)          
					to[k] = from[j++];
				else if (j == hi)           
					to[k] = from[i++];
				else if (from[j] < from[i]) 
					to[k] = from[j++];
				else
					to[k] = from[i++];
			  }
			}
			int[] temp = from;
			from = to;
			to = temp;

			System.out.print("The "+((int)(Math.log(blockSize)/Math.log(2))+1)+"th merge: ");
			for(int k=0;k<a.length;k++)
			{
				System.out.print(a[k]+" ");
				if((k+1)%(blockSize*2)==0)
				{
					System.out.print("  ");
				}
			}
			System.out.println();
			
			
		 }
		if (a != from)
			for (int k = 0; k < a.length; k++)
				a[k] = from[k];
		}
	static void mergeSort()
	{
		
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[] arr={86, 2, 91, 24, 59, 53, 97, 10, 1, 85, 25, 78, 40, 94, 23, 7, 88, 92, 21, 46};
		System.out.println("original array: "+Arrays.toString(arr));
		iterativeMergesortWithoutCopy(arr);
		System.out.println("sorted array: "+Arrays.toString(arr));
	}

}
