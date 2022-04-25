package classes;

import java.util.Random;

//This class randomly generates burst times and arrival times
//for processes. The user declares the number of processes they
//wish to generate and the max burst time and arrival
//time for each process.
public class Generator {
	Random rand = new Random();
	int burst;
	int arrival;
	
    public Process[] generateProcesses(int count, int maxbt, int maxarrt) {
    	Process[] processes = new Process[count];

    	for (int i=0; i<count; i++) {
    		// Assign randomInt (from 1 to max burst time)as the burst time
    		burst = rand.nextInt(maxbt - 1) + 1;
    		
    		// Assign another randomInt (from 1 to max arrival time) as the arrival time
    		arrival = rand.nextInt(maxarrt - 1) + 1;
    		
    		// Create process (PID = i+1) and add to array
    		processes[i] = new Process(i + 1, burst, arrival);
    	
    	}
    	
    	//Return the array
    	return processes;
    }
}
