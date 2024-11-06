import java.util.*;

class Page {
    int pageNumber;

    public Page(int pageNumber) {
        this.pageNumber = pageNumber;
    }
}

class FIFO {
    int frameSize;
    Queue<Page> frames;
    Set<Integer> pageSet;

    public FIFO(int frameSize) {
        this.frameSize = frameSize;
        this.frames = new LinkedList<>();
        this.pageSet = new HashSet<>();
    }

    public void processPage(int pageNumber) {
        if (!pageSet.contains(pageNumber)) {
            if (frames.size() == frameSize) {
                Page removedPage = frames.poll();
                pageSet.remove(removedPage.pageNumber);
            }
            frames.offer(new Page(pageNumber));
            pageSet.add(pageNumber);
            System.out.println("Page " + pageNumber + " caused a page fault.");
        } else {
            System.out.println("Page " + pageNumber + " is already in memory.");
        }
    }

    public void displayMemory() {
        System.out.print("Frames: ");
        for (Page page : frames) {
            System.out.print(page.pageNumber + " ");
        }
        System.out.println();
    }
}

class LRU {
    int frameSize;
    LinkedHashMap<Integer, Page> frames;

    public LRU(int frameSize) {
        this.frameSize = frameSize;
        this.frames = new LinkedHashMap<>(frameSize, 0.75f, true);
    }

    public void processPage(int pageNumber) {
        if (!frames.containsKey(pageNumber)) {
            if (frames.size() == frameSize) {
                Iterator<Map.Entry<Integer, Page>> iterator = frames.entrySet().iterator();
                if (iterator.hasNext()) {
                    Map.Entry<Integer, Page> entry = iterator.next();
                    frames.remove(entry.getKey());
                }
            }
            frames.put(pageNumber, new Page(pageNumber));
            System.out.println("Page " + pageNumber + " caused a page fault.");
        } else {
            System.out.println("Page " + pageNumber + " is already in memory.");
        }
    }

    public void displayMemory() {
        System.out.print("Frames: ");
        for (Page page : frames.values()) {
            System.out.print(page.pageNumber + " ");
        }
        System.out.println();
    }
}

public class PagingSimulation {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        System.out.println("Enter the number of frames: ");
        int frameSize = scanner.nextInt();
        
        System.out.println("Enter the number of pages: ");
        int numPages = scanner.nextInt();

        int[] referenceString = new int[numPages];
        
        System.out.println("Enter the reference string: ");
        for (int i = 0; i < numPages; i++) {
            referenceString[i] = scanner.nextInt();
        }

        System.out.println("\nFIFO Paging Simulation:");
        FIFO fifo = new FIFO(frameSize);
        for (int i = 0; i < numPages; i++) {
            fifo.processPage(referenceString[i]);
            fifo.displayMemory();
        }

        System.out.println("\nLRU Paging Simulation:");
        LRU lru = new LRU(frameSize);
        for (int i = 0; i < numPages; i++) {
            lru.processPage(referenceString[i]);
            lru.displayMemory();
        }

        scanner.close();
    }
}
