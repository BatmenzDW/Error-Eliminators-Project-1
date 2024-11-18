package com.revature.steps;

import com.revature.Setup;
import com.revature.TestRunner;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.Duration;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

import static com.revature.TestRunner.driver;
import static org.junit.Assert.assertTrue;

public class AddPlanetSteps {

    @Given("User is on the Home Page")
    public void userOnHomePage() throws Throwable {
        TestRunner.planetariumHome.setupTestLogin();
        TestRunner.planetariumHome.login("TestUser", "TestPassword");
    }


    @When("User clicks the Moon Dropdown option")
    public void userClicksMoonDropdownOption() throws Throwable {
        WebDriverWait wait = new WebDriverWait(TestRunner.driver, Duration.ofMinutes(1));
        wait.until(ExpectedConditions.elementToBeClickable(By.id("locationSelect")));

        Select select = new Select(TestRunner.driver.findElement(By.id("locationSelect")));
        select.selectByVisibleText("Moon");
    }

    @When("User provides a Planet Name {string}")
    public void userProvidesPlanetName(String planetName) throws Throwable {
        WebElement input = TestRunner.driver.findElement(By.id("planetNameInput"));
        input.sendKeys(planetName);
    }

    @When("User clicks Submit Planet")
    public void userClicksSubmitPlanet() throws Throwable {
        WebElement button = TestRunner.driver.findElement(By.className("submit-button"));
        button.click();
    }

//    @Then("The User is given an Alert with text {string}")
//    public void theUserIsGivenAnAlertWithText(String alertText) throws Throwable {
//        WebDriverWait wait = new WebDriverWait(TestRunner.driver, Duration.ofMillis(1));
//        wait.until(ExpectedConditions.alertIsPresent());
//
//        String result = TestRunner.planetariumHome.getAlertText();
//
//        Assert.assertTrue(result.contains(alertText));
//    }

    @And("Planet {string} Add Success")
    public void planetAddSuccess(String planetName) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        WebElement table = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("celestialTable")));
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS); // 1 second

        boolean isPlanetPresent = (!table.findElements(By.xpath(".//tr/td[contains(text(),'" + planetName + "')]")).isEmpty()) && !(Objects.equals(planetName, ""));
        System.out.println(isPlanetPresent);

        assertTrue("Moon creation failed - the moon name should be present in the table", isPlanetPresent);
    }

    @And("Planet {string} Add Fail")
    public void planetAddFailed(String planetName) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(1));
        WebElement table = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("celestialTable")));
        driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS); // 1 second

        boolean isPlanetPresent = table.findElements(By.xpath(".//tr/td[contains(text(),'" + planetName + "')]")).isEmpty();
        System.out.println(isPlanetPresent);

        assertTrue("Moon creation should fail - the moon name should not be present in the table", isPlanetPresent);
    }

    @And("Planet with name {string} Exists in database")
    public void planetWithNameExistsInDatabase(String planetName) throws Throwable {
        Connection conn = Setup.getConnection();

        PreparedStatement ps = conn.prepareStatement("select * from planets where name = ?");
        ps.setString(1, planetName);
        ResultSet rs = ps.executeQuery();

        if (rs.next()) {
            conn.close();
            return;
        }

        PreparedStatement pst = conn.prepareStatement("insert into planets (name, ownerId) values(?, 1)");
        pst.setString(1, planetName);
        pst.executeUpdate();
        conn.close();
    }

    @And("Planet with name {string} Doesn't Exist in database")
    public void planetWithNameNotExistInDatabase(String planetName) throws Throwable {
        Connection conn = Setup.getConnection();
        PreparedStatement ps = conn.prepareStatement("DELETE FROM planets WHERE name = ?");
        ps.setString(1, planetName);
        ps.executeUpdate();
        conn.close();
    }

    @Then("User provides an image Yes")
    public void userCanProvideAnImageCanAddImageYes() throws Throwable {
        String relPath = "src/test/resources/Celestial-Images/planet-1.jpg";
        File file = new File(relPath);

        String filePath = file.getAbsolutePath();

        TestRunner.driver.findElement(By.id("planetImageInput")).sendKeys(filePath);
    }

    @Then("User provides an image No")
    public void userCanProvideAnImageCanAddImageNo() throws Throwable {
        WebElement imageInput = TestRunner.driver.findElement(By.id("planetImageInput"));
        assertTrue("Expected no image to be provided.", imageInput.getAttribute("value").isEmpty());
    }

}
