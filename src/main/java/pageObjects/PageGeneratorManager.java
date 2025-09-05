package pageObjects;

import org.openqa.selenium.WebDriver;

public class PageGeneratorManager {

	public static OpenWeatherPageObject getOpenWeatherPageObject(WebDriver driver) {
		return new OpenWeatherPageObject(driver);
	}
}
