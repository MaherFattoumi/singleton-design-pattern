package com.mf.singleton;

/**
 * @author fattoumi
 * In this example, the Singleton class has a static method getInstance() that returns a single instance of the class. 
 * The instance variable is initialized to null, and the getInstance() method checks if an instance already exists. 
 * If not, it creates a new instance of the Singleton class. Then, the getInstance() method returns this single instance 
 * on each call. 
 * The constructor of the class is declared private to prevent any direct instantiation of the class from outside.
 */
public class Singleton {
	
    private static Singleton instance;
    private static int numberOfInstancesCreated = 0;

    private Singleton() {
        // Private constructor to prevent direct instantiation of the class
    }

    public static Singleton getInstance() {
        if (instance == null) {
            instance = new Singleton();
            incrementNumberOfInstancesCreated();
        }
        return instance;
    }

    public static void incrementNumberOfInstancesCreated() {
        numberOfInstancesCreated++;
    }

    public static int getNumberOfInstancesCreated() {
        return numberOfInstancesCreated;
    }
    
    // Other methods of the Singleton class
}
