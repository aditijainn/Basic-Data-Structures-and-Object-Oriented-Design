/**
 * Name: Aditi Jain
 * ID: A17098965
 * Email: adj003@ucsd.edu
 * Sources used: Piazza and Lecture Slides
 * 
 * This file contains Unit tests that test exception and invalid 
 * cases in MyLinkedList.java
 */

import static org.junit.Assert.*;
import org.junit.*;

/**
 * This class contains edge test cases that ensure basic functionality of
 * MyLinkedList. 
 */
public class MyLinkedListCustomTester {
    /**
     * Standard Test Fixture. An empty list, a list with several entries 
     * that will be populated by the array stringData
     */
    private MyLinkedList<Integer> emptyIntList;
    private MyLinkedList<String> fourStringList;
    private String[] stringData = {"Aditi", "Jain", "is", "tired"};
    
    /**
     * This sets up the test fixture. JUnit invokes this method before
     * every testXXX method. The @Before tag tells JUnit to run this method
     * before each test.
     */
    @Before
    public void setUp() {
        emptyIntList = new MyLinkedList<Integer>();
        fourStringList = new MyLinkedList<>();
    }
    private void populateLinkedList() {
        MyLinkedList<String>.Node node0 =  
            this.fourStringList.new Node(this.stringData[0]);
        MyLinkedList<String>.Node node1 =  
            this.fourStringList.new Node(this.stringData[1]);
        MyLinkedList<String>.Node node2 =  
            this.fourStringList.new Node(this.stringData[2]);
        MyLinkedList<String>.Node node3 =  
            this.fourStringList.new Node(this.stringData[3]);

        this.fourStringList.head.next = node0;
        node0.prev = this.fourStringList.head;
        node0.next = node1;
        node1.prev = node0;
        node1.next = node2;
        node2.prev = node1;
        node2.next = node3;
        node3.prev = node2;
        node3.next = this.fourStringList.tail;
        this.fourStringList.tail.prev = node3;
        this.fourStringList.size = 4;
    }
    /**
     * Test the add method when data is null
     */
    @Test
    public void testAdd() {
        boolean check = false;
        this.populateLinkedList();
		try {
			fourStringList.add(null);
		  }
		catch (NullPointerException e) {
			check = true;
		}
		assertEquals("Check if data is null", true, 
		check);
    }

    /**
     * Test the add with index method when index is out of bounds
     */
    @Test
    public void testAddWithIndexTestOne() {
        boolean check = false;
        this.populateLinkedList();
		try {
			fourStringList.add(-1, "thanks");
		  }
		catch (IndexOutOfBoundsException e) {
			check = true;
		}
		assertEquals("Check if index value is out-of-bounds", true, 
		check);
    }

    /**
     * Test the add with index method when data is null
     * @param - 
     */	
    @Test
    public void testAddWithIndexTestTwo() {
        boolean check = false;
        this.populateLinkedList();
		try {
			fourStringList.add(1, null);
		  }
		catch (NullPointerException e) {
			check = true;
		}
		assertEquals("Check if data is null", true, 
		check);
    }

    /**
     * Test the get method when index is out of bounds
     */
    @Test
    public void testGet() {
        boolean check = false;
        this.populateLinkedList();
		try {
			fourStringList.get(-1);
		  }
		catch (IndexOutOfBoundsException e) {
			check = true;
		}
		assertEquals("Check if index value is out-of-bounds", true, 
		check);
    }

    /**
     * Test the getNth method when index is out of bounds
     */
    @Test
    public void testGetNth() {
        boolean check = false;
        this.populateLinkedList();
		try {
			fourStringList.getNth(-1);
		  }
		catch (IndexOutOfBoundsException e) {
			check = true;
		}
		assertEquals("Check if index value is out-of-bounds", true, 
		check);
    }

    /**
     * Test the set method when index is out of bounds
     */
    @Test
    public void testSet() {
        boolean check = false;
        this.populateLinkedList();
		try {
			fourStringList.set(-1, "thanks");
		  }
		catch (IndexOutOfBoundsException e) {
			check = true;
		}
		assertEquals("Check if index value is out-of-bounds", true, 
		check);
    }

    /**
     * Test the remove method when called ono nonempty list 
     * and index is out of bounds
     */
    @Test
    public void testRemoveTestOne() {
        boolean check = false;
        this.populateLinkedList();
		try {
			fourStringList.remove(-1);
		  }
		catch (IndexOutOfBoundsException e) {
			check = true;
		}
		assertEquals("Check if index value is out-of-bounds", true, 
		check);
    }
    
    /**
     * Test the remove method when it is called on an empty
     * list and so index is out of bounds
     */
    @Test
    public void testRemoveTestTwo() {
        boolean check = false;
		try {
			emptyIntList.remove(1);
		  }
		catch (IndexOutOfBoundsException e) {
			check = true;
		}
		assertEquals("Check if index value is out-of-bounds", true, 
		check);
    }

    /**
     * Test the clear method when called on an empty list
     */
    @Test
    public void testClear() {
        emptyIntList.clear();
        assertEquals("Size should be same", 0, emptyIntList.size());
        assertTrue("LinkedList should be empty", emptyIntList.isEmpty());
    }

    /**
     * Test the size method when called on an nonempty list
     */
    @Test
    public void testSize() {
        this.populateLinkedList();
        assertEquals("Size should be 4", 
        4, fourStringList.size());
    }
}