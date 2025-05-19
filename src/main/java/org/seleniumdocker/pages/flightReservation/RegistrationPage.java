package org.seleniumdocker.pages.flightReservation;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.seleniumdocker.pages.BasePage;

public class RegistrationPage extends BasePage {
	
	
	@FindBy(id="firstName")
	private WebElement firstNameInput;
	
	@FindBy(id="lastName")
	private WebElement lastNameInput;
	
	@FindBy(id="email")
	private WebElement emailInput;
	
	@FindBy(id="password")
	private WebElement passwdInput;
	
	@FindBy(name="street")
	private WebElement streetInput;
	
	@FindBy(name="city")
	private WebElement cityInput;
	
	@FindBy(name="zip")
	private WebElement zipInput;
	
	@FindBy(id="register-btn")
	private WebElement registerBtn;
	
	public RegistrationPage(WebDriver driver) {
		super(driver);
	}
	
	public void goTo(String url) {
		driver.get(url);
	}
	
	public void enterUserDetails(String fname, String lname) {
		firstNameInput.sendKeys(fname);
		lastNameInput.sendKeys(lname);
	}
	
	public void enterUserCredentials(String email, String passwd) {
		emailInput.sendKeys(email);
		passwdInput.sendKeys(passwd);
	}
	
	public void enterAddress(String street, String city, String zip) {
		streetInput.sendKeys(street);
		cityInput.sendKeys(city);
		zipInput.sendKeys(zip);
	}
	
	public void register() {
		registerBtn.click();
	}

	@Override
	public boolean isPageLoaded() {
		wait.until(ExpectedConditions.visibilityOf(registerBtn));
		return registerBtn.isDisplayed();
	}

}
