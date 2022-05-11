package algorithm_management;

import java.util.*;
import process_management.Process;

public class RR{
	/* ================================================ +
	 * 				 	   class RR						|
	 * ================================================ +
	 *  - Models the round robin algorithm.			 	|
	 *   				 								|
	 *  - runs each process q (time quantum) times.		|
	 * ================================================	*/
	
	// Declare a variable to increment through the queue when a process has finished its turn
	int turn = 0;
	
	// Declare a variable to track how many times the current process has run in a row
	int timer = 0;
	
	// Declare a time quantum, how many times a process can run in a row before next is loaded
	int q = 5;
	
	// Declare a variable to hold a reference to the last PID run
	int previousLength;
	
	// Declare a Process variable 
	Process process;
	
	
	/* ================================================ +
	 * 				run(ArrayList<Process>)				|
	 * ================================================ +
	 *  - runs the rr algorithm on given queue.			|
	 * ================================================	*/
	public int run(ArrayList<Process> queue) {
		
		// check if the last run finished a process, if so reset timer for new process being run
		if(queue.size() < previousLength)
			timer = 0;
		
		// if time quantum expires without last process finishing, reset timer and move on to next
		if(timer >= q) {
			timer = 0;
			turn++;
		}
		
		// reset to the start of the queue once its end is reached
		if(turn >= queue.size()) {
			turn = 0;
		}
		
		// select the process in the current turn id of the queue
		process = queue.get(turn);
		
		// decrement selected process' burst time (simulate it running this time unit)
    	process.setBurstTime(process.getBurstTime() - 1);
    	
    	//System.out.println("RR Ran Process " + process.getPID() + " from position " + (turn+1) +" in the queue.");
    	
		// store the current queue length for comparison on next run
		previousLength = queue.size();
    	
    	// increment how many times this process has run on its turn
    	timer++;
    	
    	// return the PID the algorithm decided to run
    	return process.getPID();
	}
}
