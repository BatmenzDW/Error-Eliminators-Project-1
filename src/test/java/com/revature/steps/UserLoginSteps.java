package com.revature.steps;

import com.revature.TestRunner;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class UserLoginSteps {

    @When("The user provides username {string}")
    public void userProvidesUsername(String username) throws Throwable {
        TestRunner.planetariumLogin.inputUsername(username);
//        TestRunner.driver.findElement(By.id("usernameInput")).sendKeys(username);
    }

    @And("User provides password {string}")
    public void userProvidesAPasswordOf(String password) throws Throwable {
        TestRunner.planetariumLogin.inputPassword(password);
//        TestRunner.driver.findElement(By.id("passwordInput")).sendKeys(password);
    }

    @And("User clicks the login button")
    public void userClicksTheRegisterButton() {
        TestRunner.planetariumLogin.clickLoginButton();
    }

    @Then("The user should receive the result Logged in successfully, redirected to home page")
    public void theUserShouldReceiveTheResultSuccess() {
        WebDriverWait wait = new WebDriverWait(TestRunner.driver, Duration.ofMillis(1));
        wait.until(ExpectedConditions.urlContains("/planetarium"));
        Assert.assertEquals("Home", TestRunner.driver.getTitle());
    }

    @Then("The user should receive the result Alert -  login attempt failed: please try again")
    public void theUserShouldReceiveTheResultFailed() {
        WebDriverWait wait = new WebDriverWait(TestRunner.driver, Duration.ofMillis(5));
        wait.until(ExpectedConditions.alertIsPresent());

        String result = TestRunner.planetariumLogin.getAlertText();

        TestRunner.driver.switchTo().alert().accept();

        Assert.assertEquals("login attempt failed: please try again", result);

        Assert.assertEquals("Planetarium Login", TestRunner.driver.getTitle());
    }
}
