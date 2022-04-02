package Classes;

import java.util.Random;

//This class randomly generates burst times and arrival times
//for processes. The user declares the number of processes they
//wish to generate and the max bust time for and arrival
//time for each process.
public class Generator {
  public int[][] Generator(int count, int maxbt, int maxarrt) {

    int processes[][] = new int[count][3];

    for (int i=0; i<count; i++) {
      Random rand = new Random();
      //generate random values from 0 to declared upper bound
      int randomInt = rand.nextInt(maxbt);

      //Assign i as the pid and randomInt as the burst time
      processes[i][0] = i+1;
      processes[i][1] = randomInt;
    }

    for (int i=0; i<count; i++) {
      int arrTime;
      //Start at 0 for process 1
      if (i==0) {
        processes[i][2] = 0;
      }
      else {
        //Generate random values for arrival time. The arrival time
        //of each process must be greater than that of the previous.
        Random rand = new Random();
        arrTime = rand.nextInt(maxarrt-processes[i-1][2])+processes[i-1][2];
        processes[i][2] = arrTime;
      }
    }

    //Return the array
    return processes;
  }


}
