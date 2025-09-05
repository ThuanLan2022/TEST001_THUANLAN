package pageObjects;

import autolibraries.thuanLanLibs;
import pageUIs.WeatherPageUI;

import java.time.ZonedDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

import org.openqa.selenium.WebDriver;

/**
 * @author THUANNT
 */
public class OpenWeatherPageObject extends thuanLanLibs {

	WebDriver driver;

	public OpenWeatherPageObject(WebDriver driver) {
		this.driver = driver;
	}

	public void searchCity(String city) {
		sendKeyToElement(driver, WeatherPageUI.SEARCH_TXT, city.trim());
		clickToElement(driver, WeatherPageUI.SEARCH_BTN);
		clickToElement(driver, WeatherPageUI.CITY_RESULT_DROP);
//		sleepInSecond(2);
		waitForElementInvisible(driver, WeatherPageUI.CITY_RESULT_DROP);
	}

	public String getCurrentDateTime(String cityName) {
		ZoneId laZone = ZoneId.of(cityName);
		ZonedDateTime laNow = ZonedDateTime.now(laZone);

		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM d, h:mma", Locale.ENGLISH);
		String formattedDate = laNow.format(formatter).toLowerCase();
		
		if (!formattedDate.isEmpty()) {
			formattedDate = formattedDate.substring(0, 1).toUpperCase() + formattedDate.substring(1);
		}
		return formattedDate;
	}

}
