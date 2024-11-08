package com.revature.poms;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class PlanetariumRegistration {
    private WebDriver driver;

    @FindBy(id = "usernameInput")
    private WebElement registerUsernameInput;

    @FindBy(id = "passwordInput")
    private WebElement registerPassInput;

    @FindBy(xpath = "//input[3]")
    private WebElement registerButton;

    public PlanetariumRegistration(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void inputUsername(String username){
        registerUsernameInput.sendKeys(username);
    }

    public void inputPassword(String password){
        registerPassInput.sendKeys(password);
    }

    public void clickRegisterButton(){
        registerButton.click();
    }

    public String getAlertText(){
        return driver.switchTo().alert().getText();
    }
}
