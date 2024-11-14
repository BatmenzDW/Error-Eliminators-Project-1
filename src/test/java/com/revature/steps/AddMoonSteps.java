package com.revature.steps;

import com.revature.Setup;
import com.revature.TestRunner;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.Duration;
import java.util.concurrent.TimeUnit;

import static com.revature.TestRunner.driver;
import static com.revature.TestRunner.planetariumHome;
import static org.junit.Assert.assertTrue;

public class AddMoonSteps {

    @When("User selects Planet from Dropdown")
    public void userClicksPlanetDropdownOption() throws Throwable {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofMinutes(1));
        wait.until(ExpectedConditions.elementToBeClickable(By.id("locationSelect")));

        Select select = new Select(driver.findElement(By.id("locationSelect")));
        select.selectByVisibleText("Planet");
    }

    @When("User selects moon from Dropdown")
    public void userClicksMoonDropdownOption() throws Throwable {
        Select select = new Select(driver.findElement(By.id("locationSelect")));
        select.selectByVisibleText("Moon");
    }

    @When("User inputs {string} in the moon name section")
    public void userProvidesMoonName(String moonName) throws Throwable {
        WebElement input = driver.findElement(By.id("moonNameInput"));
        input.sendKeys(moonName);
    }

    @When("User inputs associated planet ID {string}")
    public void user_inputs_associated_planet_ID(String planetID) {
        int planetIdInt = Integer.parseInt(planetID); // Convert the string to an int
        WebElement input = driver.findElement(By.id("orbitedPlanetInput"));
        input.sendKeys(String.valueOf(planetIdInt)); // Convert it back to a string for sendKeys
    }

    @When("User optionally attaches moon image: {string}")
    public void user_optionally_attaches_moon_image(String imageOption) {
        if (imageOption.equalsIgnoreCase("yes")) {
            String relPath = "src/test/resources/Celestial-Images/moon-1.jpg";
            File file = new File(relPath);

            String filePath = file.getAbsolutePath();

            driver.findElement(By.id("moonImageInput")).sendKeys(filePath);
        }
    }
    @When("User clicks Submit Moon")
    public void userClicksSubmitMoon() throws Throwable {
        WebElement button = driver.findElement(By.className("submit-button"));
        button.click();
    }


    @Then("The moon {string} creation is a Success")
    public void moonAddSuccess(String moonName) throws Throwable {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement table = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("celestialTable")));
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS); // Waits up to 10 seconds for elements to appear

        boolean isMoonPresent = !table.findElements(By.xpath(".//tr/td[contains(text(),'" + moonName + "')]")).isEmpty();
        System.out.println(table.findElements(By.xpath(".//tr/td[contains(text(),'" + moonName + "')]")));
        System.out.println(isMoonPresent);

        assertTrue("Moon creation failed - the moon name should be present in the table", isMoonPresent);
    }

    @Then("The moon {string} creation is a Fail")
    public void moonAddFail(String moonName) throws Throwable {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofMillis(10));

        try
        {
            WebDriverWait wait2 = new WebDriverWait(TestRunner.driver, Duration.ofMillis(10));
            wait2.until(ExpectedConditions.alertIsPresent());
            driver.switchTo().alert().accept();
        }
        catch (NoAlertPresentException | TimeoutException Ex)
        {
            //
        }

        WebElement table = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("celestialTable")));

        boolean isMoonAbsent = table.findElements(By.xpath(".//tr/td[contains(text(),'" + moonName + "')]")).isEmpty();

        assertTrue("Moon creation should fail - the moon name should not be present in the table", isMoonAbsent);
    }
}

