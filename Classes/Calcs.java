//Evaluation Metrics
package Classes;

public class Calcs{

  public int CalcWait(int[][] arr, int pid) {
    int wt = 0;
    for(int i=0; i<pid; i++) {
      if (i==0) {
        wt = 0;
      }
      else {
        wt += arr[0][1];
        wt += arr[i][1];
        System.out.println(wt);
      }
    }
    return wt;
  }
}
