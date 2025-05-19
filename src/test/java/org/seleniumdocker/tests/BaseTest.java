package org.seleniumdocker.tests;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.time.Duration;

import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.seleniumdocker.listener.TestListener;
import org.seleniumdocker.utils.Config;
import org.seleniumdocker.utils.Constants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.ITestContext;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Listeners;

import com.google.common.util.concurrent.Uninterruptibles;

import io.github.bonigarcia.wdm.WebDriverManager;

@Listeners({TestListener.class})
public abstract class BaseTest {
	
	protected WebDriver driver;
	private static final Logger log = LoggerFactory.getLogger(BaseTest.class);
	
	@BeforeSuite
	public void setUpConfig() {
		Config.initialize();
	}
	
	
	@BeforeTest
	
	public void setUpDriver(ITestContext txc) throws MalformedURLException, URISyntaxException {
		
		this.driver = Boolean.parseBoolean(Config.get(Constants.GRID_ENABLED))? getRemoteDriver(): getLocalDriver();
		txc.setAttribute(Constants.DRIVER, this.driver);	
		/*
		 * if(System.getProperty("selenium.grid.enabled").equalsIgnoreCase("true")) {
		 * this.driver = getRemoteDriver(browser); } else { this.driver =
		 * getLocalDriver(); }
		 */
	}
	
	private WebDriver getRemoteDriver() throws MalformedURLException, URISyntaxException {
		Capabilities cap = new ChromeOptions();
		
		if(Constants.FIREFOX.equalsIgnoreCase(Config.get(Constants.BROWSER))) {
			cap = new FirefoxOptions();
		} else if(Constants.EDGE.equalsIgnoreCase(Config.get(Constants.BROWSER))){
			cap = new EdgeOptions();
		}
		String urlFormat = Config.get(Constants.GRID_URL_FORMAT);
		String hubHost = Config.get(Constants.GRID_HUB_HOST);
		String url = String.format(urlFormat, hubHost);
		log.info("Grid URL ==> {}", url);
		URI uri = new URI(url);
		URL gridUrl = uri.toURL();
		return new RemoteWebDriver(gridUrl,cap);
	}
	
	private WebDriver getLocalDriver() {
		WebDriverManager.chromedriver().setup();
		return new ChromeDriver();
	}
	@AfterTest
	public void tearDown() {
		this.driver.quit();
	}
	/*
	 * @AfterMethod public void sleep() {
	 * Uninterruptibles.sleepUninterruptibly(Duration.ofSeconds(10)); }
	 */

}
