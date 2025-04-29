package utilities;

import org.apache.commons.codec.binary.Base64;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.io.FileHandler;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.Markup;
import com.aventstack.extentreports.markuputils.MarkupHelper;

import pages.BasePage;

import java.io.File;
import java.io.FileInputStream;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

public class ReportHelperListener extends BasePage implements ITestListener {

	
	@Override
	public void onStart(ITestContext context) {

	}

	@Override
	public void onTestStart(ITestResult result) {
		test = extent
				.createTest(result.getTestClass().getName() + "     @TestCase : " + result.getMethod().getMethodName());

	}

	@Override
	public void onTestSuccess(ITestResult result) {
		String methodName = result.getMethod().getMethodName();
		String screenshotPath = captureScreenshot(methodName);
		String logText = "<b>" + "TEST CASE:- " + methodName.toUpperCase() + " PASSED" + "</b>";
		try {

			test.pass("<b><font color=green>" + "Screenshot of PASSED" + "</font></b><br>",
					MediaEntityBuilder.createScreenCaptureFromPath(screenshotPath).build());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			LoggerUtils.info("Exception during success reporting: " + e.getMessage());
		}

		Markup m = MarkupHelper.createLabel(logText, ExtentColor.GREEN);
		test.pass(m);

	}

	@Override
	public void onTestFailure(ITestResult result) {
		String methodName = result.getMethod().getMethodName();
		String screenshotPath = captureScreenshot(methodName);
		File imageFile = new File(screenshotPath);
		String base64String = encodeFileToBase64Binary(imageFile);
		String base64Image = "data:image/png;base64," + base64String;

		String excepionMessage = Arrays.toString(result.getThrowable().getStackTrace());
		test.fail(excepionMessage);
		String logText = "<b>" + "TEST CASE:- " + methodName.toUpperCase() + " FAILED" + "</b>";
		try {
			test.fail("<b><font color=red>" + "Screenshot of failure" + "</font></b><br>",
					MediaEntityBuilder.createScreenCaptureFromPath(screenshotPath).build());
			test.addScreenCaptureFromBase64String(base64Image, methodName + " Screenshot");

		} catch (Exception e) {
			LoggerUtils.info("Exception during failure reporting: " + e.getMessage());
			test.fail("Exception during failure reporting: " + e.getMessage());
		}
		Markup m = MarkupHelper.createLabel(logText, ExtentColor.RED);
		test.log(Status.FAIL, m);
	}

	@Override
	public void onTestSkipped(ITestResult result) {
		String methodName = result.getMethod().getMethodName();
		String screenshotPath = captureScreenshot(methodName);
		String logText = "<b>" + "Test Case:- " + methodName + " Skipped" + "</b>";
		try {

			test.skip("<b><font color=grey>" + "Screenshot of Skipped" + "</font></b><br>",
					MediaEntityBuilder.createScreenCaptureFromPath(screenshotPath).build());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			LoggerUtils.info("Exception during skipping reporting: " + e.getMessage());
			test.skip("Exception during skipping reporting: " + e.getMessage());
		}
		Markup m = MarkupHelper.createLabel(logText, ExtentColor.YELLOW);
		test.skip(m);
	}

	@Override
	public void onFinish(ITestContext context) {
		if (extent != null) {
			extent.flush();
		}
	}

	private static String encodeFileToBase64Binary(File file) {
		String encodedFile = null;
		try (FileInputStream fis = new FileInputStream(file)) {
			byte[] bytes = new byte[(int) file.length()];
			fis.read(bytes);
			encodedFile = new String(Base64.encodeBase64(bytes), "UTF-8");
		} catch (Exception e) {
			LoggerUtils.info("Base64 encoding failed: " + e.getMessage());
		}
		return encodedFile;
	}

	public static String captureScreenshot(String testName) {
		try {
			WebDriver driver = DriverManager.getDriver();
			if (driver == null) {
				LoggerUtils.info("Driver is null while capturing screenshot!");
				return null;
			}

			File srcFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);

			String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
			String screenshotPath = System.getProperty("user.dir") + "/report/screenshots/" + testName + "_" + timestamp
					+ ".png";

			File destFile = new File(screenshotPath);
			FileHandler.copy(srcFile, destFile);

			return screenshotPath;

		} catch (Exception e) {
			LoggerUtils.info("Exception during capturing screenshot: " + e.getMessage());
			return null;
		}
	}

}
