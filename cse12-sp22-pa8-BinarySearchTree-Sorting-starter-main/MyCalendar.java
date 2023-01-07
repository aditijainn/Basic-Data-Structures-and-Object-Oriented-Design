/**
 * Name: Aditi Jain
 * ID: A17098965
 * Email: adj003@ucsd.edu
 * Sources used: Zybook
 * 
 * Contains methods that implement the 
 * MyCalendar using MyTreeMap structure
 */

/**
 * This class contains methods that implement the 
 * MyCalendar using MyTreeMap structure
 */
public class MyCalendar {
    MyTreeMap<Integer, Integer> calendar;
    
    /**
     * Initizalizes calendar as new MyTreeMap
     */
    public MyCalendar() {
        calendar = new MyTreeMap<Integer, Integer>();
    }
    
    /**
     * Inserts bookings into calendar based on available times
     * @param start - starting time
     * @param end - ending time
     * @return - true if can book, if not false
     */
    public boolean book(int start, int end) {
        if(start < 0 || start >= end) {
            throw new IllegalArgumentException();
        }
        boolean output = true;
        Integer ceilingkey = calendar.ceilingKey(start);
        Integer floorkey = calendar.floorKey(start);
        // if conditions dont pass, output is false
        if(ceilingkey != null && end > ceilingkey 
        || floorkey != null && start < calendar.get(floorkey)) {
            output = false;
        }
        // if conditions pass, insert booking
        if(output) {
            calendar.put(start, end);
        }
        return output;
    }

    /**
     * Returns calendar object
     * @return - calendar object
     */
    public MyTreeMap<Integer, Integer> getCalendar(){
        return this.calendar;
    }
   
}