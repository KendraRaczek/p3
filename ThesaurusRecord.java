/////////////////////////////////////////////////////////////////////////////
// Semester:         CS367 Spring 2017 
// PROJECT:          Program 3
// FILE:             ThesaurusRecord.java
//
// TEAM:    Team 35 Java Badgers - P3
// Authors: Michael Yang, Kendra Raczek
// Author1: Michael Yang, yang363@wisc.edu, yang363, LEC 001
// Author2: Kendra Raczek, raczek@wisc.edu, raczek, LEC 001
//
//////////////////////////// 80 columns wide //////////////////////////////////

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/**
 * The ThesaurusRecord class is the child class of Record to be used when 
 * merging thesaurus data. The word field is the entry in the thesaurus, 
 * syn is the list of all associated synonyms.
 * <p>Bugs: None that we are aware of
 *
 * @author Michael Yang, Kendra Raczek
 */
public class ThesaurusRecord extends Record{
	// word of thesaurus entry
	private String word;
	// number of files to be used for array of readings
	private int numFiles;
	// list of synonyms for specified word
	private ArrayList<String> syn = new ArrayList<String>();
	
	/**
	 * Constructs a new ThesaurusRecord by passing the parameter to the 
	 * parent constructor and then calling the clear method()
	 *
	 * @parm numFiles: number of files to be used for array of readings
	 */
	public ThesaurusRecord(int numFiles) {
		super(numFiles);
		clear();
	} //end of ThesaurusRecord(numFiles) constructor

	/**
	 * This Comparator should simply behave like the default(lexicographic)
	 * compareTo() method for Strings, applied to the portions of the 
	 * FileLines' Strings up to the ":". The getComparator() method of the 
	 * ThesaurusRecord class will simply return an instance of this class.
	 * <p>Bugs: None that we are aware of
 	 *
 	 * @author Michael Yang, Kendra Raczek
	 */
	private class ThesaurusLineComparator implements Comparator<FileLine> {
		
		/**
		 * This method compares two thesaurus FileLines: if the word
		 * entry of the first is less than the second in lexographic
		 * ordering, return a negative number; if greater than, return
		 * a positive number; if equal, return 0.
		 *
		 * @param l1: the first FileLine to compare
		 * @param l2: the second FileLine to compare
		 * @return negative, positive, or 0 indicating how the two
		 * FileLines compare
		 */
		public int compare(FileLine l1, FileLine l2) {
			String[] line1 = l1.getString().split(":");
			String[] line2 = l2.getString().split(":");
			// compares first word of each line
			return line1[0].compareTo(line2[0]);
		} //end of compare(l1, l2) method
		
		/**
		 * This method checks for whether or not an object is equal
		 * to another object
		 * 
		 * @param o: the object that is being compared to
		 */
		public boolean equals(Object o) {
			return this.equals(o);
		} //end of equals(o) method
		
	} //end of ThesaurusLineComparator class
    
	/**
	 * This method should simply create and return a new instance of 
	 * the ThesaurusLineComparator class.
	 */
	public Comparator<FileLine> getComparator() {
		return new ThesaurusLineComparator();
	} //end of getComparator() method
	
	/**
	 * This method should (1) set the word to null and (2) empty the 
	 * list of synonyms.
	 */
	public void clear() {
    		word = null;
    		try {
			syn.clear();
		} catch (NullPointerException e) {	
		}
	} //end of clear() method
	
	/**
	 * This method should parse the list of synonyms contained in the 
	 * given FileLine and insert any which are not already found in this 
	 * ThesaurusRecord's list of synonyms.
	 *
	 * @param w: the FileLine to be parsed
	 */
	public void join(FileLine w) {
		// array containing word and list of synonyms
		String[] fileSplit = w.getString().split(":");
		word = fileSplit[0];
		// array of synonyms in FileLine
		String[] synArray = fileSplit[1].split(",");
		for (int i = 0; i < synArray.length; i++) {
			if (!syn.contains(synArray[i])) {
				syn.add(synArray[i]);
			}
		}
		Collections.sort(syn);
	} //end of join(w) method
	
	/**
	 * See the assignment description and example runs for the exact 
	 * output format.
	 * 
	 * @return tRecord: a line of output to thesaurus_ouput.txt
	 */
	public String toString() {
		String tRecord = word + ":";
		for (int i = 0; i < syn.size() - 1; i++) {
			tRecord += syn.get(i) + ",";
		}
		// accounts for last synonym without a following comma
		tRecord += syn.get(syn.size() - 1);
		return tRecord;
	} //end of toString() method
	
} //end of ThesaurusRecord class
