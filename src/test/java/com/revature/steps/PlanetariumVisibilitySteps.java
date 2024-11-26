package com.revature.steps;

import com.revature.TestRunner;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import org.junit.Assert;
import io.restassured.RestAssured;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static com.revature.TestRunner.driver;

public class PlanetariumVisibilitySteps {

    @When("User is already logged in")
    public void loggedIn(){
        Assert.assertEquals("Planetarium Login", TestRunner.driver.getTitle());
    }

    @When("User is not logged in yet")
    public void notLoggedIn(){
        Assert.assertNotEquals("Planetarium Login", TestRunner.driver.getTitle());
    }

    @And("User directs to the planetarium home page through {string}")
    public void redirect(String link){
        TestRunner.planetariumHome.goToPlanetariumHome();
        Assert.assertEquals(link, TestRunner.driver.getCurrentUrl());
    }

    @Then("A response status of {string} should be given")
    public void responseStatus(String status){
        Response response = RestAssured.get("http://localhost:8080/planetarium");
        int actualStatus = response.getStatusCode();
        int expectedStatusInt = Integer.parseInt(status); // Convert the string to an int
        // Assert that the actual status matches the expected status
        Assert.assertEquals(expectedStatusInt, actualStatus);
    }

    @And("User is {string} to the Planetarium")
    public void statusReturn(String Status){
        System.out.println(Status);
    }

    @Then("User is logged in Yes, if not then they receive an alert due to invalid credential input")
    public void user_is_logged_in_Yes_if_not_then_they_receive_an_alert_due_to_invalid_credential_input() {
        Assert.assertNotEquals("Planetarium Login", TestRunner.driver.getTitle());
    }

    @Then("User is able to see their Planets and Moons added to their Planetarium : Yes")
    public void user_is_able_to_see_their_Planets_and_Moons_added_to_their_Planetarium_Yes() {
        Assert.assertEquals("Home", TestRunner.driver.getTitle());
    }

    @Then("User is logged in No, if not then they receive an alert due to invalid credential input")
    public void user_is_logged_in_No_if_not_then_they_receive_an_alert_due_to_invalid_credential_input() {
        Assert.assertTrue(TestRunner.planetariumHome.isAlertPresent());

        String result = TestRunner.planetariumHome.getAlertText();
        TestRunner.driver.switchTo().alert().accept();

        // Find that the alert statement appearing is as expected
        Assert.assertEquals("login attempt failed: please try again", result);
    }

    @Then("User is able to see their Planets and Moons added to their Planetarium : No")
    public void user_is_able_to_see_their_Planets_and_Moons_added_to_their_Planetarium_No() {
        Assert.assertEquals("Planetarium Login", TestRunner.driver.getTitle());
    }
}
