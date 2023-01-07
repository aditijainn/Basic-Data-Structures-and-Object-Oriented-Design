/**
 * Name: Aditi Jain
 * ID: A17098965
 * Email: adj003@ucsd.edu
 * Sources used: Zybook
 * 
 * This file contains the custom testers that test
 * edge cases for MyBST, MyBSTIterator, & MyCalendar
 */

import java.util.*;
import static org.junit.Assert.*;
import org.junit.*;

/**
 * This class contains custom tests for 
 * MyBST, MyBSTIterator, & MyCalendar
 */
public class CustomTester {
    MyBST<Integer, Integer> completeTree;

    // 
    /**
     * The setup method creates a complete tree with height four
     * The tree has following structure and will be used as testing purpose
     *           8
     *         /  \
     *       3     10
     *     /  |     \
     *    1   6      14
     *       / \    /
     *      4   7  13
     */
    @Before
    public void setup(){

        MyBST.MyBSTNode<Integer, Integer> root = 
            new MyBST.MyBSTNode<>(8, 1, null);
        MyBST.MyBSTNode<Integer, Integer> three = 
            new MyBST.MyBSTNode<>(3, 1, root);
        MyBST.MyBSTNode<Integer, Integer> ten = 
            new MyBST.MyBSTNode<>(10, 5, root);
        MyBST.MyBSTNode<Integer, Integer> one = 
            new MyBST.MyBSTNode<>(1, 2, three);
        MyBST.MyBSTNode<Integer, Integer> six = 
            new MyBST.MyBSTNode<>(6, 9, three);
        MyBST.MyBSTNode<Integer, Integer> four = 
            new MyBST.MyBSTNode<>(4, 6, six);
        MyBST.MyBSTNode<Integer, Integer> seven = 
            new MyBST.MyBSTNode<>(7, 20, six);
        MyBST.MyBSTNode<Integer, Integer> fourteen = 
            new MyBST.MyBSTNode<>(14, 30, ten);
        MyBST.MyBSTNode<Integer, Integer> thirteen = 
            new MyBST.MyBSTNode<>(13, 50, fourteen);

        this.completeTree = new MyBST<>();
        this.completeTree.root = root;
        root.setLeft(three);
        root.setRight(ten);
        three.setLeft(one);
        three.setRight(six);
        six.setLeft(four);
        six.setRight(seven);
        ten.setRight(fourteen);
        fourteen.setLeft(thirteen);
        this.completeTree.size = 9;
    }

    // ====== MyBSTNode class ======

    // Test predecessor() on smallest node
    @Test
    public void testNodePredecessorSmallestNode() {
        MyBST.MyBSTNode<Integer, Integer> node = completeTree.root.getLeft().getLeft();
        assertSame(null, node.predecessor());
    }

    // ====== MyBST class ======

    // Test insert method inserting node with empty tree
    @Test
    public void testInsert(){
        MyBST<Integer, Integer> emptytree = new MyBST<>(); 
        assertEquals(null, emptytree.insert(10, 1));
        emptytree.insert(10, 1);
        assertEquals((Integer)1, emptytree.root.getValue());
        assertEquals(1, emptytree.size);
    }

    // Test insert method inserting node with existing key
    @Test
    public void testInsert2(){
        MyBST.MyBSTNode<Integer, Integer> node = completeTree.root.getRight(); 
        assertEquals((Integer)5, completeTree.insert(10, 1));
        assertEquals((Integer)1, node.getValue());
        assertEquals(9, completeTree.size);
    }

    // Test insert if key is null
    @Test
    public void testInsert3(){
        boolean check = false;
        try {
            completeTree.insert(null, 1);
        }
        catch(NullPointerException e) {
            check = true;
        }
        assertEquals(true, check);
    }

    // Test search method if key equals null
    @Test
    public void testSearch(){
        assertEquals(null, completeTree.search(null));
    }

    // Test remove method if key equals null
    @Test
    public void testRemove(){
        assertEquals(null, completeTree.remove(null));
    }
    // Test remove method with node that has 2 children
    @Test
    public void testRemove2(){
        MyBST.MyBSTNode<Integer, Integer> root = completeTree.root; 
        assertEquals((Integer)1, completeTree.remove(3));
        completeTree.remove(3);
        assertEquals((Integer)4, root.getLeft().getKey());
        assertEquals((Integer)7, root.getLeft().getRight().getRight().getKey());
        assertEquals((Integer)6, root.getLeft().getRight().getKey());
        assertEquals(8, completeTree.size);
    }

    // Test inorder method if tree is empty
    @Test
    public void testInorder(){
        MyBST<Integer, Integer> emptytree = new MyBST<>(); 
        assertEquals(0, emptytree.inorder().size());
    }

    // ====== MyBSTIterator class ======

    // Test iterator when iterating twice
    @Test
    public void testIteratorProperFunctionality(){
        // because MyBSTIterator extends MyBST, so it has the root instance
        // variable. Assign this variable from completeTree to the iterator
        MyBSTIterator<Integer, Integer> iterTree = new MyBSTIterator<>();
        iterTree.root = completeTree.root;

        // Initialize the BST value iterator that start from root
        MyBSTIterator<Integer, Integer>.MyBSTValueIterator vi = 
            iterTree.new MyBSTValueIterator(iterTree.root);

        // next should points to the root
        assertSame(iterTree.root, vi.next);
        //lastVisited should be null
        assertNull(vi.lastVisited);
        // Moving forward, nextNode should return root
        assertSame(completeTree.root, vi.nextNode());
        // next should be forwarded to the successor of root
        assertSame(completeTree.root.successor(), vi.next);
        // root become the last visited node
        assertSame(completeTree.root, vi.lastVisited);
        // root become the last visited node
        assertSame(completeTree.root.successor(), vi.nextNode());
        // next should be forwarded to the successor of root
        assertSame(completeTree.root.successor().successor(), vi.next);
        // root become the last visited node
        assertSame(completeTree.root.successor(), vi.lastVisited);
    }

    // ====== Calender class ======

    // Test calendar if start is less than 0
    @Test
    public void testCalendar(){
        MyCalendar cal = new MyCalendar();
        boolean check = false;
        try {
            cal.book(-1, 20);
        }
        catch(IllegalArgumentException e) {
            check = true;
        }
        assertEquals(true, check);
    }
}
