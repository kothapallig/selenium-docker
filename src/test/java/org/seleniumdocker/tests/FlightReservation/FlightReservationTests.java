package org.seleniumdocker.tests.FlightReservation;


import org.seleniumdocker.pages.flightReservation.FlightConfirmationPage;
import org.seleniumdocker.pages.flightReservation.FlightSearchPage;
import org.seleniumdocker.pages.flightReservation.RegistrationConfirmationPage;
import org.seleniumdocker.pages.flightReservation.RegistrationPage;
import org.seleniumdocker.pages.flightReservation.SelectFlightsPage;
import org.seleniumdocker.tests.BaseTest;
import org.seleniumdocker.tests.FlightReservation.model.FlightReservationTestData;
import org.seleniumdocker.utils.Config;
import org.seleniumdocker.utils.Constants;
import org.seleniumdocker.utils.JsonUtility;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class FlightReservationTests extends BaseTest{
	
	private FlightReservationTestData testData;

	
	@BeforeTest
    @Parameters("testDataPath")
    public void setParameters(String testDataPath){
        this.testData = JsonUtility.getTestData(testDataPath, FlightReservationTestData.class);
    }
	
	
	@Test
	public void userRegistrationTest() {
		RegistrationPage register = new RegistrationPage(driver);
		register.goTo(Config.get(Constants.APPURL));
		Assert.assertTrue(register.isPageLoaded());
		register.enterUserDetails(testData.firstName(), testData.lastName());
		register.enterUserCredentials(testData.email(), testData.password());
		register.enterAddress(testData.street(), testData.city(), testData.zip());
		register.register();
		
	}
	
	@Test(dependsOnMethods = "userRegistrationTest")
	public void registrationConfirmationTest() {
		RegistrationConfirmationPage regConfirm = new RegistrationConfirmationPage(driver);
		Assert.assertTrue(regConfirm.isPageLoaded());
		regConfirm.goToFlightSearch();		
	}
	
	@Test(dependsOnMethods = "registrationConfirmationTest")
	public void flightSearchTest() {
		FlightSearchPage fSearch = new FlightSearchPage(driver);
		Assert.assertTrue(fSearch.isPageLoaded());
		fSearch.selectPassengers(testData.passengersCount());
		fSearch.searchFlightBtn();
	}
	
	@Test(dependsOnMethods = "flightSearchTest")
	public void selectFlightsTest() {
		SelectFlightsPage fSearch = new SelectFlightsPage(driver);
		Assert.assertTrue(fSearch.isPageLoaded());
		fSearch.selectFlights();
		fSearch.confirmFlightsBtn();
	}
	
	@Test(dependsOnMethods = "selectFlightsTest")
	public void flightConfirmationTest() {
		FlightConfirmationPage fSearch = new FlightConfirmationPage(driver);
		Assert.assertTrue(fSearch.isPageLoaded());
		Assert.assertEquals(fSearch.getPrice(), testData.expectedPrice());
	}
	
}
