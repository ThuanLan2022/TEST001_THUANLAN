package pageUIs;

public class WeatherPageUI {

	public static final String SEARCH_TXT = "//input[@placeholder='Search city']";
	public static final String SEARCH_BTN = "//button[text()='Search']";
	public static final String CITY_BOX_DROP = "//div[@class='search-block']";
	public static final String CITY_RESULT_DROP = "//div[@class='search-block']//ul//span[contains(text(),'Los Angeles, US')]";
	public static final String CITY_HEADER = "//div[@class='current-container mobile-padding']//h2";
	public static final String DATE_LABEL = "//div[@class='current-container mobile-padding']//span[@class='orange-text']";
	public static final String TEMP_LABEL = "//div[@class='current-container mobile-padding']//span[@class='heading']";
}
