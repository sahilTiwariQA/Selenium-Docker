package org.example.pages.flightReservations;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class RegistrationConfirmationPage {

    @FindBy(id = "go-to-flights-search")
    private WebElement goToFlightsSearchButton;

    //constructor
    public RegistrationConfirmationPage(WebDriver driver)
    {
        PageFactory.initElements(driver,this);
    }

    public void goToFlightsSearch()
    {
        this.goToFlightsSearchButton.click();
    }
}
