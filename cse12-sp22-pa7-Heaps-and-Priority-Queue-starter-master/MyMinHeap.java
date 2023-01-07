/**
 * Name: Aditi Jain
 * ID: A17098965
 * Email: adj003@ucsd.edu
 * Sources used: Zybooks and Lecture Slides
 * 
 * Contains helper and core methods that implement
 * the MinHeap data structure
 */

import java.util.*;

/**
 * Contains helper and core methods that implement
 * the MinHeap data structure
 */
public class MyMinHeap<E extends Comparable<E>> implements MinHeapInterface <E>{
	public ArrayList<E> data;
	private static final int TWO = 2;
	/**
	 * No-argument constructor that initializes 
	 * data to an empty ArrayList
	 */
	public MyMinHeap() {
		data = new ArrayList<>();
	}
	/**
	 * Initializes a min-heap using the elements in collection
	 * @param collection - arraylist of elements to insert
	 */
	public MyMinHeap(Collection<? extends E> collection) {
		if(collection == null || collection.contains(null)) {
			throw new NullPointerException();
		}
		// iterate backward to sort in correct order
		data = new ArrayList<>(collection);
		for(int i=data.size()-1; i>=0; i--) {
			percolateDown(i);
		}
	}
	/**
	 * Swap the elements at from and to indices in data.
	 * @param from - starting index
	 * @param to - ending index
	 */
	protected void swap(int from, int to) {
		E first = data.get(from);
		data.set(from, data.get(to));
		data.set(to, first);
	}
	/**
	 * Calculate and return the parent index of 
	 * the parameter index.
	 * @param index - of selected element
	 * @return - parent index
	 */
	protected int getParentIdx(int index) {
		return (index-1)/TWO;
	}
	/**
	 * Calculate and return the left child index of 
	 * the parameter index.
	 * @param index - of selected element
	 * @return - left child index
	 */
	protected int getLeftChildIdx(int index) {
		return (TWO*index)+1;
	}
	/**
	 * Calculate and return the right child index of 
	 * the parameter index.
	 * @param index - of selected element
	 * @return - right child index
	 */
	protected int getRightChildIdx(int index) {
		return (TWO*index)+2;
	}
	/**
	 * Return the index of the smaller child 
	 * of the element at index.
	 * @param index - of selected element
	 * @return
	 */
	protected int getMinChildIdx(int index) {
		if(index * 2+1 > data.size()-1) {
			return -1;
		}
		else if(index * 2 + 2 > data.size() -1) {
			return getLeftChildIdx(index);
		}
		else {
		// comparing two children to return smaller
			if(data.get(getLeftChildIdx(index)).compareTo
			(data.get(getRightChildIdx(index))) <= 0) {
				return getLeftChildIdx(index);
			}
			else {
				return getRightChildIdx(index);
			}
		}
	}
	/**
	 * Percolate the element at index up until no heap 
	 * properties are violated by this element
	 * @param index - of selected element
	 */
	protected void percolateUp(int index) {
		while(index > 0) {
			// returns if parent is smaller
			if(data.get(index).compareTo
			(data.get(getParentIdx(index))) >= 0) {
				return;
			}	
			else {
				swap(index, getParentIdx(index));
				index = getParentIdx(index);
			}
		}
	}
	/**
	 * Percolate the element at index down until 
	 * no heap properties are violated by this element
	 * @param index - of selected element
	 */
	protected void percolateDown(int index) {
		E value = data.get(index);
		int childidx = getLeftChildIdx(index);
		while(childidx < data.size()) {
			E minVal = value;
			int minIndex = -1;
			for(int i=0; i<TWO && (i+childidx) < data.size(); i++) {
				if(data.get(childidx+i).compareTo(minVal) < 0) {
					minVal = data.get(childidx+i);
					minIndex = childidx+i;
				}
			}
			// return if values are equal
			if(minVal.compareTo(value) == 0) {
				return;
			}
			else {
				swap(index, minIndex);
				index = minIndex;
				childidx = getLeftChildIdx(index);
			}
		}
	}
	/**
	 * Remove the element at index from data and return it
	 * @param index - of selected element
	 * @return - deleted element
	 */
	protected E deleteIndex(int index) {
		E removed = data.get(index);
		// return if last element after removing
		if(index == data.size()-1) {
			data.remove(index);
			return removed;
		}
		else {
			data.set(index, data.get(data.size()-1));
			data.remove(data.size()-1);
			if(data.get(index).compareTo
			// compare to parent to decide percolation direction
			(data.get(getParentIdx(index))) < 0) {
				percolateUp(index);
			}
			else {
				percolateDown(index);
			}
			return removed;
		}
	} 
	/**
	 * Add element to the end of the heap
	 * @param - element to insert
	 */
	public void insert(E element) {
		if(element == null) {
			throw new NullPointerException();
		}
		data.add(data.size(), element);
		percolateUp(data.size()-1);
	}
	/**
	 * Return root of heap
	 * @return - smallest element
	 */
	public E getMin() {
		if(data.size() == 0) {
			return null;
		}
		else {
			return data.get(0);
		}
	}
	/**
	 * Remove and return the root
	 * @return - smallest element
	 */
	public E remove() {
		if(data.size() == 0) {
			return null;
		}
		percolateDown(data.size()-1);
		E removed = data.get(0);
		// use helper method deleteIndex()
		deleteIndex(0);
		return removed;
	}
	/**
	 * Return the number of elements in this min-heap
	 * @return - size of int
	 */
	public int size() {
		return data.size();
	}
	/**
	 * Clear out entire heap
	 */
	public void clear() {
		data.clear();
	}
}