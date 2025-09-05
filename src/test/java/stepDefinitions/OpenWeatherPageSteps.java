package stepDefinitions;

import io.cucumber.java.en.*;
import pageObjects.OpenWeatherPageObject;
import pageObjects.PageGeneratorManager;
import pageUIs.WeatherPageUI;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import commons.GlobalConstants;
import cucumberOption.Hooks;

public class OpenWeatherPageSteps {
	WebDriver driver;
	OpenWeatherPageObject openWeatherPage;

	public OpenWeatherPageSteps() {
		driver = Hooks.getAndCloseBrowser();
		openWeatherPage = PageGeneratorManager.getOpenWeatherPageObject(driver);
	}

	@Given("Open OpenWeather website")
	public void openOpenWeatherWebsite() {
		openWeatherPage.openUrl(driver, GlobalConstants.UAT_URL);
	}

	@When("Search for city {string}")
	public void searchForCity(String city) {
		openWeatherPage.searchCity(city);
	}

	@Then("Should see the city name {string}")
	public void shouldSeeTheCityName(String expectedCity) {
		openWeatherPage.waitForElementVisible(driver, WeatherPageUI.CITY_HEADER);
		Assert.assertEquals(openWeatherPage.getTextElement(driver, WeatherPageUI.CITY_HEADER), expectedCity);
	}

	@Then("Should see the current date")
	public void shouldSeeTheCurrentDate() {
		String expectedDate = openWeatherPage.getCurrentDateTime("America/Los_Angeles").trim();
		expectedDate = expectedDate.split(",")[0].trim();
		String actualDate = openWeatherPage.getTextElement(driver, WeatherPageUI.DATE_LABEL).trim();
		actualDate = actualDate.split(",")[0].trim();
		Assert.assertEquals(expectedDate, actualDate);
	}

	@Then("Should see the temperature as a number")
	public void shouldSeeTheTemperatureAsANumber() {
		String tempText = openWeatherPage.getTextElement(driver, WeatherPageUI.TEMP_LABEL).replaceAll("[^0-9.-]", "")
				.trim();
		Assert.assertTrue("Temperature is not a number. Extracted: " + tempText, tempText.matches("-?\\d+(\\.\\d+)?"));
	}
}
