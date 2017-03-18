import java.util.ArrayList;
import java.util.Comparator;

/**
 * The WeatherRecord class is the child class of Record to be used when merging weather data. Station and Date
 * store the station and date associated with each weather reading that this object stores.
 * l stores the weather readings, in the same order as the files from which they came are indexed.
 */
public class WeatherRecord extends Record{
    // TODO declare data structures required - WORKING
	private int stationID;
	private int numFiles;
	private int date;
	private double [] readings;

	/**
	 * Constructs a new WeatherRecord by passing the parameter to the parent constructor
	 * and then calling the clear method()
	 */
    	public WeatherRecord(int numFiles) {
	    	super(numFiles);
	    	readings = new double[numFiles];
	    	clear();
    	}
	
	/**
	 * This comparator should first compare the stations associated with the given FileLines. If 
	 * they are the same, then the dates should be compared. 
	 */
    	private class WeatherLineComparator implements Comparator<FileLine> {
		
	    	public int compare(FileLine l1, FileLine l2) {
			String station1 = l1.getString().split(",")[0];
			String station2 = l2.getString().split(",")[0];
			String date1 = l1.getString().split(",")[1];
			String date2 = l2.getString().split(",")[1];
			
			int stationInt1 = Integer.parseInt(station1);
			int stationInt2 = Integer.parseInt(station2);
			int dateInt1 = Integer.parseInt(date1);
			int dateInt2 = Integer.parseInt(date2);
							 
			if(stationInt1 != stationInt2) {
				return stationInt1 - stationInt2;
			}
			else if(dateInt1 != dateInt2) {
				return date1 - date2;
			}
			else {
				return 0;
			}
		}
		
		public boolean equals(Object o) {
			return this.equals(o);
		}
    }
    
	/**
	 * This method should simply create and return a new instance of the WeatherLineComparator
	 * class.
	 */
    	public Comparator<FileLine> getComparator() {
		return new WeatherLineComparator();
    	}
	
	/**
	 * This method should fill each entry in the data structure containing
	 * the readings with Double.MIN_VALUE
	 */
    	public void clear() {
		for (int i = 0; i < readings.length; i++) {
			readings[i] = Double.MIN_VALUE;
		}
    	}

	/**
	 * This method should parse the String associated with the given FileLine to get the station, date, and reading
	 * contained therein. Then, in the data structure holding each reading, the entry with index equal to the parameter 
	 * FileLine's index should be set to the value of the reading. Also, so that
	 * this method will handle merging when this WeatherRecord is empty, the station and date associated with this
	 * WeatherRecord should be set to the station and date values which were similarly parsed.
	 */
    	public void join(FileLine li) {
	    	String [] splitLine = li.getString.split(",");
	    	stationID = Integer.parseInt(splitLine[0]);
	    	date = Integer.parseInt(splitLine[1]);
	    	readings[li.getFileIterator().getIndex()] = Double.parseDouble(splitLine[2]);
    	}
	
	/**
	 * See the assignment description and example runs for the exact output format.
	 */
    	public String toString() {
		String line = stationID + "," + date + ",";
	    	for(int i = 0; i < readings.length; i++) {
			if(readings[i] > Double.MIN_VALUE) {
				line += readings[i] + ",";
			}
			else {
				line += "-,";
			}
		}
	    	if(readings[readings.length - 1] > Double.MIN_VALUE) {
			line += readings[readings.length - 1];
		}
	    	else {
			line += "-";
		}
	    	return line;
    	}
}
