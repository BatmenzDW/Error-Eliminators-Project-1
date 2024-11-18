package com.revature.steps;

import com.revature.Setup;
import com.revature.TestRunner;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.time.Duration;
import java.util.SortedMap;

/*
Unique Name Nessus - Intended to not be deleted (correct response)
Unique Name Earth - Intended to be deleted but alert received (correct response)
Planet Ownership Batman Earth - Intended to be deleted but alert received (correct response)
Planet Ownership BatmanAndRobin Earth - Intended to not be deleted (correct response)
Planet Ownership 2 Batman Nessus - Intended to be deleted (correct response)
Planet Ownership 2 BatmanAndRobin Nessus - Intended to not be deleted, but it was deleted (correct response)
 */

public class DeletePlanetSteps {
    @Given("User {string} is on the Home Page")
    public void userIsOnTheHomePage(String username) throws Throwable{
        TestRunner.planetariumHome.setupTestUserLogin(username);
        TestRunner.planetariumHome.login(username, "I am the night");

        WebDriverWait wait = new WebDriverWait(TestRunner.driver, Duration.ofSeconds(3));
        wait.until(ExpectedConditions.titleIs("Home"));

        Assert.assertEquals("Home", TestRunner.driver.getTitle());
        Assert.assertEquals("Welcome to the Home Page " + username, TestRunner.planetariumHome.getHomePageGreeting());
    }

    @And("User enters planet name {string}")
    public void userEntersPlanetName(String planetName) throws Throwable{
        TestRunner.planetariumHome.inputDeletePlanetName(planetName);
    }

    @And("User enters planet name {string} having Owner Id 1")
    public void userEntersPlanetNameHavingOwnerId1(String planetName) throws Throwable{
        TestRunner.planetariumHome.inputDeletePlanetName(planetName);
    }

    @And("User clicks the delete button")
    public void userClicksTheDeleteButton() throws Throwable{
        TestRunner.planetariumHome.clickDeleteButton();
    }

    @Then("Planet Earth is not deleted")
    public void planetEarthIsNotDeleted() throws Throwable{
        Assert.assertTrue(TestRunner.planetariumHome.isAlertPresent());

        String result = TestRunner.planetariumHome.getAlertText();
        TestRunner.driver.switchTo().alert().accept();

        // Find that the alert statement appearing is as expected
        Assert.assertEquals("Failed to delete planet with name ", result);
    }

    @Then("Planet Earth is deleted successfully")
    public void planetEarthIsDeletedSuccessfully() throws Throwable{
        // Find that planet Earth no longer exists on page/in database

        Connection connection = Setup.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(
                "DELETE FROM planets WHERE name = 'Earth'"
        );
        preparedStatement.executeUpdate();
        connection.close();
    }

    @Then("Planet Nessus is not deleted")
    public void planetNessusIsNotDeleted() throws Throwable{
        Assert.assertTrue(TestRunner.planetariumHome.isAlertPresent());

        String result = TestRunner.planetariumHome.getAlertText();
        TestRunner.driver.switchTo().alert().accept();

        // Find that the alert statement appearing is as expected
        Assert.assertEquals("Failed to delete planet with name ", result);
    }

    @Then("Planet Nessus is deleted successfully")
    public void planetNessusIsDeletedSuccessfully() throws Throwable{
        // Find that planet Nessus no longer exists on page/in database

        Connection connection = Setup.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(
                "DELETE FROM planets WHERE name = 'Nessus'"
        );
        preparedStatement.executeUpdate();
        connection.close();
    }
}
