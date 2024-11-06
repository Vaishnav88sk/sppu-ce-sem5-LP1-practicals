import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

class Process {
    int id;
    int burstTime;
    int arrivalTime;
    int priority;
    int waitingTime;
    int turnaroundTime;
    int remainingTime;

    public Process(int id, int burstTime, int arrivalTime, int priority) {
        this.id = id;
        this.burstTime = burstTime;
        this.arrivalTime = arrivalTime;
        this.priority = priority;
        this.remainingTime = burstTime;
    }
}

class Scheduler {
    public void sjfPreemptive(List<Process> processes) {
        int time = 0, completed = 0, n = processes.size();
        Process currentProcess = null;

        System.out.println("\n--- SJF (Preemptive) Scheduling ---");
        while (completed < n) {
            for (Process p : processes) {
                if (p.arrivalTime <= time && p.remainingTime > 0) {
                    if (currentProcess == null || p.remainingTime < currentProcess.remainingTime) {
                        currentProcess = p;
                    }
                }
            }
            if (currentProcess != null) {
                currentProcess.remainingTime--;
                time++;

                if (currentProcess.remainingTime == 0) {
                    currentProcess.turnaroundTime = time - currentProcess.arrivalTime;
                    currentProcess.waitingTime = currentProcess.turnaroundTime - currentProcess.burstTime;
                    completed++;
                    currentProcess = null;
                }
            } else {
                time++;
            }
        }
        displayProcessDetails(processes);
    }

    public void priorityNonPreemptive(List<Process> processes) {
        processes.sort(Comparator.comparingInt((Process p) -> p.arrivalTime).thenComparingInt(p -> p.priority));
        int time = 0;

        System.out.println("\n--- Priority (Non-Preemptive) Scheduling ---");
        for (Process p : processes) {
            if (time < p.arrivalTime) time = p.arrivalTime;
            p.waitingTime = time - p.arrivalTime;
            p.turnaroundTime = p.waitingTime + p.burstTime;
            time += p.burstTime;
        }
        displayProcessDetails(processes);
    }

    private void displayProcessDetails(List<Process> processes) {
        System.out.println("Process\tBurst Time\tArrival Time\tPriority\tWaiting Time\tTurnaround Time");
        for (Process p : processes) {
            System.out.println("P" + p.id + "\t\t" + p.burstTime + "\t\t" + p.arrivalTime + "\t\t" +
                               p.priority + "\t\t" + p.waitingTime + "\t\t" + p.turnaroundTime);
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

public class SchedulingAlgorithms {
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
            System.out.print("Enter priority for Process " + i + ": ");
            int priority = sc.nextInt();
            processes.add(new Process(i, burstTime, arrivalTime, priority));
        }

        Scheduler scheduler = new Scheduler();
        System.out.println("\nChoose Scheduling Algorithm:");
        System.out.println("1. SJF (Preemptive)");
        System.out.println("2. Priority (Non-Preemptive)");
        int choice = sc.nextInt();

        switch (choice) {
            case 1:
                scheduler.sjfPreemptive(processes);
                break;
            case 2:
                scheduler.priorityNonPreemptive(processes);
                break;
            default:
                System.out.println("Invalid choice");
        }
        sc.close();
    }
}
