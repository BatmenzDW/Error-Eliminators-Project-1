package com.revature.steps;

import com.revature.Setup;
import com.revature.TestRunner;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.Duration;
import java.util.SortedMap;

public class DeleteMoonSteps {
    @Given("User {string} is on Home Page")
    public void userOnTheHomePage(String username) throws Throwable{
        TestRunner.planetariumHome.setupTestUserLogin(username);
        TestRunner.planetariumHome.login(username, "I am the night");
        Assert.assertEquals("Home", TestRunner.driver.getTitle());
        Assert.assertEquals("Welcome to the Home Page " + username, TestRunner.planetariumHome.getHomePageGreeting());
    }

    @When("User selects moon from Dropdown")
    public void userClicksMoonDropdown() throws Throwable {
        WebDriverWait wait = new WebDriverWait(TestRunner.driver, Duration.ofMinutes(1));
        wait.until(ExpectedConditions.elementToBeClickable(By.id("locationSelect")));

        Select select = new Select(TestRunner.driver.findElement(By.id("locationSelect")));
        select.selectByVisibleText("Moon");
    }

    @When("User enters moon name {string}")
    public void userProvidesMoonName(String moonName) throws Throwable {
        WebElement input = TestRunner.driver.findElement(By.id("deleteInput"));
        input.sendKeys(moonName);
    }

    @Then("User clicks delete button")
    public void userClicksDeleteButton() throws Throwable{
        TestRunner.planetariumHome.clickDeleteButton();
    }

    @Then("Moon {string} is deleted Success")
    public void moonLunaDeletedSuccessfully(String moonName) throws Throwable {
        Connection connection = Setup.getConnection();

        try {
            // Query the database to confirm the moon does not exist
            PreparedStatement checkStatement = connection.prepareStatement(
                    "SELECT COUNT(*) FROM moons WHERE name = ?"
            );
            checkStatement.setString(1, moonName);
            ResultSet resultSet = checkStatement.executeQuery();

            resultSet.next();
            int count = resultSet.getInt(1); // Get the count of matching records

            // Assert that the count is zero, meaning the moon was deleted
            if (count > 0) {
                throw new AssertionError("Moon with name '" + moonName + "' still exists in the database.");
            }

        } finally {
            // Ensure the connection is always closed
            if (connection != null && !connection.isClosed()) {
                connection.close();
            }
        }
    }


    @Then("Moon {string} is deleted Fail")
    public void moonLunaDeletedUnsuccessfully(String moonName) throws Throwable{

        Connection connection = Setup.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(
                "DELETE FROM moons WHERE name = ?"
        );
        preparedStatement.setString(1, moonName);
        preparedStatement.executeUpdate();
        connection.close();
    }

}
