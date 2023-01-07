/**
 * Name: Aditi Jain
 * ID: A17098965
 * Email: adj003@ucsd.edu
 * Sources used: Piazza and Lecture Slides
 * 
 * This file contains Unit tests that test exception and invalid 
 * cases in MyArrayList.java
 */

 //IMPORTANT: Do not change the headers!

import static org.junit.Assert.*;
import org.junit.*;

/**
 * This class creates a test fixture and runs multiple tests on 
 * the implementation for MyArrayList.
 * 
 * Instance variables:
 * arr - Object array that will be used to set up the test fixture
 * fullInts - Integer array that will be used to set up the test fixture
 * arrInts - Integer array that will be used to set up the test fixture
 */
public class MyArrayListHiddenTester {
	static final int DEFAULT_CAPACITY = 5;
	static final int MY_CAPACITY = 3;
	static final int ILLEGAL_CAPACITY = -1;

	Object[] arr = null;
	Integer[] fullInts = {1, 2, 3, 4, 5};
	Integer[] arrInts = {1, 2, 3};

	private MyArrayList nullArgList, fullList, listWithInt;
	/**
	 * This sets up the test fixture. JUnit invokes this method before
	 * every testXXX method. The @Before tag tells JUnit to run this method
	 * before each test */
	@Before
	public void setUp() throws Exception {
		nullArgList = new MyArrayList(null);
		fullList = new MyArrayList<Integer>(fullInts);
		listWithInt = new MyArrayList<Integer>(arrInts);
	}

	/**
	 * Aims to test the constructor when the input argument
	 * is not valid
	 */
	@Test
	public void testConstructorInvalidArg(){
		boolean check = false;
		try {
			MyArrayList invalidArgList = new MyArrayList(ILLEGAL_CAPACITY);
		  }
		catch (IllegalArgumentException e) {
			check = true;
		}
		assertEquals("Check input for capacity arg constructor", true, 
		check);
	}

	/**
	 * Aims to test the constructor when the input argument
	 * is null
	 */
	@Test
	public void testConstructorNullArg(){
		assertEquals("Check size for the constructor with list argument", 0,
		nullArgList.size);
		assertEquals("Check capacity for the constructor with size argument", 5, 
		nullArgList.data.length);
	}

	/**
	 * Aims to test the append method when an element is added to a full list
	 * Check reflection on size and capacity
	 */
	@Test
	public void testAppendAtCapacity(){
		fullList.append(2);

		assertEquals("Check that append increments size", 6, 
			fullList.size);
		assertEquals("Check that capacity is doubled", 10, 
			fullList.data.length);
		assertEquals("check the correct element", 1, 
			fullList.data[0]);
		assertEquals("check the correct element", 2, 
			fullList.data[5]);
	}

	/**
	 * Aims to test the prepend method when a null element is added
	 * Checks reflection on size and capacity
	 * Checks whether null was added successfully
	 */
	@Test
	public void testPrependNull(){
		listWithInt.prepend(null);

		assertEquals("Check that prepended item", null, listWithInt.data[0]);
		assertEquals("Check list size after the prepend", 4, listWithInt.size);
		assertEquals("Check that capacity is unchanged", 6, 
			listWithInt.data.length);
		
	}
	
	/**
	 * Aims to test the insert method when input index is out of bounds
	 */
	@Test
	public void testInsertOutOfBound(){
		boolean check = false;
		try {
			listWithInt.insert(-1, Integer.valueOf(5));
		  }
		catch (IndexOutOfBoundsException e) {
			check = true;
		}
		assertEquals("Check if index value is out-of-bounds", true, 
		check);
	}

	/**
	 * Insert multiple (eg. 1000) elements sequentially beyond capacity -
	 * Check reflection on size and capacity
	 * Hint: for loop could come in handy
	 */
	@Test
	public void testInsertMultiple(){
		for(int i=0; i<20; i++) {
			listWithInt.insert(i, Integer.valueOf(i));
		}
		assertEquals("Check size of list", 23, listWithInt.size);
		assertEquals("Check capacity of the list", 24, listWithInt.data.length);
	}

	/**
	 * Aims to test the get method when input index is out of bound
	 */
	@Test
	public void testGetOutOfBound(){
		boolean check = false;
		try {
			listWithInt.get(-1);
		  }
		catch (IndexOutOfBoundsException e) {
			check = true;
		}
		assertEquals("Check if index value is out-of-bounds", true, 
		check);
	}

	/**
	 * Aims to test the set method when input index is out of bound
	 */
	@Test
	public void testSetOutOfBound(){
		boolean check = false;
		try {
			listWithInt.set(-1, Integer.valueOf(5));
		  }
		catch (IndexOutOfBoundsException e) {
			check = true;
		}
		assertEquals("Check if index value is out-of-bounds", true, 
		check);
	}


	/**
	 * Aims to test the remove method when input index is out of bound
	 */
	@Test
	public void testRemoveOutOfBound(){
		boolean check = false;
		try {
			listWithInt.remove(-1);
		  }
		catch (IndexOutOfBoundsException e) {
			check = true;
		}
		assertEquals("Check if index value is out-of-bounds", true, 
		check);
	}

	/**
	 * Aims to test the expandCapacity method when 
	 * requiredCapacity is strictly less than the current capacity
	 */
	@Test
	public void testExpandCapacitySmaller(){
		boolean check = false;
		try {
			listWithInt.expandCapacity(1);
		  }
		catch (IllegalArgumentException e) {
			check = true;
		}
		assertEquals("Check when current capacity is less than required", 
		true, check);
	}

	/**
	 * Aims to test the expandCapacity method when 
	 * requiredCapacity is greater than double(2x) the current capacity
	 */
	@Test
	public void testExpandCapacityExplode(){
		listWithInt.expandCapacity(10);
		assertEquals("Should set capacity to required capacity", 10, 
		listWithInt.data.length);
	}
	

}
