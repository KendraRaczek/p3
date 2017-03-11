/**
 * The FileLine class represents a single line from a sorted
 * input file. It simply contains a string, and a reference to the FileIterator
 * which represents the file from which that line came. This pointer is necessary
 * for implementing the file merge algorithm.
 */
public class FileLine {
    private String s;
    private FileIterator fi;

	/**
	 * Constructs a new FileLine instance with the given String and FileIterator
	 */
    public FileLine(String s, FileIterator fi) {
		this.s = s;
		this.fi = fi;
    }

	/**
	 * @return this FileLine's FileIterator
	 */
    public FileIterator getFileIterator() {
		return fi;
    }

	/**
	 * @return this FileLine's String
	 */
    public String getString() {
		return s;
    }
}
    
    
