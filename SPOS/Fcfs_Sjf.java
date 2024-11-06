import java.util.*;

class Process {
    int id;
    int burstTime;
    int arrivalTime;
    int remainingTime;
    int waitingTime;
    int turnaroundTime;

    public Process(int id, int burstTime, int arrivalTime) {
        this.id = id;
        this.burstTime = burstTime;
        this.arrivalTime = arrivalTime;
        this.remainingTime = burstTime;
        this.waitingTime = 0;
        this.turnaroundTime = 0;
    }
}

class Scheduler {
    
    public void fcfs(List<Process> processes) {
        processes.sort(Comparator.comparingInt(p -> p.arrivalTime));
        int currentTime = 0;

        System.out.println("\nFCFS Scheduling:");

        for (Process p : processes) {
            if (currentTime < p.arrivalTime) {
                currentTime = p.arrivalTime;
            }
            p.waitingTime = currentTime - p.arrivalTime;
            p.turnaroundTime = p.waitingTime + p.burstTime;
            currentTime += p.burstTime;
            System.out.println("Process " + p.id + " | Arrival Time: " + p.arrivalTime + 
                               " | Burst Time: " + p.burstTime + 
                               " | Waiting Time: " + p.waitingTime + 
                               " | Turnaround Time: " + p.turnaroundTime);
        }

        double avgWaitingTime = processes.stream().mapToInt(p -> p.waitingTime).average().orElse(0);
        double avgTurnaroundTime = processes.stream().mapToInt(p -> p.turnaroundTime).average().orElse(0);
        
        System.out.printf("Average Waiting Time: %.2f\n", avgWaitingTime);
        System.out.printf("Average Turnaround Time: %.2f\n", avgTurnaroundTime);
    }

    public void sjfPreemptive(List<Process> processes) {
        processes.sort(Comparator.comparingInt(p -> p.arrivalTime));
        int currentTime = 0;
        int completed = 0;
        PriorityQueue<Process> readyQueue = new PriorityQueue<>(Comparator.comparingInt(p -> p.remainingTime));
        
        System.out.println("\nSJF (Preemptive) Scheduling:");

        while (completed < processes.size()) {
            for (Process p : processes) {
                if (p.arrivalTime <= currentTime && p.remainingTime > 0 && !readyQueue.contains(p)) {
                    readyQueue.add(p);
                }
            }

            if (readyQueue.isEmpty()) {
                currentTime++;
                continue;
            }

            Process currentProcess = readyQueue.poll();
            currentProcess.remainingTime--;
            currentTime++;

            if (currentProcess.remainingTime == 0) {
                currentProcess.turnaroundTime = currentTime - currentProcess.arrivalTime;
                currentProcess.waitingTime = currentProcess.turnaroundTime - currentProcess.burstTime;
                completed++;
            } else {
                readyQueue.add(currentProcess);
            }

            System.out.println("Process " + currentProcess.id + " | Time: " + currentTime + 
                               " | Remaining Time: " + currentProcess.remainingTime);
        }

        double avgWaitingTime = processes.stream().mapToInt(p -> p.waitingTime).average().orElse(0);
        double avgTurnaroundTime = processes.stream().mapToInt(p -> p.turnaroundTime).average().orElse(0);
        
        System.out.printf("Average Waiting Time: %.2f\n", avgWaitingTime);
        System.out.printf("Average Turnaround Time: %.2f\n", avgTurnaroundTime);
    }
}

public class SchedulingAlgorithms {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        List<Process> processes = new ArrayList<>();

        System.out.print("Enter number of processes: ");
        int n = sc.nextInt();

        for (int i = 1; i <= n; i++) {
            System.out.print("Enter burst time for process " + i + ": ");
            int burstTime = sc.nextInt();

            System.out.print("Enter arrival time for process " + i + ": ");
            int arrivalTime = sc.nextInt();

            processes.add(new Process(i, burstTime, arrivalTime));
        }

        Scheduler scheduler = new Scheduler();

        System.out.println("\nChoose Scheduling Algorithm:");
        System.out.println("1. FCFS");
        System.out.println("2. SJF (Preemptive)");
        int choice = sc.nextInt();

        switch (choice) {
            case 1:
                scheduler.fcfs(processes);
                break;
            case 2:
                scheduler.sjfPreemptive(processes);
                break;
            default:
                System.out.println("Invalid choice.");
        }
        
        sc.close();
    }
}
