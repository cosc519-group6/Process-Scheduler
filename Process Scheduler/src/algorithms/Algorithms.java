package algorithms;

import java.util.*;

import classes.Process;

// First Come First Serve algorithm
public class Algorithms{
		
	// Declare boolean for when all processes have finished running
	boolean done = false;
		
	// Declare time unit variable
	int time = 0;
		
	// Declare total time spent waiting
	int total = 0;
		
	// Declare number of processes (determined by input array)
	int count;
	
	// Declare a PID variable to reference the process which ran that time unit
	int pid = -1;
	
	// Declare algorithms for use
	FCFS fcfs = new FCFS();
	SJF sjf = new SJF();
		
	// Declare wait queue for scheduler 
	ArrayList<Process> queue = new ArrayList<>();
	
	// Declare wait time array to store each processes total wait time.
	int[] wait;
		
	public void run(Process[] processes, String algorithm) {
		
		count = processes.length;
		wait = new int[count];

		while(true) {
			// increment the time unit's value
			time++;
			//System.out.println("T:" + time); //(testing print)
			
			// check processes array for any new arrivals and put them into the wait queue
			for (int j = 0; j < processes.length; j++) {
				if(processes[j].getArrivalTime() == time)
					queue.add(processes[j]);
			}
	    	//System.out.println(queue.toString()); //(testing print)
	    	
	    	// decide which process to run on this time unit		
			if(!queue.isEmpty()) {
				if ( algorithm == "sjf") {
					pid = sjf.run(queue);
				}
				else {
		            pid = fcfs.run(queue);
				}
				
				//System.out.println(pid);
				//System.out.println("Remaining Burst:" + processes[pid-1].getBurstTime()); //(testing print)
			}
	    	// for each process still waiting, increment the wait time array index equal to its PID-1
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
			
			//System.out.println("Wait Times:" + Arrays.toString(wait)); //(testing print)
			
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
			
			System.out.println(processes[p] +
					" arrived at time unit " + processes[p].getArrivalTime() + 
					" and waited " + wait[p] + " time units.");
			total += wait[p];
		}
		System.out.println("Execution Time: " + time);
		System.out.println("Average Waiting Time: " + (total / count));
	}
}