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
 * <p>Bugs: None that we are aware of
 *
 * @author Michael Yang, Kendra Raczek
 */
public class FileLinePriorityQueue implements MinPriorityQueueADT<FileLine> {
	// array-based min heap containing FileLines taken from 
	private FileLine[] queue;
	// number of FileLine objects currently in priority queue
	private int numItems;
	// comparator used to compare values of FileLines in the priority queue
	private Comparator<FileLine> cmp;
	// the maximum size of the array depending on the number of files
	private int maxSize;

	/**
	* Constructs a new min priority queue for either weather or thesaurus 
	* FileLines. Contains a comparator, array-based heap, its size, and 
	* count of FileLines.
	* @param initialSize: maximum size of the array
	* @param cmp comparator for either weather or thesaurus
	*/
	public FileLinePriorityQueue (int initialSize, 
			Comparator<FileLine> cmp) {
		this.cmp = cmp;
		maxSize = initialSize;
		queue = new FileLine[maxSize + 1];
		numItems = 0;	
	} //end of FileLinePriorityQueue(initialSize, cmp) constructor
	
       /**
	* Removes the minimum element from the Priority Queue, and returns it.
	* Also reheapifies the array to preserve min-based ordering.
	* @throws PriorityQueueEmptyException if the priority queue has no 
	* elements in it
	* @return min the minimum element in the queue, according to the 
	* compareTo() method of FileLine.
	*/
	public FileLine removeMin() throws PriorityQueueEmptyException {
		if (isEmpty()) throw new PriorityQueueEmptyException();
		
		// remove min and replace root with last item
		FileLine min = queue[1];
		queue[1] = queue[numItems];
		queue[numItems] = null;
	    	
		// reheapify min priority queue
		int parent = 1;
		int child = 0;
		boolean done = false;
		numItems--;
		while (!done) {
			child = parent * 2;
			// checks to set child to the lesser of two children
			if ((!(child + 1 > numItems))) {
				if ( cmp.compare(queue[child], 
						 queue[child+1]) > 0) {
					child++;
				}
			}
			// if child index is beyond the array
			if (child > numItems) {
				done = true;
			// if parent is less than the child, queue is ordered
			} else if (cmp.compare(queue[parent], 
					       queue[child]) <= 0) {
				done = true;
			} else {
				swap(parent, child);
				parent = child;
			}
		}
		return min;
	} //end of removeMin() method
	
	/**
	* Inserts a FileLine into the queue, making sure to keep the shape and
	* order properties intact.
	* @throws PriorityQueueFullException if the priority queue is full
	* @throws IllegalArgumentException if FileLine to insert is null
	* @param fl the FileLine to insert
	*/
	public void insert(FileLine fl) throws PriorityQueueFullException, 
			IllegalArgumentException {
		if (fl == null) throw new IllegalArgumentException();
		if (numItems >= maxSize) 
			throw new PriorityQueueFullException();
				
		// insert item at end of the array
		queue[numItems + 1] = fl;
    		int child = numItems + 1;
    		int parent = 0;
    		boolean done = false;
    		queue[child] = fl;
				
		// reheapify min priority queue	
    		while (! done) {
    			parent = child / 2;
    			if (parent == 0) {
    				done = true;
			// if parent is less than the child, queue is ordered
    			} else if (cmp.compare(queue[parent], 
					      queue[child]) <= 0) {
    				done = true;
    			} else {
				swap(parent, child);
				child = parent;
    			}	
    		}
    		numItems++;
	} //end of insert(fl) method
	
	/**
	* This method swaps two FileLines in the priority queue.
	* @param parent: the higher priority FileLine to swap
	* @param child: the lower priority FileLine to swap
	*/
	private void swap(int parent, int child) {
		FileLine temp;
		temp = queue[child];
		queue[child] = queue[parent];
		queue[parent] = temp;
	} //end of swap(parent, child) method

	/**
	* This method checks for whether or not the queue is empty.
	* @return true, if it is empty; false otherwise
	*/
	public boolean isEmpty() {
		return numItems == 0;
	} //end of isEmpty() method
	
} //end of FileLinePriorityQueue class
