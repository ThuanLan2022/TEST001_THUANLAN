package cucumberOption;

import java.time.Duration;
import java.util.*;
import java.util.logging.Logger;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;

import commons.GlobalConstants;
import io.github.bonigarcia.wdm.WebDriverManager;
import io.cucumber.java.Before;
import io.cucumber.java.After;
import io.cucumber.java.Scenario;

public class Hooks {

    private static WebDriver driver;
    private static final Logger log = Logger.getLogger(Hooks.class.getName());
    private static final ThreadLocal<Scenario> currentScenario = new ThreadLocal<>();

    @Before
    public void beforeScenario(Scenario scenario) {
        currentScenario.set(scenario);
        log.info("=== Start scenario: " + scenario.getName() + " ===");
    }

    @After
    public void afterScenario() {
        Scenario scenario = currentScenario.get();
        if (scenario != null) {
            log.info("=== Finished scenario: " + scenario.getName() + " | Status: " + scenario.getStatus() + " ===");
        }
        currentScenario.remove();
    }

    public static Scenario getScenario() {
        return currentScenario.get();
    }

    // Log report + console
    public static void logToReport(String message) {
        Scenario scenario = currentScenario.get();
        if (scenario != null) {
            scenario.log(message);   // log ra Cucumber HTML report
        }
        log.info(message);          // log ra console
    }

    // Init Browser
    public synchronized static WebDriver getAndCloseBrowser() {
        String browser = System.getProperty("BROWSER");

        if (driver == null) {
            try {
                if (browser == null) {
                    browser = System.getenv("BROWSER");
                    if (browser == null) {
                        browser = "chrome";
                    }
                }

                switch (browser) {
                    case "chrome":
                        WebDriverManager.chromedriver().setup();
                        ChromeOptions options = new ChromeOptions();
                        options.addArguments("--start-maximized");
                        options.addArguments("--disable-popup-blocking");
                        options.addArguments("--disable-notifications");

                        Map<String, Object> chromePrefs = new HashMap<>();
                        chromePrefs.put("profile.default_content_settings.popups", 0);
                        chromePrefs.put("download.default_directory", GlobalConstants.DOWNLOAD_FOLDER);
                        options.setExperimentalOption("prefs", chromePrefs);

                        driver = new ChromeDriver(options);
                        break;

                    case "edge":
                        WebDriverManager.edgedriver().setup();
                        EdgeOptions edgeOptions = new EdgeOptions();
                        edgeOptions.addArguments("--disable-notifications");
                        edgeOptions.addArguments("--disable-popup-blocking");
                        driver = new EdgeDriver(edgeOptions);
                        break;

                    default:
                        throw new RuntimeException("Please choose your browser!");
                }
            } finally {
                Runtime.getRuntime().addShutdownHook(new Thread(new BrowserCleanup()));
            }

            driver.get(GlobalConstants.UAT_URL);
            driver.manage().window().maximize();
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(GlobalConstants.LONG_TIMEOUT));
            log.info("------------- Started the browser -------------");
        }
        return driver;
    }

    public static void closeBrowserAndDriver() {
        try {
            if (driver != null) {
                driver.manage().deleteAllCookies();
                driver.quit();
                driver = null;
            }
        } catch (Exception e) {
            log.info("Error closing browser: " + e.getMessage());
        }
    }

    private static class BrowserCleanup implements Runnable {
        @Override
        public void run() {
            closeBrowserAndDriver();
        }
    }
}
