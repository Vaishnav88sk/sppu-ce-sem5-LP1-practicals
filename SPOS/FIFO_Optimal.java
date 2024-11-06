import java.util.*;

class PageReplacement {

    // FIFO Page Replacement Algorithm
    public void fifoPageReplacement(int[] pages, int frameCount) {
        Set<Integer> pagesInMemory = new HashSet<>();
        Queue<Integer> pageQueue = new LinkedList<>();
        int pageFaults = 0;

        System.out.println("\nFIFO Page Replacement Algorithm:");

        for (int page : pages) {
            // Check if the page is not in memory
            if (!pagesInMemory.contains(page)) {
                pageFaults++;
                // If memory is full, remove the oldest page
                if (pagesInMemory.size() == frameCount) {
                    int oldestPage = pageQueue.poll();
                    pagesInMemory.remove(oldestPage);
                }
                // Add the new page to memory
                pagesInMemory.add(page);
                pageQueue.add(page);
                System.out.println("Page " + page + " caused a page fault.");
            } else {
                System.out.println("Page " + page + " is already in memory.");
            }
        }
        System.out.println("Total Page Faults: " + pageFaults);
    }

    // Optimal Page Replacement Algorithm
    public void optimalPageReplacement(int[] pages, int frameCount) {
        Set<Integer> pagesInMemory = new HashSet<>();
        int pageFaults = 0;
        int[] nextUse = new int[pages.length];

        System.out.println("\nOptimal Page Replacement Algorithm:");

        for (int i = 0; i < pages.length; i++) {
            int currentPage = pages[i];

            if (!pagesInMemory.contains(currentPage)) {
                pageFaults++;
                if (pagesInMemory.size() < frameCount) {
                    pagesInMemory.add(currentPage);
                    System.out.println("Page " + currentPage + " caused a page fault.");
                } else {
                    int farthestPage = -1;
                    int farthestIndex = -1;
                    for (int page : pagesInMemory) {
                        boolean foundInFuture = false;
                        for (int j = i + 1; j < pages.length; j++) {
                            if (pages[j] == page) {
                                foundInFuture = true;
                                nextUse[page] = j;
                                break;
                            }
                        }
                        if (!foundInFuture) {
                            farthestPage = page;
                            break;
                        }
                    }

                    if (farthestPage == -1) {
                        farthestPage = pagesInMemory.iterator().next();
                    }

                    pagesInMemory.remove(farthestPage);
                    pagesInMemory.add(currentPage);
                    System.out.println("Page " + currentPage + " caused a page fault, replacing page " + farthestPage);
                }
            } else {
                System.out.println("Page " + currentPage + " is already in memory.");
            }
        }
        System.out.println("Total Page Faults: " + pageFaults);
    }
}

public class PageReplacementSim {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter the number of frames: ");
        int frameCount = scanner.nextInt();

        System.out.print("Enter the number of pages: ");
        int numPages = scanner.nextInt();

        int[] pages = new int[numPages];
        System.out.println("Enter the page reference string:");
        for (int i = 0; i < numPages; i++) {
            pages[i] = scanner.nextInt();
        }

        PageReplacement pageReplacement = new PageReplacement();

        System.out.println("\nChoose Page Replacement Algorithm:");
        System.out.println("1. FIFO");
        System.out.println("2. Optimal");
        int choice = scanner.nextInt();

        switch (choice) {
            case 1:
                pageReplacement.fifoPageReplacement(pages, frameCount);
                break;
            case 2:
                pageReplacement.optimalPageReplacement(pages, frameCount);
                break;
            default:
                System.out.println("Invalid choice.");
        }

        scanner.close();
    }
}
