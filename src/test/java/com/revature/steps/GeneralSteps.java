package com.revature.steps;

import com.revature.TestRunner;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.Select;

import static com.revature.TestRunner.driver;

public class GeneralSteps {

    @Given(".*User is on the Home Page")
    public void userOnHomePage() throws Throwable {
        //TODO: IMPLEMENT LOGIN/ACCOUNT CREATION STEPS AS EACH RESET WOULD REQUIRE LOGIN/ACCOUNT CREATION
        TestRunner.planetariumLogin.goToPlanetariumLogin();
    }

    @Given("The User is on the Login Page")
    @Given("User is on the Login Page")
    public void userOnLoginPage() throws Throwable {
        TestRunner.planetariumLogin.goToPlanetariumLogin();
    }

    @When("User clicks Planet from the dropdown")
    public void userClicksPlanetFromTheDropdown() throws Throwable {
        Select select = new Select(TestRunner.driver.findElement(By.id("locationSelect")));
        select.selectByVisibleText("Planet");
    }

//    @When("User selects moon from Dropdown")
//    public void userClicksMoonDropdownOption() throws Throwable {
//        Select select = new Select(driver.findElement(By.id("locationSelect")));
//        select.selectByVisibleText("Moon");
//    }
}
