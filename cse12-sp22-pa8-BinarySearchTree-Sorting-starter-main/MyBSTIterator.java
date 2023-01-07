/**
 * Name: Aditi Jain
 * ID: A17098965
 * Email: adj003@ucsd.edu
 * Sources used: Zybook
 * 
 * Contains helper and core methods that
 * implement the MyBSTIterator to iterate 
 * through MyBST
 */
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Contains helper and core methods that implement the 
 * MyBSTIterator to iterate through MyBST
 */
public class MyBSTIterator<K extends Comparable<K>, V> extends MyBST<K, V> {
    abstract class MyBSTNodeIterator<T> implements Iterator<T> {
        MyBSTNode<K, V> next;
        MyBSTNode<K, V> lastVisited;

        /**
         * Constructor that initializes the node iterator
         *
         * @param first The initial node that next points
         */
        MyBSTNodeIterator(MyBSTNode<K, V> first) {
            next = first;
            lastVisited = null;
        }

        /**
         * This method is used for determining if the next pointer in the
         * iterator points to null.
         *
         * @return If next is null based on the current position of iterator
         */
        public boolean hasNext() {
            return next != null;
        }

        /**
         * Iterates to next node of MyBST data structure
         * @return - lastvisited node in MyBST
         */
        MyBSTNode<K, V> nextNode() {
            if(hasNext() == false) {
                throw new NoSuchElementException();
            }
            lastVisited = next;
            next = next.successor();
            return lastVisited;
        }

        /**
         * This method removes the last visited node from the tree.
         */
        public void remove() {
            // if no node visited before, exception is thrown
            if (lastVisited == null) {
                throw new IllegalStateException();
            }
            // checks if lastVisted has two children
            if (lastVisited.getRight() != null &&
                    lastVisited.getLeft() != null) {
                // next node changed to lastVisted
                next = lastVisited;
            }
            // remove method removes lastVisited node from tree
            MyBSTIterator.this.remove(lastVisited.getKey());
            // lastVisited changed to null
            lastVisited = null;
        }
    }

    /**
     * BST Key iterator class that extends the node iterator.
     */
    class MyBSTKeyIterator extends MyBSTNodeIterator<K> {

        MyBSTKeyIterator(MyBSTNode<K, V> first) {
            super(first);
        }

        /**
         * This method advance the iterator and returns a node key
         *
         * @return K the next key
         */
        public K next() {
            return super.nextNode().getKey();
        }
    }

    /**
     * BST value iterator class that extends the node iterator.
     */
    class MyBSTValueIterator extends MyBSTNodeIterator<V> {

        /**
         * Call the constructor method from node iterator
         *
         * @param first The initial value that next points
         */
        MyBSTValueIterator(MyBSTNode<K, V> first) {
            super(first);
        }

        /**
         * This method advance the iterator and returns a node value
         *
         * @return V the next value
         */
        public V next() {
            return super.nextNode().getValue();
        }
    }

    /**
     * This method is used to obtain an iterator that iterates through the
     * value of BST.
     *
     * @return The value iterator of BST.
     */
    public MyBSTKeyIterator getKeyIterator() {
        MyBSTNode<K, V> curr = root;
        if (curr != null) {
            while (curr.getLeft() != null) {
                curr = curr.getLeft();
            }
        }
        return new MyBSTKeyIterator(curr);
    }

    /**
     * This method is used to obtain an iterator that iterates through the
     * value of BST.
     *
     * @return The value iterator of BST.
     */
    public MyBSTValueIterator getValueIterator() {
        MyBSTNode<K, V> curr = root;
        if (curr != null) {
            while (curr.getLeft() != null) {
                curr = curr.getLeft();
            }
        }
        return new MyBSTValueIterator(curr);
    }
}