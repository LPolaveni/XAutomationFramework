package utils;


import com.x.webAutomation.common.Log4jUtil;
import com.x.webAutomation.controllers.DriverManager;
import com.x.webAutomation.controllers.SetUpTest;
import org.apache.log4j.Logger;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.UncheckedIOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.List;
import java.util.*;

import static org.testng.Assert.assertTrue;




public class CommonUtilities extends SetUpTest {

	protected WebDriver driverInstance = DriverManager.getDriver();
	Logger log = Log4jUtil.loadLogger(CommonUtilities.class);
	private String scenario;
	private String directory;
	private String strImage = "";


	public static final String[] VISA_PREFIX_LIST = new String[] { "4539", "4556", "4916", "4532", "4929", "40240071",
			"4485", "4716", "4" };

	public static final String[] MASTERCARD_PREFIX_LIST = new String[] { "51", "52", "53", "54", "55" };

	public static final String[] AMEX_PREFIX_LIST = new String[] { "34", "37" };

	public static final String[] DISCOVER_PREFIX_LIST = new String[] { "6011" };

	public static final String[] DINERS_PREFIX_LIST = new String[] { "300", "301", "302", "303", "36", "38" };

	public static final String[] ENROUTE_PREFIX_LIST = new String[] { "2014", "2149" };

	public static final String[] JCB_16_PREFIX_LIST = new String[] { "3088", "3096", "3112", "3158", "3337", "3528" };

	public static final String[] JCB_15_PREFIX_LIST = new String[] { "2100", "1800" };

	public static final String[] VOYAGER_PREFIX_LIST = new String[] { "8699" };


	public CommonUtilities(String scenario) {
		this.scenario = scenario;
	}

	public WebDriverWait driverWait() {
		WebDriverWait explicitWait = new WebDriverWait(driverInstance, Duration.ofSeconds(2));
		return explicitWait;
	}

	public boolean isElementPresent(WebElement ele) {
		try {
			driverWait().until(ExpectedConditions.visibilityOf(ele));
		} catch (Exception e) {
			return false;
		}
		return true;
	}

	public boolean click(WebElement locator) throws Exception {
		boolean blnVal = false;
		try {
			driverWait().until(ExpectedConditions.elementToBeClickable(locator));
			if (locator.isDisplayed()) {
				locator.click();
				log.info("Clicked on element: " + locator);
				blnVal = true;
			}

		} catch (Exception e) {
			log.error("Unable to click on element: " + locator);
			e.printStackTrace();
			assertTrue(blnVal);
			throw (e);
		}
		return blnVal;
	}

	public boolean type(WebElement locator, String message, String strData) throws Exception {
		boolean blnVal = false;
		try {
			driverWait().until(ExpectedConditions.elementToBeClickable(locator));
			if (locator.isDisplayed()) {
				if (locator.getText() != null || !locator.getText().equalsIgnoreCase("")) {
					locator.clear();
				}
				locator.sendKeys(strData);
				log.info("Text entered in the textbox is: " + strData);
				blnVal = true;

			}
		} catch (RuntimeException localRuntimeException) {
			log.error("Unable to Enter the value in the Textbox :" + locator);
			throw (localRuntimeException);
		}
		return blnVal;

	}

	public boolean type(WebElement locator, String message, String strData, boolean masked) throws Exception {
		boolean blnVal = true;
		try {
			driverWait().until(ExpectedConditions.elementToBeClickable(locator));
			if (locator.isDisplayed()) {
				if (locator.getText() != null || !locator.getText().equalsIgnoreCase("")) {
					locator.clear();
				}
				locator.sendKeys(strData);
				if (masked)
					strData = getMasked(strData);
				log.info("Text entered in the textbox is: " + strData);
				blnVal = true;

			}
		} catch (RuntimeException localRuntimeException) {
			log.error("Unable to Enter the value in the Textbox :" + locator);
			throw (localRuntimeException);
		}
		return blnVal;

	}

	public static String getMasked(String data) {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < data.length(); i++)
			sb.append('*');
		return sb.toString();
	}


	public boolean JSClick(WebElement locator) throws Exception {
		boolean flag = false;
		try {
			driverWait().until(ExpectedConditions.elementToBeClickable(locator));
			if (locator.isDisplayed()) {
				JavascriptExecutor executor = (JavascriptExecutor) driverInstance;
				executor.executeScript("arguments[0].click();", locator);
				log.info("Clicked on the element using Javascript: " + locator);
				flag = true;
			}
		} catch (Exception e) {
			log.error("Error in clicking on the element using Javascript: " + locator);
			e.printStackTrace();
			assertTrue(flag);
			throw (e);

		}
		return flag;

	}

	public boolean JSWebElementClick(WebElement locator) throws Exception {
		boolean flag = false;
		try {
			JavascriptExecutor executor = (JavascriptExecutor) driverInstance;
			executor.executeScript("arguments[0].click();", locator);
			log.info("Clicked on the element using Javascript: " + locator);
			flag = true;
		} catch (Exception e) {
			log.error("Error in clicking on the element using Javascript: " + locator);
			assertTrue(flag);
			throw (e);
		}
		return flag;
	}

	public void pageRefresh() throws Exception {
		checkPageLoad();
		driverInstance.navigate().refresh();
		Thread.sleep(2000);
	}
	
	public void verticalScroll(int x, int y) throws Exception{
		JavascriptExecutor js = (JavascriptExecutor) driverInstance;
		js.executeScript("window.scrollTo({ left: "+x+", top: "+y+", behavior: \"smooth\" });");
	}

	public void checkPageLoad() {
		JavascriptExecutor js = (JavascriptExecutor) driverInstance;
		for (int i = 0; i < 60; i++) {
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
			}
			if ((js.executeScript("return document.readyState").toString().equals("complete"))
					/*&& ((Long) js.executeScript("return jQuery.active") == 0)*/) {
				break;
			}
		}
	}

	/*
	 * This method is used to get the random email id
	 * @version: 1.0
	 * @since: 08/16/2018
	 * @param: NA
	 * @return: String
	 */
	public String randomEmailID() {
		String strVal = new SimpleDateFormat("yyMMddhhmmssMs").format(new Date());
		return  /*strBrand +*/ "callhealth"+System.nanoTime() + "@yopmail.com";
	}
	
	public String randomNumber() {
		String strVal = new SimpleDateFormat("mmddhhmmss").format(new Date());
		return  strVal;
	}

	public boolean selectFromDDByText(WebElement locator, String textToSelect) throws Exception {
		boolean blnVal = false;

		try {
			driverWait().until(ExpectedConditions.elementToBeClickable(locator));
			if (locator.isDisplayed()) {
				Select selectDD = new Select(locator);
				selectDD.selectByVisibleText(textToSelect);
				log.info("Selected : " + textToSelect);
				blnVal = true;
			}

		} catch (Exception e) {
			log.error("Unable to select the element: " + locator);
			assertTrue(blnVal);
			throw (e);

		}
		return blnVal;
	}

	public boolean waitForElement(WebElement locator) throws Exception {
		boolean blnVal = false;
		try {
			driverWait().until(ExpectedConditions.elementToBeClickable(locator));
			JavascriptExecutor js = (JavascriptExecutor) driverInstance;
			if (locator.isDisplayed()) {
				if (js.executeScript("return document.readyState").toString().equals("complete")) {
					log.info("Element found: " + locator);
					blnVal = true;
				}
			}
		} catch (RuntimeException localRuntimeException) {
			log.error("Element not found : " + locator);
		}
		return blnVal;
	}

	public boolean waitForElementInvisibility(By locator) throws Exception {
		boolean blnVal = false;
		try {
			if (driverWait().until(ExpectedConditions.invisibilityOfElementLocated(locator))) {
				log.info("Element " + locator + "is no more visible");
			}
		} catch (RuntimeException localRuntimeException) {
			log.info("Element " + locator + "is still visible");
		}
		return blnVal;
	}

	public boolean waitForElementVisibility(WebElement locator) throws Exception {
		boolean blnVal = false;
		try {
			if (driverWait().until(ExpectedConditions.visibilityOf(locator)) != null) {
				log.info("Element " + locator + "is no more visible");
			}
		} catch (RuntimeException localRuntimeException) {
			log.info("Element " + locator + "is still visible");
		}
		return blnVal;
	}

	public void refreshPage() throws Exception {
		driverInstance.navigate().refresh();
		JavascriptExecutor js = (JavascriptExecutor) driverInstance;
		if (js.executeScript("return document.readyState").toString().equals("complete")) {
		}
		;
	}

	public void clearTxt(WebElement locator) throws Exception {
		if (!locator.getText().equals("")) {
			locator.clear();
		}
		log.info("Cleared default text in the text box");
	}


	public void switchToNewWindow(int windowNumber) throws Exception {
		ArrayList<String> tabs = new ArrayList<String>(driverInstance.getWindowHandles());
		driverInstance.switchTo().window(tabs.get(windowNumber));
	}

	public void switchToParentWindow() throws Exception {
		driverInstance.switchTo().defaultContent();
	}

	public void getWindow() throws Exception {
		driverInstance.getWindowHandle();
	}

	public boolean selectFromDDByIndex(WebElement locator, int index) throws Exception {
		boolean blnVal = false;

		try {
			driverWait().until(ExpectedConditions.elementToBeClickable(locator));
			if (locator.isDisplayed()) {
				Select selectDD = new Select(locator);
				selectDD.selectByIndex(index);
				blnVal = true;
			}

		} catch (Exception e) {
			log.error("Unable to select the element: " + locator);
			assertTrue(blnVal);
			throw (e);

		}
		return blnVal;
	}

	public boolean selectFromDDByValue(WebElement locator, String value) throws Exception {
		boolean blnVal = false;

		try {
			driverWait().until(ExpectedConditions.elementToBeClickable(locator));
			if (locator.isDisplayed()) {
				Select selectDD = new Select(locator);
				selectDD.selectByValue(value);
				blnVal = true;
			}

		} catch (Exception e) {
			log.error("Unable to select the element: " + locator);
			assertTrue(blnVal);
			throw (e);

		}
		return blnVal;
	}

	public boolean selectFromDDAngularWeb(WebElement locator, String DDValue) throws Exception {
		boolean blnVal = false;
		try {
			Actions builder = new Actions(driverInstance);
			builder.moveToElement(locator).click(locator).build().perform();
			Thread.sleep(2000);
			builder.sendKeys(DDValue).build().perform();
			Thread.sleep(2000);
			builder.sendKeys(Keys.ENTER).build().perform();
			blnVal = true;
		} catch (Exception e) {
			assertTrue(blnVal);
		}
		return blnVal;

	}

	public String convertDate(String dateString, String dateForReformat) throws ParseException {
		log.info("Given date is " + dateString);
		DateFormat sdf = new SimpleDateFormat(dateForReformat);
		Date date = sdf.parse(dateString);
		log.info("Date after Reformation is : " + new SimpleDateFormat("MM/dd/yy").format(date));
		return new SimpleDateFormat("MM/dd/yy").format(date);
	}

	public void switchToTab(int tabNumber) throws Exception {
		ArrayList<String> tabs = new ArrayList<String>(driverInstance.getWindowHandles());
		driverInstance.switchTo().window(tabs.get(tabNumber));
	}

	public boolean selectFromDDAngularWeb1(WebElement locator, String DDValue) throws Exception {
		boolean blnVal = false;
		try {
			Actions builder = new Actions(driverInstance);
			builder.moveToElement(locator).click(locator).build().perform();
			Thread.sleep(2000);
			WebElement elementToBeClicked = driverInstance.findElement(By.xpath("//p[text()='" + DDValue + "']"));
			builder.moveToElement(elementToBeClicked).click(elementToBeClicked).build().perform();
			blnVal = true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return blnVal;

	}

	public boolean moveHover(WebElement locator1, WebElement locator2) throws Exception {
		boolean blnval = false;
		try {
			Actions action = new Actions(driverInstance);
			action.moveToElement(locator1).moveToElement(locator2).click(locator2).build().perform();
			blnval = true;
		} catch (Exception e) {
			assertTrue(blnval);
		}
		return blnval;
	}

	public void mouseHover(WebElement locator1, WebElement locator2, WebElement locator3, WebElement locator4)
			throws Exception {

		try {
			Actions action = new Actions(driverInstance);
			action.moveToElement(locator1).perform();
			action.moveToElement(locator2).perform();
			action.moveToElement(locator3).perform();
			action.moveToElement(locator4).click().perform();
			System.out.println("Clicked on all 4 elements");
		} catch (Exception e) {
			System.out.println("Not able to click on settings icon");

		}
	}

	public String getElementText(WebElement locator, String message) throws Exception {
		String strRetVal = null;
		try {
			driverWait().until(ExpectedConditions.elementToBeClickable(locator));
			if (locator.isDisplayed()) {
				strRetVal = locator.getText();
				log.info("Successfully captured text from " + message + ": " + strRetVal);
			}

		} catch (Exception e) {

			log.error("Unable to captured the text :" + locator);
			e.printStackTrace();
			assertTrue(false);
			throw (e);
		}
		return strRetVal;
	}

	public void dragAndDrop(By source, By destination) {
		WebElement e1 = driverInstance.findElement(source);
		WebElement e2 = driverInstance.findElement(destination);
		Actions a = new Actions(driverInstance);
		a.dragAndDrop(e1, e2).build().perform();
	}



	public synchronized static String generateRandomMin() {
		return Long.toString(System.nanoTime()).substring(0, 10);
	}

	public boolean scrollToView(WebElement locator) throws Exception {
		boolean blnval = false;
		try {
			JavascriptExecutor js = (JavascriptExecutor) driverInstance;
			js.executeScript("arguments[0].scrollIntoView();", locator);
			blnval = true;
		} catch (Exception e) {
			assertTrue(blnval);
		}
		return blnval;
	}

	public void newTabAndSwitch() {
		((JavascriptExecutor) driverInstance).executeScript("window.open();");
		ArrayList<String> tabs = new ArrayList<String>(driverInstance.getWindowHandles());
		driverInstance.switchTo().window(tabs.get(1)); // switches to new tab
	}

	public String addDaysToCurrentDate(int period, String format) {
		Calendar currentDate = Calendar.getInstance();
		currentDate.setTime(new Date());
		SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
		sdf.setTimeZone(TimeZone.getTimeZone("ET"));
		currentDate.add(Calendar.DAY_OF_MONTH, period);
		String date = sdf.format(currentDate.getTime());

		System.out.println("The generated date(Expected Date) is: " + date);
		return date;
	}

	public String addDaysToSpecifiedDate(int period, String format, String strDate) throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
		Date dt = sdf.parse(strDate);
		Calendar currentDate = Calendar.getInstance();
		currentDate.setTime(dt);
		currentDate.add(Calendar.DAY_OF_MONTH, period);
		currentDate.setTimeZone(TimeZone.getTimeZone("ET"));
		String date = sdf.format(currentDate.getTime());
		return date;
	}

	public int generateRandomDigits(int n) {
		int m = (int) Math.pow(10, n - 1);
		return m + new Random().nextInt(9 * m);
	}

	public boolean click(WebElement locator, String locatorName) throws Exception {
		boolean blnVal = false;
		try {
			driverWait().until(ExpectedConditions.elementToBeClickable(locator));
			if (locator.isDisplayed()) {
				Thread.sleep(500);
				locator.click();
				log.info("Clicked on element: " + locator);
				blnVal = true;
				Thread.sleep(1000);
			}
		} catch (Exception e) {
			log.error("Unable to click on element: " + locator);
			e.printStackTrace();
			assertTrue(blnVal);
			throw (e);
		}
		return blnVal;
	}

	public String addMonthsToCurrentDateWFM(int period, String format) {
		Calendar currentDate = Calendar.getInstance();
		currentDate.setTime(new Date());
		SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
		sdf.setTimeZone(TimeZone.getTimeZone("ET"));
		currentDate.add(Calendar.MONTH, period);
		String date = sdf.format(currentDate.getTime());

		int dueDate, dueMonth, dueYear;
		String[] dues = date.split("/");
		dueDate = Integer.parseInt(dues[1]);
		dueMonth = Integer.parseInt(dues[0]);
		dueYear = Integer.parseInt(dues[2]);
		if (dueDate > 28) {
			dueDate = 01;
			dueMonth++;
			if (dueMonth > 12) {
				dueMonth = 01;
				dueYear++;
			}
		}
		String expDueDate = (dueMonth < 10 ? "0" : "") + dueMonth + "/" + (dueDate < 10 ? "0" : "") + dueDate + "/"
				+ dueYear;
		return expDueDate;
	}

	public void moveHover(WebElement locator) throws Exception {

		Actions action = new Actions(driverInstance);
		action.moveToElement(locator).click(locator);
		action.perform();
	}

	public String getAttributeValue(WebElement locator, String message) throws Exception {
		String strRetVal = null;
		try {
			driverWait().until(ExpectedConditions.elementToBeClickable(locator));
			if (locator.isDisplayed()) {
				strRetVal = locator.getAttribute("value");
				log.info("Successfully captured text from " + message + ": " + strRetVal);
			}

		} catch (Exception e) {

			log.error("Unable to captured the text :" + locator);
			e.printStackTrace();
			throw (e);
		}
		return strRetVal;
	}

	public boolean typeAttribute(WebElement locator, String message, String strData) throws Exception {
		boolean blnVal = false;
		try {
			driverWait().until(ExpectedConditions.visibilityOf(locator));
			if (locator.isDisplayed()) {
				if (!locator.getAttribute("value").equals("")) {
					locator.clear();
				}
				Thread.sleep(5000);
				locator.clear();
				locator.sendKeys(strData);
				log.info("Text entered in the textbox is: " + strData);
				blnVal = true;
			}
		} catch (RuntimeException localRuntimeException) {
			log.error("Unable to Enter the value in the Textbox :" + locator);
			throw (localRuntimeException);
		}
		return blnVal;
	}

	public void waitForPageLoad() {
		ExpectedCondition<Boolean> expectation = new ExpectedCondition<Boolean>() {
			public Boolean apply(WebDriver driver) {
				return ((JavascriptExecutor) driverInstance).executeScript("return document.readyState")
						.equals("complete");
			}
		};
		try {
			WebDriverWait wait = new WebDriverWait(driverInstance, Duration.ofSeconds(6));
			wait.until(expectation);
		} catch (Throwable error) {
			Assert.fail("Timeout waiting for Page Load Request to complete.");
		}
	}

	public boolean cntToNxtPage(WebElement cntEleBtn, String expectedPagePhrase) throws Exception {
		String currentPageURL = driverInstance.getCurrentUrl();
		click(cntEleBtn);
		waitForPageLoad();
		if (currentPageURL.contains(expectedPagePhrase)) {
			return true;
		}
		return false;
	}

	public int getNoOfOptionsFromDropDown(WebElement locator) throws Exception {
		int numberOfOptions = 0;
		try {
			driverWait().until(ExpectedConditions.elementToBeClickable(locator));
			if (locator.isDisplayed()) {
				Select selectDD = new Select(locator);
				List<WebElement> options = selectDD.getOptions();
				numberOfOptions = options.size();
				log.info("Number of options are : " + options.size());
			}

		} catch (Exception e) {
			log.error("Unable to get the options for : " + locator);
			throw (e);
		}
		return numberOfOptions;
	}

	public boolean typeChars(WebElement locator, String message, String strData) throws Exception {
		boolean blnVal = false;
		try {
			driverWait().until(ExpectedConditions.visibilityOf(locator));
			if (locator.isDisplayed()) {
				if (locator.getText() != null || !locator.getText().equalsIgnoreCase("")) {
					locator.clear();
				}
				locator.sendKeys(Keys.chord(strData));
				log.info("Text entered in the textbox is: " + strData);
				blnVal = true;

			}
		} catch (RuntimeException localRuntimeException) {
			log.error("Unable to Enter the value in the Textbox :" + locator);
			throw (localRuntimeException);
		}
		return blnVal;

	}

	private String strrev(String str) {
		if (str == null)
			return "";
		String revstr = "";
		for (int i = str.length() - 1; i >= 0; i--) {
			revstr += str.charAt(i);
		}
		return revstr;
	}

	public void scrollToElement(int x_coordinate, int y_coordinate) {
		JavascriptExecutor javScriptExecutor = (JavascriptExecutor) driverInstance;
		javScriptExecutor.executeScript("window.scrollBy(" + x_coordinate + ", " + y_coordinate + ");");
	}

	public boolean typeAndEnter(WebElement locator, String message, String strData) throws Exception {
		boolean blnVal = false;
		try {
			driverWait().until(ExpectedConditions.visibilityOf(locator));
			if (locator.isDisplayed()) {
				if (locator.getText() != null || !locator.getText().equalsIgnoreCase("")) {
					locator.clear();
				}
				locator.sendKeys(strData + Keys.ENTER);
				log.info("Text entered in the textbox is: " + strData);
				blnVal = true;

			}
		} catch (RuntimeException localRuntimeException) {
			log.error("Unable to Enter the value in the Textbox :" + locator);
			throw (localRuntimeException);
		}
		return blnVal;

	}

	public void switchToIFrame(String frameId) {
		driverInstance.switchTo().frame(frameId);
	}

	/*
	 * This method is used to capture screenshot in page wise (As and When required)
	 * @version: 1.0
	 * @since: 07/16/2020
	 * @param: NA
	 * @return: String
	 */
	public String generatePageScreenshot() throws IOException {
		try {
			int width = 800;
			File src = ((TakesScreenshot) driverInstance).getScreenshotAs(OutputType.FILE);
			BufferedImage img = ImageIO.read(src);
			strImage = imgToBase64String(img, width, width * img.getHeight() / img.getWidth());
		} catch (IOException e) {
			e.printStackTrace();
		}
		return strImage;
	}

	/*
	 * This method is used to encode and resize the captured image
	 * @version: 1.0
	 * @since: 07/16/2020
	 * @param: RenderedImage, Integer, Integer
	 * @return: String
	 */
	public String imgToBase64String(final RenderedImage img, int width, int height) {
		final ByteArrayOutputStream byteStream = new ByteArrayOutputStream();
		try {
			Image tmp = ((Image) img).getScaledInstance(width, height, Image.SCALE_SMOOTH);
			BufferedImage resizedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
			Graphics2D g2d = resizedImage.createGraphics();
			g2d.drawImage(tmp, 0, 0, null);
			g2d.dispose();
			ImageIO.write(resizedImage, "png", java.util.Base64.getEncoder().wrap(byteStream));
			return byteStream.toString(StandardCharsets.ISO_8859_1.name());
		} catch (final IOException ioe) {
			throw new UncheckedIOException(ioe);
		}
	}

	/*
	 * This method is used to take screenshot at page level
	 * @version: 1.0
	 * @since: 07/28/2020
	 * @param: String
	 * @return:
	 */

	/*
	 * This method is used to click on the element using Javascript
	 * @version: 1.0
	 * @since: 06/03/2018
	 * @param: By
	 * @return: NA
	 */
	public WebDriverWait elementWait() {
		WebDriverWait elementWait = new WebDriverWait(driverInstance, Duration.ofSeconds(2));
		return elementWait;
	}

	public String getAttributehref(WebElement locator, String message) throws Exception {
		String strRetVal = null;
		try {
			driverWait().until(ExpectedConditions.elementToBeClickable(locator));
			if (locator.isDisplayed()) {
				strRetVal = locator.getAttribute("href");
			}
		} catch (Exception e) {
			log.error("UNABLE TO CAPTURE HREF ATTRIBUTE :" + locator);
			e.printStackTrace();
			throw (e);
		}
		return strRetVal;
	}

	public List<String> getListOfAttributeshref(List<WebElement> locators, String message) throws Exception {
		List<String> hrefAttributes = new ArrayList<String>();

		try {
			for (int i = 0; i < locators.size(); i++) {
				if (scrollToView(locators.get(i))) {
					hrefAttributes.add(getAttributehref(locators.get(i), message));
				}
			}
		} catch (Exception e) {
			log.error("Unable to capture the href:" + locators);
			e.printStackTrace();
			throw (e);
		}
		return hrefAttributes;
	}

	public List<String> getListOfElementsText(List<WebElement> locators, String message) {
		List<String> textValues = new ArrayList<String>();
		try {
			for (int i = 0; i < locators.size(); i++) {
				if (scrollToView(locators.get(i))) {
					textValues.add(getElementText(locators.get(i), message));
				}
			}
		} catch (Exception e) {
			log.error(e);
			e.printStackTrace();
			assertTrue(false);
		}
		return textValues;
	}
	/**
	 * METHOD DESCRIPTION HERE
	 * @param - INPUT PARAM INFO / REMOVE IF NO INPUT PARAMS
	 * @return - RETURN VALUES INFO / REMOVE IF NO RETURN VALUES
	 */
	public boolean clickRecaptcha(WebElement locator1,WebElement locator2) throws Exception {
		boolean flag = false;
		try {
			driverWait().until(ExpectedConditions.visibilityOf(locator1));
			if (locator1.isDisplayed()) {
				driverInstance.switchTo().frame(locator1);
				JavascriptExecutor executor = (JavascriptExecutor) driverInstance;
				executor.executeScript("arguments[0].click();", locator2);
				log.info("Clicked on the element using Javascript: " + locator2);
				flag = true;
			}
		} catch (Exception e) {
			log.fatal("Error in clicking on the element using Javascript: " + locator2);
			e.printStackTrace();
			assertTrue(flag);
			throw (e);
		}
		return flag;
	}

	public void mouseOver(WebElement locator) throws Exception {
		Actions action = new Actions(driverInstance);
		action.moveToElement(locator);
		action.perform();
	}
	/*
	@param: List of WebElements for the images
	Check whether there are any broken device images
	 * **/

	public boolean checkForBrokenLinks(List<WebElement> imageLinks, String attribute) throws Exception{
		waitForPageLoad();
		String url=null;
		int respCode=200;
		WebElement imageEle=null;
		HttpURLConnection huc=null;
		List<WebElement> links=imageLinks;
		Iterator<WebElement> it=links.iterator();
		while(it.hasNext()){
			imageEle=it.next();
			scrollToView(imageEle);
			url=imageEle.getAttribute(attribute);
			System.out.println(url);
			if(url == null || url.isEmpty()){
				System.out.println("URL is either not configured for anchor tag or it is empty");
				continue;
			}
			try {
				Thread.sleep(1000);
				huc = (HttpURLConnection)(new URL(url).openConnection());
				huc.setRequestMethod("HEAD");
				huc.connect();
				respCode = huc.getResponseCode();
				if(respCode >= 400){
					System.out.println(url+" is a broken link");
					return false;
				}
				else{
					System.out.println(url+" is a valid link");
				}
			} catch (MalformedURLException e) {
				System.out.println("Malformed link url: "+url);
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return true;
	}


	public boolean typeWithJS(WebElement locator, String message, String strData) throws Exception {
		boolean blnVal = false;
		try {
			driverWait().until(ExpectedConditions.elementToBeClickable(locator));
			if (locator.isDisplayed()) {
				if (locator.getText() != null || !locator.getText().equalsIgnoreCase("")) {
					locator.clear();
				}
				JavascriptExecutor executor = (JavascriptExecutor) driverInstance;
				executor.executeScript("arguments[0].value='"+strData+"';", locator);
				locator.click();
				log.info("Text entered in the textbox is: " + strData);
				blnVal = true;

			}
		} catch (RuntimeException localRuntimeException) {
			log.error("Unable to Enter the value in the Textbox :" + locator);
			throw (localRuntimeException);
		}
		return blnVal;

	}
	
	public void deleteAllCookies() throws Exception{
		driverInstance.manage().deleteAllCookies();
	}
	
	
}
