//Evaluation Metrics
package classes;

public class Calcs{

  public int CalcWait(int[][] arr, int pid) {
    int wt = 0;
    for(int i=0; i<pid; i++) {
      if (i==0) {
        wt = 0;
        System.out.println("Waiting time is:" + wt);
      }
      else {
        wt += arr[i][1];
        System.out.println("Waiting time is:" + wt);
      }
    }
    return wt;
  }
}
