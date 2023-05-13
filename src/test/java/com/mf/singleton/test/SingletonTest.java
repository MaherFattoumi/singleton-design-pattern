package com.mf.singleton.test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;

import com.mf.singleton.Singleton;

public class SingletonTest {
    @Test
    public void testGetInstance() {
        Singleton instance1 = Singleton.getInstance();
        Singleton instance2 = Singleton.getInstance();
        assertSame(instance1, instance2);
    }

    @Test
    public void testGetInstanceMultipleThreads() {
        Runnable task = new Runnable() {
            @Override
            public void run() {
                Singleton.getInstance();
            }
        };

        // Start multiple threads and try to create instances of the Singleton class concurrently
        Thread thread1 = new Thread(task);
        Thread thread2 = new Thread(task);
        thread1.start();
        thread2.start();

        // Wait for the threads to finish
        try {
            thread1.join();
            thread2.join();
        } catch (InterruptedException ex) {
            fail("Thread interrupted.");
        }

        // Check that only one instance of the Singleton class was created
        assertEquals(1, Singleton.getNumberOfInstancesCreated());
    }
}
