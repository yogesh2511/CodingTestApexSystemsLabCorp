package pages;

import utilities.WaitUtils;
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
        searchButton.click();
    }

    public void selectFirstPosition() {
        By firstPositionLocator = By.xpath("(//li[@class='jobs-list-item'])[1]");
        WaitUtils.waitForElementClickable(driver,firstPositionLocator);
        driver.findElement(firstPositionLocator).click();
    }
}
