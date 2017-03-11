import java.util.Comparator;

/**
 * The Record class represents all of the data associated with a single key value.
 * Thus it represents a single line in the output file. There will be one child class
 * for each application (i.e. weather data, thesaurus data, etc.).
 */
abstract class Record {
    private int numFiles;

	/**
	 * Constructs a new Record by initializing the numFiles field with the given integer.
	 */
    public Record(int numFiles) {
		this.numFiles = numFiles;
    }

	/**
	 * Constructs an uninitialized Record
	 */
    public Record() {
    }

	/**
	 * @return the total number of files which are present in the merge process in which this Record is being used.
	 */
    public int getNumFiles() {
		return numFiles;
    }
	
	/**
	 * Merges the given FileLine with this record object in a way which is appropriate for
	  * the given application
	 */
    abstract public void join(FileLine l);
	
    abstract public String toString();
	
	/**
	 *Removes all application data from this Record. 
	 */
    abstract public void clear();

	/**
	 * @eturn a Comparator which can compare two FileLine objects in
	 * a way that is appropriate for the given application.
	 */ 
    abstract public Comparator<FileLine> getComparator();
}
