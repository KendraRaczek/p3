import java.lang.Exception;

/**
 * The Class PriorityQueueEmptyException. It is to be thrown when an attempt
 * is made to remove an element from an empty queue.
 */
public class PriorityQueueEmptyException extends Exception {
    
    private static final long serialVersionUID = 1L;

    /**
     * Instantiates a new priority queue empty exception.
     */
    public PriorityQueueEmptyException() { 
        super(); 
    }
}
