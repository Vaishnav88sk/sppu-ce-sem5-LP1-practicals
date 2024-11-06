import java.util.Scanner;

class MemoryBlock {
    int size;
    boolean isAllocated;

    public MemoryBlock(int size) {
        this.size = size;
        this.isAllocated = false;
    }
}

public class MemoryPlacement {
    public static void firstFit(MemoryBlock[] memoryBlocks, int[] processSizes) {
        for (int i = 0; i < processSizes.length; i++) {
            boolean allocated = false;
            for (int j = 0; j < memoryBlocks.length; j++) {
                if (!memoryBlocks[j].isAllocated && memoryBlocks[j].size >= processSizes[i]) {
                    memoryBlocks[j].isAllocated = true;
                    System.out.println("Process " + (i + 1) + " allocated to block " + (j + 1));
                    allocated = true;
                    break;
                }
            }
            if (!allocated) {
                System.out.println("Process " + (i + 1) + " not allocated.");
            }
        }
    }

    public static void worstFit(MemoryBlock[] memoryBlocks, int[] processSizes) {
        for (int i = 0; i < processSizes.length; i++) {
            boolean allocated = false;
            int maxBlockIndex = -1;
            for (int j = 0; j < memoryBlocks.length; j++) {
                if (!memoryBlocks[j].isAllocated && memoryBlocks[j].size >= processSizes[i]) {
                    if (maxBlockIndex == -1 || memoryBlocks[j].size > memoryBlocks[maxBlockIndex].size) {
                        maxBlockIndex = j;
                    }
                }
            }
            if (maxBlockIndex != -1) {
                memoryBlocks[maxBlockIndex].isAllocated = true;
                System.out.println("Process " + (i + 1) + " allocated to block " + (maxBlockIndex + 1));
            } else {
                System.out.println("Process " + (i + 1) + " not allocated.");
            }
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter the number of memory blocks: ");
        int n = sc.nextInt();
        MemoryBlock[] memoryBlocks = new MemoryBlock[n];
        System.out.println("Enter the sizes of memory blocks:");
        for (int i = 0; i < n; i++) {
            memoryBlocks[i] = new MemoryBlock(sc.nextInt());
        }

        System.out.print("Enter the number of processes: ");
        int m = sc.nextInt();
        int[] processSizes = new int[m];
        System.out.println("Enter the sizes of processes:");
        for (int i = 0; i < m; i++) {
            processSizes[i] = sc.nextInt();
        }

        System.out.println("\nFirst Fit Allocation:");
        firstFit(memoryBlocks, processSizes);

        // Reset memory blocks for Worst Fit simulation
        for (int i = 0; i < n; i++) {
            memoryBlocks[i].isAllocated = false;
        }

        System.out.println("\nWorst Fit Allocation:");
        worstFit(memoryBlocks, processSizes);

        sc.close();
    }
}
