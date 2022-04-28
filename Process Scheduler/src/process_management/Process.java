package process_management;

public class Process {
	/* ================================================ +
	 * 				 	class Project					|
	 * ================================================ +
	 *  - Models the example processes to enter into 	|
	 *    the scheduler.  								|
	 * ================================================	*/
	
	// Declare PID for identifying processes
	private int processID;
	
	// Declare burst time to model how long the process takes to run
	private int burstTime;
	
	// Declare arrival time to model variable timing processes enter scheduling
	private int arrivalTime;

	
	/* ================================================ +
	 * 				Process(int, int, int)				|
	 * ================================================ +
	 *  - Constructor to create new processes.			|
	 * ================================================	*/
	public Process(int pid, int burst, int arrival) {
		processID = pid;
		burstTime = burst;
		arrivalTime = arrival;
		}   

	public void setPID(int value) {
		processID = value;
	}

	public void setBurstTime(int value) {
		burstTime = value;
	}
	 
	 public int getPID() {
		return processID;
	}
	 
	 public int getBurstTime() {
		return burstTime;
	}

	public void setArrivalTime(int value) {
		arrivalTime = value;
	}
	 
	public int getArrivalTime() {
		return arrivalTime;
	}
	
	// Custom clone function, copies variable values to new Process
	public Process clone() {
		return new Process(processID, burstTime, arrivalTime);
	}
	
	// Process toString prints the process ID
	public String toString() {
	    return "Process " + processID;
	}
}

