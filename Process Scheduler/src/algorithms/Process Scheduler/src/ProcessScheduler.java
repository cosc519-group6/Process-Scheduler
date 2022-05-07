import java.util.*;

import classes.*;
import classes.Process;
import algorithms.*;

import java.io.*;
import java.util.Arrays;

class ProcessScheduler {

	/*
	 * TODO: Ask question about if the size of the wait queue needs to be considered?
	 * 		 (do we need to account for the wait queue filling up and a process arriving?)
	 */
	
	public static void main(String[] args) {
		
		// Declare boolean for when all processes have finished running
		boolean done = false;
		
		// Declare time unit variable
		int time = 0;
		
		// Declare total time spent waiting
		int total = 0;
		  
		// Declare number of processes (pid will be 1-9 in this case)
		int count = 9;
	
		// Declare max burst time
		int maxbt = 30;
	
		// Declare max arrival time
		int maxarrt = 50;
		
		// Declare a PID variable to reference the process which ran that time unit
		int pid = -1;
		
		// Declare algorithms for use
		FCFS fcfs = new FCFS();
		SJF sjf = new SJF();
		
		// Declare wait queue for scheduler 
		ArrayList<Process> queue = new ArrayList<>();
	
		// Declare wait time array to store each processes total wait time.
		int[] wait = new int[count];
		
		// Randomly generate processes
		Generator gen = new Generator();
		Process[] processes = gen.generateProcesses(count, maxbt, maxarrt);
	    
		// Test to see the generated processes
		System.out.println("(PID, Arrival, Burst) Generated Processes:");
		for(int i = 0; i < processes.length; i++) {
			System.out.println((i+1) + ")\t" + processes[i].getArrivalTime() + "\t" + processes[i].getBurstTime());
		}
		/* Requirements:
		 * - need to have a way to measure avg waiting time
		 *   avg wait = (wait time for each program added up) / number of programs
		 *   so, need to track process wait time and store that when a process finishes.
		 */
		while(true) {
			// increment the time unit's value
			time++;
			System.out.println("T:" + time); //(testing print)
			
			// check processes array for any new arrivals and put them into the wait queue
			for (int j = 0; j < processes.length; j++) {
				if(processes[j].getArrivalTime() == time)
					queue.add(processes[j]);
			}
	    	System.out.println(queue.toString()); //(testing print)
	    	
	    	// run each algorithm to see which process it decides to run on this time unit
	    	// 'run' that process (decrement its burst time)
			// TODO: make some sort of function/files so that all 3 can be run in this file on the same input.
			
			if(!queue.isEmpty()) {
				//pid = fcfs.run(queue);
				pid = sjf.run(queue);
				System.out.println(pid);
				System.out.println("Remaining Burst:" + processes[pid-1].getBurstTime()); //(testing print)
			}
	    	// for each process still waiting, increment the total wait time array index equal to its PID-1
			for (int j = 0; j < queue.size(); j++) {
				
				// don't increase the wait of the process that was run
				if(queue.get(j).getPID() != pid) {
					wait[queue.get(j).getPID()-1]++;
				}
				// remove a process from queue when its burst time is up
				if(queue.get(j).getBurstTime() == 0){
					queue.remove(j);
					j--;
				}
			}
			
			System.out.println("Wait Times:" + Arrays.toString(wait)); //(testing print)
			
			// check if the loop can end (when all process burst times are zero)
			done = true;
			for (Process process : processes) {
				if(process.getBurstTime() != 0)
					done = false;
			}
			if(done) {
				break;
			}
		}
		for (int p = 0; p < wait.length; p++) {
			
			System.out.println("\n" + processes[p] +
					" arrived at time unit " + processes[p].getArrivalTime() + 
					" and waited " + wait[p] + " time units.\n");
			total += wait[p];
		}
		System.out.println("Execution Time: " + time);
		System.out.println("Average Waiting Time: " + (total / count));
	}
}
