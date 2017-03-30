
package project2;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Floyd_array_shortest {
	private static int[] array;
	private static int[] array1;
	private static int[][] path;
	private static int INF = 1000;
	private List<Integer> result = new ArrayList<Integer>();

	public static void main(String args[]) {
		int a;
		int b;
		int start;
		int end;
		//System.out.println("please input the number of the nodes");
		Scanner sc = new Scanner(System.in);

		array = Tools.Create_1d_array();
		boolean choose = true;
		do {
			System.out.println(
					"do you want to find the specific path from one node to other node ?(true for yes ,false for no)");
			choose = sc.nextBoolean();
			if (choose == true) {
				//System.out.println("please input the start node (from 0 to " + (a - 1) + ")");
				System.out.println("please input the start node (from 0 to 9)");
				start = sc.nextInt();
				//System.out.println("please input the end node (from 0 to " + (a - 1) + ")");
				System.out.println("please input the start node (from 0 to  9)");
				end = sc.nextInt();
				long starttime = System.currentTimeMillis();
				floyd(10);
				long endtime = System.currentTimeMillis();
				System.out.println("using time is "+(endtime - starttime)+" ms");
				int temp2 = 0;
				int v=start;
				int u=end;
				if(v < u)
				{
					temp2 = u ;
					u=v;
					v=temp2;
					
				}
				int temp = (v - 1) * v / 2 + u;
				System.out.println("the weight is " + array[temp]);
				// graph.findCheapestPath(start,end,matrix);
				System.out.println(start + " to " + end + ",the shortest path is:");
				System.out.print(start + "->");
				pathway(start, end);
				System.out.println(end);
			} else {
				System.out.println("EXIT!");
				break;
			}
		} while (choose == true);
	}

	public static void pathway(int v, int u) {
		if (path[v][u] != 0) {
			pathway(v, path[v][u]);
			System.out.print(path[v][u] + "->");
			pathway(path[v][u], u);
		}
	}
	// if the start node bigger than end node , we need to exchange the value of start and end
	public static int getWeight(int v, int u) {
		if (v == u) {
			return 0;
		} else if (v < u) {
			int temp = v;
			v = u;
			u = temp;
		}
		return array[(v - 1) * v / 2 + u];
	}
	//set the exchange of start and end value 
	public static void setWeight(int v, int u, int value) {
		if (v < u) {
			int temp = v;
			v = u;
			u = temp;
		}
		array[(v - 1) * v / 2 + u] = value;
	}

	public static void floyd(int vertex) {
		path = new int[vertex][vertex];
		for (int i = 0; i < vertex; i++) {
			for (int j = 0; j < vertex; j++) {
				path[i][j] = 0;
			}
		}
		for (int k = 0; k < vertex; k++) {    // following the order from the first to next node
			for (int i = 0; i < vertex; i++) {
				for (int j = 0; j < vertex; j++) {
					if (getWeight(i, k) + getWeight(k, j) < getWeight(i, j)) {
						setWeight(i, j, getWeight(i, k) + getWeight(k, j));
						path[i][j] = k;
					}
				}
			}
		}
	}


}
