package algorithms;

import java.util.*;

import classes.Process;

//Shortest Job First preemptive algorithm, sorted by burst time
public class SJF{
	
	int initBurst;
	int position;
	
	Process process;
	Process selected;
	
	public int run(ArrayList<Process> queue) {
		
		// make sure to reset selected on init and when previous selected has finished (is zero).
		for(int i = 0; i < queue.size(); i++) {
			process = queue.get(i);
			if (selected == null || selected.getBurstTime() == 0 || process.getBurstTime() < selected.getBurstTime()) {
				selected = process;
				position = i;
			}
		}
		
		// "run" (decrement the burst of) selected process
		initBurst = selected.getBurstTime();
    	selected.setBurstTime(initBurst - 1);
    		
    	//System.out.println("SJF Ran Process " + selected.getPID() + " from position " + (position+1) + " in the queue.");
    	
    	// return the PID the algorithm decided to run
    	return selected.getPID();
	}
}
