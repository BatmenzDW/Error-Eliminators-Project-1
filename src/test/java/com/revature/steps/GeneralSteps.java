package com.revature.steps;

import com.revature.TestRunner;
import io.cucumber.java.en.Given;

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
}
