package process_management;

import java.util.Random;

public class Generator {
	/* ================================================ +
	 * 				 	class Generator					|
	 * ================================================ +
	 *  - Randomly generates processes burst and 	    |
	 *    arrival times within the defined limits. 		|
	 * ================================================	*/
	
	// Declare Random
	Random r = new Random();
	
	// Declare Process array
	Process[] processes;
	
	// Declare process burst time
	int burst;
	
	// Declare process arrival time
	int arrival;
	
	/* ================================================ +
	 * 			generateProcesses(int, int, int)		|
	 * ================================================ +
	 *  - Generates number of processes equal to count. |
	 *   												|
	 *  - Burst times vary from 1 to maxbt.				|
	 *     												|
	 *  - Burst times vary from 1 to maxarrt.			|
	 *  												|
	 *  - Returns: Process[].							|
	 * ================================================	*/
  public Process[] generateProcesses(int count, int maxbt, int maxarrt) {
	  // Create a new Process array
	  processes = new Process[count];
	  
	  // Populate each array element with process
	  for (int i = 0; i < count; i++) {
		  
		  // Define process burst and arrival time as random values between 1 and given max
		  burst = r.nextInt(maxbt) + 1;
		  arrival = r.nextInt(maxarrt) + 1;
		  
		// Populate array element with process
		  processes[i] = new Process(i+1, burst, arrival);
	  }
    //Return the array
    return processes;
  }


}
