/*import java.util.Random;

import classes.*;

import java.io.*;
import java.util.Arrays;

class ProcessSchedulerOld {

  public static void main(String[] args) {

    //Declare number of processes (pid will be 1-9 in this case)
    int count = 9;

    //Declare max burst time
    int maxbt = 30;

    //Declare max arrival time
    int maxarrt = 50;

    //Randomly generate processes
    Generator gen  = new Generator();
    int[][] processes = gen.Generator(count, maxbt, maxarrt);
    for(int i = 0; i < processes.length; i++)
    	System.out.println(Arrays.toString(processes[i]));

    //Print the pid and the burst time for each process. This is the
    //order that we will have for FCFS, so we don't need to write a separate
    //algorithm for that.
    for (int j=0; j<processes.length; j++) {
      System.out.println("pid: " + processes[j][0] + ", burst time: " + processes[j][1]
      + ", arrival time: " + processes[j][2]);
    }

    //Calculate different evaluation metrics
    Calcs calculations = new Calcs();

    //Find wait time for each process in FCFS
    int wt = calculations.CalcWait(processes, processes.length);
    System.out.println(wt);

    //SJF sort Array
    SJF sjf = new SJF();
    int[][] procSJF = processes;
    sjf.SortArray(procSJF);

    //Print sorted array
    for (int i = 0; i < processes.length; i++) {
      System.out.println("pid: " + processes[i][0] + " burst time: " + processes[i][1]
      + ", arrival time: " + processes[i][2]);
    }

    //Find wait times for SJF algorithm
    int wtSJF = calculations.CalcWait(procSJF, procSJF.length);
    System.out.println(wtSJF);
  }
}*/
