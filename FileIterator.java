import java.util.*;
import java.lang.*;
import java.io.*;

/**
 * FileIterator represents a sorted input file. It implements iterator,
 * where each call to next() returns a FileLine object representing the next  
 * line in the file. The index field indicates the positive 
 * of this FileIterator relative to the other FileIterators in use by a given instance
 * of the Reducer class.
 */

public class FileIterator implements Iterator {
    private Scanner sc;
    private int idx;

	/**
	 * Constructs a new FileIterator by 1)initializing the Scanner passing the given file name to the constructor
	 * of the Scanner class and 2)setting the index 
	 */
    public FileIterator(String filename, int idx) {
		this.idx = idx;

		try {
			sc = new Scanner(new File(filename));
		} catch (Exception e) {
			System.exit(1);
			sc = null;
		}
    }

	/**
	 * @return the index of this FileIterator
	 */
    public int getIndex() {
		return idx;
    }

    /** @return true if and only if there is a line in this input file which has
     * not yet been returned
     * 
     */
    public boolean hasNext() {
		return sc != null && sc.hasNextLine();
    }

    /** @return the first unseen line from this input file
     * 
     */
    public FileLine next() {
        String s = null;

		try {
			s = sc.nextLine();
		} catch (Exception e) {
			System.out.println("failed to construct instance");
		}

		return new FileLine(s, this);
    }

    /** the remove operation is not supported
     * 
     */
    public void remove() {
		throw new UnsupportedOperationException();
    }
}
