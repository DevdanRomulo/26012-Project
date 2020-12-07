/*import java.util.*;

public class SRTF {
    // Java program to implement Shortest Remaining Time First 
// Shortest Remaining Time First (SRTF) 
  
class Process 
{ 
    int pid; // Process ID 
    int bt; // Burst Time 
    int art; // Arrival Time 
      
    public Process(int pid, int bt, int art) 
    { 
        this.pid = pid; 
        this.bt = bt; 
        this.art = art; 
    } 
} 
  
public static class GFG  
{ 
    // Method to find the waiting time for all 
    // processes 
    static void findWaitingTime(Process proc[], int n, int wt[]) {
        int rt[] = new int[n];

        // Copy the burst time into rt[]
        for (int i = 0; i < n; i++)
            rt[i] = proc[i].bt;

        int complete = 0, t = 0, minm = Integer.MAX_VALUE;
        int shortest = 0, finish_time;
        boolean check = false;

        // Process until all processes gets
        // completed
        while (complete != n) {

            // Find process with minimum
            // remaining time among the
            // processes that arrives till the
            // current time`
            for (int j = 0; j < n; j++) {
                if ((proc[j].art <= t) && (rt[j] < minm) && rt[j] > 0) {
                    minm = rt[j];
                    shortest = j;
                    check = true;
                }
            }

            if (check == false) {
                t++;
                continue;
            }

            // Reduce remaining time by one
            rt[shortest]--;

            // Update minimum
            minm = rt[shortest];
            if (minm == 0)
                minm = Integer.MAX_VALUE;

            // If a process gets completely
            // executed
            if (rt[shortest] == 0) {

                // Increment complete
                complete++;
                check = false;

                // Find finish time of current
                // process
                finish_time = t + 1;

                // Calculate waiting time
                wt[shortest] = finish_time - proc[shortest].bt - proc[shortest].art;

                if (wt[shortest] < 0)
                    wt[shortest] = 0;
            }
            // Increment time
            t++;
        }
    }

    // Method to calculate turn around time
    static void findTurnAroundTime(Process proc[], int n, int wt[], int tat[]) {
        // calculating turnaround time by adding
        // bt[i] + wt[i]
        for (int i = 0; i < n; i++)
            tat[i] = proc[i].bt + wt[i];
    }

    // Method to calculate average time
    static void findavgTime(Process proc[], int n) {
        int wt[] = new int[n], tat[] = new int[n];
        int total_wt = 0, total_tat = 0;

        // Function to find waiting time of all
        // processes
        findWaitingTime(proc, n, wt);

        // Function to find turn around time for
        // all processes
        findTurnAroundTime(proc, n, wt, tat);
       
        // Display processes along with all 
        // details 
        System.out.println("Processes " + 
                           " Burst time " + 
                           " Waiting time " + 
                           " Turn around time"); 
       
        // Calculate total waiting time and 
        // total turnaround time 
        for (int i = 0; i < n; i++) { 
            total_wt = total_wt + wt[i]; 
            total_tat = total_tat + tat[i]; 
            System.out.println(" " + proc[i].pid + "\t\t"
                             + proc[i].bt + "\t\t " + wt[i] 
                             + "\t\t" + tat[i]); 
        } 
       
        System.out.println("Average waiting time = " + 
                          (float)total_wt / (float)n); 
        System.out.println("Average turn around time = " + 
                           (float)total_tat / (float)n); 
    } 
      
    // Driver Method 

    public static void main(String[] args) 
    { 
        System.out.println("Input the processes");
         Process proc[] = { new Process(1, 6, 1),  
                            new Process(2, 8, 1), 
                            new Process(3, 7, 2),  
                            new Process(4, 3, 3)}; 
          
         findavgTime(proc, proc.length); 
    } 

} 

}
*/
import java.util.*;
 
public class SRTF {
	public static void main (String args[])
	{
        Scanner sc = new Scanner(System.in);
        //Getting the number of processes
        System.out.print("Please Enter number of Process: ");
      	int num = sc.nextInt();
        //Checking if the number of processes is between 2 and 9
        while(num < 2 || num > 9) {
            System.out.println("Please Enter number of Process between 2 to 9 only.");
            num = sc.nextInt();
        };

		int num_proc = num;
		int proc_id[] = new int[num_proc]; 
		int arv_time[] = new int[num_proc]; 
		int burst_time[] = new int[num_proc]; 
		int completion_time[] = new int[num_proc]; 
		int turnaround_time[] = new int[num_proc];
		int waiting_time[] = new int[num_proc]; 
		int p[] = new int[num_proc];  
		int k[]= new int[num_proc];   
	    int i, st=0, ToT=0;
	    double ave_waiting_time=0, Ave_turnaround=0;
 
	    for (i=0;i<num_proc;i++)
	    {	
        proc_id[i] = i+1;
	    	System.out.print("Enter Arrival Time for Process "+(i+1)+": ");
        arv_time[i]= sc.nextInt();

		System.out.print("Enter Brust Time for Process "+(i+1)+": ");
        burst_time[i]= sc.nextInt();

	    	k[i]= burst_time[i];
		p[i]= 0;	    		
	    }
	    
	    while(true){
	    	int min=99,cur=num_proc;
	    	if (ToT==num_proc)
	    		break;
	    	
	    	for ( i=0;i<num_proc;i++)
	    	{
	    		if ((arv_time[i]<=st) && (p[i]==0) && (burst_time[i]<min))
	    		{	
	    			min=burst_time[i];
	    			cur=i;
	    		}
	    	}
	    	
	    	if (cur==num_proc)
	    		st++;
	    	else
	    	{
	    		burst_time[cur]--;
	    		st++;
	    		if (burst_time[cur]==0)
	    		{
	    			completion_time[cur]= st;
	    			p[cur]=1;
	    			ToT++;
	    		}
	    	}
	    }
	    
	    for(i=0;i<num_proc;i++)
	    {
	    	turnaround_time[i] = completion_time[i] - burst_time[i];
	    	waiting_time[i] = turnaround_time[i] - k[i];
	    	ave_waiting_time += waiting_time[i];
	    	Ave_turnaround += turnaround_time[i];
	   }
	   System.out.printf("%s %s %s %s %s %s\n", "ProcessID", "ArrivalTime", "BurstTime", "CompletionTime", "TurnAroundTime", "WaitingTime");
	    for(i=0;i<num_proc;i++)
	    {
	    	System.out.printf("%8s %12s %9s %13s %15s %10s\n", proc_id[i], arv_time[i], k[i], completion_time[i], turnaround_time[i], waiting_time[i]); 
	    };
	    
		System.out.println ("\n The average turn around time is: "+ (double)(Ave_turnaround/num_proc));
		System.out.println ("\n The average waiting time is: "+ (double)(ave_waiting_time/num_proc));
	    sc.close();
	}
}