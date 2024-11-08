package com.revature.poms;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import java.util.List;

public class PlanetariumHome {
    private WebDriver driver;

    @FindBy(id = "greeting")
    private WebElement greeting;

    // Other possibility for celestial drop down selection
    // private Select celestialDropDown = new Select(driver.findElement(By.id("locationSelect")));

    @FindBy(id = "locationSelect")
    private Select celestialDropDown;

    @FindBy(id = "planetNameInput")
    private WebElement createPlanetNameInput;

    @FindBy(id = "planetImageInput")
    private WebElement planetImageInput;

    @FindBy(className = "submit-button")
    private WebElement submitPlanetButton;

    @FindBy(id = "celestialTable")
    private WebElement celestialTable;

    @FindBy(id = "deleteInput")
    private WebElement deletePlanetNameInput;

    @FindBy(id = "deleteButton")
    private WebElement deletePlanetButton;

    public PlanetariumHome(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public String getHomePageGreeting(){
        return greeting.getText();
    }

    public void goToPlanetariumHome() {
        driver.get("http://localhost:8080/planetarium");
    }

    public void selectPlanet(){
        celestialDropDown.selectByVisibleText("Planet");
    }

    public void inputCreatePlanetName(String planetName){
        createPlanetNameInput.sendKeys(planetName);
    }

    public void inputPlanetImage(String planetImagePath){
        //TODO: ENSURE THAT JUST SENDING THE PLANET IMAGE PATH WILL WORK HERE
        planetImageInput.sendKeys(planetImagePath);
    }

    public void clickSubmitButton(){
        submitPlanetButton.click();
    }

    public boolean findPlanetByName(String planetName){
        List<WebElement> tableRows = celestialTable.findElements(By.tagName("tr"));

        for(int i = 1; i <= tableRows.size();){
            String rowText = tableRows.get(i).toString();

            //TODO: FIGURE OUT HOW THE TABLE ROW IS CONVERTED TO A STRING AND FORMAT APPROPRIATELY
            if(rowText.equals(planetName)){
                return true;
            }
            else{
                i++;
            }
        }
        return false;
    }

    public void inputDeletePlanetName(String planetName){
        deletePlanetNameInput.sendKeys(planetName);
    }

    public void clickDeleteButton(){
        deletePlanetButton.click();
    }

    public String getAlertText(){
        return driver.switchTo().alert().getText();
    }
}
