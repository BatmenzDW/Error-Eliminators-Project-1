package com.revature.poms;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PlanetariumLogin {

    private static final Logger log = LoggerFactory.getLogger(PlanetariumLogin.class);
    private WebDriver driver;

    @FindBy(linkText = "Create an Account")
    private WebElement createAccount;

    @FindBy(id = "usernameInput")
    private WebElement loginUsernameInput;

    @FindBy(id = "passwordInput")
    private WebElement loginPassInput;

    @FindBy(xpath = "//input[3]")
    private WebElement loginButton;

    public PlanetariumLogin(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void goToPlanetariumLogin()
    {
        driver.get("http://localhost:8080/");
    }

    public void clickCreateAccount() {
        createAccount.click();
    }

    public void inputUsername(String username){
        loginUsernameInput.sendKeys(username);
    }

    public void inputPassword(String password){
        loginPassInput.sendKeys(password);
    }

    public void clickLoginButton(){
        loginButton.click();
    }

    public String getAlertText()
    {
        return driver.switchTo().alert().getText();
    }
}
