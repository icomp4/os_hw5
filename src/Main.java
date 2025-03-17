import java.util.Comparator;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;

public class Main {
    public static void main(String[] args) {
        ProcessData p1 = new ProcessData("p1", 2, 2 );
        ProcessData p2 = new ProcessData("p2", 1, 1 );
        ProcessData p3 = new ProcessData("p3", 8, 4 );
        ProcessData p4 = new ProcessData("p4", 4, 2 );
        ProcessData p5 = new ProcessData("p5", 5, 3 );
        System.out.println("First Come First Serve");
        fcfs(p1,p2,p3,p4,p5);
        System.out.println();
        System.out.println("Shortest Job First");
        sjf(p1,p2,p3,p4,p5);

    }
    public static class ProcessData{
        String processID;
        int burstTime;
        int priority;
        int turnaround;
        int wait;

        ProcessData(String pid, int burst, int prio){
            this.processID = pid;
            this.burstTime = burst;
            this.priority = prio;
            this.turnaround = 0;
            this.wait = 0;
        }
    }

    public static void fcfs(ProcessData p1, ProcessData p2,  ProcessData p3,  ProcessData p4, ProcessData p5){
        Queue<ProcessData> q = new LinkedList<>(); // Queue to uphold FIFO
        int totalTime = 0; // Running variable to keep track of how long our processes take
        q.add(p1);
        q.add(p2);
        q.add(p3);
        q.add(p4);
        q.add(p5);

        while(!q.isEmpty()){
            ProcessData curr = q.poll(); // get the next process
            curr.wait = totalTime; // keep track of how long the process had to wait
            curr.turnaround = totalTime + curr.burstTime; // calculate how long it takes for the process to finish, plus the time it took to wait
            totalTime += curr.burstTime; // increment the total time by how long the current process took
        }

        System.out.println("Process ID      | Waiting Time     | Turnaround Time");
        System.out.printf("%-15s | %-16d | %-15d\n", p1.processID, p1.wait, p1.turnaround);
        System.out.printf("%-15s | %-16d | %-15d\n", p2.processID, p2.wait, p2.turnaround);
        System.out.printf("%-15s | %-16d | %-15d\n", p3.processID, p3.wait, p3.turnaround);
        System.out.printf("%-15s | %-16d | %-15d\n", p4.processID, p4.wait, p4.turnaround);
        System.out.printf("%-15s | %-16d | %-15d\n", p5.processID, p5.wait, p5.turnaround);
    }

    public static void sjf(ProcessData p1, ProcessData p2,  ProcessData p3,  ProcessData p4, ProcessData p5){

        // Custom comparator to compare burst times between processes so we can uphold shortest job first
        Queue<ProcessData> q = new PriorityQueue<>(new Comparator<>() {
            @Override
            public int compare(ProcessData p1, ProcessData p2) {
                return Integer.compare(p1.burstTime, p2.burstTime);
            }
        });

        int totalTime = 0;
        q.add(p1);
        q.add(p2);
        q.add(p3);
        q.add(p4);
        q.add(p5);

        while(!q.isEmpty()){
            ProcessData curr = q.poll();
            curr.wait = totalTime;
            curr.turnaround = totalTime + curr.burstTime;
            totalTime += curr.burstTime;
        }

        System.out.println("Process ID      | Waiting Time     | Turnaround Time");
        System.out.printf("%-15s | %-16d | %-15d\n", p1.processID, p1.wait, p1.turnaround);
        System.out.printf("%-15s | %-16d | %-15d\n", p2.processID, p2.wait, p2.turnaround);
        System.out.printf("%-15s | %-16d | %-15d\n", p3.processID, p3.wait, p3.turnaround);
        System.out.printf("%-15s | %-16d | %-15d\n", p4.processID, p4.wait, p4.turnaround);
        System.out.printf("%-15s | %-16d | %-15d\n", p5.processID, p5.wait, p5.turnaround);
    }
}