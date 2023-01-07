/**
 * Name: Aditi Jain
 * ID: A17098965
 * Email: adj003@ucsd.edu
 * Sources used: None
 * 
 * This file contains a MyDeque class, that implements 
 * DequeInterface. It is used in the implmentation for 
 * the MyStack and MyQueue data structures. 
 */

 /**
  * This class implements the Deque data structure using an object
  * array as the backend, underlying data structure
  */
public class MyDeque<E> implements DequeInterface<E> {
    Object[] data;
    int size;
    int rear;
    int front;
    private static final int DEFAULT_CAPACITY = 10;
    private static final int DOUBLE_CAPACITY = 2;

    /**
     * Constructor to create Deque's data structure using 
     * the underlying Object array
     * @param initialCapacity The max amount of elements this data structure
     * can hold.
     */
    public MyDeque(int initialCapacity) {
        if(initialCapacity < 0) {
            throw new IllegalArgumentException();
        }
        this.data = new Object[initialCapacity];
        this.size = 0;
        this.rear = 0;
        this.front = 0;
    }

   /**
     * Returns the number of elements in this DequeInterface.
     * PRECONDITION: none
     * POSTCONDITION: the DequeInterface is unchanged.
     * @return the number of elements in this DequeInterface
     */
    public int size() {
        return size;
    }

    /**
     * Doubles the capacity of this DequeInterface.
     * PRECONDITION: none
     * POSTCONDITION: the DequeInterface's capacity is now doubled and
     * maintains the same elements. No elements have changed. If the capacity
     * is 0, set capacity to some default capacity.
     */
    public void expandCapacity() {
        Object[] arr;
        if(data.length == 0) {
            arr = new Object[DEFAULT_CAPACITY];
            this.data = arr;
        }
        else {
            // Creates copy of arraylist with new capacity
            arr = new Object[data.length*DOUBLE_CAPACITY];
            for(int i=0; i<this.size; i++) {
                arr[i] = data[(front+i)%data.length];
            }
            // Reset front and rear variables
            this.front = 0;
            if(size == 0) { 
                this.rear = 0;
            } 
            else {
                this.rear = size-1;
            }
            this.data = arr;
        }
    }

    /**
     * Adds the specified element to the front of this DequeInterface.
     * PRECONDITION: none
     * POSTCONDITION: if the MyDeque is at capacity, expandCapacity is called 
     * to double the size of this container. The element is now the front
     * element in this DequeInterface, none of the other elements have been
     * changed, and the size is increased by 1.
     * @param element the element to add to the front of the array
     * @throws NullPointerException if the specified element is null.
     */
    public void addFirst(E element) {
        if(element == null) {
            throw new NullPointerException();
        }
        if(data.length == size) {
            expandCapacity();
        }
        if(size == 0) {
            this.front = 0;
        }
        // Checks this case to keep it circular
        else if(front == 0 && size > 0) {
            this.front = data.length-1;
        }
        else {
            this.front = front-1;
        }
        data[front] = element;
        this.size++;
    }

    /**
     * Adds the specified element to the back of this DequeInterface.
     * PRECONDITION: none
     * POSTCONDITION: if the MyDeque is at capacity, expandCapacity is called
     * to double the size of this container. The element is now the back element
     * in this DequeInterface, none of the other elements have been changed, and
     * the size is increased by 1.
     * @param element the element to add to the back of the attay
     * @throws NullPointerException if the specified element is null.
     */
    public void addLast(E element) {
        if(element == null) {
            throw new NullPointerException();
        }
        if(data.length == size) {
            expandCapacity();
        }
        // Checks this case to keep it circular
        if(size == 0) {
            this.rear = front;
        }
        else if(rear == data.length-1) {
            this.rear = 0;
        }
        else {
            this.rear = rear+1;
        }
        data[rear] = element;
        this.size++;
    }


    /**
     * Removes the element at the front of this DequeInterface.
     * Returns the element removed, or null if there was no such element.
     * PRECONDITION: the DequeInterface's size is greater than zero.
     * POSTCONDITION: the front element in this DequeInterface has been removed,
     * none of the other elements have been changed, and
     * the size is decreased by 1.
     * @return  the element removed, or null if the size was zero.
     */
    public E removeFirst() {
        E first = (E) data[front];
        data[front] = null;
        if(size == 0) {
            return null;
        }
        else if(size == 1) {
            front = rear;
        }
        // Checks this case to keep it circular
        else if(front == data.length-1) {
            this.front = 0;
        }
        else {
            this.front = front+1;
        }
        size--;
        return first;
    }

    /**
     * Removes the element at the back of this DequeInterface.
     * Returns the element removed, or null if there was no such element.
     * PRECONDITION: the DequeInterface's size is greater than zero.
     * POSTCONDITION: the back element in this DequeInterface has been removed,
     * none of the other elements have been changed, and
     * the size is decreased by 1.
     * @return  the element removed, or null if the size was zero.
     */
    public E removeLast() {
        E last = (E) data[rear];
        data[rear] = null;
        if(size == 0) {
            return null;
        }
        else if(size == 1) {
            rear = front;
        }
        // Checks this case to keep it circular
        else if(rear == 0) {
            this.rear = data.length-1;
        }
        else {
            this.rear = rear-1;
        }
        size--;
        return last;
    }

    /**
     * Returns the element at the front of this DequeInterface,
     * or null if there was no such element.
     * PRECONDITION: the DequeInterface's size is greater than zero.
     * POSTCONDITION: The DequeInterface is unchanged.
     * @return  the element at the front, or null if the size was zero.
     */
    public E peekFirst() {
        if(size == 0) {
            return null;
        }
        else {
            return (E) data[front];
        }
    }

    /**
     * Returns the element at the back of this DequeInterface,
     * or null if there was no such element.
     * PRECONDITION: the DequeInterface's size is greater than zero.
     * POSTCONDITION: The DequeInterface is unchanged.
     * @return  the element at the back, or null if the size was zero.
     */
    public E peekLast() {
        if(size == 0) {
            return null;
        }
        else {
            return (E) data[rear];
        }
    }
}