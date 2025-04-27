package pages;

import utilities.JavaScriptUtils;
import utilities.LoggerUtils;
import utilities.WaitUtils;
import utilities.WebElementHelper;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CareersPage extends BasePage{
    
    public CareersPage() {
    	super();
    }

    private WebElement searchInput = driver.findElement(By.id("typehead"));

    private WebElement searchButton = driver.findElement(By.cssSelector("#ph-search-backdrop"));

    public void searchForPosition(String position) {
        WaitUtils.waitForElementVisible(driver, searchInput);
        searchInput.sendKeys(position);
        LoggerUtils.info("Searching for position: " + position);
        searchButton.click();
        LoggerUtils.info("Search button clicked.");
        
    }

    public void selectFirstPosition() {
    	// WebElementHelper.cookiesHandler(driver);
    	 
         LoggerUtils.info("Before Select First position handle Cookies.");
        By firstPositionLocator = By.xpath("(//li[@class='jobs-list-item'])//*[contains(text(),'Automation')]");
        LoggerUtils.info("Waiting for the first position to be clickable.");
        WaitUtils.waitForElementClickable(driver,firstPositionLocator);
        JavaScriptUtils.clickElement(driver, firstPositionLocator);
        LoggerUtils.info("First position clicked.");
      //  driver.findElement(firstPositionLocator).click();
    }
}
