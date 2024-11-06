package com.revature.steps;

import io.cucumber.java.en.Given;

public class UserRegistrationSteps {

    @Given("^User is on the Login Page$")
    public void userOnLoginPage() throws Throwable {
        System.out.println("Login Page");
    }


}
