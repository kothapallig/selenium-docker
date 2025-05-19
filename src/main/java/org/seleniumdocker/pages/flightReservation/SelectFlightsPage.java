package org.seleniumdocker.pages.flightReservation;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.seleniumdocker.pages.BasePage;

public class SelectFlightsPage extends BasePage {
	
	@FindBy(id="confirm-flights")
	private WebElement confirmFlightsBtn;
	
	@FindBy(name="departure-flight")
	private List<WebElement> departureRadioBtnOptions;
	
	@FindBy(name="arrival-flight")
	private List<WebElement> arrivalRadioBtnOptions;

	public SelectFlightsPage(WebDriver driver) {
		super(driver);
	}
	
	
	public void selectFlights() {
		
		int random = ThreadLocalRandom.current().nextInt(0, departureRadioBtnOptions.size());
		
		departureRadioBtnOptions.get(random).click();
		((JavascriptExecutor) driver).executeScript("arguments[0].click();", arrivalRadioBtnOptions.get(random));
	//	arrivalRadioBtnOptions.get(random).click();
	}
	
	public void confirmFlightsBtn() {
((JavascriptExecutor) driver).executeScript("arguments[0].click();", confirmFlightsBtn);
	//	confirmFlightsBtn.click();
	}
	@Override
	public boolean isPageLoaded() {
		wait.until(ExpectedConditions.visibilityOf(confirmFlightsBtn));
		return confirmFlightsBtn.isDisplayed();
	}
	
	

}
