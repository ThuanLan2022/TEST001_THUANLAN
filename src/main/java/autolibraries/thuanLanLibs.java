/*
 * Created by ThuanLan
 */

package autolibraries;

import java.time.Duration;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.util.logging.Logger;
import commons.GlobalConstants;

/**
 * @author THUANLAN
 */
public class thuanLanLibs {

	/** The action. */
	public Actions action;

	/** The logger. */
	private Logger logger = Logger.getLogger(getClass().getName());

	/**
	 * Instantiates a new mobio libs.
	 */
	public thuanLanLibs() {
	}

	/**
	 * Open a given URL with logging.
	 *
	 * @param driver   WebDriver instance
	 * @param urlValue URL to open
	 */
	public void openUrl(WebDriver driver, String urlValue) {
		try {
			driver.get(urlValue);
			logger.info("Opened URL: " + urlValue);
		} catch (Exception e) {
			logger.severe("Failed to open URL: " + urlValue + " | " + e.getMessage());
			throw e;
		}
	}

	/**
	 * Build a dynamic By locator from XPath template.
	 *
	 * @param locator XPath template (with %s placeholders)
	 * @param values  dynamic values to inject
	 * @return By locator
	 */
	public By getByXpath(String locator, String... values) {
		return By.xpath(String.format(locator, (Object[]) values));
	}

	/**
	 * Gets the text element.
	 *
	 * @param driver  the driver
	 * @param locator the locator
	 * @return the text element
	 */
	public String getTextElement(WebDriver driver, String locator) {
		return findElementByXpath(driver, locator).getText().trim();
	}

	/**
	 * Find element by id.
	 *
	 * @param driver the driver
	 * @param idLocator the id locator
	 * @return the web element
	 */
	public WebElement findElementById(WebDriver driver, String idLocator) {
		return driver.findElement(By.id(idLocator));
	}

	/**
	 * Find element by name.
	 *
	 * @param driver the driver
	 * @param nameLocator the name locator
	 * @return the web element
	 */
	public WebElement findElementByName(WebDriver driver, String nameLocator) {
		return driver.findElement(By.name(nameLocator));
	}

	/**
	 * Find element by link.
	 *
	 * @param driver the driver
	 * @param linkText the link text
	 * @return the web element
	 */
	public WebElement findElementByLink(WebDriver driver, String linkText) {
		return driver.findElement(By.linkText(linkText));
	}

	/**
	 * Find element by partial link.
	 *
	 * @param driver the driver
	 * @param partialLinkLocator the partial link locator
	 * @return the web element
	 */
	public WebElement findElementByPartialLink(WebDriver driver, String partialLinkLocator) {
		return driver.findElement(By.partialLinkText(partialLinkLocator));
	}

	/**
	 * Find element by class.
	 *
	 * @param driver the driver
	 * @param classNameLocator the class name locator
	 * @return the web element
	 */
	public WebElement findElementByClass(WebDriver driver, String classNameLocator) {
		return driver.findElement(By.className(classNameLocator));

	}

	/**
	 * Find element by XPath with explicit wait.
	 *
	 * @param driver  WebDriver instance
	 * @param locator XPath locator string
	 * @return WebElement found
	 */
	public WebElement findElementByXpath(WebDriver driver, String locator) {
		By byLocator = By.xpath(locator);
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(GlobalConstants.SHORT_TIMEOUT));

		try {
			return wait.until(ExpectedConditions.presenceOfElementLocated(byLocator));
		} catch (TimeoutException e) {
			logger.warning("Element not found within timeout using XPath: " + locator);
			throw e;
		} catch (Exception e) {
			logger.severe("Unexpected error while locating element by XPath: " + locator + " | " + e.getMessage());
			throw e;
		}
	}

	/**
	 * Click element by XPath with explicit wait for clickability.
	 *
	 * @param driver  WebDriver instance
	 * @param locator XPath locator string
	 */
	public void clickToElement(WebDriver driver, String locator) {
		By byLocator = By.xpath(locator);
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(GlobalConstants.SHORT_TIMEOUT));

		try {
			WebElement element = wait.until(ExpectedConditions.elementToBeClickable(byLocator));
			element.click();
		} catch (TimeoutException e) {
			logger.warning("Element not clickable within timeout using XPath: " + locator);
			throw e;
		} catch (Exception e) {
			logger.severe("Unexpected error while clicking element by XPath: " + locator + " | " + e.getMessage());
			throw e;
		}
	}

	/**
	 * Send keys to an element (waits until visible and enabled).
	 *
	 * @param driver  WebDriver instance
	 * @param locator XPath locator string
	 * @param value   input text
	 */
	public void sendKeyToElement(WebDriver driver, String locator, String value) {
		By byLocator = By.xpath(locator);
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(GlobalConstants.SHORT_TIMEOUT));

		try {
			WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(byLocator));
			element.clear();
			element.sendKeys(value);
		} catch (TimeoutException e) {
			logger.warning("Element not visible within timeout using XPath: " + locator);
			throw e;
		} catch (Exception e) {
			logger.severe(
					"Unexpected error while sending keys to element by XPath: " + locator + " | " + e.getMessage());
			throw e;
		}
	}

	/**
	 * Sleep in second.
	 *
	 * @param timeout the timeout
	 */
	public void sleepInSecond(long timeout) {
		try {
			Thread.sleep(timeout * 1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Wait until the element is present in the DOM (may not be visible).
	 *
	 * @param driver    WebDriver instance
	 * @param byLocator By locator of the element
	 */
	public void waitForElementPresence(WebDriver driver, By byLocator) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(GlobalConstants.SHORT_TIMEOUT));
		try {
			wait.until(ExpectedConditions.presenceOfElementLocated(byLocator));
		} catch (TimeoutException e) {
			logger.warning("Element not present within timeout: " + byLocator);
			throw e;
		} catch (Exception e) {
			logger.severe("Unexpected error while waiting for element presence: " + byLocator + " | " + e.getMessage());
			throw e;
		}
	}

	/**
	 * Wait until the element is visible on the page.
	 *
	 * @param driver    WebDriver instance
	 * @param locator the locator
	 */
	public void waitForElementVisible(WebDriver driver, String locator) {
		By byLocator = By.xpath(locator);
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(GlobalConstants.SHORT_TIMEOUT));
		try {
			wait.until(ExpectedConditions.visibilityOfElementLocated(byLocator));
		} catch (TimeoutException e) {
			logger.warning("Element not visible within timeout: " + byLocator);
			throw e;
		}
	}

	/**
	 * Wait until the element becomes invisible (hidden or removed from DOM).
	 *
	 * @param driver  WebDriver instance
	 * @param locator XPath locator string
	 */
	public void waitForElementInvisible(WebDriver driver, String locator) {
		By byLocator = By.xpath(locator);
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(GlobalConstants.LONG_TIMEOUT));

		try {
			wait.until(ExpectedConditions.invisibilityOfElementLocated(byLocator));
		} catch (TimeoutException e) {
			logger.warning("Element still visible after timeout: " + byLocator);
			throw e;
		} catch (Exception e) {
			logger.severe(
					"Unexpected error while waiting for element invisibility: " + byLocator + " | " + e.getMessage());
			throw e;
		}
	}

}
