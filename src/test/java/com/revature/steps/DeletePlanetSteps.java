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
import java.sql.ResultSet;
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

    @Then("Planet {string} is deleted successfully")
    public void planetEarthIsDeletedSuccessfully(String planetName) throws Throwable{
        Connection connection = Setup.getConnection();

        try {
            // Query the database to confirm the moon does not exist
            PreparedStatement checkStatement = connection.prepareStatement(
                    "SELECT COUNT(*) FROM planets WHERE name = ?"
            );
            checkStatement.setString(1, planetName);
            ResultSet resultSet = checkStatement.executeQuery();

            resultSet.next();
            int count = resultSet.getInt(1); // Get the count of matching records

            // Assert that the count is zero, meaning the planet was deleted
            if (count > 0) {
                throw new AssertionError("Planet with name '" + planetName + "' still exists in the database.");
            }

        } finally {
            // Ensure the connection is always closed
            if (connection != null && !connection.isClosed()) {
                connection.close();
            }
        }
    }

    @Then("Planet {string} is not deleted")
    public void planetEarthIsNotDeletedSuccessfully(String planetName) throws Throwable {
        Connection connection = Setup.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(
                "DELETE FROM moons WHERE name = ?"
        );
        preparedStatement.setString(1, planetName);
        preparedStatement.executeUpdate();
        connection.close();
    }
}
