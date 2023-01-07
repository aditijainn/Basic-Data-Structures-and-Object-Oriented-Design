/**
 * Name: Aditi Jain
 * ID: A17098965
 * Email: adj003@ucsd.edu
 * Sources used: None
 * 
 * This file contains a MyStack class, which is an implementation for the Stack
 * ADT based on MyDeque. Elements can be added/removed from the queue in a LIFO
 * ordering.
 */

/**
 * This class implements the Stack ADT using a MyDeque instance variable called
 * theStack.
 */
public class MyStack<E> implements StackInterface<E> {
    MyDeque<E> theStack;

    /**
     * Constructor to create new MyStack that holds a MyDeque.
     * 
     * @param initialCapacity The max amount of elements this data structure
     * can hold.
     */
    public MyStack(int initialCapacity) {
        this.theStack = new MyDeque<>(initialCapacity);
    }

    /**
     * Checks whether or not the stack is empty.
     * 
     * @return True if there are no elements in the stack, false otherwise.
     */
    @Override
    public boolean empty() {
        if(theStack.size == 0) {
            return true;
        }
        else { 
            return false;
        }
    }

    /**
     * Adds the specified element to the top of this StackInterface.
     * 
     * @param element the element to add to the stack
     */
    @Override
    public void push(E element) {
        theStack.addFirst(element);
    }

    /**
     * Removes the element at the top of this StackInterface.
     * Returns the element removed, or null if there was no such element.
     * 
     * @return the element removed, or null if the size was zero.
     */
    @Override
    public E pop() {
        E first = (E) theStack.data[theStack.front];
        theStack.data[theStack.front] = null;
        if(theStack.size == 0) {
            return null;
        }
        else if(theStack.front == theStack.data.length-1) {
            this.theStack.front = 0;
        }
        // Checks this case to keep it circular
        else if(theStack.size == 1) {
            theStack.rear = theStack.front;
        }
        else {
            theStack.front = theStack.front+1;
        }
        theStack.size--;
        return first;
    }

    /**
     * Returns the element at the top of this stack, or null if there was no
     * such element.
     * 
     * @return the element at the top, or null if the size was zero
     */
    @Override
    public E peek() {
        return theStack.peekFirst();
    }

    /**
     * Returns the number of elements in this stack.
     * 
     * @return the number of elements in this stack.
     */
    @Override
    public int size() {
        return theStack.size();
    }

}
