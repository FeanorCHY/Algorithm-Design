
public class MedianSelection {

	// Best
	static int []best = {19,18,17,16,15,14,13,12,11,10,9,8,7,6,5,4,3,2,1};
	
	
	// Avg
	static int []avg1 = {2,3,11,1,3,19,5,3,3,7,1,7,9,14,14,17,9,0,16};
	static int []avg2 = {3,13,7,9,13,0,6,7,12,15,17,4,2,8,5,0,12,9,0};
	static int []avg3 = {0,11,14,9,14,15,5,19,13,12,15,6,12,15,11,4,1,0,8};

	
	// Worst
	static int []worst = {1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19};
	
	
	
	static int []s;
	int pivotPoint;
	
	
	public static void main(String[] args) {
		
		s = avg3;
		
		int low = 0;
		int high = s.length - 1;
		int k = 19;
		
		MedianSelection test = new MedianSelection();
		
		long a = System.nanoTime();
		int value = test.select(s, k-1);

		long b = System.nanoTime() - a;
		
		System.out.println("The "+k+" th smallest element in the array is "+ value); 
		System.out.println("The run time is "+b+" ns");

	}
	
	public int select(int []s,int k){
		return selection2(s,k);
	}

    public static int selection2(int[] a, int k) {
        final int n = a.length;
        int i,j,l,m;
        int x;
        l=0;
        m = n-1;
        while (l<m) {
            x=a[k];
            i=l;
            j=m;
            do {
                while (a[i]<x)
                    i++;
                while (x<a[j])
                    j--;
                if (i<=j) {
                    final int temp = a[i];
                    a[i]=a[j];
                    a[j]=temp;
                    
                    i++;
                    j--;
                }
            } while (i<=j);
            if (j<k) l=i;
            if (k<i) m=j;
        }
        return a[k];
    }
    
    public int partition2(int []s,int low,int high){
		final int arraySize = high - low +1;
		final int r = (int)Math.ceil(arraySize/5);
		int mark;
		int first;
		int last;
		int pivotItem;
		int []t = new int[r];
		int j;
		
		if (r == 0){
			return partition(low,high);
		}
		
		for (int i = 0; i < r;i++){
				first = low + 5 * i;
				last = minimum(low + 5 * i + 5, arraySize);
				t[i] = median(first,last,s);			
		}
		
		pivotItem = select(t,(int)Math.floor((r-1)/2));
		j = low;
		mark = j;
		for (int i = low;i <= high;i++){
			if (s[i] == pivotItem){
				int temp = s[i];
				s[i] = s[j];
				s[j] = temp;
				mark = j;
				j++;
			}else if (s[i] < pivotItem){
				int temp = s[i];
				s[i] = s[j];
				j++;			
			}
			
			pivotPoint = j - 1;
			
			int temp = s[mark];
			s[mark] = s[pivotPoint];
			s[pivotPoint] = temp;
		}
		return pivotPoint;
		
	}
    
    private int minimum(int a, int b) {
		if (a < b){
			return a;
		}else{
			return b;
		}
	}
	
	public int median (int a,int b,int[] s){
		int x;
		int j;
		for (int i = a+1;i <= b;i++){
			x = s[i];
			j = i - 1;
			while (j >= 0 && s[j] > x){
				s[j + 1] = s[j];
				j--;
			}
			s[j + 1] = x;
		}
		return s[(s.length/2)];
	}
	
	public int partition(int low,int high){
		int pivotItem = s[low];
		int j = low;
		
		for (int i = low + 1;i<=high;i++){
			if (s[i] < pivotItem){
				j++;
				int temp = s[i];
				s[i] = s[j];
				s[j] = temp;
			}
		}
		pivotPoint = j;
		int temp = s[low];
		s[low] = s[pivotPoint];
		s[pivotPoint] = temp;
		return pivotPoint;
	}


}
