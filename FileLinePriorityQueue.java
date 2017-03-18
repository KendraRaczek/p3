/////////////////////////////////////////////////////////////////////////////
// Semester:         CS367 Spring 2017 
// PROJECT:          Program 3
// FILE:             FileLinePriorityQueue.java
//
// TEAM:    Team 35 Java Badgers - P3
// Authors: Michael Yang, Kendra Raczek
// Author1: Michael Yang, yang363@wisc.edu, yang363, LEC 001
// Author2: Kendra Raczek, raczek@wisc.edu, raczek, LEC 001
//
//////////////////////////// 80 columns wide //////////////////////////////////

import java.util.Comparator;

/**
 * An implementation of the MinPriorityQueueADT interface. This implementation 
 * stores FileLine objects. See MinPriorityQueueADT.java for a description 
 * of each method. 
 *
 */
public class FileLinePriorityQueue implements MinPriorityQueueADT<FileLine> {
    	// TODO - working
	private FileLine[] queue;
	private int numItems;
	private Comparator<FileLine> cmp;
	private int maxSize;
	public FileLinePriorityQueue
			(int initialSize, Comparator<FileLine> cmp) {
		this.cmp = cmp;
		maxSize = initialSize;
		queue = new FileLine[maxSize + 1];
		numItems = 0;	
    	}
	
	public FileLine removeMin() throws PriorityQueueEmptyException {
	    	if (isEmpty()) throw new PriortyQueueEmptyException();
	    	FileLine min = queue[1];
	    	queue[1] = queue[numItems];
	    
	    	int parent = 1;
	    	int child;
	    	boolean done = false;
	    	while (!done) {
		    	if (child > numItems) {
			    	done = true;
		    	} else if (cmp.compare(queue[parent], 
					     queue[child]) <= 0) {
			    	done = true;
		    	} else {
			    	swap(parent, child);
			    	child = parent;
		    	}
	    	}
	    	numItems--;
	    	return min;
    	}
	
	public void insert(FileLine fl) throws PriorityQueueFullException, 
			IllegalArgumentException {
		if (fl == null) throw new IllegalArgumentException();
		if (numItems >= maxSize) 
			throw new PriorityQueueFullException();
		queue[numItems + 1] = fl;
				
    		int child = numItems + 1;
    		int parent = 0;
    		boolean done = false;
    		queue[child] = fl;
    		while (! done) {
    			parent = child / 2;
    			if (parent == 0) {
    				done = true;
    			} else if (cmp.compare(queue[parent], 
					       queue[child]) <= 0) {
    				done = true;
    			} else {
				swap(parent, child);
				child = parent;
    		}
    		numItems++;
    	}
		
    	private void swap(int parent, int child) {
	    	FileLine temp;
	    	temp = queue[child];
	    	queue[child] = queue[parent];
	    	queue[parent] = temp;
    	}

    	public boolean isEmpty() {
	    	return numItems == 0;
    	}
}
