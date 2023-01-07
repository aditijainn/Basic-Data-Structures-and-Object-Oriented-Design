/**
 * Name: Aditi Jain
 * ID: A17098965
 * Email: adj003@ucsd.edu
 * Sources used: None
 * 
 * This file contains the custom testers that test
 * edge cases for MyMinHeap
 */

import org.junit.*;
import static org.junit.Assert.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

/**
 * This class contains custom test cases for MyMinHeap
 * 
 * IMPORTANT: Do not change the method names and points are awarded
 * only if your test cases cover cases that the public tester file
 * does not take into account.
 */
public class CustomTester {

	/**
	 * Helper method to initialize all instance variables of MyDeque
	 * 
	 * @param meanHeap The heap to initialize
	 * @param data     The data array
	 */
	static void testMinHeap(MyMinHeap<Integer> heap, ArrayList<Integer> data) {
		heap.data = new ArrayList<>(data);
	}
	
	/**
	 * Test the constructor when null element exists
	 */
	@Test
	public void testMyMinHeapConstructor() {
		ArrayList<Integer> inputList = new ArrayList<Integer>(
			Arrays.asList(
					new Integer[] { 3, 9, 8, 5, 6, null, 1 }
			)
		);
		boolean check = false;
		try {
			MyMinHeap<Integer> minHeap = new MyMinHeap<>(inputList);
		}
		catch(NullPointerException e) {
			check = true;
		}
		assertEquals("if null element", true, check);
	}

	/**
	 * Test the getMinChildIdx method when 
	 */
	@Test
	public void testGetMinChildIdx() {
		MyMinHeap<Integer> heap = new MyMinHeap<>();
		ArrayList<Integer> startingList = new ArrayList<Integer>(
			Arrays.asList(
				new Integer[] { 1, 2, 4, 5, 3, 6 }
			)
		);
		testMinHeap(heap, startingList);
		assertEquals("Minimum child index of 2", 5, heap.getMinChildIdx(2));
		Integer[] expected = { 1, 2, 4, 5, 3, 6 };
		for (int i = 0; i < 5; i++) {
			assertEquals(
					"Heap after getting minimum child index should be unchanged. ",
					expected[i],
					heap.data.get(i));
		}
	}

	/**
	 * Test the percolateUp method on valid index at end of heap
	 */
	@Test
	public void testPercolateUp() {
		MyMinHeap<Integer> heap = new MyMinHeap<>();
		ArrayList<Integer> startingList = new ArrayList<Integer>(
			Arrays.asList(
				new Integer[] { 5, 3, 4, 6, 2, 1 }
			)
		);
		testMinHeap(heap, startingList);
		heap.percolateUp(5);
		Integer[] expected = { 1, 3, 5, 6, 2, 4 };
		for (int i = 0; i < 5; i++) {
			assertEquals(
					"Heap after getting minimum child index should be unchanged. ",
					expected[i],
					heap.data.get(i));
		}
	}

	/**
	 * Test the percolateDown method 
	 * on a valid index in the middle of the heap
	 */
	@Test
	public void testPercolateDown() {
		MyMinHeap<Integer> heap = new MyMinHeap<>();
		ArrayList<Integer> startingList = new ArrayList<Integer>(
			Arrays.asList(
				new Integer[] { 5, 3, 4, 6, 2, 1 }
			)
		);
		testMinHeap(heap, startingList);
		heap.percolateDown(2);
		Integer[] expected = { 5, 3, 1, 6, 2, 4 };
		for (int i = 0; i < 5; i++) {
			assertEquals(
				"Heap after getting minimum child index should be unchanged. ", 
				expected[i], 
				heap.data.get(i)
			);
		}
	}

	/**
	 * Test the deleteIndex method when deleting in the middle
	 */
	@Test
	public void testDeleteIndex() {
		MyMinHeap<Integer> heap = new MyMinHeap<>();
		ArrayList<Integer> startingList = new ArrayList<Integer>(
			Arrays.asList(
				new Integer[] { 5, 3, 4, 6, 2, 1 }
			)
		);
		testMinHeap(heap, startingList);
		heap.deleteIndex(2);
		Integer[] expected = { 1, 3, 5, 6, 2 };
		for (int i = 0; i < 5; i++) {
			assertEquals(
				"Delete in the middle", 
				expected[i], 
				heap.data.get(i)
			);
		}
	}

	/**
	 * Test the deleteIndex method at valid index at the end
	 */
	@Test
	public void testDeleteIndex2() {
		MyMinHeap<Integer> heap = new MyMinHeap<>();
		ArrayList<Integer> startingList = new ArrayList<Integer>(
			Arrays.asList(
				new Integer[] { 5, 3, 4, 6, 2, 1 }
			)
		);
		testMinHeap(heap, startingList);
		heap.deleteIndex(5);
		Integer[] expected = { 5, 3, 4, 6, 2 };
		for (int i = 0; i < 5; i++) {
			assertEquals(
				"Delete in the end", 
				expected[i], 
				heap.data.get(i)
			);
		}
	}

	/**
	 * Test the insert method when inserting multiple times
	 */
	@Test
	public void testInsert(){
		MyMinHeap<Integer> heap = new MyMinHeap<>();
		ArrayList<Integer> startingList = new ArrayList<Integer>(
			Arrays.asList(
				new Integer[] { 3, 5, 4, 6 }
			)
		);
		testMinHeap(heap, startingList);
		heap.insert(1);
		heap.insert(2);
		Integer[] expected = { 1, 3, 2, 6, 5, 4 };
		for (int i = 0; i < 6; i++) {
			assertEquals(
				"Heap after inserting 11. ", 
				expected[i], 
				heap.data.get(i)
			);
		}
		assertEquals(
			"Heap after inserting elements should have bigger size. ", 
			6, 
			heap.data.size()
		);
	}

	/**
	 * Test the insert method when inserting null element
	 */
	@Test
	public void testInsert2(){
		ArrayList<Integer> inputList = new ArrayList<Integer>(
			Arrays.asList(
					new Integer[] { 3, 9, 8, 5, 6, 1 }
			)
		);
		MyMinHeap<Integer> minHeap = new MyMinHeap<>(inputList);
		boolean check = false;
		try {
			minHeap.insert(null);
		}
		catch(NullPointerException e) {
			check = true;
		}
		assertEquals("if null element", true, check);
	}

   
	/**
	 * Test remove when empty heap
	 */
	@Test
	public void testRemove(){
		MyMinHeap<Integer> heap = new MyMinHeap<>(); 
		ArrayList<Integer> startingList = new ArrayList<Integer>(
			Arrays.asList(
				new Integer[]{}
			)
		);
		heap.data = new ArrayList<>(startingList);
		assertEquals("Return null", null, heap.remove());
		assertEquals("Size", 0, heap.data.size());
	}

  
	/**
	 * Test getMin when heap is empty
	 */
	@Test
	public void testGetMin(){
		MyMinHeap<Integer> heap = new MyMinHeap<>(); 
		ArrayList<Integer> startingList = new ArrayList<Integer>(
			Arrays.asList(
				new Integer[]{}
			)
		);
		heap.data = new ArrayList<>(startingList);
		assertEquals("Return null", null, heap.getMin());
		assertEquals("Size", 0, heap.data.size());
	}
}