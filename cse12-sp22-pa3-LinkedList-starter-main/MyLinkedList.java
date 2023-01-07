/**
 * Name: Aditi Jain
 * ID: A17098965
 * Email: adj003@ucsd.edu
 * Sources used: Zybooks and Lecture Slides
 * 
 * Implements a data structure similar to Java’s 
 * doubly linked LinkedList with 2 dummy nodes
 */

import java.util.AbstractList;

/**
 * Contains all the methods used to implement the 
 * MyLinkedList data type
 */
public class MyLinkedList<E> extends AbstractList<E> {

    int size;
    Node head;
    Node tail;

    /**
     * A Node class that holds data and references to previous and next Nodes.
     */
    protected class Node {
        E data;
        Node next;
        Node prev;

        /** 
         * Constructor to create singleton Node 
         * @param element Element to add, can be null	
         */
        public Node(E element) {
            // Initialize the instance variables
            this.data = element;
            this.next = null;
            this.prev = null;
        }

        /** 
         * Set the parameter prev as the previous node
         * @param prev - new previous node
         */
        public void setPrev(Node prev) {
            this.prev = prev;		
        }

        /** 
         * Set the parameter next as the next node
         * @param next - new next node
         */
        public void setNext(Node next) {
            this.next = next;
        }

        /** 
         * Set the parameter element as the node's data
         * @param element - new element 
         */
        public void setElement(E element) {
            this.data = element;
        }

        /** 
         * Accessor to get the next Node in the list 
         * @return the next node
         */
        public Node getNext() {
            return this.next;
        }

        /** 
         * Accessor to get the prev Node in the list
         * @return the previous node  
         */
        public Node getPrev() {
            return this.prev;
        }

        /** 
         * Accessor to get the Nodes Element 
         * @return this node's data
         */
        public E getElement() {
            return this.data;
        }
    }

    //  Implementation of the MyLinkedList Class
    /** Only 0-argument constructor is defined */
    public MyLinkedList() {
        this.size = 0;
        this.head = new Node(null);
        this.tail = new Node(null);
        head.setNext(tail);
        tail.setPrev(head);
    }

    /**
     * Returns the size of linkedlist
     * @return - number of nodes being stored
     */
    @Override
    public int size() {
        return size;
    }

    /**
     * Retrieves data of node at specified index
     * @param index - specified index of node
     * @return - data of type E within  node at desired index 
     */
    @Override
    public E get(int index) {
        if(index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }
        Node curr = head;
        for(int i = 0; i <= index; i++) {
            curr = curr.next;
        }
        return curr.getElement();
    }

    /**
     * Adds node at desired index 
     * @param index - specified index of node
     * @param data - desired element to add for node 
     */
    @Override
    public void add(int index, E data) {
        if(index < 0 || index > size) {
            throw new IndexOutOfBoundsException();
        }
        if(data == null) {
            throw new NullPointerException();
        }
        // for the case of an empty linkedlist
        if(size == 0 || index == size) {
            add(data);
        }
        else {
            // assigning the values to variables 
            // so they are easier to work with 
            Node input = new Node(data);
            Node prev = getNth(index).getPrev();
            Node next = getNth(index);
            input.setPrev(prev);
            input.setNext(next);
            prev.setNext(input);
            next.setPrev(input);
            size++;
        }
    }

    /**
     * Adds node to linkedlist's end
     * @param data - desired element to add for node 
     * @return - boolean value true
     */
    public boolean add(E data) {
        if(data == null) {
            throw new NullPointerException();
        }
        Node newNode = new Node(data);
        newNode.setPrev(tail.getPrev());
        newNode.setNext(tail);
        (tail.getPrev()).setNext(newNode);
        tail.setPrev(newNode);
        size++;
        return true; 
    }

    /**
     * Puts desired element for node at index
     * @param index - specified index of node
     * @param data - desired element to set for node 
     * @return - element that was previously stored in node
     */
    public E set(int index, E data) {
        if(data == null) {
            throw new NullPointerException();
        }
        if(index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }
        E replaced = get(index);
        getNth(index).setElement(data);
        return replaced;
    }

    /**
     * Removes node at desired index
     * @param index - index to remove node at
     * @return data within node that was removed
     */
    public E remove(int index) {
        if(index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }
        // assigning the values to variables 
        // so they are easier to work with 
        E removed = getNth(index).getElement();
        Node sucNode = getNth(index).getNext();
        Node predNode = getNth(index).getPrev();
        sucNode.setPrev(predNode);
        predNode.setNext(sucNode);
        size--;
        return removed;
    }

    /** 
     * Removes all nodes from list
    */
    public void clear() {
        head.setNext(tail);
        tail.setPrev(head);
        size = 0;
    }

    /** 
     * Determines if list is empty
     * @return true if empty, else false
    */
    public boolean isEmpty() {
        if(size == 0) {
            return true;
        }
        else {
            return false;
        }
    }

    /**
     * Returns the Node at a specified index, not the content
     * @param index - specified index of element
     * @return - the Node at a specified index
     */
    protected Node getNth(int index) {
        if(index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }
        Node curr = head;
        for(int i = 0; i <= index; i++) {
            curr = curr.next;
        }
        return curr;
    }
}