import java.util.Comparator;

/**
 * An implementation of the MinPriorityQueueADT interface. This implementation stores FileLine objects.
 * See MinPriorityQueueADT.java for a description of each method. 
 *
 */
public class FileLinePriorityQueue implements MinPriorityQueueADT<FileLine> {
    // TODO - working
	private FileLine[] queue;
	private int numItems;
	
    private Comparator<FileLine> cmp;
    private int maxSize;

    public FileLinePriorityQueue(int initialSize, Comparator<FileLine> cmp) {
		this.cmp = cmp;
		maxSize = initialSize;
		
		// TODO - working
		queue = new FileLine[maxSize];
		numItems = 0;
		
    }

    public FileLine removeMin() throws PriorityQueueEmptyException {
		// TODO

		return null;
    }

    public void insert(FileLine fl) throws PriorityQueueFullException {
		// TODO - working
    	try {
    		if (fl == null) throw new IllegalArgumentExeption();
    		if (numItems >= maxSize) throw new PriorityQueueFullException();
    	} catch (IllegalArgumentEception e) {
    		System.out.println("Illegal Argument");
    		System.exit(1);
    	} catch (PriorityQueueFullException e) {
    		System.out.println("Priority Queue Full");
    		System.exit(1);
    	}
    	
    	int child = numItems + 1;
    	int parent = 0;
    	boolean done = false;
    	queue[child] = fl;
    	while (! done) {
    		parent = child / 2;
    		if (parent == 0) {
    			done = true;
    		}
    		else if (cmp.compare (queue[parent], queue[child]) <= 0) {
    			done = true;
    		}
		else {
			swapQueue(parent, child);
			parent = child;
    	}
    	numItems++;
    }
    private void swapQueue(int parent, int child) {
	    FileLine temp;
	    temp = queue[child];
	    queue[child] = queue[parent];
	    queue[parent] = temp;
    }

    public boolean isEmpty() {
	    return numItems == 0;
    }
}
