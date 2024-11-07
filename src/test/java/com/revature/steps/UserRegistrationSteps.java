package com.revature.steps;

import com.revature.Setup;
import com.revature.TestRunner;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.Duration;

public class UserRegistrationSteps {

    @And("User with username {string} Doesn't Exist in database")
    public void userWithUsernameNotExistsInDatabase(String username) throws Throwable {
        Connection conn = Setup.getConnection();
        PreparedStatement ps = conn.prepareStatement("DELETE FROM users WHERE username = ?");
        ps.setString(1, username);
        ps.executeUpdate();
        conn.close();
    }

    @And("User with username {string} Exists in database")
    public void userWithUsernameExistsInDatabase(String username) throws Throwable {
        Connection conn = Setup.getConnection();

        PreparedStatement ps = conn.prepareStatement("SELECT * FROM users WHERE username = ?");
        ps.setString(1, username);
        ResultSet rs = ps.executeQuery();

        if (rs.next()) {
            conn.close();
            return;
        }

        String pass = username.equals("Batman") ? "I am the night" : "Lorem ipsus";

        PreparedStatement ps2 = conn.prepareStatement("INSERT INTO users (username, password) VALUES (?, ?)");
        ps2.setString(1, username);
        ps2.setString(2, pass);
        ps2.executeUpdate();
        conn.close();
    }

    @When("User clicks Create Account")
    public void userClicksCreateAccount() {
        TestRunner.planetariumHome.clickCreateAccount();
    }

    @Then("User is taken to the Account Creation Page")
    public void userIsTakenToTheAccountCreationPage() {
        Assert.assertEquals("Account Creation", TestRunner.driver.getTitle());
    }

    @When("User provides a username of {string}")
    public void userProvidesAUsernameOf(String username) throws Throwable {
        TestRunner.driver.findElement(By.id("usernameInput")).sendKeys(username);
    }

    @And("User provides a password of {string}")
    public void userProvidesAPasswordOf(String password) throws Throwable {
        TestRunner.driver.findElement(By.id("passwordInput")).sendKeys(password);
    }

    @And("User clicks the Register button")
    public void userClicksTheRegisterButton() {
        TestRunner.driver.findElement(By.xpath("//input[3]")).click();
    }

    @Then("The User is given an Alert with text {string}")
    public void theUserIsGivenAnAlertWithText(String alertText) throws Throwable {
        WebDriverWait wait = new WebDriverWait(TestRunner.driver, Duration.ofMillis(5));
        wait.until(ExpectedConditions.alertIsPresent());

        String result = TestRunner.planetariumHome.getAlertText();

//        System.out.println(result);

        Assert.assertTrue(result.contains(alertText));
    }

    @And("Account Creation Success and User Redirected to Login")
    public void accountCreationSuccess() throws Throwable {
        TestRunner.driver.switchTo().alert().accept();

        Assert.assertEquals("Planetarium Login", TestRunner.driver.getTitle());
    }

    @And("Account Creation Failed")
    public void accountCreationFailed() throws Throwable {
        TestRunner.driver.switchTo().alert().accept();

        Assert.assertEquals("Account Creation", TestRunner.driver.getTitle());
    }

    @Before
    public static void before(){
        System.out.println("Before");
        Setup.resetTestDatabase();
    }

    @After
    public static void after(){
        System.out.println("After");
        try {
            WebDriverWait wait = new WebDriverWait(TestRunner.driver, Duration.ofMillis(5));
            wait.until(ExpectedConditions.alertIsPresent());
            TestRunner.driver.switchTo().alert().accept();
        } catch (NoAlertPresentException nape) {
            // No need to close alert if not there
        } catch (TimeoutException te) {
            // Catch for the other type of exception it can throw
        }
    }
}
