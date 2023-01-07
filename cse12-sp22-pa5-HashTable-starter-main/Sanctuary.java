
/**
 * Name: Aditi Jain
 * ID: A17098965
 * Email: adj003@ucsd.edu
 * Sources used: None
 * 
 * Keeps track of how many of each species 
 * are currently located on sanctuary's 
 * grounds using HashMaps.
 */

import java.util.HashMap;
import java.util.Set;

/**
 * Contains the methods required to implement the
 * Sanctuary class
 */
public class Sanctuary {
    
    HashMap<String, Integer> sanctuary;
    int maxAnimals;
    int maxSpecies;

    /**
     * Initializes the HashMap with no elements and 
     * initializes the other instance variables.
     * @param maxAnimals - max number of animals that 
     * the sanctuary can support
     * @param maxSpecies - max number of species that
     *  the sanctuary can support
     */
    public Sanctuary(int maxAnimals, int maxSpecies) {
        if(maxAnimals < 0 || maxSpecies < 0) {
            throw new IllegalArgumentException();
        }
        sanctuary = new HashMap<>();
        this.maxAnimals = maxAnimals;
        this.maxSpecies = maxSpecies;
    }

    /**
     * Returns the number of animals at the sanctuary
     * that are of the given species
     * @param species - given species
     * @return - number of animals
     */
    public int getNum(String species) {
        if(species == null) {
            throw new IllegalArgumentException();
        }
        if(sanctuary.containsKey(species)) {
            return sanctuary.get(species);
        }
        else {
            return 0;
        }
    }
    
    /**
     * Returns the total number of animals
     * at the sanctuary
     * @return - total num of animals
     */
    public int getTotalAnimals() {
        int total = 0;
        for(Integer num: sanctuary.values()) {
            total = total + num;
        }
        //adds values of sanctuary to total to 
        //obtain number of total animals
        return total;
    }
    
    /**
     * Returns the total number of species
     * at the sanctuary
     * @return - total num of species
     */
    public int getTotalSpecies() {
        return sanctuary.size();
    }

    /**
     * Rescue num of animals by adding up to
     * sanctuary's limit
     * @param species - species to add to
     * @param num - num of animals to add
     * @return - the num of animals that 
     * can't be rescued
     */
    public int rescue(String species, int num) {
        if(num <= 0 || species == null) {
            throw new IllegalArgumentException();
        }
        if(getTotalAnimals() == maxAnimals 
        || (!sanctuary.containsKey(species) 
        && getTotalSpecies()+1 > maxSpecies)) {
            return num;
        }
        int animalsbefore = getTotalAnimals();
        // stores total animals before putting animals to use value
        // in return statement
        int difference = maxAnimals-animalsbefore;
        if((num + getTotalAnimals()) >= maxAnimals) {
            if(sanctuary.containsKey(species)) {
                sanctuary.replace(species, 
                this.getNum(species)+difference);
            }
            else {
                sanctuary.put(species, difference);
            }
            return num-difference;
            // since all animals can be saved, 0 is returned
        }
        else {
            if(sanctuary.containsKey(species)) {
                sanctuary.replace(species, 
                this.getNum(species)+num);
            }
            else {
                sanctuary.put(species, num);
            }
            return 0;
        }
    }

    /**
     * Removes num animals of species from the 
     * sanctuary
     * @param species - to remove
     * @param num - num of animals to remove
     */
    public void release(String species, int num) {
        if(num <= 0 || num > getNum(species)) {
            throw new IllegalArgumentException();
        }
        if(species == null || !sanctuary.containsKey(species)) {
            throw new IllegalArgumentException();
        }
        int total = sanctuary.get(species) - num;
        if(total <= 0) {
            sanctuary.remove(species);
            // removes species as it should not exist in
            // sanctuary if no animals exist
        }
        else {
            sanctuary.replace(species, total);
        }
    }
}
