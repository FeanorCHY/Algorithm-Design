import java.util.Arrays;
import java.util.Random;

public class ProbabilisticSelection {
	
	    // Best
		static int []best = {19,18,17,16,15,14,13,12,11,10,9,8,7,6,5,4,3,2,1};
		
		
		// Avg
		static int []avg1 = {2,3,11,1,3,19,5,3,3,7,1,7,9,14,14,17,9,0,16};
		static int []avg2 = {3,13,7,9,13,0,6,7,12,15,17,4,2,8,5,0,12,9,0};
		static int []avg3 = {0,11,14,9,14,15,5,19,13,12,15,6,12,15,11,4,1,0,8};

		
		// Worst
		static int []worst = {1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19};
		
				
		static int []s;
		
		
		public static void main(String[] args) {
			
			s = avg3;
			
			int low = 0;
			int high = s.length - 1;
			int k = 19;
			
			ProbabilisticSelection test = new ProbabilisticSelection();
			
			long a = System.nanoTime();
			int value = test.select(low,high,k-1);

			long b = System.nanoTime() - a;
			
			System.out.println("The "+k+" th smallest element in the array is "+ value); 
			System.out.println("The run time is "+b+" ns");

		}
	


	    public int select(int low, int high, int k) {
	        if (low == high) {
	            return s[low];
	        }
	        else {
	            int pivot = partition(s, low, high, low + 1);
	            if (k == pivot) {
	                return s[pivot];
	            }
	            else if(k < pivot) {
	                return select(low, pivot - 1, k);
	            }
	            else {
	                return select(pivot + 1, high, k);
	            }
	        }
	    }
	    
	    private int partition(int [] s, int left, int right, int index) {
	        Random r = new Random();
	        int randSpot = r.nextInt(right - left + 1) + left;
	        int pivotItem = s[randSpot];
	    	
	    	int pivotValue = s[index];
	        swap(s,index,right);
	        int newPivot = left;
	        for (int i = left; i <= right; ++i) {
	            if (s[i] < pivotValue) {
	                swap(s,newPivot++,i);
	            }
	        }
	        swap(s,right,newPivot);
	        this.s = s;
	        return newPivot;
	    }
	    
	    private static void swap(int [] s, int a, int b) {
	        int temp = s[a];
	        s[a] = s[b];
	        s[b] = temp;
	    }

}
