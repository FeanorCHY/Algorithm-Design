
public class RobustInterpolation {
	
    static int []s = {1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19};
	
	// Best
	static int best = 10;
	
	// Avg
	static int avg1 = 4;
	static int avg2 = 16;
	static int avg3 = 7;
	
	// Worst
	static int worst = 100;
	
	static int x;

	
	 public static int robustInterpolationSearch(int[] s, int key){

		  int low = 0;
		  int high = s.length - 1;
		  int mid = (low + high)/2;
		  
		  int gap = (int)Math.sqrt((high - low + 1));

		 
		  while (s[low] <= key && s[high] >= key) {
			  
			  if (s[high] - s[low] == 0){
				  return (low + high)/2;
			  }
			  mid = minimum(high - gap,maximum(mid,low + gap));  
		 
			  if (s[mid] < key){
				 low = mid + 1;
			  }else if (s[mid] > key){
				  high = mid - 1;
		      }else{
			   return mid;
			  }
		 }
		 
		 if (s[low] == key){
			 return low;
		 }else{
			 return -1; // Not found
		 }
	}
	 
	private static int minimum(int a, int b) {
		if (a < b){
			return a;
		}else{
			return b;
		}
	}
	
	private static int maximum(int a, int b) {
		if (a > b){
			return a;
		}else{
			return b;
		}
	}
	
	public static void main(String[] args) {
		
		int x = avg3;
		
		long a = System.nanoTime();
		int position = InterpolationSearch.interpolationSearch(s,x);
		long b = System.nanoTime() - a;
		
		if (position == -1){
			System.out.println("Not found");
			System.out.println("The run time is "+b+" ns");
		}else{
			System.out.println("The element ["+x+"] you want is on "+(position + 1)+" position of the array"); 
			System.out.println("The run time is "+b+" ns");
		}

	}


}
