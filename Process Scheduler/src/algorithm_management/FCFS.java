package algorithm_management;

import java.util.*;
import process_management.Process;

public class FCFS{
	/* ================================================ +
	 * 				 	class FCFS						|
	 * ================================================ +
	 *  - Models the first come first serve algorithm. 	|
	 *   				 								|
	 *  - Simply runs the process element in the queue.	|
	 * ================================================	*/
	
	// Declare a Process variable to store first process in queue
	Process process;
	
	
	/* ================================================ +
	 * 				run(ArrayList<Process>)				|
	 * ================================================ +
	 *  - runs the fcfs algorithm on given queue.		|
	 * ================================================	*/
	public int run(ArrayList<Process> queue) {
		// get first element of queue
		process = queue.get(0);
		
		// decrement process burst time (simulate it running this time unit)
    	process.setBurstTime(process.getBurstTime() - 1);
    	
    	//System.out.println("FCFS Ran Process " + process.getPID() + " from position 0 in the queue."); // (test print)
    	
    	// return the PID of the process the algorithm decided to run
    	return process.getPID();
	}
}


