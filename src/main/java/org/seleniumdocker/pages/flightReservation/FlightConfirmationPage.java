package org.seleniumdocker.pages.flightReservation;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.seleniumdocker.pages.BasePage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FlightConfirmationPage extends BasePage {
	
	private static final Logger log = LoggerFactory.getLogger(FlightConfirmationPage.class);
	
	@FindBy(xpath = "//div[normalize-space(text())='Flight Confirmation #']")
	private WebElement flightConfirmationNumber;
	
	@FindBy(css = "#flights-confirmation-section .card-body .row:nth-child(3) .col:nth-child(2)")
    private WebElement totalPriceElement;

	public FlightConfirmationPage(WebDriver driver) {
		super(driver);
	}
	
	public String getPrice(){
        String confirmation = this.flightConfirmationNumber.getText();
        String price = this.totalPriceElement.getText();
        log.info("Flight confirmation# : {}", confirmation);
        log.info("Total price : {}", price);
        return price;
    }

	@Override
	public boolean isPageLoaded() {
		wait.until(ExpectedConditions.visibilityOf(totalPriceElement));
		return totalPriceElement.isDisplayed();
	}

}
