package com.revature.poms;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public class PlanetariumHome {

    private WebDriver driver;

    public PlanetariumHome(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void goToPlanetariumHome()
    {
        driver.get("http://localhost:8080/");
    }

    public void clickCreateAccount()
    {
        driver.findElement(By.linkText("Create an Account")).click();
    }

    public String getAlertText()
    {
        return driver.switchTo().alert().getText();
    }
}
