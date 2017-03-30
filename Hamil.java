
public class Hamil {

	static int notConn = 99999;

	public static void main(String[] args) {
		
		int [][] graph = {{notConn, 8, 8, notConn, notConn, notConn, 8, 8},
				          {8, notConn, 8, notConn, notConn, notConn, 8, 8},
				          {8, 8, notConn, 8, notConn, 8, notConn, notConn},
				          {notConn, notConn, 8, notConn, 8, notConn, notConn},
				          {notConn, notConn, notConn, 8, notConn, 8, notConn, notConn},
				          {notConn, notConn, 8, notConn, 8, notConn, 8, notConn},
				          {8, 8, notConn, notConn, notConn, 8, notConn, 8},
				          {8, 8, notConn, notConn, notConn, notConn, 8, notConn}};
		int [] path = {0, 1, 7, 6, 5, 4, 3, 2, 0};
		System.out.println(hamiltonianVerify(path, graph));
	}
	public static boolean hamiltonianVerify(int []a, int [][]s){	
		if (a[0] != a[a.length - 1] || a[0] == a[1] || a.length != s.length + 1){
			return false;
		}
		
		for (int i = 1;i < a.length - 1;i++){
			for (int j = i+1;j < a.length;j++){
				if (a[i] == a[j]){
					return false;
				}else{
					continue;
				}
			}
		}
		
		for (int i = 0;i < a.length - 1;i++){
							
			int pointA = a[i];
			int pointB = a[i + 1];
			
			if (s[pointA][pointB] == notConn){
				return false;
			}else{
				continue;
			}				
			
		}		
		return true;		
	}

}

