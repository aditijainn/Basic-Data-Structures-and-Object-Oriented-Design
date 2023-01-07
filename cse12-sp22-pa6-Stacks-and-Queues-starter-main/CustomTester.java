/**
 * Name: Aditi Jain
 * ID: A17098965
 * Email: adj003@ucsd.edu
 * Sources used: None
 * 
 * This file contains all the custom tests I implemented to 
 * verify my MyDeque, MyStack, and MyQueue implementation
 */

import org.junit.*;
import static org.junit.Assert.*;

/**
 * This class contains custom test cases for MyDeque, MyStack, and MyQueue
 */
public class CustomTester {
    /**
     * Helper method to initialize all instance variables of MyDeque
     * @param deque The deque to initialize
     * @param data The data array
     * @param size The value for size
     * @param front The value for front
     * @param rear The value for rear
     */
    static void initDeque(MyDeque<Integer> deque, Object[] data, int size, 
            int front, int rear) {
        deque.data = data;
        deque.size = size;
        deque.front = front;
        deque.rear = rear;
    }
    // ----------------MyDeque class----------------
    /**
     * Test the constructor when initialcapacity < 0
     */
    @Test
    public void testMyDequeConstructor() {
        boolean check = false;
        try {
            MyDeque<Integer> deque = new MyDeque<>(-1);
        }
        catch (IllegalArgumentException e) {
            check = true;
        }
        assertEquals("if arg < 0", true, check);
    }

    /**
     * Test the expandCapacity method with several elements at the end of the array 
     */
    @Test
    public void testMyDequeExpandCapacity() {
        MyDeque<Integer> deque = new MyDeque<>(5);
        Integer[] orig = { null, null, 1, 2, 3 };
        Integer[] finalOrdering = { null, null, 1, 2, 3,
                null, null, null, null, null };
        initDeque(deque, orig, 3, 2, 4);

        deque.expandCapacity();

        assertEquals("Capacity should have doubled", 10, deque.data.length);
        assertEquals("Size should not have changed", 3, deque.size);
        assertEquals("Front should be 0", 0, deque.front);
        assertEquals("Rear should be at size - 1", 2, deque.rear);
        for (int i = 0; i < 10; i++) {
            assertEquals("Deque structure should be maintained", 
            finalOrdering[i], deque.data[i]);
        }
    }

    /**
     * Test the addFirst method with array full of elements
     */
    @Test
    public void testAddFirst() {
        MyDeque<Integer> deque = new MyDeque<>(5);
        Integer[] orig = { 1, 2, 3, 4, 5};
        initDeque(deque, orig, 5, 0, 4);

        deque.addFirst(6);

        assertEquals("Capacity should double", 10,
                deque.data.length);
        assertEquals("Should increment size", 6, deque.size);
        assertEquals("Front should be data.length-1", 9, deque.front);
        assertEquals("Rear shouldn't change when calling addFirst", 4,
                deque.rear);
        assertEquals("6 should have been inserted into index 9",
                Integer.valueOf(6), deque.data[9]);
        assertEquals("Index 0 should not have changed", Integer.valueOf(1),
                deque.data[0]);
        assertEquals("Index 1 should not have changed",
                Integer.valueOf(2), deque.data[1]);
        assertEquals("Index 2 should not have changed", Integer.valueOf(3),
                deque.data[2]);
        assertEquals("Index 3 should not have changed", Integer.valueOf(4),
                deque.data[3]);
        assertEquals("Index 4 should not have changed", Integer.valueOf(5),
                deque.data[4]);
    }

    /**
     * Test the addLast method with array full of elements
     */
    @Test
    public void testAddLast() {
        MyDeque<Integer> deque = new MyDeque<>(5);
        Integer[] orig = { 1, 2, 3, 4, 5};
        initDeque(deque, orig, 5, 0, 4);

        deque.addLast(6);

        assertEquals("Capacity should double", 10,
                deque.data.length);
        assertEquals("Should increment size", 6, deque.size);
        assertEquals("Front shouldn't change when called addLast", 0,
                deque.front);
        assertEquals("Rear should move one index when inserting into " +
                "non-empty deque", 5, deque.rear);
        assertEquals("6 should have been inserted into index 5",
                Integer.valueOf(6), deque.data[5]);
        assertEquals("Index 0 should not have changed", Integer.valueOf(1),
                deque.data[0]);
        assertEquals("Index 1 should not have changed",
                Integer.valueOf(2), deque.data[1]);
        assertEquals("Index 2 should not have changed", Integer.valueOf(3),
                deque.data[2]);
        assertEquals("Index 3 should not have changed", Integer.valueOf(4),
                deque.data[3]);
        assertEquals("Index 4 should not have changed", Integer.valueOf(5),
                deque.data[4]);
    }

    /**
     * Test the removeFirst method with full array and front at the end
     */
    @Test
    public void testRemoveFirst() {
        MyDeque<Integer> deque = new MyDeque<>(5);
        Integer[] orig = {1, 2, 3, 4, 5};
        initDeque(deque, orig, 5, 4, 3);
        assertEquals("Element removed should be returned", 5,
                deque.removeFirst().intValue());

        assertEquals("Array length shouldn't be changed", 5,
                deque.data.length);
        assertEquals("Size should decremto 0", 0, deque.front);
        assertEquals("Rear should not change after calling removeFirst", 3,
                deque.rear);
        assertEquals("Index 0 should not have changed", Integer.valueOf(1),
                deque.data[0]);
        assertEquals("Index 1 should not have changed",
                Integer.valueOf(2), deque.data[1]);
        assertEquals("Index 2 should not have changed", Integer.valueOf(3),
                deque.data[2]);
        assertEquals("Index 3 should not have changed", Integer.valueOf(4),
                deque.data[3]);
        assertEquals("Index 4 should have been set to null", null,
                deque.data[4]);

    }

    /**
     * Test the removeLast method when with full array and rear at the start
     */
    @Test
    public void testRemoveLast() {
        MyDeque<Integer> deque = new MyDeque<>(5);
        Integer[] orig = {1, 2, 3, 4, 5};
        initDeque(deque, orig, 5, 1, 0);

        assertEquals("Element removed should be returned", 1,
                deque.removeLast().intValue());
        assertEquals("Array length shouldn't be changed", 5,
                deque.data.length);
        assertEquals("Size should decrement", 4, deque.size);
        assertEquals("Front should not change after calling removeLast", 1,
                deque.front);
        assertEquals("Rear should move to data.length-1", 4, deque.rear);
        assertEquals("Index 4 should not have changed", Integer.valueOf(5),
                deque.data[4]);
        assertEquals("Index 1 should not have changed",
                Integer.valueOf(2), deque.data[1]);
        assertEquals("Index 2 should not have changed", Integer.valueOf(3),
                deque.data[2]);
        assertEquals("Index 3 should not have changed", Integer.valueOf(4),
                deque.data[3]);
        assertEquals("Index 0 should have been set to null", null,
                deque.data[0]);
    }

    /**
     * Test the peekFirst method when containing elements at middle of the array
     */
    @Test
    public void testPeekFirst(){
        MyDeque<Integer> deque = new MyDeque<>(10);
        Integer[] orig = {null, null, 1, 2, 3, null, null};
        initDeque(deque, orig, 3, 2, 4);

        assertEquals("Value at front should be returned",
                Integer.valueOf(1), deque.peekFirst());

        assertEquals("peekFirst should not change capacity", 7,
                deque.data.length);
        assertEquals("peekFirst should not change size", 3, deque.size);
        assertEquals("peekFirst should not change front", 2, deque.front);
        assertEquals("peekFirst should not change rear", 4, deque.rear);
    }

    /**
     * Test the peekLast method when containing elements at middle of the array
     */
    @Test
    public void testPeekLast(){
        MyDeque<Integer> deque = new MyDeque<>(10);
        Integer[] orig = {null, null, 1, 2, 3, null, null};
        initDeque(deque, orig, 3, 2, 4);

        assertEquals("Value at end should be returned",
                Integer.valueOf(3), deque.peekLast());
        assertEquals("peekFirst should not change capacity", 7,
                deque.data.length);
        assertEquals("peekFirst should not change size", 3, deque.size);
        assertEquals("peekFirst should not change front", 2, deque.front);
        assertEquals("peekFirst should not change rear", 4, deque.rear);
    }

    // ----------------MyStack class----------------
    /**
     * Test MyStack when filled with invalid elements
     * (an empty deque)
     */
    @Test
    public void testMyStack(){
        MyStack<Integer> stack = new MyStack<>(5);
        Integer[] orig = { null, null, null, null, null };
        initDeque(stack.theStack, orig, 0, 0, 0);

        assertEquals("Check if null", null,
            stack.theStack.peekFirst());
    }

    // ----------------MyQueue class----------------
    /**
     * Test MyQueue when filled with invalid elements 
     * (an empty deque)
     */
    @Test
    public void testMyQueue(){
        MyQueue<Integer> queue = new MyQueue<>(5);
        Integer[] orig = { null, null, null, null, null };
        initDeque(queue.theQueue, orig, 0, 0, 0);

        assertEquals("Check if null", null,
            queue.theQueue.peekFirst());    }
}
