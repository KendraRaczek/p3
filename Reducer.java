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
		
		FileLinePriorityQueue fileQueue = new FileLinePriorityQueue
				(fileList.size(), r.getComparator());
		PrintWriter output = new PrintWriter(outFile);
	    	try {
			for (int = 0; i < fileList.size(); i++) {
				fileQueue.insert(fileList.get(i).next());
			}
			FileLine file1 = fileQueue.removeMin()
			r.join(file1);
			fileQueue.insert(file1.getFileIterator().next());
			while (!fileQueue.isEmpty()) {
				FileLine file2 = fileQueue.removeMin();
				if (file2.getFileIterator().hasNext()) {
					fileQueue.insert(file2.
							 getFileIterator().
							 next());
				}
				if (r.getComparator().compare(file1, file2) 
				    	== 0) {
					r.join(file2);
				}
				else {
					output.println(r);
					r.clear()
					file1 = file2;
					r.join(file1);
				}
			}
			output.println(r.toString());
			output.close();
			
		} catch (FileNotFoundException e) {
			System.out.println("Error: File not found.");
		} catch (PriorityQueueEmptyException e) {
			System.out.println("Error: cannot remove from"
					   +" an empty queue.");
		} catch (FileNotFoundException e) {
			System.out.println("Error: queue is overfull");
		}
    	}
}
