/**
 * Name: Aditi Jain
 * ID: A17098965
 * Email: adj003@ucsd.edu
 * Sources used: None
 * 
 * Helps us store the students registered for this 
 * particular course in the form of a HashSet. 
 * Contains the necessary methods for this.
 */

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;

/**
 * Helps us store the students registered for this 
 * particular course in the form of a HashSet. 
 */
public class Course {
    HashSet<Student> enrolled;
    private final int capacity;
    private final String department;
    private final String number;
    private final String description;

    /**
     * Initialize the course’s information with an initial 
     * enrollment of 0 students.
     * @param department - course falls under this department
     * @param number - course number
     * @param description - course description
     * @param capacity - maximum number of students that can 
     * be enrolled in this course
     */
    public Course(String department, String number, String description, 
        int capacity){
            if(department == null || number == null 
            || description == null) {
                throw new IllegalArgumentException();
            }
            if(capacity <= 0) {
                throw new IllegalArgumentException();
            }
            //initializing variables
            enrolled = new HashSet<>();
            this.department = department;
            this.number = number;
            this.description = description;
            this.capacity = capacity;
            
        }

    /**
     * Returns department name
     * @return - department
     */
    public String getDepartment(){
        return department;
    }

    /**
     * Returns the course number
     * @return - number
     */
    public String getNumber(){
        return number;
    }

    /**
     * Returns the description of the course
     * @return - description
     */
    public String getDescription(){
        return description;
    }

    /**
     * Returns the capacity of the course
     * @return - capacity
     */
    public int getCapacity(){
        return capacity;
    }

    /**
     * Adds student to course if there's room &
     * student is not currently enrolled
     * @param student - to be added
     * @return - true if enrolled, false if not
     */
    public boolean enroll(Student student) {
        if(student == null) {
            throw new IllegalArgumentException();
        }
        if(enrolled.size() < capacity 
        && !enrolled.contains(student)) {
            enrolled.add(student);
            return true;
        }
        else {
            return false;
        }
    }

    /**
     * Removes student from course if student
     * is enrolled
     * @param student - to be removed
     * @return - true if removed, false if not
     */
    public boolean unenroll(Student student) {
        if(student == null) {
            throw new IllegalArgumentException();
        }
        if(enrolled.contains(student)) {
            enrolled.remove(student);
            return true;
        }
        else {
            return false;    
        }
    }

    /**
     * Removes all students from course
     * since course is cancelled
     */
    public void cancel() {
        enrolled.clear();
    }

    /**
     * Checks if course is at capacity
     * @return - true if at capacity, false
     * if not
     */
    public boolean isFull() {
        if(enrolled.size() == capacity) {
            return true;
        }
        else {
            return false;
        }
    }

    /**
     * Returns the current number of 
     * enrolled students
     * @return - int number of students
     */
    public int getEnrolledCount() {
        return enrolled.size();
    }

    /**
     * Return the number of students that
     * can still enroll in the course
     * @return - int number of seats
     */
    public int getAvailableSeats() {
        return capacity - enrolled.size();
    }

    /**
     * Returns a shallow copy of all the 
     * students enrolled in this course
     * @return - clone of enrolled
     */
    public HashSet<Student> getStudents() {
        return (HashSet<Student>) enrolled.clone();
    }

    /**
     * Turns the collection of enrolled students
     * into an ordered ArrayList collection
     * @return - ArrayList of students
     */
    public ArrayList<Student> getRoster() {
        ArrayList<Student> roster = new ArrayList<Student>();
        for(Student ele : enrolled) {
            roster.add(ele);
        }
        //adds students to arraylist and then sorts
        Collections.sort(roster);
        return roster;

    }

    /**
     * Returns a string representation
     * for a Course object
     * @return - String format
     */
    public String toString() {
        String format = "%s %s [%s]\n%s";
        String output = String.format(format, department, 
        number, capacity, description);
        //uses string.format method to format
        return output;
    }
}

