package genericlibrary;

 

import java.io.BufferedReader;

import java.io.FileReader;

import java.io.IOException;

import java.text.SimpleDateFormat;

import java.time.Duration;

import java.util.Calendar;

import java.util.Date;

import java.util.List;

 

import org.openqa.selenium.*;

import org.openqa.selenium.interactions.Actions;

import org.openqa.selenium.support.PageFactory;

import org.openqa.selenium.support.ui.ExpectedConditions;

import org.openqa.selenium.support.ui.FluentWait;

 

import com.aventstack.extentreports.MediaEntityBuilder;

import com.aventstack.extentreports.Status;

import com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter;

 

import util.DriverFactory;

 

public class CommonMethods extends DriverFactory {

 

              // WebDriver getDriver();

             

              public CommonMethods() throws IOException {

              //           this.getDriver() = getDriver();

                             PageFactory.initElements(getDriver(), this);

              }

 

              public void launchURL(String env) throws Throwable {

 

                             String url = "";

                             switch (env) {

                             case "APP":

                                           switch (((String) propConfigurationFile.get("typeOfenvironment")).toUpperCase()) {

                                           case "QA":

 

                                                          url = (String) propConfigurationFile.get("url_APP_qa");

                                                          break;

 

                                           case "DEV":

 

                                                          url = (String) propConfigurationFile.get("url_APP_dev");

                                                          break;

 

                                           case "UAT":

 

                                                          url = (String) propConfigurationFile.get("url_APP_uat");

                                                          break;

                                           }

 

                                           break;

 

                            

                             }

                             getDriver().get(url);

                             getDriver().navigate().refresh();

 

              }

 

              /* wait methods */

              public static void waitForWebElementToBeVisibleFor(WebElement element) {

                            FluentWait<WebDriver> fluentWait = new FluentWait<>(getDriver()).withTimeout(Duration.ofSeconds(20))

                                           .pollingEvery(Duration.ofSeconds(2)).ignoring(StaleElementReferenceException.class)

                                                          .ignoring(NoSuchElementException.class);

              fluentWait.until(ExpectedConditions.refreshed(ExpectedConditions.visibilityOf(element)));

              }

 

              public static void waitForWebElementToBeVisibilityOf(String element) {

                             FluentWait<WebDriver> fluentWait = new FluentWait<>(getDriver()).withTimeout(Duration.ofSeconds(30))

                                           .pollingEvery(Duration.ofSeconds(2)).ignoring(StaleElementReferenceException.class)

                                                          .ignoring(NoSuchElementException.class);

                             fluentWait

                                           .until(ExpectedConditions.refreshed(ExpectedConditions.visibilityOfElementLocated(By.xpath(element))));

              }

 

              public static void waitForWebElementToBeInvisible(WebElement element) {

                             FluentWait<WebDriver> fluentWait = new FluentWait<>(getDriver()).withTimeout(Duration.ofSeconds(20))

                                           .pollingEvery(Duration.ofSeconds(2)).ignoring(StaleElementReferenceException.class)

                                                          .ignoring(NoSuchElementException.class);

              fluentWait.until(ExpectedConditions.refreshed(ExpectedConditions.invisibilityOf(element)));

              }

 

              public static void waitForWebElementToBeInvisible(String element) {

                             FluentWait<WebDriver> fluentWait = new FluentWait<>(getDriver()).withTimeout(Duration.ofSeconds(120))

                                           .pollingEvery(Duration.ofSeconds(2)).ignoring(StaleElementReferenceException.class)

                                                          .ignoring(NoSuchElementException.class);

                             fluentWait.until(

                                           ExpectedConditions.refreshed(ExpectedConditions.invisibilityOfElementLocated(By.xpath(element))));

              }

 

              public static void waitForPresenceOfWebElement(String element) {

                            

                                           FluentWait<WebDriver> fluentWait = new FluentWait<>(getDriver()).withTimeout(Duration.ofSeconds(30))

                                           .pollingEvery(Duration.ofSeconds(1)).ignoring(StaleElementReferenceException.class)

                                                          .ignoring(NoSuchElementException.class);

              fluentWait.until(ExpectedConditions.refreshed(ExpectedConditions.presenceOfElementLocated(By.xpath(element))));

                                          

                             }

             

              public static void waitForPresenceOfWebElements(List<WebElement> elements) {

                             FluentWait<WebDriver> fluentWait = new FluentWait<>(getDriver()).withTimeout(Duration.ofSeconds(150))

                                           .pollingEvery(Duration.ofSeconds(1)).ignoring(StaleElementReferenceException.class)

                                                          .ignoring(NoSuchElementException.class);

              fluentWait.until(ExpectedConditions.refreshed(ExpectedConditions.visibilityOfAllElements(elements)));

              }

 

              public static void waitForVisibilityOfAllWebElement(String element) {

                             FluentWait<WebDriver> fluentWait = new FluentWait<>(getDriver()).withTimeout(Duration.ofSeconds(20))

                                           .pollingEvery(Duration.ofSeconds(2)).ignoring(StaleElementReferenceException.class)

                                                          .ignoring(NoSuchElementException.class);

                             fluentWait.until(

                                           ExpectedConditions.refreshed(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath(element))));

              }

 

              public static void scrollIntoView(WebElement element) {

                             try {

                                           waitForWebElementToBeVisibleFor(element);

                                           Actions actions = new Actions(getDriver());

                                           actions.moveToElement(element);

                                           CommonAppianMethods.highlightElementbeforeAction(element, getDriver());

 

                                           actions.perform();

                             } catch (Exception e) {

                                           waitForWebElementToBeVisibleFor(element);

                                           ((JavascriptExecutor) getDriver()).executeScript("arguments[0].scrollIntoView(true);", element);

                             }

              }

 

              public static WebElement detectWebElement(String webElementXpath) {

                             WebElement element;

                             waitForPresenceOfWebElement(webElementXpath);

 

                             if (webElementXpath.contains("[")) {

                                           element = getDriver().findElement(By.xpath(webElementXpath));

 

                             } else {

                                           element = getDriver().findElement(By.id(webElementXpath));

                             }

                            CommonAppianMethods.highlightElementbeforeAction(element, getDriver());

                             return element;

              }

 

              public static List<WebElement> returnListOfWebElements(String elementXpath) {

                             List<WebElement> elements;

                             waitForVisibilityOfAllWebElement(elementXpath);

                             elements = getDriver().findElements(By.xpath(elementXpath));

                             return elements;

              }

 

              public static void clickElement(WebElement element) {

                             try {

                                           scrollIntoView(element);

                                           element.click();

                             } catch (Exception e) {

                                           scrollIntoView(element);

                                           ((JavascriptExecutor) getDriver()).executeScript("arguments[0].click();", element);

                             }

              }

 

              public static void clickElement(String elementXpath) {

                             WebElement element = detectWebElement(elementXpath);

                             try {

                                           scrollIntoView(element);

                                           element.click();

                             } catch (Exception e) {

                                           scrollIntoView(element);

                                           ((JavascriptExecutor) getDriver()).executeScript("arguments[0].click();", element);

                             }

              }

             

              public static void doubleClickElement(String elementXpath) {

                             WebElement element = detectWebElement(elementXpath);

                             try {

                                           scrollIntoView(element);

                                           element.click();

                             } catch (Exception e) {

                                           scrollIntoView(element);

                                           ((JavascriptExecutor) getDriver()).executeScript("arguments[0].doubleClick();", element);

                             }

              }

 

              public static boolean isElementDisplayed(WebElement element, String... flag) {

                             boolean flag1 = false;

 

                             try {

                                           waitForWebElementToBeVisibleFor(element);

                                           if (flag.length > 0) {

                                                          scrollIntoView(element);

                                           }

                                           flag1 = element.isDisplayed();

                             } catch (Exception ignored) {

                             }

 

                             return flag1;

              }

 

              public static boolean isElementDisplayed(String elementXpath, String... flag) {

                             boolean flag1 = false;

                             try {

                                           WebElement element = detectWebElement(elementXpath);

                                           if (flag.length > 0) {

                                                          scrollIntoView(element);

                                           }

                                           flag1 = element.isDisplayed();

                             } catch (Exception ignored) {

                             }

 

                             return flag1;

              }

 

              public static String getElementText(WebElement element) {

                             String text = "";

                             waitForWebElementToBeVisibleFor(element);

                             scrollIntoView(element);

                             try {

                                           text = element.getText();

                             } catch (Exception ignored) {

                             }

                             return text;

              }

 

              public static String getElementText(String elementXpath) {

                             String text = "";

                             WebElement element = detectWebElement(elementXpath);

                             scrollIntoView(element);

                             try {

                                           text = element.getText();

                             } catch (Exception ignored) {

                             }

                             return text;

              }

 

              public static String getElementAttribute(String webElementXpath, String string) {

                             WebElement element = detectWebElement(webElementXpath);

                             scrollIntoView(element);

                             return element.getAttribute(string);

              }

 

              public static void sendElementText(String elementXpath, String inputText, String... flag) {

                             WebElement element = detectWebElement(elementXpath);

                             scrollIntoView(element);

                             try {

                                           element.clear();

                                           element.click();

                                           if (flag.length > 0) {

                                                          element.sendKeys(inputText, Keys.ENTER);

                                           } else {

                                                          element.sendKeys(inputText);

                                           }

                             } catch (Exception e) {

                                           JavascriptExecutor jse = (JavascriptExecutor) getDriver();

                                           jse.executeScript("arguments[0].value='" + inputText + "';", detectWebElement(elementXpath));

                             }

              }

 

              public static void addLogInfo(String stringMessage, boolean throwError, char... softAssert) {

                             System.out.println(stringMessage);

                             if (softAssert.length > 0) {

                                           ExtentCucumberAdapter.getCurrentStep().log(Status.FAIL, stringMessage,

                                                          MediaEntityBuilder.createScreenCaptureFromBase64String(getBase64Screenshot()).build());

                             } else if (throwError) {

                                           ExtentCucumberAdapter.getCurrentStep().log(Status.FAIL, stringMessage,

                                                          MediaEntityBuilder.createScreenCaptureFromBase64String(getBase64Screenshot()).build());

                                           throw new Error(stringMessage);

                             } else {

                                           ExtentCucumberAdapter.getCurrentStep().log(Status.PASS, stringMessage,

                                                          MediaEntityBuilder.createScreenCaptureFromBase64String(getBase64Screenshot()).build());

                             }

              }

 

              public static void assertingValueLable(String actualText, String expectedText) {

                               try {

                                            

                                                          if (expectedText.equals(actualText)) {

                                                                        CommonMethods.addLogInfo("The correct lable/value is shown as expected like: " + expectedText, false);

                                                          }

                                                          else {

                                                                        CommonMethods.addLogInfo(

                                                                                                     "The correct lable/value is NOT showed as " + expectedText+" but showed the label/value as " +actualText +"  ========================> ", false, 'y');

                                                          }

                                                                       

                                           } catch (Throwable e) {

 

                                                         

 

                                           }

              }

              public static String getBase64Screenshot() {

                             return ((TakesScreenshot) getDriver()).getScreenshotAs(OutputType.BASE64);

              }

 

              public static void pageRefresh() {

                             getDriver().navigate().refresh();

                             try {

                                           getDriver().switchTo().alert().accept();

                             } catch (Exception ignored) {

                             }

              }

 

              public static void uploadFile(WebElement element, String filePath) {

                             element.sendKeys(filePath);

              }

 

              public static String capitalizeWord(String str) {

                             String[] words = str.split("\\s");

                             StringBuilder capitalizeWord = new StringBuilder();

                             for (String w : words) {

                                           String first = w.substring(0, 1);

                                           String afterFirst = w.substring(1);

                                        capitalizeWord.append(first.toUpperCase()).append(afterFirst).append(" ");

                             }

                             return capitalizeWord.toString().trim();

              }

 

              public static void launchApplication(String url) {

                             getDriver().get(url);

              }

 

              public static void quitDriver() {

                             getDriver().quit();

              }

 

              public static void clearAllBrowserCookies() {

                             getDriver().manage().deleteAllCookies();

                             getDriver().manage().timeouts().getPageLoadTimeout();

              }

 

              public static String dateFormatter(SimpleDateFormat format1, SimpleDateFormat format2, String dateValue) {

                             String formattedDate;

                             Date date;

 

                             try {

                                           date = format1.parse(dateValue);

                                           formattedDate = format2.format(date);

                             } catch (Exception e) {

                                           formattedDate = "";

                             }

                             return formattedDate;

              }

 

              // to get future date of working day

              public static String getFutureDate(int numberOfDaysToAdd) {

                             String format = "MMMM-d-yyyy";

                             Calendar cal = Calendar.getInstance();

                             SimpleDateFormat sdf = new SimpleDateFormat(format);

 

                             cal.add(Calendar.DATE, numberOfDaysToAdd);

                             Date d1 = cal.getTime();

                             cal.setTime(d1);

 

                             int day = cal.get(Calendar.DAY_OF_WEEK);

                             while (day == Calendar.SATURDAY || day == Calendar.SUNDAY) {

                                           numberOfDaysToAdd = numberOfDaysToAdd + 1;

                                           cal = Calendar.getInstance();

                                           cal.add(Calendar.DATE, numberOfDaysToAdd);

                                           d1 = cal.getTime();

                                           cal.setTime(d1);

 

                                           day = cal.get(Calendar.DAY_OF_WEEK);

                             }

 

                             return sdf.format(d1);

 

              }

 

              // Reads text/html file and returns as string

              public static String readFile(String filePath) throws IOException {

                             BufferedReader reader = new BufferedReader(new FileReader(filePath));

                             String line = null;

                             StringBuilder stringBuilder = new StringBuilder();

                             String ls = System.getProperty("line.separator");

 

                             try {

                                           while ((line = reader.readLine()) != null) {

                                                          stringBuilder.append(line);

                                                          stringBuilder.append(ls);

                                           }

 

                                           return stringBuilder.toString();

                             } finally {

                                           reader.close();

                             }

              }

 

              public static void triggerMails(String mailSubject, String filePath, String attachmentFilePath) throws Exception {

 

                             String environment = propConfigurationFile.getProperty("typeOfenvironment");

                             String mailBody = CommonMethods.readFile(filePath);

                             // String attachmentFilePath = "";

                             new EmailEngine().sendEmail(mailBody, mailSubject, environment, attachmentFilePath);

              }

             

              public static void scrollUp () {

                             JavascriptExecutor jse = (JavascriptExecutor)getDriver();

                             jse.executeScript("window.scrollBy(0,-300)");

              }

             

              public static void scrollDown () {

                             JavascriptExecutor jse = (JavascriptExecutor)getDriver();

                             jse.executeScript("window.scrollBy(0,300)");

              }

 

}