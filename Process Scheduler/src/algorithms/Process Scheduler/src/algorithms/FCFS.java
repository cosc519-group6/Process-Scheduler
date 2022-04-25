package algorithms;

import java.util.*;

import classes.Process;

// First Come First Serve algorithm
public class FCFS{
	
	int initBurst;
	
	Process first;
	
	public int run(ArrayList<Process> queue) {
		first = queue.get(0);
		initBurst = first.getBurstTime();
    	first.setBurstTime(initBurst - 1);
    	
    	//System.out.println("FCFS Ran Process " + first.getPID() + " from position 0 in the queue.");
    	
    	// return the PID the algorithm decided to run
    	return first.getPID();
	}
}
