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
		int count = 500;
	
		// Declare max burst time
		int maxbt = 250;
	
		// Declare max arrival time
		int maxarrt = 200;

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
		
		// Output to visualize the generated processes
		/*System.out.printf("%-7s%-7s%-7s%n","PID","Arrival","Burst");
		for(int i = 0; i < processes.length; i++) {
			System.out.printf("%-7s%-7s%-7s%n",(i+1),processes[i].getArrivalTime(),processes[i].getBurstTime());
		}
		System.out.printf("%n");*/
		
		System.out.println("Process Count: " + (count));
		
		int total = 0;
		for(int i = 0; i < processes.length; i++) {
			total += processes[i].getBurstTime();
		}
		System.out.println("Avg Burst Time: " + (total / count));
		
		total = 0;
		for(int i = 0; i < processes.length; i++) {
			total += processes[i].getArrivalTime();
		}
		System.out.println("Avg Arrival Time: " + (total / count));
		
		/* ================================================ +
		 * 					   Algorithms					|
		 * ================================================ +
		 *  - The scheduler is run for each algorithm. 		|
		 *    												|
		 *  - Outputs are printed for comparison. 			|
		 * ================================================	*/
		
		//run to init all variables so the timing is fair, no output
		scheduler = new ProcessScheduler();
		scheduler.run(clone(processes), "fcfs", true);
		
		//run first come first serve and write to output file
		scheduler = new ProcessScheduler();
		System.out.println("\nFCFS ================================================ ");

		writeMetrics(scheduler.run(clone(processes), "fcfs"), "resultsFCFS.txt");
		
		//run to init all variables so the timing is fair, no output
		scheduler = new ProcessScheduler();
		scheduler.run(clone(processes), "sjf", true);
		
		//run shortest job first
		scheduler = new ProcessScheduler();
		System.out.println("\nSJF ================================================ ");
		
		writeMetrics(scheduler.run(clone(processes), "sjf"), "resultsSJF.txt");
		
		//run to init all variables so the timing is fair, no output
		scheduler = new ProcessScheduler();
		scheduler.run(clone(processes), "rr", true);
		
		//run round robin
		scheduler = new ProcessScheduler();
		System.out.println("\nRR ================================================ ");
		
		writeMetrics(scheduler.run(clone(processes), "rr"), "resultsRR.txt");
		
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


