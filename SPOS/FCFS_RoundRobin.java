import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class Process {
    int id;
    int burstTime;
    int arrivalTime;
    int waitingTime;
    int turnaroundTime;
    int remainingTime;

    public Process(int id, int burstTime, int arrivalTime) {
        this.id = id;
        this.burstTime = burstTime;
        this.arrivalTime = arrivalTime;
        this.remainingTime = burstTime;
    }
}

class Scheduler {
    public void fcfs(List<Process> processes) {
        int time = 0;
        for (Process p : processes) {
            if (time < p.arrivalTime) time = p.arrivalTime;
            p.waitingTime = time - p.arrivalTime;
            p.turnaroundTime = p.waitingTime + p.burstTime;
            time += p.burstTime;
        }
        displayProcessDetails(processes);
    }

    public void roundRobin(List<Process> processes, int quantum) {
        int time = 0, remainingProcesses = processes.size();
        while (remainingProcesses > 0) {
            for (Process p : processes) {
                if (p.remainingTime > 0) {
                    if (p.remainingTime > quantum) {
                        time += quantum;
                        p.remainingTime -= quantum;
                    } else {
                        time += p.remainingTime;
                        p.waitingTime = time - p.arrivalTime - p.burstTime;
                        p.turnaroundTime = p.waitingTime + p.burstTime;
                        p.remainingTime = 0;
                        remainingProcesses--;
                    }
                }
            }
        }
        displayProcessDetails(processes);
    }

    private void displayProcessDetails(List<Process> processes) {
        System.out.println("Process\tBurst Time\tArrival Time\tWaiting Time\tTurnaround Time");
        for (Process p : processes) {
            System.out.println("P" + p.id + "\t\t" + p.burstTime + "\t\t" + p.arrivalTime + "\t\t" +
                               p.waitingTime + "\t\t" + p.turnaroundTime);
        }
        double totalWaitingTime = 0, totalTurnaroundTime = 0;
        for (Process p : processes) {
            totalWaitingTime += p.waitingTime;
            totalTurnaroundTime += p.turnaroundTime;
        }
        System.out.printf("Average Waiting Time: %.2f\n", totalWaitingTime / processes.size());
        System.out.printf("Average Turnaround Time: %.2f\n", totalTurnaroundTime / processes.size());
    }
}

public class FCFS_RoundRobin {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        List<Process> processes = new ArrayList<>();

        System.out.print("Enter the number of processes: ");
        int n = sc.nextInt();

        for (int i = 1; i <= n; i++) {
            System.out.print("Enter burst time for Process " + i + ": ");
            int burstTime = sc.nextInt();
            System.out.print("Enter arrival time for Process " + i + ": ");
            int arrivalTime = sc.nextInt();
            processes.add(new Process(i, burstTime, arrivalTime));
        }

        Scheduler scheduler = new Scheduler();
        System.out.println("\nChoose Scheduling Algorithm:");
        System.out.println("1. FCFS");
        System.out.println("2. Round Robin");
        int choice = sc.nextInt();

        switch (choice) {
            case 1:
                scheduler.fcfs(processes);
                break;
            case 2:
                System.out.print("Enter time quantum: ");
                int quantum = sc.nextInt();
                scheduler.roundRobin(processes, quantum);
                break;
            default:
                System.out.println("Invalid choice");
        }
        sc.close();
    }
}
