package org.seleniumdocker.pages.flightReservation;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.seleniumdocker.pages.BasePage;

public class FlightSearchPage extends BasePage{
	
	@FindBy(id="passengers")
	private WebElement passengersDrpDwn;
	
	@FindBy(id="search-flights")
	private WebElement searchFlightsBtn;


	public FlightSearchPage(WebDriver driver) {
		super(driver);
	}
	
	public void selectPassengers(String noPassenger) {
		Select passenger = new Select(passengersDrpDwn);
		passenger.selectByValue(noPassenger);
	}
	
	public void searchFlightBtn() {
		((JavascriptExecutor) driver).executeScript("arguments[0].click();", searchFlightsBtn);
	//	searchFlightsBtn.click();
	}

	@Override
	public boolean isPageLoaded() {
		wait.until(ExpectedConditions.visibilityOf(passengersDrpDwn));
		return passengersDrpDwn.isDisplayed();
	}

}
