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

public class DeletePlanetSteps {
    @Given("User {string} is on the Home Page")
    public void userIsOnTheHomePage(String username) throws Throwable{
        Assert.assertEquals("Home", TestRunner.driver.getTitle());
        Assert.assertEquals("Welcome to the Home Page " + username, TestRunner.planetariumHome.getHomePageGreeting());
    }

    @When("User clicks Planet from the dropdown")
    public void userClicksPlanetFromTheDropdown() throws Throwable{
        TestRunner.planetariumHome.selectPlanet();
    }

    @And("User enters planet name {string}")
    public void userEntersPlanetName(String planetName) throws Throwable{
        TestRunner.planetariumHome.inputCreatePlanetName(planetName);
    }

    @And("User enters planet name {string} having Owner Id 1")
    public void userEntersPlanetNameHavingOwnerId1(String planetName) throws Throwable{
        TestRunner.planetariumHome.inputCreatePlanetName(planetName);
    }

    @And("User clicks the delete button")
    public void userClicksTheDeleteButton() throws Throwable{
        TestRunner.planetariumHome.clickDeleteButton();
    }

    @Then("Planet Earth is deleted successfully")
    public void planetEarthIsDeletedSuccessfully() throws Throwable{
        // Find that planet Earth no longer exists on page/in database
        //TODO: IMPROVE DELETION CHECK LOGIC
        if(TestRunner.planetariumHome.findPlanetByName("Earth")){
            System.out.println("Planet was not deleted successfully");
        }
        else{
            System.out.println("Planet was deleted successfully");
        }

        Connection connection = Setup.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(
                "DELETE FROM planets WHERE planet_name = 'Earth'"
        );
        preparedStatement.executeUpdate();
        connection.close();
    }

    @Then("Planet Nessus is not deleted")
    public void planetNessusIsNotDeleted() throws Throwable{
        WebDriverWait wait = new WebDriverWait(TestRunner.driver, Duration.ofMillis(5));
        wait.until(ExpectedConditions.alertIsPresent());

        String result = TestRunner.planetariumHome.getAlertText();
        TestRunner.driver.switchTo().alert().accept();

        // Find that the alert statement appearing is as expected
        Assert.assertEquals("Failed to delete planet with name ", result);
    }

    @Then("Planet Nessus is deleted successfully")
    public void planetNessusIsDeletedSuccessfully() throws Throwable{
        // Find that planet Nessus no longer exists on page/in database
        //TODO: IMPROVE DELETION CHECK LOGIC
        if(TestRunner.planetariumHome.findPlanetByName("Nessus")){
            System.out.println("Planet was not deleted successfully");
        }
        else{
            System.out.println("Planet was deleted successfully");
        }

        Connection connection = Setup.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(
                "DELETE FROM planets WHERE planet_name = 'Nessus'"
        );
        preparedStatement.executeUpdate();
        connection.close();
    }
}
