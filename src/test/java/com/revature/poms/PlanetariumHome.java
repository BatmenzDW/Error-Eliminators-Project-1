package com.revature.poms;

import com.revature.Setup;
import com.revature.TestRunner;
import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.Duration;

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

    public void setupTestLogin() throws Throwable
    {
        Connection conn = Setup.getConnection();

        PreparedStatement ps = conn.prepareStatement("SELECT * FROM users WHERE username = ?");
        ps.setString(1, "TestUser");
        ResultSet rs = ps.executeQuery();

        if (rs.next()) {
            conn.close();
            return;
        }

        PreparedStatement ps2 = conn.prepareStatement("INSERT INTO users (username, password) VALUES (?, ?)");
        ps2.setString(1, "TestUser");
        ps2.setString(2, "TestPassword");
        ps2.executeUpdate();
        conn.close();
    }

    public void login(String username, String password) throws Throwable
    {
        TestRunner.driver.findElement(By.id("usernameInput")).sendKeys(username);
        TestRunner.driver.findElement(By.id("passwordInput")).sendKeys(password);
        TestRunner.driver.findElement(By.xpath("//input[3]")).click();
    }



    public boolean isAlertPresent()
    {
        try
        {
            WebDriverWait wait = new WebDriverWait(TestRunner.driver, Duration.ofMillis(10));
            wait.until(ExpectedConditions.alertIsPresent());
            driver.switchTo().alert();
            return true;
        }   // try
        catch (NoAlertPresentException | TimeoutException Ex)
        {
            return false;
        }   // catch
    }
}
