# Process-Scheduler
This is a start to the Process Scheduler project. The scheduler uses a class called Generator to randomly generate processes of varying burst times and arrival times. There is also a class that can calculate wait time (although it does not yet consider arrival time, only burst time).

The algorithms covered so far are FCFS and SJF. FCFS does not need any specific code - it only needs to have the processes generated because they start out in order according to their pid. The SJF algorithm sorts processes by burst time and does not yet consider arrival time. More complicated calculations will be necessary for the SJF algorithm to consider arrival time as well as burst time. It is a preemptive algorithm.
