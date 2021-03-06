/////////////////////////////////////////////////////////////////////////////
// Semester:         CS367 Spring 2017 
// PROJECT:          Program 3
// FILE:             WeatherRecord.java
//
// TEAM:    Team 35 Java Badgers - P3
// Authors: Michael Yang, Kendra Raczek
// Author1: Michael Yang, yang363@wisc.edu, yang363, LEC 001
// Author2: Kendra Raczek, raczek@wisc.edu, raczek, LEC 001
//
//////////////////////////// 80 columns wide //////////////////////////////////

import java.util.ArrayList;
import java.util.Comparator;

/**
 * The WeatherRecord class is the child class of Record to be used when merging 
 * weather data. Station and Date store the station and date associated with 
 * each weather reading that this object stores. l stores the weather readings, 
 * in the same order as the files from which they came are indexed.
 * <p>Bugs: None that we are aware of
 *
 * @author Michael Yang, Kendra Raczek
 */
public class WeatherRecord extends Record{
	// first number in FileLine
	private int stationID;
	// second number in FileLine
	private int date;
	// number of files to be used for array of readings
	private int numFiles;
	// array of readings for a specific station and date, ordered by type
	private double [] readings;

	/**
	 * Constructs a new WeatherRecord by passing the parameter to the 
	 * parent constructor and then calling the clear method()
	 *
	 * @parm numFiles: number of files to be used for array of readings
	 */
	public WeatherRecord(int numFiles) {
		super(numFiles);
		readings = new double[numFiles];
		clear();
	} //end of WeatherRecord(numFiles) constructor
	
	/**
	 * This comparator should first compare the stations associated with  
	 * the given FileLines. If they are the same, then the dates should 
	 * be compared. 
	 * <p>Bugs: None that we are aware of
 	 *
 	 * @author Michael Yang, Kendra Raczek
	 */
	private class WeatherLineComparator implements Comparator<FileLine> {
		
		/**
		 * This method compares two weather FileLines: if the stationID
		 * or date of the first is less than the second, return a 
		 * negative number; if greater than, return a positve number;
		 * if equal, return 0.
		 *
		 * @param l1: the first FileLine to compare
		 * @param l2: the second FileLine to compare
		 * @return negative, positive, or 0 indicating how the two
		 * FileLines compare
		 */
		public int compare(FileLine l1, FileLine l2) {
			String station1 = l1.getString().split(",")[0];
			String station2 = l2.getString().split(",")[0];
			String date1 = l1.getString().split(",")[1];
			String date2 = l2.getString().split(",")[1];
			
			// converts station and date from String to int
			int stationInt1 = Integer.parseInt(station1);
			int stationInt2 = Integer.parseInt(station2);
			int dateInt1 = Integer.parseInt(date1);
			int dateInt2 = Integer.parseInt(date2);
			
			// first checks if station IDs are equal
			if (stationInt1 != stationInt2) {
				return stationInt1 - stationInt2;
			// if stationIDs equal, then checks if dates are equal
			} else if (dateInt1 != dateInt2) {
				return dateInt1 - dateInt2;
			} else {
				return 0;
			}
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
		
	} //end of WeatherLineComparator class
    
	/**
	 * This method should simply create and return a new instance of the 
	 * WeatherLineComparator class.
	 *
	 * @return new WeatherLineComparator(): new instance of
	 * WeatherLineComparator class.
	 */
	public Comparator<FileLine> getComparator() {
		return new WeatherLineComparator();
	} //end of getComparator() method
	
	/**
	 * This method should fill each entry in the data structure containing
	 * the readings with Double.MIN_VALUE
	 */
	public void clear() {
		for (int i = 0; i < readings.length; i++) {
			readings[i] = Double.MIN_VALUE;
		} 
	} //end of clear() method

	/**
	 * This method should parse the String associated with the given 
	 * FileLine to get the station, date, and reading contained therein. 
	 * Then, in the data structure holding each reading, the entry with 
	 * index equal to the parameter FileLine's index should be set to the 
	 * value of the reading. Also, so that this method will handle merging 
	 * when this WeatherRecord is empty, the station and date associated 
	 * with this WeatherRecord should be set to the station and date 
	 * values which were similarly parsed.
	 *
	 * @param l1: the FileLine to be parsed
	 */
	public void join(FileLine li) {
		String [] splitLine = li.getString().split(",");
		stationID = Integer.parseInt(splitLine[0]);
		date = Integer.parseInt(splitLine[1]);
		readings[li.getFileIterator().getIndex()] 
			= Double.parseDouble(splitLine[2]);
	} //end of join(li) method
	
	/**
	 * See the assignment description and example runs for the 
	 * exact output format.
	 *
	 * @return line: a line of output
	 */
	public String toString() {
		String line = stationID + "," + date + ",";
		for (int i = 0; i < readings.length - 1; i++) {
			if (readings[i] > Double.MIN_VALUE 
			    		|| readings[i] < 0) {
				line += readings[i] + ",";
			// fills absent readings with a "-,"
			} else {
				line += "-,";
			}
		} 
		// accounts for last reading without a following comma
		if (readings[readings.length - 1] > Double.MIN_VALUE 
	    		|| readings[readings.length - 1] < 0) {
			line += readings[readings.length - 1];
		// fills absent readings with a "-"
		} else {
			line += "-";
		}
		return line;
	} //end of toString() method
	
} //end of WeatherRecord class
