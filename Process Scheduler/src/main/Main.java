package main;

import process_management.*;
import process_management.Process;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
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
	public static void main(String[] args) throws IOException {
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
		int count = 5;
	
		// Declare max burst time
		int maxbt = 10;
	
		// Declare max arrival time
		int maxarrt = 30;

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
		System.out.printf("%-7s%-7s%-7s%n","PID","Arival","Burst");
		for(int i = 0; i < processes.length; i++) {
			System.out.printf("%-7s%-7s%-7s%n",(i+1),processes[i].getArrivalTime(),processes[i].getBurstTime());
		}
		System.out.printf("%n");
		
		/* ================================================ +
		 * 					   Algorithms					|
		 * ================================================ +
		 *  - The scheduler is run for each algorithm. 		|
		 *    												|
		 *  - Outputs are printed for comparison. 			|
		 * ================================================	*/
		
		//run first come first serve and write to output file
		scheduler = new ProcessScheduler();
		System.out.println("FCFS ======== ");

		writeMetrics(scheduler.run(a, "fcfs"), "resultsFCFS.txt");
		
		//run shortest job first
		scheduler = new ProcessScheduler();
		System.out.println("\nSJF ======== ");
		
		writeMetrics(scheduler.run(b, "sjf"), "resultsSJF.txt");
		
		//run round robin
		scheduler = new ProcessScheduler();
		System.out.println("\nRR ======== ");
		
		writeMetrics(scheduler.run(c, "rr"), "resultsRR.txt");
		
	}
	
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
	
	/* ================================================ +
	 * 			 writeMetrics(float[], String)			|
	 * ================================================ +
	 *  - writes the metric results of an algorithm to  |
	 *    its own output text file.						|
	 * ================================================	*/
	private static void writeMetrics(float[] metrics, String name) throws IOException {
		String resultsFCFS = Arrays.toString(metrics); //convert array to String so we can add to file
		resultsFCFS = resultsFCFS.replaceAll("[\\p{Pe}\\p{Ps}]",""); //remove brackets from String 
		
		String metricsDir = System.getProperty("user.dir") + "\\src\\metrics\\" + name; //gets directory to store metrics in
		
		try (Writer writer = new BufferedWriter(new FileWriter(new File(metricsDir), true));) {
			writer.append(resultsFCFS + "\n");
		} 
		catch(Exception e) { /* Block of code to handle errors*/ }
	}
}


