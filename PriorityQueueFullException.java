import java.lang.Exception;

/**
 * The Class PriorityQueueFullException. It is to be thrown when an attempt is made
 * to add an element to a full queue.
 */
public class PriorityQueueFullException extends Exception {
    
    private static final long serialVersionUID = 1L;

    /**
     * Instantiates a new priority queue full exception.
     */
    public PriorityQueueFullException() { 
        super(); 
    }
}
