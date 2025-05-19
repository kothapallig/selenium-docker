package org.seleniumdocker.pages.flightReservation;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.seleniumdocker.pages.BasePage;

public class RegistrationConfirmationPage extends BasePage {
	
	@FindBy(id="go-to-flights-search")
	private WebElement searchFlightsBtn;
	
	public RegistrationConfirmationPage(WebDriver driver) {
		super(driver);
	}

	public void goToFlightSearch() {
		searchFlightsBtn.click();
	}

	@Override
	public boolean isPageLoaded() {
		wait.until(ExpectedConditions.visibilityOf(searchFlightsBtn));
		return searchFlightsBtn.isDisplayed();
	}
}
