
/**
 * Name: Aditi Jain
 * ID: A17098965
 * Email: adj003@ucsd.edu
 * Sources used: None
 * 
 * Creates the student objects that will be
 * stored in course objects. Contains the 
 * necessary methods for this.
 */

import java.util.Objects;

/**
 * Contains the methods required to implement the
 * Student class
 */
public class Student implements Comparable<Student> {
    private final String firstName;
    private final String lastName;
    private final String PID;

    /**
     * Initializes the student’s information.
     * @param firstName - student's first name
     * @param lastName - student's last name
     * @param PID - student's PID
     */
    public Student(String firstName, String lastName, String PID) {
        if(firstName == null || lastName == null || PID == null) {
            throw new IllegalArgumentException();
        }
        this.firstName = firstName;
        this.lastName = lastName;
        this.PID = PID;
    }

    /**
     * Returns the student’s last name
     * @return - lastname
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * Returns the student’s first name
     * @return - firstname
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * Returns the student’s PID
     * @return - PID
     */
    public String getPID() {
        return PID;
    }

    /**
     * Checks if all instance variables
     * are equal
     * @param o - object to compare
     * @return - true if equal, false if not
     */
    @Override
    public boolean equals(Object o) {
        if(o instanceof Student) {
            Student other = (Student) o;
            if (other.firstName.equals(this.firstName)
            &&  other.lastName.equals(this.lastName) 
            &&  other.PID.equals(this.PID)) {
                // checks if every intialized variable is equal
                return true;
            }
        }
        return false;
    }

    /**
     * Returns the hash value generated 
     * by Object’s hash function
     * @return - hash value
     */
    @Override
    public int hashCode() {
        return Objects.hash(firstName, lastName, PID);
    }

    /**
     * Checks if student's instance 
     * variables come lexicographically 
     * before or after
     * @param o - object to compare
     * @return - 0 if equal, <0 if before, 
     * >0 if after
     */
    @Override
    public int compareTo(Student o) {
        if(this.equals(o)) {
            return 0;
        }
        else if(this.lastName.compareTo(o.lastName) != 0) {
            return this.lastName.compareTo(o.lastName);
        }
        else if(this.firstName.compareTo(o.firstName) != 0) {
            return this.firstName.compareTo(o.firstName);
        }
        else if(this.PID.compareTo(o.PID) != 0) {
            return this.PID.compareTo(o.PID);
        }
        // checks in the order of lastName, firstName, PID as required
        else {
            return 0;
        }
    }
}
