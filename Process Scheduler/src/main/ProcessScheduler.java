package main;

import java.util.*;
import algorithm_management.FCFS;
import algorithm_management.RR;
import algorithm_management.SJF;
import process_management.Process;

// First Come First Serve algorithm
public class ProcessScheduler{
	/* ================================================ +
	 * 				class ProcessScheduler				|
	 * ================================================ +
	 *  - Receives Process[] and simulates their		|
	 *    arrival into a ready queue.			 		|
	 *   				 								|
	 *  - Using desired algorithm, determines which		|
	 *    process to run from the ready queue.			|
	 *    												|
	 *  - Continues running until all processes finish,	|
	 *    then breaks the loop to allow for analysis.	|
	 * ================================================	*/
	
	// Declare boolean for determining if a process has just arrived
	boolean firstTime = true;
		
	// Declare time unit variable
	int time = 0;
	
	// Declare execution time (milli) variable
	long executionTime = 0;
	
	// Declare first arrival variable
	int firstArrival = 0;
		
	// Declare total time spent waiting
	int total = 0;
	
	// Declare total time without a process scheduled
	int wasted = 0;
		
	// Declare number of processes (determined by input array)
	int count;
	
	// Declare a PID variable to reference the process which ran that time unit
	int pid;
	
	// Declare a variable to compare processes in gantt to the last process id ran
	int prev;
	
	// Declare wait time array to store each processes total wait time.
	int[] wait;
	
	// Declare gantt array to store process run on each time unit.
	ArrayList<Integer> gantt;
		
	// Declare wait queue for scheduler 
	ArrayList<Process> queue;

	// Declare firstRun array list to store when each process runs for the first time.
	ArrayList<int[]> firstRun;
	
	// Declare algorithms for use
	FCFS fcfs = new FCFS();
	SJF sjf = new SJF();
	RR rr = new RR();
	
	// Declare metrics information
	float cpuUtilizationPercentage;
	float avgWaitTime;
	float avgResponseTime;
	
	// Declare metrics array to store information about algorithm performance
	float[] metrics = new float[4];
	
	// test output avg
	float avg = 0;
	
	// run with output
	public float[] run(Process[] processes, String algorithm) {
		return run(processes,algorithm, false);
	}
	
	
	/* ================================================ +
	 * 				run(Process[], String)				|
	 * ================================================ +
	 *  - runs a while loop, scheduling processes until |
	 *    all reach completion.							|
	 * ================================================	*/
	public float[] run(Process[] processes, String algorithm, boolean mute) {
		long startTime = System.currentTimeMillis();
		// store the number of processes
		count = processes.length;
		
		// Declare a new wait array
		wait = new int[count];
		
		// Declare a new gantt array
		gantt = new ArrayList<>();
			
		// Declare a new wait queue
		queue = new ArrayList<>();
		
		// Declare a new firstRun array
		firstRun = new ArrayList<>();

		while(true) {
			// increment the time unit's value
			time++;
			
			//System.out.println("T:" + time); //(testing print)
			
			// check processes array for any new arrivals and put them into the wait queue
			for (int j = 0; j < processes.length; j++) {
				if(processes[j].getArrivalTime() == time) {
					queue.add(processes[j]);
					if(firstArrival == 0)
						firstArrival = time;
				}
			}
	    	//System.out.println(queue.toString()); //(testing print)
	    	long startTime2 = System.nanoTime();
			pid = scheduler(algorithm);
			executionTime = System.nanoTime()- startTime2;
			
			avg += executionTime;
			
			// Document which process ran on this time unit in the gantt
			gantt.add(pid);
			
			//System.out.println("PID chosen: " + pid); //(testing print)
			if(pid == 0) {
				wasted ++;
			}
			else if (newlyScheduled()) {
				int[] arrival = {pid, time};
				firstRun.add(arrival);
			}
			
			//System.out.println(pid); //(testing print)
			//System.out.println("Remaining Burst:" + processes[pid-1].getBurstTime()); //(testing print)
				
			
	    	// for each process still waiting, increment the wait time array index equal to its PID-1
			addWaitTime(queue);
			
			//System.out.println("Wait Times:" + Arrays.toString(wait)); //(testing print)
			
			// check if the loop can end (when all process burst times are zero)
			if(done(processes)) {
				break;
			}
		}
		if(!mute) System.out.printf("%41s%s%n","Algorithm Time (Nanoseconds): ",(avg / time));
		executionTime = System.currentTimeMillis()- startTime;
		if(!mute) System.out.println("Scheduler Execution Time (Milliseconds): " + executionTime);
		return printMetrics(processes, executionTime, mute);
	}
	
	private float[] printMetrics(Process[] processes, long executionTime, boolean mute) {
	
		// store the algorithm time
		metrics[0] = (avg / time);
		
		// store the execution time
		metrics[1] = executionTime;
		
		// store the average waiting time
		for (int p = 0; p < wait.length; p++) { total += wait[p]; }
		avgWaitTime = (float)total/count;
		metrics[2] = avgWaitTime;
		if(!mute) System.out.printf("%n%41s%s%n","Avg Wait Time (Units): ",avgWaitTime);
		// store the average response time
		total = 0;
		for (int q = 0; q < firstRun.size(); q++) {	
			int[] array = firstRun.get(q);
			total += array[1] - processes[array[0]-1].getArrivalTime();
		}
		avgResponseTime = (float)total/count;
		metrics[3] = avgResponseTime;
		if(!mute) System.out.printf("%41s%s%n","Avg Response Time (Units): ",avgResponseTime);
		
		
		/*//Print Gantt for quick analysis of performance when testing
		System.out.println("\nProcess ID Gantt:");
		gantt(gantt);*/
		
		return metrics;
	}
	
	private int scheduler(String algorithm) {
		int num = 0;
    	// decide which process to run on this time unit		
		if(!queue.isEmpty()) {
			switch (algorithm) {
				case "sjf": num = sjf.run(queue);
							break;

				case "rr":  num = rr.run(queue);
							break;
				
				default: 	num = fcfs.run(queue);
			}
		}
		return num;
	}
	
	private boolean newlyScheduled() {
		if(!firstRun.isEmpty()) {
				for(int[] response : firstRun) {
					if(response[0] == pid)
						return false;	
				}
			}
		return true;
	}
	
	private boolean done(Process[] processes) {
		for (Process process : processes) {
			if(process.getBurstTime() != 0)
				return false;
		}
		return true;
	}
	
	private void addWaitTime(ArrayList<Process> queue) {
		for (int j = 0; j < queue.size(); j++) {
			
			// don't increase the wait of the process that was run
			if(queue.get(j).getPID() != pid) {
				wait[queue.get(j).getPID()-1]++;
			}
			// remove a process from queue when its burst time is up
			if(queue.get(j).getBurstTime() == 0){
				queue.remove(j);
				j--; // accounts for an object being removed, no skipped objects this way
			}
		}
	}
	
	private void gantt(ArrayList<Integer> gantt) {
		if(gantt.size() < 30)
			System.out.println(gantt);
		else {
			System.out.print("Process " + gantt.get(0) + " ran from: " + 1 + "-");
			prev = gantt.get(0);
			for(int v = 1; v < gantt.size(); v++) {
				int current = gantt.get(v);
				if(prev != current) {
					System.out.print(v + "\n");
					System.out.print("Process " + current + " ran from: " + (v+1) + "-");
				}
				prev = current;
			}
		}
		System.out.print(time + "\n");
	}
}