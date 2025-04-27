package utilities;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;

public class DriverManager {
	private static DriverManager instance;
	private WebDriver driver;

	private DriverManager() {
		String browser = Config.getBrowser();
		switch (browser.toLowerCase()) {
		case "chrome":
			// driver = new ChromeDriver();
			driver = ChrmeDriverManager();
			break;
		case "firefox":
			driver = new FirefoxDriver();
			break;
		default:
			throw new IllegalArgumentException("Unsupported browser: " + browser);
		}
		driver.manage().window().maximize();
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(30));
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
	}

	private WebDriver ChrmeDriverManager() {
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--disable-notifications");
		return new ChromeDriver(options);
		// return driver;
	}

	public static synchronized DriverManager getInstance() {
		if (instance == null) {
			instance = new DriverManager();
		}
		return instance;
	}

	public WebDriver getDriver() {
		return driver;
	}

	public void quitDriver() {
		if (driver != null) {
			driver.quit();
			driver = null;
			instance = null;
		}
	}
}