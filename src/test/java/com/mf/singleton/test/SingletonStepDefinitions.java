package com.mf.singleton.test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertSame;

import org.junit.runner.RunWith;

import com.mf.singleton.Singleton;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(features = "src/test/resources")
public class SingletonStepDefinitions {
    private Singleton instance;
    private Singleton secondInstance;

    @Given("^I have not created an instance of the Singleton class$")
    public void iHaveNotCreatedAnInstanceOfTheSingletonClass() {
        instance = null;
        secondInstance = null;
    }

    @When("^I create a new instance of the Singleton class$")
    public void iCreateANewInstanceOfTheSingletonClass() {
        instance = Singleton.getInstance();
        secondInstance = Singleton.getInstance();
    }

    @Then("^I should get the same instance of the Singleton class every time I create a new instance$")
    public void iShouldGetTheSameInstanceOfTheSingletonClassEveryTimeICreateANewInstance() {
        assertNotNull(instance);
        assertNotNull(secondInstance);
        assertSame(instance, secondInstance);
    }

    @When("^I create multiple instances of the Singleton class in different threads$")
    public void iCreateMultipleInstancesOfTheSingletonClassInDifferentThreads() throws Exception {
        Runnable task = new Runnable() {
            @Override
            public void run() {
                Singleton.getInstance();
            }
        };

        Thread thread1 = new Thread(task);
        Thread thread2 = new Thread(task);

        thread1.start();
        thread2.start();

        thread1.join();
        thread2.join();
    }

    @Then("^I should get only one instance of the Singleton class$")
    public void iShouldGetOnlyOneInstanceOfTheSingletonClass() {
        assertEquals(1, Singleton.getNumberOfInstancesCreated());
    }
}
