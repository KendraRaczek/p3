/////////////////////////////////////////////////////////////////////////////
// Semester:         CS367 Spring 2017 
// PROJECT:          Program 3
// FILE:             Reducer.java
//
// TEAM:    Team 35 Java Badgers - P3
// Authors: Michael Yang, Kendra Raczek
// Author1: Michael Yang, yang363@wisc.edu, yang363, LEC 001
// Author2: Kendra Raczek, raczek@wisc.edu, raczek, LEC 001
//
//////////////////////////// 80 columns wide //////////////////////////////////

import java.io.*;
import java.util.*;
import java.lang.*;

/**
 * Reducer solves the following problem: given a set of sorted input files 
 * (each containing the same type of data), merge them into one sorted file. 
 */
public class Reducer {
    // list of files for stocking the PQ
    private List<FileIterator> fileList;
    private String type,dirName,outFile;

    public static void main(String[] args) {
		if (args.length != 3) {
			System.out.println("Usage: java Reducer <weather|" 
					   + "thesaurus> <dir_name>" 
					   +" <output_file>");
			System.exit(1);
		}
		String type = args[0];
		String dirName = args[1];
		String outFile = args[2];
		Reducer r = new Reducer(type, dirName, outFile);
		r.run();
    }
	
	/**
	 * Constructs a new instance of Reducer with the given type (a string 
	 * indicating which type of data is being merged), the directory which 
	 * contains the files to be merged, and the name of the output file.
	 */
    public Reducer(String type, String dirName, String outFile) {
		this.type = type;
		this.dirName = dirName;
		this.outFile = outFile;
    }

	/**
	 * Carries out the file merging algorithm described in the assignment 
	 * description. 
	 */
    public void run() {
		File dir = new File(dirName);
		File[] files = dir.listFiles();
		Arrays.sort(files);

		Record r = null;

		// list of files for stocking the PQ
		fileList = new ArrayList<FileIterator>();

		for (int i = 0; i < files.length; i++) {
			File f = files[i];
			if(f.isFile() && f.getName().endsWith(".txt")) {
				fileList.add(new FileIterator
					     (f.getAbsolutePath(), i));
			}
		}

		switch (type) {
		case "weather":
			r = new WeatherRecord(fileList.size());
			break;
		case "thesaurus":
			r = new ThesaurusRecord(fileList.size());
			break;
		default:
			System.out.println("Invalid type of data! " + type);
			System.exit(1);
		}
		
	    	try {
	    		Comparator<FileLine> cmp = r.getComparator();
			FileLinePriorityQueue fileQueue = new 
	    				FileLinePriorityQueue
	    				(fileList.size(), cmp);
	    		File output = new File(outFile);
			PrintWriter writer = new PrintWriter(output);
			
	    		for (FileIterator itr1 : fileList) {
	    			fileQueue.insert(itr1.next());
	    		}
	    		FileLine file1 = fileQueue.removeMin();
	    		r.join(file1);
			FileIterator itr2 = file1.getFileIterator();
			if (itr2.hasNext()) {
				file1 = itr2.next();
	    			fileQueue.insert(file1);
			}
	    		while (!fileQueue.isEmpty()) {
	    			file1 = fileQueue.removeMin();
				String temp1 = null;
				String temp2 = null;
	    			if (type.equals("thesaurus")) {
	    				temp1 = r.toString().split(":")[0];
					temp2 = file1.getString().split(":")[0];
	    			} else if (type.equals("weather")) {
	    				String [] strArray1 = r.toString().split(",");
					temp1 = strArray1[0] + "," + strArray1[1];
					String [] strArray2 = file1.getString().split(",");
					temp2 = strArray2[0] + "," + strArray2[1];
	    			} if (temp1.equals(temp2)) {
					r.join(file1);
				} else {
	    				writer.println(r);
	    				r.clear();
	    				r.join(file1);
	    			}
	    		}
	    		writer.println(r);
			writer.flush();
	    		writer.close();
			
	    	} catch (FileNotFoundException e) {
	    		System.out.println("Error: File not found.");
	    	} catch (PriorityQueueEmptyException e) {
	    		System.out.println("Error: cannot remove from"
	    				+" an empty queue.");
	    	} catch (PriorityQueueFullException e) {
	    		System.out.println("Error: queue is overfull");
	    	}
    }
}
