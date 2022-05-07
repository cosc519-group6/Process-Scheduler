package main;

import process_management.*;
import process_management.Process;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.Writer;


import java.util.Arrays;

class Main {
	/* ================================================ +
	 * 					   class Main					|
	 * ================================================ +
	 *  - Runs the Generator to make a desired number   |
	 *    of algorithms. 								|
	 *    												|
	 *  - Runs the ProcessScheduler for each of the		|
	 *    defined algorithms. 							|
	 * ================================================	*/
	public static void main(String[] args) {
		/* ================================================ +
		 * 					Initializations					|
		 * ================================================ +
		 *  - integer variables which determine the number	|
		 *    and properties of processes to be generated.	|
		 *    												|
		 *  - a scheduler object with which to test the		|
		 *    scheduling algorithms on the processes.		|
		 * ================================================	*/

		// Declare number of processes
		int count = 10;

		// Declare max burst time
		int maxbt = 8;

		// Declare max arrival time
		int maxarrt = 10;

		// Declare scheduler
		ProcessScheduler scheduler;

		/* ================================================ +
		 * 					   Generator					|
		 * ================================================ +
		 *  - Initializes a generator and passes in the 	|
		 *    desired ranges and process production number.	|
		 *    												|
		 *  - the generated process array is cloned so that	|
		 *    each algorithm can run the same process set.	|
		 * ================================================	*/

		// Randomly generate processes
		Generator gen = new Generator();
		Process[] processes = gen.generateProcesses(count, maxbt, maxarrt);
		Process[] a = clone(processes);
		Process[] b = clone(processes);
		Process[] c = clone(processes);

		// Output to visualize the generated processes
		System.out.printf("%-7s%-7s%-7s\n","PID","Arival","Burst");
		for(int i = 0; i < processes.length; i++) {
			System.out.printf("%-7s%-7s%-7s\n",(i+1),processes[i].getArrivalTime(),processes[i].getBurstTime());
		}
		System.out.printf("\n");

		/* ================================================ +
		 * 					   Algorithms					|
		 * ================================================ +
		 *  - The scheduler is run for each algorithm. 		|
		 *    												|
		 *  - Outputs are printed for comparison. 			|
		 * ================================================	*/

		//run first come first serve
		scheduler = new ProcessScheduler();
		System.out.println("FCFS ======== ");
		scheduler.run(a, "fcfs");

		//write FCFS results to array
		float metricsFCFS[] = new float[3];
		metricsFCFS = scheduler.printMetrics(a, false);
		String resultsFCFS = Arrays.toString(metricsFCFS);
		resultsFCFS = resultsFCFS.replaceAll("[\\p{Pe}\\p{Ps}]","");

		//run shortest job first
		scheduler = new ProcessScheduler();
		System.out.println("\nSJF ======== ");
		scheduler.run(b, "sjf");

		//write SJF results to array
		float metricsSJF[] = new float[3];
		metricsSJF = scheduler.printMetrics(b, false);
		String resultsSJF = Arrays.toString(metricsSJF);
		resultsSJF = resultsSJF.replaceAll("[\\p{Pe}\\p{Ps}]","");

		//run round robin
		scheduler = new ProcessScheduler();
		System.out.println("\nRR ======== ");
		scheduler.run(c, "rr");

		//write RR results to array
		float metricsRR[] = new float[3];
		metricsRR = scheduler.printMetrics(c, false);
		String resultsRR = Arrays.toString(metricsRR);
		resultsRR = resultsRR.replaceAll("[\\p{Pe}\\p{Ps}]","");

		//write to three seperate results files for each algorithm
		Writer outputFCFS;
		Writer outputSJF;
		Writer outputRR;

		try {
			outputFCFS = new BufferedWriter(new FileWriter("resultsFCFS.txt", true));
			outputFCFS.append(resultsFCFS + "\n");
			outputFCFS.close();

			outputSJF = new BufferedWriter(new FileWriter("resultsSJF.txt", true));
			outputSJF.append(resultsSJF + "\n");
			outputSJF.close();

			outputRR = new BufferedWriter(new FileWriter("resultsRR.txt", true));
			outputRR.append(resultsRR + "\n");
			outputRR.close();

		} catch(Exception e) {
  //  Block of code to handle errors
		}


	}



	    //more code




	/* ================================================ +
	 * 				   clone(Process[])					|
	 * ================================================ +
	 *  - Makes a new Process array containing the same |
	 *    processes to avoid errors.					|
	 *    												|
	 *  - Returns: Process[], copy of original array.	|
	 * ================================================	*/
	private static Process[] clone(Process[] original) {
		Process[] copy = new Process[original.length];
		for(int i = 0; i < copy.length; i++) {
			copy[i] = original[i].clone();
		}
		return copy;
	}
}
