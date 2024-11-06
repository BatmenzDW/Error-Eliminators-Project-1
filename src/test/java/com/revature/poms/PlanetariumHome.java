package com.revature.poms;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public class PlanetariumHome {

    private WebDriver driver;

    public PlanetariumHome(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void goToPlanetariumHome() {driver.get("http://localhost:8080/");}
}
