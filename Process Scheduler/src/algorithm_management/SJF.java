package algorithm_management;

import java.util.*;
import process_management.Process;

public class SJF{
	/* ================================================ +
	 * 				 	  class SJF						|
	 * ================================================ +
	 *  - Models the shortest job first algorithm.	 	|
	 *   				 								|
	 *  - runs the process with the least burst time.	|
	 * ================================================	*/
	
	// Declare variable to store position of selected process for testing
	int position;
	
	// Declare a Process variable to store current queue reference process
	Process process;
	
	// Declare a Process variable to store selected process (least burst time)
	Process selected;
	
	
	/* ================================================ +
	 * 				run(ArrayList<Process>)				|
	 * ================================================ +
	 *  - runs the sjf algorithm on given queue.		|
	 * ================================================	*/
	public int run(ArrayList<Process> queue) {
		
		// check each process in queue to find which has the lowest remaining burst time
		for(int i = 0; i < queue.size(); i++) {
			// get process at position i
			process = queue.get(i);
			
			/* if there is no currently select process OR
			 * if the selected process has finished its burst time OR
			 * if the process from queue has a smaller burst time than the current process,
			 * select the current process from queue as the one to run                     */
			if (selected == null || selected.getBurstTime() <= 0 || process.getBurstTime() < selected.getBurstTime()) {
				selected = process;
				position = i;
			}
		}
		
		// decrement selected burst time (simulate it running this time unit)
    	selected.setBurstTime(selected.getBurstTime() - 1);

    	//System.out.println("SJF Ran Process " + selected.getPID() + " from position " + position + " in the queue.");
    	
    	// return the PID the algorithm decided to run
    	return process.getPID();
	}
}
