import java.util.ArrayList;
import java.util.Comparator;

/**
 * The ThesaurusRecord class is the child class of Record to be used when merging thesaurus data.
 * The word field is the entry in the thesaurus, syn is the list of all associated synonyms.
 */

public class ThesaurusRecord extends Record{
    // TODO declare data structures required - WORKING
	
	private String word;
	private ArrayList<String> syn;
	
	

	/**
	 * Constructs a new ThesaurusRecord by passing the parameter to the parent constructor
	 * and then calling the clear method()
	 */
    public ThesaurusRecord(int numFiles) {
	super(numFiles);
	clear();
    }

    /**
	 * This Comparator should simply behave like the default (lexicographic) compareTo() method
	 * for Strings, applied to the portions of the FileLines' Strings up to the ":"
	 * The getComparator() method of the ThesaurusRecord class will simply return an
	 * instance of this class.
	 */
	private class ThesaurusLineComparator implements Comparator<FileLine> {
		public int compare(FileLine l1, FileLine l2) {
			// TODO implement compare() functionality

			String[] line1 = l1.getString().split(":");
			String[] line2 = l2.getString().split(":");
			
			return line1[0].compareTo(line2[0]);
			
		}
		
		public boolean equals(Object o) {
			return this.equals(o);
		}
    }
    
	/**
	 * This method should simply create and return a new instance of the ThesaurusLineComparator class.
	 */
    public Comparator<FileLine> getComparator() {
		return new ThesaurusLineComparator();
    }
	
	/**
	 * This method should (1) set the word to null and (2) empty the list of synonyms.
	 */
    public void clear() {
		// TODO initialize/reset data members
    }
	
	/**
	 * This method should parse the list of synonyms contained in the given FileLine and insert any
	 * which are not already found in this ThesaurusRecord's list of synonyms.
	 */
    public void join(FileLine w) {
		// TODO implement join() functionality
    	// use FileLine iterator
    }
	
	/**
	 * See the assignment description and example runs for the exact output format.
	 */
    public String toString() {
		// TODO
		return null;
	}
}
