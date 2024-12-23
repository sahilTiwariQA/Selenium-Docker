package flightReservation;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.example.pages.flightReservations.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class FlightReservationTest {
    private WebDriver driver;
    private String noOfPassengers;
    private String expectedPrice;

    @BeforeTest
    @Parameters({"noOfPassengers","expectedPrice"})
    public void setDriver(String noOfPassengers, String expectedPrice)
    {
        this.noOfPassengers = noOfPassengers;
        this.expectedPrice = expectedPrice;
        //driver setup
        WebDriverManager.chromedriver().setup();
        this.driver=new ChromeDriver();
        this.driver.manage().window().maximize();
    }

    @Test
    public void userRegistrationPage()
    {
        FlightRegistration flightRegistration = new FlightRegistration(driver);
        flightRegistration.goTo("https://d1uh9e7cu07ukd.cloudfront.net/selenium-docker/reservation-app/index.html");
        Assert.assertTrue(flightRegistration.isAt());

        flightRegistration.enterUserDetails("Sam","Jayraj");
        flightRegistration.enterUserCredentials("selenium@docker.com","docker");
        flightRegistration.enterUserAddress("123 Street", "Kansas", "100444");
        flightRegistration.register();
    }

    @Test(dependsOnMethods = "userRegistrationPage")
    public void registrationConfirmationTest()
    {
        RegistrationConfirmationPage registrationConfirmationPage = new RegistrationConfirmationPage(driver);
        Assert.assertTrue(registrationConfirmationPage.isAt());

        registrationConfirmationPage.goToFlightsSearch();
    }

    @Test(dependsOnMethods = "registrationConfirmationTest")
    public void flightSearchTest()
    {
        FlightSearchPage flightSearchPage = new FlightSearchPage(driver);
        Assert.assertTrue(flightSearchPage.isAt());

        flightSearchPage.selectPassengers(noOfPassengers);
        flightSearchPage.searchFlightsBtnClick();
    }

    @Test(dependsOnMethods = "flightSearchTest")
    public void flightSelectionPageTest()
    {
        FlightsSelectionPage flightsSelectionPage = new FlightsSelectionPage(driver);
        Assert.assertTrue(flightsSelectionPage.isAt());

        flightsSelectionPage.selectFlights();
        flightsSelectionPage.confirmFlight();
    }

    @Test(dependsOnMethods = "flightSelectionPageTest")
    public void flightReservationConfirmationTest()
    {
        FlightConfirmationPage flightConfirmationPage = new FlightConfirmationPage(driver);
        Assert.assertTrue(flightConfirmationPage.isAt());

        Assert.assertEquals(flightConfirmationPage.getPrice(),expectedPrice);
    }

    @AfterTest
    public void quitDriver()
    {
        this.driver.quit();
    }
}
