/**
 * Name: Aditi Jain
 * ID: A17098965
 * Email: adj003@ucsd.edu
 * Sources used: Zybooks and Lecture Slides
 * 
 * Implements a data structure similar to Java’s 
 * ArrayLists with generic types, including methods
 * such as insert, append, prepend, get, etc.
 */

 /**
 * Contains all the methods used to implement the 
 * MyArrayList data type
 */
public class MyArrayList<E> implements MyList<E> {
    Object[] data;
    int size;
    private static final int DEFAULT_CAPACITY = 5;
    private static final int DOUBLE_CAPACITY = 2;

    /**
     * Constructor - Initializes data to an array 
     * of Objects of size DEFAULT_CAPACITY
     */
    public MyArrayList() {
        this.data = new Object[DEFAULT_CAPACITY];
        this.size = 0;
    }
    /**
     * Constructor - Initializes the Object array
     * with the length of initialCapacity
     * @param initialCapacity
     */
    public MyArrayList(int initialCapacity) {
        if(initialCapacity < 0) {
            throw new IllegalArgumentException();
        }
        else { 
            this.data = new Object[initialCapacity];
            this.size = 0;
        }
    }

    /**
     * Constructor - Initializes the instance 
     * variables with the input array of capacity 
     * equal to the length of arr.
     * @param arr
     */
    public MyArrayList (E[] arr) {
        if(arr == null) {
            this.data = new Object[DEFAULT_CAPACITY];
            this.size = 0;
        }
        else {
            this.data = arr;
            this.size = arr.length;
        }
    }

    /**
     * Adjusts array's capacity based on required capacity
     * @param requiredCapacity - capacity needed to input elements in array
     */
    public void expandCapacity(int requiredCapacity) {
        int newCapacity = 0;
        if(requiredCapacity < data.length) {
            throw new IllegalArgumentException();
        }
        if(data.length == 0) {
            newCapacity = DEFAULT_CAPACITY;
            if(DEFAULT_CAPACITY < requiredCapacity) {
                newCapacity = requiredCapacity;
            }
        }
        if(data.length != 0) {
            newCapacity = data.length*DOUBLE_CAPACITY;
            if(newCapacity < requiredCapacity) {
                newCapacity = requiredCapacity;
            }
        }
        // Creates copy of arraylist with new capacity
        Object[] arr = new Object[newCapacity];
        for(int i=0; i<data.length; i++) {
            arr[i] = this.data[i];
        }
        this.data = arr;
    }

	/**
     * Returns arraylist's capacity
     * @return length of data array
     */
	public int getCapacity() {
        return data.length;
    }
	/**
     * Inserts element at desired index
     * @param index - specified index to insert element at
     * @param element - desired element to insert
     */
	public void insert(int index, E element) {
        if(index < 0 || index > size) {
            throw new IndexOutOfBoundsException();
        }
        if(data.length == this.size) {
            expandCapacity(data.length+1);
        }
        
        //Moving elements for new element
        for(int i = size; i > index; i--) {
            data[i] = data[i - 1];
        }

        data[index] = element;
        size++;
    }

	/**
     * Adds element to end of arraylist
     * @param element - desired element to add
     */
	public void append(E element) {
        if(data.length == this.size) {
            expandCapacity(data.length+1);
        }
        data[size] = element;
        this.size++;
    }

	/**
     * Adds element to start of arraylist
     * @param element - desired element to add
     */
	public void prepend(E element) {
        if(this.size == data.length) {
            expandCapacity(data.length+1);
        }
        // Shifts all other elements up an index
        for(int i=this.size; i>0; i--) {
            data[i] = data[i-1];
        }
        data[0] = element;
        this.size++;
    }

	/**
     * Retrieves element at specified index
     * @param index - specified index of element
     * @return element of type E at desired index 
     */
	public E get(int index) {
        if(index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }
        return (E)(data[index]);
    }

    /**
     * Puts desired element at index
     * @param index - specified index to set element at
     * @param element - desired element to set
     * @return newly set element of type E
     */
	public E set(int index, E element) {
        if(index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }
        data[index] = element;
        return (E)data[index];
    }
	/**
     * Removes element at desired index
     * @param index - index to remove element at
     * @return element that was removed
     */
	public E remove(int index) {
        if(index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }
        Object removed = this.data[index];
        this.size = this.size-1;
        for(int i = index; i < size; i++) {
            this.data[i] = this.data[i+1];
        }
        return (E)removed;
    }
	/**
     * Returns size of arraylist
     * @return size 
     */
	public int size() {
        return this.size;
    }
}

 // Hint: the 'capacity' (length of data array) is not the same as the 'size'
 // Size is the number of elements in the ArrayList, i.e., the number of valid
 // elements in the array