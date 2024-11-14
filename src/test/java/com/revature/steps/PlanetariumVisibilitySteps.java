package com.revature.steps;

import com.revature.TestRunner;
import io.cucumber.java.en.Then;
import org.junit.Assert;

public class PlanetariumVisibilitySteps {
    @Then("User is logged in Yes, if not then they receive an alert due to invalid credential input")
    public void user_is_logged_in_Yes_if_not_then_they_receive_an_alert_due_to_invalid_credential_input() {
        Assert.assertNotEquals("Planetarium Login", TestRunner.driver.getTitle());
    }

    @Then("User is able to see their Planets and Moons added to their Planetarium : Yes")
    public void user_is_able_to_see_their_Planets_and_Moons_added_to_their_Planetarium_Yes() {
        Assert.assertEquals("Home", TestRunner.driver.getTitle());
    }

    @Then("User is logged in No, if not then they receive an alert due to invalid credential input")
    public void user_is_logged_in_No_if_not_then_they_receive_an_alert_due_to_invalid_credential_input() {
        Assert.assertTrue(TestRunner.planetariumHome.isAlertPresent());

        String result = TestRunner.planetariumHome.getAlertText();
        TestRunner.driver.switchTo().alert().accept();

        // Find that the alert statement appearing is as expected
        Assert.assertEquals("login attempt failed: please try again", result);
    }

    @Then("User is able to see their Planets and Moons added to their Planetarium : No")
    public void user_is_able_to_see_their_Planets_and_Moons_added_to_their_Planetarium_No() {
        Assert.assertEquals("Planetarium Login", TestRunner.driver.getTitle());
    }
}
