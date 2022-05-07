package algorithms;

import java.util.*;

import classes.Process;

// process Come process Serve algorithm
public class RR{
	
	int initBurst;
	
	int turn = 0;
	
	int timer = 0;
	
	// time quantum
	int q = 2;
	
	Process process;
	
	public int run(ArrayList<Process> queue) {
		
		// Rotate around the queue
		if(timer >= q) {
			turn++;
			timer = 0;
		}
		
		if(turn >= queue.size())
			turn = 0;
		
		process = queue.get(turn);
		initBurst = process.getBurstTime();
    	process.setBurstTime(initBurst - 1);
    	
    	//System.out.println("RR Ran Process " + process.getPID() + " from position " + (turn+1) +" in the queue.");
    	
    	timer++;
    	
    	// return the PID the algorithm decided to run
    	return process.getPID();
	}
}
