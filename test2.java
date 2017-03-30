public class test2 {

 public static void main(String args[]) {
  new test2().combination(new String[] { "A", "B", "C", "D", "E" }, 3);
 }
 public void combination(int record[], String info[], int n, int r, int k1,
   int k2) {
  if (k1 == r) { 
   for (int i = 0; i < r; ++i)
    System.out.print(info[record[i] - 1] + " ");
   System.out.println();
  } else
   for (int i = k2; i < n; ++i) {
    record[k1] = i + 1; 
    combination(record, info, n, r, k1 + 1, i + 1); 
   }
 }

 public void combination(String info[], int r) {
  int record[] = new int[r];
  int n = info.length;
  combination(record, info, n, r, 0, 0);
 }

}