import classes.*;
import classes.Process;

import java.util.Arrays;

import algorithms.*;


class Main {

	public static void main(String[] args) {
		  
		// Declare number of processes
		int count = 5;
	
		// Declare max burst time
		int maxbt = 20;
	
		// Declare max arrival time
		int maxarrt = 10;

		// Declare algorithms for use (make a new one each time to avoid issues)
		Algorithms algorithms;
		
		// Randomly generate processes
		Generator gen = new Generator();
		Process[] processes = gen.generateProcesses(count, maxbt, maxarrt);
		Process[] a = clone(processes);
		Process[] b = clone(processes);
		Process[] c = clone(processes);
		
		// Test to see the generated processes
		System.out.println("(PID, Arrival, Burst) Generated Processes:");
		for(int i = 0; i < processes.length; i++) {
			System.out.println((i+1) + ")\t" + processes[i].getArrivalTime() + "\t" + processes[i].getBurstTime() + "\n");
		}
		
		//run fcfs
		algorithms = new Algorithms();
		System.out.println("FCFS======== ");
		algorithms.run(a, "fcfs");
		
		//run sjf
		algorithms = new Algorithms();
		System.out.println("\nSJF======== ");
		algorithms.run(b, "sjf");
		
		//run rr
		algorithms = new Algorithms();
		System.out.println("\nSJF======== ");
		algorithms.run(c, "rr");
	}
	
	private static Process[] clone(Process[] a) {
		Process[] b = new Process[a.length];
		for(int i = 0; i < b.length; i++) {
			b[i] = a[i].clone();
		}
		return b;
	}
}
