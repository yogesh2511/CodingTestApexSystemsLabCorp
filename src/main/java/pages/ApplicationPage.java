package pages;

import org.testng.Assert;

import utilities.AliasUtility;
import utilities.LoggerUtils;
import utilities.WebElementHelper;

public class ApplicationPage extends BasePage {

	public ApplicationPage() {
		super();
	}

	public void pageTitleVerification(String title) {
		WebElementHelper.switchToWindowWithRedirect(driver, title);
		Assert.assertEquals(driver.getTitle(), title);
	}

	public void backToJobDetails() {
		LoggerUtils.info("Switching back to Job Details page.");
		String parentWindow = AliasUtility.getValue("parentWindow");
		WebElementHelper.switchToParentWindow(driver);
		driver.navigate().back();
	}
}
