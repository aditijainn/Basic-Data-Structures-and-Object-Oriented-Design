/**
 * Name: Aditi Jain
 * ID: A17098965
 * Email: adj003@ucsd.edu
 * Sources used: Zybook
 * 
 * Contains helper and core methods that
 * implement the MyBST data structure
 */

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/**
 * Contains helper and core methods that
 * implement the MyBST data structure
 */
public class MyBST<K extends Comparable<K>,V>{
    MyBSTNode<K,V> root = null;
    int size = 0;

    /**
     * Represents the number of nodes in the tree
     * @return - size
     */
    public int size(){
        return size;
    }
    /**
     * Inserts node based on BST rules
     * @param key - specificed node's key
     * @param value - node's value to insert
     * @return - value of existing node with key or null
     */
    public V insert(K key, V value){
        MyBSTNode<K,V> curr = root;
        V output = null;
        if(key == null) {
            throw new NullPointerException();
        }
        // if MyBST is empty, add node as root
        if(curr == null) {
            root = new MyBSTNode<K,V>(key, value, null);
            size++;
        }
        else {
            while(curr != null) {
                // if key already exists
                if(key.compareTo(curr.getKey()) == 0) {
                    output = curr.getValue();
                    // set value to new value
                    curr.setValue(value);
                    curr = null;
                }
                // if key is less than current node's key
                else if(key.compareTo(curr.getKey()) < 0) {
                    // current node doesn't have a left child
                    if(curr.getLeft() == null) {
                        curr.setLeft(new MyBSTNode<K,V>(key, value, curr));
                        size++;
                        curr = null;
                    }
                    else {
                        curr = curr.getLeft();
                    }
                }
                else {
                    // current node doesn't have a right child
                    if(curr.getRight() == null) {
                        curr.setRight(new MyBSTNode<K,V>(key, value, curr));
                        size++;
                        curr = null;
                    }
                    else {
                        curr = curr.getRight();
                    }
                }
            } 
        }
        return output;
    }
    /**
     * Search for a node with key equal to key and 
     * return the value associated with that node
     * @param key - to search for node with
     * @return - value of node of existing key or null
     */
    public V search(K key){
        MyBSTNode<K,V> curr = root;
        if(key == null) {
            return null;
        }
        while(curr != null) {
            // if key already exists
            if(key == curr.getKey()) {
                // return node's value
                return curr.getValue();
            }
            // if key less than current node's key
            else if(key.compareTo(curr.getKey()) < 0) {
                curr = curr.getLeft();
            }
            else {
                curr = curr.getRight();
            }
        }
        return null;
    }

    /**
     * Search for a node with key equal to key and 
     * return the value associated with that node.
     * @param key - node's key to search for
     * @return - value of node
     */
    public V remove(K key){
        MyBSTNode<K,V> par = null;
        MyBSTNode<K,V> curr = root;
        while(curr != null && key != null) {
            if(key == curr.getKey()) {
                // store value of current node
                V keyval = curr.getValue();
                par = curr.getParent();
                // if curr node has no children
                if(curr.getLeft() == null 
                && curr.getRight() == null) {
                    size--;
                    // Remove leaf
                    if(par == null) {
                        root = null;
                    }
                    else if(par.getLeft() == curr) {
                        par.setLeft(null);
                    }
                    else {
                        par.setRight(null);
                    }
                }
                // if only left child exists
                else if(curr.getRight() == null) {
                    size--;
                    if(par == null) {
                        root = curr.getLeft();
                    }
                    curr.setValue(curr.getLeft().getValue());
                    curr.setKey(curr.getLeft().getKey());
                    curr.setLeft(null);
                }
                // if only right child exists
                else if(curr.getLeft() == null) {
                    size--;
                    if(par == null) {
                        root = curr.getRight();
                    }
                    // Remove node with only right child
                    curr.setValue(curr.getRight().getValue());
                    curr.setKey(curr.getRight().getKey());
                    curr.setRight(null);
                }
                else {
                    // if current node has both children
                    MyBSTNode<K,V> suc = curr.successor();
                    // Remove successor
                    remove(suc.getKey());
                    curr.setValue(suc.getValue());
                    curr.setKey(suc.getKey());
                }
                // return value of current node that was removed
                return keyval;
            }
            // if current key less than key
            else if(curr.getKey().compareTo(key) < 0) {
                par = curr;
                curr = curr.getRight();
            }
            // if current key greater than key
            else {
                par = curr;
                curr = curr.getLeft();
            }
        }
        // if node not found
        return null;
    } 
    /**
     * In-order traversal of the tree, adding each 
     * node to the end of an ArrayList, which will 
     * be returned
     * @return - sorted ArrayList
     */
    public ArrayList<MyBSTNode<K, V>> inorder(){
        ArrayList<MyBSTNode<K, V>> output = new ArrayList<MyBSTNode<K, V>>();
        MyBSTNode<K, V> curr = root;
        if(size == 0) {
            return output;
        }
        while(curr.getLeft() != null) {
            curr = curr.getLeft();
        }
        while(curr != null) {
            output.add(curr);
            curr = curr.successor();
        }
        return output;
    }

    /**
     * class contains methods and helper methods for MyBSTNode
     */
    static class MyBSTNode<K,V>{
        private static final String TEMPLATE = "Key: %s, Value: %s";
        private static final String NULL_STR = "null";

        private K key;
        private V value;
        private MyBSTNode<K,V> parent;
        private MyBSTNode<K,V> left = null;
        private MyBSTNode<K,V> right = null;

        /**
         * Creates a MyBSTNode<K,V> storing specified data
         * @param key the key the MyBSTNode<K,V> will
         * @param value the data the MyBSTNode<K,V> will store
         * @param parent the parent of this node
         */
        public MyBSTNode(K key, V value, MyBSTNode<K, V> parent){
            this.key = key;
            this.value = value;
            this.parent = parent; 
        }

        /**
         * Return the key stored in the the MyBSTNode<K,V>
         * @return the key stored in the MyBSTNode<K,V>
         */
        public K getKey(){
            return key;
        }

        /**
         * Return data stored in the MyBSTNode<K,V>
         * @return the data stored in the MyBSTNode<K,V>
         */
        public V getValue(){
            return value;
        }

        /**
         * Return the parent
         * @return the parent
         */
        public MyBSTNode<K,V> getParent(){
            return parent;
        }

        /**
         * Return the left child 
         * @return left child
         */
        public MyBSTNode<K,V> getLeft(){
            return left;
        }

        /**
         * Return the right child 
         * @return right child
         */
        public MyBSTNode<K,V> getRight(){
            return right;
        }

        /**
         * Set the key stored in the MyBSTNode<K,V>
         * @param newKey the key to be stored
         */
        public void setKey(K newKey){
            this.key = newKey;
        }

        /**
         * Set the data stored in the MyBSTNode<K,V>
         * @param newValue the data to be stored
         */
        public void setValue(V newValue){
            this.value = newValue;
        }

        /**
         * Set the parent
         * @param newParent the parent
         */
        public void setParent(MyBSTNode<K,V> newParent){
            this.parent = newParent;
        }

        /**
         * Set the left child
         * @param newLeft the new left child
         */
        public void setLeft(MyBSTNode<K,V> newLeft){
            this.left = newLeft;
        }

        /**
         * Set the right child
         * @param newRight the new right child
         */
        public void setRight(MyBSTNode<K,V> newRight){
            this.right = newRight;
        }

        /*
         * This method returns the in order successor of current node object.
         * It can be served as a helper method when implementing inorder().
         * @return the successor of current node object
         */
        public MyBSTNode<K, V> successor(){
            //checks if right child exists
            if(this.getRight() != null){
                MyBSTNode<K,V> curr = this.getRight();
                // keeps going left down to the last edge
                while(curr.getLeft() != null){
                    curr = curr.getLeft();
                }
                return curr;
            }
            else{
                //if no right children, get parent successor
                MyBSTNode<K,V> parent = this.getParent();
                MyBSTNode<K,V> curr = this;
                // gets parent's successor
                while(parent != null && curr == parent.getRight()){
                    curr = parent;
                    parent = parent.getParent();
                }
                return parent;
            }
        }
        /**
         * This method returns the in order predecessor of current node object.
         * @return - node of predecessor
         */
        public MyBSTNode<K, V> predecessor(){
            //checks if left child exists
            if(this.getLeft() != null){
                MyBSTNode<K,V> curr = this.getLeft();
                // keeps going right down to the last edge
                while(curr.getRight() != null){
                    curr = curr.getRight();
                }
                return curr;
            }
            else{
                //if no left children, get parent successor
                MyBSTNode<K,V> parent = this.getParent();
                MyBSTNode<K,V> curr = this;
                // gets parent's successor
                while(parent != null && curr == parent.getLeft()){
                    curr = parent;
                    parent = parent.getParent();
                }
                return parent;
            }
        }

        /** This method compares if two node objects are equal.
         * @param obj The target object that the currect object compares to.
         * @return Boolean value indicates if two node objects are equal
         */
        public boolean equals(Object obj){
            if (!(obj instanceof MyBSTNode))
                return false;

            MyBSTNode<K,V> comp = (MyBSTNode<K,V>)obj;
            
            return( (this.getKey() == null ? comp.getKey() == null : 
                this.getKey().equals(comp.getKey())) 
                && (this.getValue() == null ? comp.getValue() == null : 
                this.getValue().equals(comp.getValue())));
        }

        /**
         * This method gives a string representation of node object.
         * @return "Key:Value" that represents the node object
         */
        public String toString(){
            return String.format(
                    TEMPLATE,
                    this.getKey() == null ? NULL_STR : this.getKey(),
                    this.getValue() == null ? NULL_STR : this.getValue());
        }
    }

}