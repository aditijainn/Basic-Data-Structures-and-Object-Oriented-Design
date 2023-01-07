/**
 * Name: Aditi Jain
 * ID: A17098965
 * Email: adj003@ucsd.edu
 * Sources used: None
 * 
 * This file contains all the custom test cases I wrote
 * to test edge cases for PA5
 */

import java.util.*;
import static org.junit.Assert.*;
import org.junit.*;

/**
 * This class contains a subset of unit test cases for PA5. It ensures the 
 * basic functionalities of the Course, Sanctuary and Student class.
 * 
 * IMPORTANT: Do not change the method names and points are awarded
 * only if your test cases cover cases that the public tester file
 * does not take into account.
 */
public class CustomTester {

    // ----------------Student class----------------
    /**
     * Test the equals method when student objects are not equal
     */
    @Test
    public void testEquals() {
        Student student1 = new Student(new String("Aditi"), 
            new String("Jain"), new String("A17098965"));
        Student student2 = new Student(new String("Test"), 
            new String("Student"), new String("A12345678"));
        assertFalse(student1.equals(student2));
    }

    /**
     * Test the compareTo method when first student object 
     * is lexicographically comes before, giving a negative
     * value
     */
    @Test
    public void testCompareTo() {
        Student student1 = new Student(new String("Aditi"), 
            new String("Jain"), new String("A17098965"));
        Student student2 = new Student(new String("Test"), 
            new String("Student"), new String("A12345678"));
        assertTrue(student1.compareTo(student2) < 0);
    }

    // ----------------Course class----------------
    /**
     * Test the enroll method when null is used as 
     * student argument
     */
    @Test
    public void testEnroll() {
        boolean check = false;
        Course course = new Course("CSE", "12", 
            "Data Structure", 100);
        course.enrolled = new HashSet<>();
        try {
            course.enroll(null);
        }
        catch (IllegalArgumentException e) {
            check = true;
        }
        assertEquals("Check if student is null", true, check);
    }

    /**
     * Test the unenroll method when when null is used as 
     * student argument
     */
    @Test
    public void testUnenroll() {
        boolean check = false;
        Course course = new Course("CSE", "12", 
            "Data Structure", 100);
        course.enrolled = new HashSet<>();
        try {
            course.unenroll(null);
        }
        catch (IllegalArgumentException e) {
            check = true;
        }
        assertEquals("Check if student is null", true, check);
    }

    /**
     * Test the getRoster method when 4 student objects 
     * are added to the course
     */
    @Test
    public void testGetRoster() {
        Course course = new Course("CSE", "12", 
            "Data Structure", 100);
        course.enrolled = new HashSet<>();
        Student student1 = new Student("Whales", "Ocean", 
            "A123");
        Student student2 = new Student(new String("Aditi"), 
            new String("Jain"), new String("A17098965"));
        Student student3 = new Student(new String("Test"), 
            new String("Student"), new String("A12345678"));
        Student student4 = new Student(new String("Rohan"), 
            new String("Patel"), new String("A12345666"));
        course.enroll(student1);
        course.enroll(student2);
        course.enroll(student3);
        course.enroll(student4);
        ArrayList<Student> tester = new ArrayList<>();
        tester.add(student1);
        tester.add(student2);
        tester.add(student3);
        tester.add(student4);
        Collections.sort(tester);
        assertEquals(tester,course.getRoster());
    }

    // ----------------Sanctuary class----------------
    /**
     * Test the constructor when maxAnimals is < 0 
     */
    @Test
    public void testSanctuaryConstructor() {
        boolean check = false;
        try {
            Sanctuary sanct = new Sanctuary(-1, 50);
        }
        catch (IllegalArgumentException e) {
            check = true;
        }
        assertEquals("if arg < 0", true, check);
    }

    /**
     * Test the rescue method when num is equal to 0
     */
    @Test
    public void testRescueTestOne(){
        boolean check = false;
        Sanctuary sanct = new Sanctuary(100, 20);
        try {
            sanct.rescue("Panda", 0);
        }
        catch (IllegalArgumentException e) {
            check = true;
        }
        assertEquals("if num <= 0", true, check);
    }

    /**
     * Test the rescue method when species is null
     */
    @Test
    public void testRescueTestTwo(){
        boolean check = false;
        Sanctuary sanct = new Sanctuary(100, 20);
        try {
            sanct.rescue(null, 10);
        }
        catch (IllegalArgumentException e) {
            check = true;
        }
        assertEquals("if species is null", true, check);
    }

    /**
     * Test the release method when num = 0
     */
    @Test
    public void testReleaseTestOne(){
        boolean check = false;
        Sanctuary sanct = new Sanctuary(100, 20);
        try {
            sanct.release("Panda", 0);
        }
        catch (IllegalArgumentException e) {
            check = true;
        }
        assertEquals("if num <= 0", true, check);
    }

    /**
     * Test the release method when species = null
     */
    @Test
    public void testReleaseTestTwo(){
        boolean check = false;
        Sanctuary sanct = new Sanctuary(100, 20);
        try {
            sanct.release(null, 10);
        }
        catch (IllegalArgumentException e) {
            check = true;
        }
        assertEquals("if num <= 0", true, check);
    }
}

