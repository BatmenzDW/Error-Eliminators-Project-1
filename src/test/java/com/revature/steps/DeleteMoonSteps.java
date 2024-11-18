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

    @Then("Moon {string} is deleted {string}")
    public void moonLunaDeletedSuccessfully(String moonName) throws Throwable{

        Connection connection = Setup.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(
                "DELETE FROM moons WHERE name = 'Luna'"
        );
        preparedStatement.executeUpdate();
        connection.close();
    }

    @Then("Moon {string} is deleted Fail")
    public void moonLunaDeletedUnsuccessfully(String moonName) throws Throwable{

        Connection connection = Setup.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(
                "DELETE FROM moons WHERE name = 'Luna'"
        );
        preparedStatement.executeUpdate();
        connection.close();
    }

}
