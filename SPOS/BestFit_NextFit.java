import java.util.*;

class Process {
    int id;
    int size;

    public Process(int id, int size) {
        this.id = id;
        this.size = size;
    }
}

class MemoryBlock {
    int size;
    boolean isOccupied;

    public MemoryBlock(int size) {
        this.size = size;
        this.isOccupied = false;
    }
}

class MemoryManager {

    public void bestFit(List<MemoryBlock> memoryBlocks, List<Process> processes) {
        System.out.println("\nBest Fit Memory Allocation:");

        for (Process process : processes) {
            int bestIdx = -1;
            int minSizeDifference = Integer.MAX_VALUE;

            for (int i = 0; i < memoryBlocks.size(); i++) {
                if (!memoryBlocks.get(i).isOccupied && memoryBlocks.get(i).size >= process.size) {
                    int sizeDifference = memoryBlocks.get(i).size - process.size;
                    if (sizeDifference < minSizeDifference) {
                        minSizeDifference = sizeDifference;
                        bestIdx = i;
                    }
                }
            }

            if (bestIdx != -1) {
                memoryBlocks.get(bestIdx).isOccupied = true;
                System.out.println("Process " + process.id + " of size " + process.size +
                        " allocated to block of size " + memoryBlocks.get(bestIdx).size);
            } else {
                System.out.println("Process " + process.id + " of size " + process.size +
                        " cannot be allocated.");
            }
        }
    }

    public void nextFit(List<MemoryBlock> memoryBlocks, List<Process> processes) {
        System.out.println("\nNext Fit Memory Allocation:");

        int lastAllocated = 0;

        for (Process process : processes) {
            boolean allocated = false;

            for (int i = lastAllocated; i < memoryBlocks.size(); i++) {
                if (!memoryBlocks.get(i).isOccupied && memoryBlocks.get(i).size >= process.size) {
                    memoryBlocks.get(i).isOccupied = true;
                    System.out.println("Process " + process.id + " of size " + process.size +
                            " allocated to block of size " + memoryBlocks.get(i).size);
                    lastAllocated = i;
                    allocated = true;
                    break;
                }
            }

            if (!allocated) {
                for (int i = 0; i < lastAllocated; i++) {
                    if (!memoryBlocks.get(i).isOccupied && memoryBlocks.get(i).size >= process.size) {
                        memoryBlocks.get(i).isOccupied = true;
                        System.out.println("Process " + process.id + " of size " + process.size +
                                " allocated to block of size " + memoryBlocks.get(i).size);
                        lastAllocated = i;
                        allocated = true;
                        break;
                    }
                }
            }

            if (!allocated) {
                System.out.println("Process " + process.id + " of size " + process.size +
                        " cannot be allocated.");
            }
        }
    }
}

public class MemoryAllocation {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter number of memory blocks: ");
        int numBlocks = scanner.nextInt();
        List<MemoryBlock> memoryBlocks = new ArrayList<>();
        
        System.out.println("Enter the sizes of memory blocks:");
        for (int i = 0; i < numBlocks; i++) {
            System.out.print("Size of block " + (i + 1) + ": ");
            int blockSize = scanner.nextInt();
            memoryBlocks.add(new MemoryBlock(blockSize));
        }

        System.out.print("\nEnter number of processes: ");
        int numProcesses = scanner.nextInt();
        List<Process> processes = new ArrayList<>();
        
        System.out.println("Enter the sizes of processes:");
        for (int i = 0; i < numProcesses; i++) {
            System.out.print("Size of process " + (i + 1) + ": ");
            int processSize = scanner.nextInt();
            processes.add(new Process(i + 1, processSize));
        }

        MemoryManager memoryManager = new MemoryManager();

        System.out.println("\nChoose Memory Allocation Strategy:");
        System.out.println("1. Best Fit");
        System.out.println("2. Next Fit");
        int choice = scanner.nextInt();

        switch (choice) {
            case 1:
                memoryManager.bestFit(memoryBlocks, processes);
                break;
            case 2:
                memoryManager.nextFit(memoryBlocks, processes);
                break;
            default:
                System.out.println("Invalid choice.");
        }

        scanner.close();
    }
}
