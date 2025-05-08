package utilities;

import java.time.Duration;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class DriverManager {

	private static ThreadLocal<WebDriver> driver = new ThreadLocal<>();

	private DriverManager() {
		// Private constructor to prevent instantiation
	}

	public static void initDriver() {
		String browser = Config.getBrowser();
		WebDriver localDriver = null;
		switch (browser.toLowerCase()) {
		case "chrome":
			localDriver = createChromeDriver();
			break;
		case "firefox":
			localDriver = new FirefoxDriver();
			break;
		case "edge":
			localDriver = new EdgeDriver();
			break;
		default:
			throw new IllegalArgumentException("Unsupported browser: " + browser);
		}
		localDriver.manage().window().maximize();
		localDriver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(30));
		localDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
		driver.set(localDriver);
	}

	private static WebDriver createChromeDriver() {
		ChromeOptions options = new ChromeOptions();
		if (Config.getHeadless().equalsIgnoreCase("true")) {
			options.addArguments("--headless");
		}
		if (Config.getNotifications().equalsIgnoreCase("true")) {

			options.addArguments("--disable-notifications");
		}
		return new ChromeDriver(options);
	}

	public static WebDriver getDriver() {
		return driver.get();
	}

	public static void quitDriver() {
		if (driver.get() != null) {
			driver.get().quit();
			driver.remove();
		}
	}
}
