package classes;

// Process object. Has the properties of a PID and burst time


public class Process {
	private int processID;
	private int burstTime;
	private int arrivalTime;
	
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
 
	public Process clone() {
		return new Process(processID, burstTime, arrivalTime);
	}
	
	public String toString() {
	    return "Process " + processID;
	}
// Returns a string of the form: "Process --"
}
