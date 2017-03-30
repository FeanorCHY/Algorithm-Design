public class assignment3 {
	
	public static void main(String []args){
		binMo(10,9);
	}
	
	public static void binMo(int n, int k){
		int a[] = new int[k+1];

		int x = 1;
		
		while(x <= k){
			
			int nProduct = 1;
			for(int i = n; i >= 1; i--){
				nProduct=nProduct * i;
			}
			
			int xProduct = 1;
			for(int i = x; i >= 1; i--){
				xProduct=xProduct * i;
			}
			
			int minusProduct = 1;
			for(int i = n-x; i >= 1; i--){
				minusProduct=minusProduct * i;
			}
			
			int result = nProduct/(xProduct * minusProduct);
			
			a[x] = result;
			x++;
		}
		
		System.out.println(a[k]);

	}
		
}
