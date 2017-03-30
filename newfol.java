import java.util.Arrays;




public class newfol {




public static void main(String[] args) {
	int max = 99999;	
	int[][] P = new int[7][7];
	//int[][] W = {{0,4,bignum,bignum,bignum,10,bignum},{3,0,bignum,18,bignum,bignum,bignum},{bignum,6,0,bignum,bignum,bignum,bignum},{bignum,5,15,0,2,19,5},{bignum,bignum,12,1,0,bignum,bignum},{bignum,bignum,bignum,bignum,bignum,0,10},{bignum,bignum,bignum,8,bignum,bignum,0}};
	int[][] W = {{0,4,max,max,max,10,max},{4,0,6,18,max,max,max},{max,6,0,15,12,max,max},{max,18,15,0,2,19,8},{max,max,12,2,0,max,max},{10,max,max,19,max,0,10},{max,max,max,8,max,10,0}};
	int[][] D = new int[7][7];
	for(int i = 0;i<W.length;i++){		
		for(int j = 0;j<W[i].length;j++){
			P[i][j] = 0;
		}
	}
	D = W.clone();
	for(int k = 0;k<7;k++){
		for(int i = 0; i<W.length;i++){
		
			for(int j = 0;j<W[i].length;j++){
				if(D[i][k]+D[k][j]<D[i][j]){
				
					P[i][j] = k+1;
					
					D[i][j] = D[i][k]+D[k][j];
				
				}
			
			}
		
		}
	
	}
	 
	
	//printArrays(P);
	
	printArrays(D);
	
	 

}

 

public static void printArrays(int[][] A){

for(int i = 0; i<A.length;i++){

System.out.println(Arrays.toString(A[i]));

}

}




}





 


