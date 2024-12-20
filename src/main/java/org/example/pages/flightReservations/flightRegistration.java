package org.example.pages.flightReservations;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class flightRegistration {
    private WebDriver driver;

    @FindBy(id = "firstName")
    private WebElement firstNameInput;

    @FindBy(id = "lastName")
    private WebElement lastNameInput;

    @FindBy(id = "email")
    private WebElement emailInput;

    @FindBy(id = "password")
    private WebElement passwordInput;

    @FindBy(name = "street")
    private WebElement streetInput;

    @FindBy(name = "city")
    private WebElement cityInput;

    @FindBy(name = "zip")
    private WebElement zipInput;

    @FindBy(id="register-btn")
    private WebElement registerButton;

    //constructor
    public flightRegistration(WebDriver driver)
    {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    //to get the base URL:
    public void goTo(String url)
    {
        this.driver.get(url);
    }

    //enter details defined above
    public void enterUserDetails(String firstName, String lastName)
    {
        this.firstNameInput.sendKeys(firstName);
        this.lastNameInput.sendKeys(lastName);
    }

    public void enterUserCredentials(String email, String password)
    {
        this.emailInput.sendKeys(email);
        this.passwordInput.sendKeys(password);
    }
    public void enterUserAddress(String street, String city, String zip)
    {
        this.streetInput.sendKeys(street);
        this.cityInput.sendKeys(city);
        this.zipInput.sendKeys(zip);
    }

    public void register()
    {
        this.registerButton.click();
    }
}
