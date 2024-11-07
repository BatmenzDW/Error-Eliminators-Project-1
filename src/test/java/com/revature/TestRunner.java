package com.revature;

import com.revature.poms.PlanetariumHome;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.openqa.selenium.Alert;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

/*
    When creating automated test suites, it is common to have a "test runner" used to
    facilitate your testing. This allows you to define all the resources you need for testing
    that are shared in one location, where you can handle setup and tear down for those resources
 */
// RunWith tells Junit that we are using the Cucumber testing framework to run our tests
@RunWith(Cucumber.class)
// We need to tell Cucumber where the steps and feature files are located: use @CucumberOptions for this
@CucumberOptions(
        // this tells Cucumber where your step implementations are
        glue = "com.revature.steps",
        // this tells Cucumber where your feature files are
        features = "classpath:features",
        // this tells Cucumber to use a better format for the test results
        // the html plugin tells Cucumber to generate an HTML test report
        plugin = {"pretty","html:src/test/resources/reports/html-report.html"}
)
public class TestRunner {
    /*
        since we are using Junit to facilitate our tests, we will make our TestRunner fields public
        and static so we can access them wherever we need them without needing to worry about creating
        an object of the class first
    */
    public static WebDriver driver;

    public static PlanetariumHome planetariumHome;

    @BeforeClass
    public static void setup() {
        ChromeOptions options = new ChromeOptions();
//        options.addArguments("headless");

        driver = new ChromeDriver(options);

        planetariumHome = new PlanetariumHome(driver);
    }

    @AfterClass
    public static void tearDown(){
        if(driver != null){
            driver.quit();
        }
    }
}
