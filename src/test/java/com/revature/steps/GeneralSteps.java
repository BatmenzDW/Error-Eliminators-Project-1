package com.revature.steps;

import com.revature.TestRunner;
import io.cucumber.java.en.Given;

public class GeneralSteps {

    @Given("User is on the Login Page$")
    public void userOnLoginPage() throws Throwable {
        TestRunner.planetariumHome.goToPlanetariumHome();
    }
}
