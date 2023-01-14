package genericlibrary;

 

import java.time.LocalDate;

import java.util.Map;

import static org.junit.Assert.assertTrue;

import org.apache.commons.collections4.map.HashedMap;

import org.openqa.selenium.By;

import org.openqa.selenium.JavascriptExecutor;

import org.openqa.selenium.NoSuchElementException;

import org.openqa.selenium.TimeoutException;

import org.openqa.selenium.WebDriver;

import org.openqa.selenium.WebElement;

import org.openqa.selenium.support.PageFactory;

 

import util.DriverFactory;

 

public class CommonAppianMethods extends DriverFactory {

 

              public CommonAppianMethods() throws Throwable {

 

                             PageFactory.initElements(getDriver(), this);

 

              }

 

              public static void highlightElementbeforeAction(WebElement element, WebDriver driver) {

 

                             if (DriverFactory.propConfigurationFile.getProperty("highlight").equalsIgnoreCase("true")) {

                                           if (driver instanceof JavascriptExecutor) {

                                                          ((JavascriptExecutor) getDriver()).executeScript("arguments[0].style.border='3px solid red'", element);

                                                          try {

                                                                        Thread.sleep(7);

                                                          } catch (InterruptedException e) {

 

                                                                        e.printStackTrace();

                                                          }

                                                          ((JavascriptExecutor) getDriver()).executeScript("arguments[0].style.border='1px solid white'",

                                                                                      element);

                                           }

                             }

 

              }

 

              public static void uploadFile(String labelName, String fileName) {

                             String xpath = "//*[text()='" + labelName

                                                          + "']/parent::div/following-sibling::div[contains(@class,'FieldLayout')]//input";

                             CommonMethods.waitForPresenceOfWebElement(xpath);

                             getDriver().findElement(By.xpath(xpath))

                                                          .sendKeys(System.getProperty("user.dir") + "/src/test/java/uploads/" + fileName);

              }

 

              public static void loginToAppianApplicationWithAppURL(String applicationURL, String username, String password)

                                           throws Throwable {

                             launchAppianApplicationURL(applicationURL);

                             populateTextFieldWithPlaceHolderName("Username", username);

                             populateTextFieldWithPlaceHolderName("Password", password);

                             selectButtonUsingValue("Sign In");

              }

 

              public static void loginToAppianApplicationWithoutAppURL(String username, String password) throws Throwable {

                             populateTextFieldWithPlaceHolderName("Username", username);

                             populateTextFieldWithPlaceHolderName("Password", password);

                             selectButtonUsingValue("Sign In");

              }

 

              public static void selectSiteMenuTab(String siteMenuName) throws Throwable {

                             siteMenuName = CommonMethods.capitalizeWord(siteMenuName);

                             String siteMenuXpath = "//div[@class='SiteMenuTab---nav_label' and text()='" + siteMenuName + "']";

                            CommonMethods.waitForPresenceOfWebElement(siteMenuXpath);

                             CommonMethods.clickElement(siteMenuXpath);

              }

 

              public static void selectFromDropDownBelowLabel(String labelName, String OptionToBeSelected) throws Exception {

 

                             String dropDownElementXpath = "//*[text()='" + labelName + "']/parent::div/following-sibling::div/div";

 

                             CommonMethods.scrollUp();

                CommonMethods.waitForPresenceOfWebElement(dropDownElementXpath);

                             CommonMethods.clickElement(dropDownElementXpath);

 

                             String xpath = "//li[contains(@class,'inDropdownWidget')]/div[text()='" + OptionToBeSelected + "']";

 

                             CommonMethods.waitForPresenceOfWebElement(xpath);

 

                             if (getDriver().findElements(By.xpath(xpath)).size() != 0)

                                           CommonMethods.clickElement(xpath);

                             else {

 

                                           throw new Exception(OptionToBeSelected + " Not found ========================>");

 

                             }

              }

 

// Need to impliment *****************************

              public static void incrementedDate(String labelName, int numberOfDaysToAdd) throws InterruptedException {

                             String dropDownElementXpath = "//*[text()='" + labelName + "']/parent::div/following-sibling::div/div";

                CommonMethods.waitForPresenceOfWebElement(dropDownElementXpath);

              }

 

              public static void populateTextFieldWithLabelName(String labelName, String value) throws Throwable {

                             String xpath = "//*[text()='" + labelName

                                                          + "']/parent::div/following-sibling::div[contains(@class,'FieldLayout')]//input";

                             CommonMethods.waitForPresenceOfWebElement(xpath);

                             CommonMethods.sendElementText(xpath, value);

              }

 

              // textbox M

              public static void populateTextBoxWithLableName(String lableName, String text) throws InterruptedException {

                             String xpath = "//*[text()='" + lableName + "']/parent::div/following-sibling::div//textarea";

                             CommonMethods.waitForPresenceOfWebElement(xpath);

                             CommonMethods.sendElementText(xpath, text);

              }

 

              // comment box M

              public static void populateCommentBoxWithPlaceholder(String placeHolder, String commentText)

                                           throws InterruptedException {

                             String xpath = "//div[@data-placeholder='" + placeHolder + "']";

                             CommonMethods.waitForPresenceOfWebElement(xpath);

                             CommonMethods.sendElementText(xpath, commentText);

              }

 

              public static void populateTextFieldWithPlaceHolderName(String placeHolderName, String value) throws Throwable {

                             String xpath = "//input[@placeholder='" + placeHolderName + "']";

                             CommonMethods.waitForPresenceOfWebElement(xpath);

                             CommonMethods.sendElementText(xpath, value);

              }

 

              public static void selectRadioButton(String radioButtonName) throws Throwable {

                             String xpath = "//*[@type='radio' and @value='" + radioButtonName + "']";

                             CommonMethods.waitForPresenceOfWebElement(xpath);

                             CommonMethods.clickElement(xpath);

              }

 

              // checkbox M

              public static void selectChkeckBox(String checkBoxName) throws InterruptedException {

                             String xpath = "//*[text()='" + checkBoxName + "']/parent::div/input[@type='checkbox']";

                             CommonMethods.waitForPresenceOfWebElement(xpath);

                             CommonMethods.clickElement(xpath);

              }

 

              public static void selectRadioButtonBelowLabel(String labelName, String radioButtonName) throws Throwable {

                             String xpath = "//*[text()='" + labelName

                                                          + "']/parent::div[@class='FieldLayout---label_above']/following-sibling::div[@class='FieldLayout---input_below']//*[@type='radio' and @value='"

                                                          + radioButtonName + "']";

                             CommonMethods.waitForPresenceOfWebElement(xpath);

                             CommonMethods.clickElement(xpath);

              }

 

              public static void selectButtonUsingName(String buttonName) throws Throwable {

                             String xpath = "//button[text()='" + buttonName + "']";

                             CommonMethods.waitForPresenceOfWebElement(xpath);

                             CommonMethods.clickElement(xpath);

              }

 

              public static void selectButtonUsingValue(String buttonValue) throws Throwable {

                             String xpath = "//input[@value='" + buttonValue + "']";

                             CommonMethods.waitForPresenceOfWebElement(xpath);

                             CommonMethods.clickElement(xpath);

              }

 

              public static void selectButtonUsingText(String buttonName) throws Throwable {

                             String xpath = "//span[text()='" + buttonName + "']";

                             CommonMethods.waitForPresenceOfWebElement(xpath);

                             CommonMethods.clickElement(xpath);

              }

 

              public static boolean isLabelDisplayedForBoxedLayoutWithDriver(String labelName) throws Throwable {

                             String xpath = "//*[@class='BoxLayout---box_heading' and text()='" + labelName + "']";

                             CommonMethods.waitForPresenceOfWebElement(xpath);

                             return getDriver().findElements(By.xpath(xpath)).size() != 0;

              }

 

              public static boolean isLabelDisplayedFataError(String labelName) throws Throwable {

                             String xpath = "//*[@class='FieldLayout---field_error' and text()='" + labelName + "']";

                             CommonMethods.waitForPresenceOfWebElement(xpath);

                             return CommonMethods.isElementDisplayed(xpath);

              }

 

              public static String getEmphasisTextDisplayed(String labelName) throws Throwable {

                             String xpath = "//*[@class='EmphasisText---richtext_emphasis EmphasisText---inStrongText' and text()='"

                                                          + labelName + "']";

                             String actualTextDisplayed = CommonMethods.getElementText(xpath);

 

                             return actualTextDisplayed;

              }

 

              public static void clickFieldValueFromGrid(String gridName, String fieldValue) throws Throwable {

                             String xpath = "//*[text()='" + gridName

                                                          + "']/parent::div[@class='FieldLayout---label_above']/following-sibling::div//table//tbody//p[text()='"

                                                          + fieldValue + "']";

                             CommonMethods.waitForPresenceOfWebElement(xpath);

                             CommonMethods.clickElement(xpath);

              }

 

              public static void clickLinkFromGrid(String linkValue) throws Throwable {

                             String xpath = "//table//tbody//a[text()='" + linkValue + "']";

                             CommonMethods.waitForPresenceOfWebElement(xpath);

                             CommonMethods.clickElement(xpath);

              }

 

              // my testing text value from grid

              public static String getTextFromGrid(String textValue) throws Throwable {

                             String xpath = "//table//tbody//p[text()='" + textValue + "']";

                             CommonMethods.waitForPresenceOfWebElement(xpath);

                             return getDriver().findElement(By.xpath(xpath)).getText();

              }

 

              public static void clickUserProfileIcon() throws Throwable {

                             String xpath = "//a[@aria-label='Open user options menu']";

                             CommonMethods.waitForPresenceOfWebElement(xpath);

                             CommonMethods.clickElement(xpath);

              }

 

              public static void logout(char... clearCache) throws Throwable {

                             clickUserProfileIcon();

                             selectButtonUsingName("Sign Out");

 

                             if (clearCache.length > 0) {

                                           CommonMethods.clearAllBrowserCookies();

                             }

              }

 

              public static String getErrorMessageText() throws Throwable {

                             String xpath = "//div[@class='message']";

                             CommonMethods.waitForPresenceOfWebElement(xpath);

                             return CommonMethods.getElementAttribute(xpath, "innerText");

              }

 

              public static String getTextFieldWithLabelName(String labelName) throws Throwable {

                             String xpath = "//*[text()='" + labelName + "']/parent::div/following-sibling::div//p";

                             CommonMethods.waitForPresenceOfWebElement(xpath);

                             return CommonMethods.getElementText(xpath);

              }

 

              public static void expandSectionLayout(String sectionLayoutName) throws Throwable {

                             String xpath = "//*[text()='" + sectionLayoutName + "']/parent::a";

                             if (CommonMethods.getElementAttribute(xpath, "ariaExpanded").equalsIgnoreCase("false")) {

                                           CommonMethods.waitForPresenceOfWebElement(xpath);

                                           CommonMethods.clickElement(xpath);

                             }

              }

 

              public static void collapseSectionLayout(String sectionLayoutName) throws Throwable {

                             String xpath = "//*[text()='" + sectionLayoutName + "']/parent::a";

                             if (CommonMethods.getElementAttribute(xpath, "ariaExpanded").equalsIgnoreCase("true")) {

                                           CommonMethods.waitForPresenceOfWebElement(xpath);

                                           CommonMethods.clickElement(xpath);

                             }

              }

 

              public static void populateDateField(String labelName, Map<String, String> calendarDate)

                                           throws InterruptedException {

                             String year = calendarDate.get("year");

                             String month = CommonMethods.capitalizeWord(calendarDate.get("month"));

                             String day = calendarDate.get("day");

 

                             String datePickerXpath = "//*[text()='" + labelName + "']/parent::div/following-sibling::div//button";

                             CommonMethods.clickElement(datePickerXpath);

 

                            CommonMethods.clickElement("//div[contains(@class,'DatePicker---year')]");

                             CommonMethods.clickElement("//div[text()='" + year + "']");

 

                            CommonMethods.clickElement("//div[contains(@class,'DatePicker---month')]");

                             CommonMethods.clickElement("//div[text()='" + month + "']");

 

              CommonMethods.clickElement("//button[contains(@class,'current_month')]//span[text()='" + day + "']");

              }

 

              public static void populateDateFieldWithSection(String labelName, Map<String, String> calendarDate,

                                           String sectionName) throws InterruptedException {

                             String year = calendarDate.get("year");

                             String month = CommonMethods.capitalizeWord(calendarDate.get("month"));

                             String day = calendarDate.get("day");

 

                             String datePickerXpath = "//*[text()='" + sectionName + "']/parent::div/following-sibling::div//span[text()='"

                                                          + labelName + "']/parent::div/following-sibling::div//button";

                             CommonMethods.clickElement(datePickerXpath);

 

                            CommonMethods.clickElement("//div[contains(@class,'DatePicker---year')]");

                             CommonMethods.clickElement("//div[text()='" + year + "']");

 

                            CommonMethods.clickElement("//div[contains(@class,'DatePicker---month')]");

                             CommonMethods.clickElement("//div[text()='" + month + "']");

 

              CommonMethods.clickElement("//button[contains(@class,'current_month')]//span[text()='" + day + "']");

              }

 

              public static void populateDateAndTimeField(String labelName, Map<String, String> calendarDate)

                                           throws InterruptedException {

                             String year = calendarDate.get("year");

                             String month = CommonMethods.capitalizeWord(calendarDate.get("month"));

                             String day = calendarDate.get("day");

 

                             String datePickerXpath = "//*[text()='" + labelName + "']/parent::div/following-sibling::div//button";

                             CommonMethods.clickElement(datePickerXpath);

 

                            CommonMethods.clickElement("//div[contains(@class,'DatePicker---year')]");

                             CommonMethods.clickElement("//div[text()='" + year + "']");

 

                            CommonMethods.clickElement("//div[contains(@class,'DatePicker---month')]");

                             CommonMethods.clickElement("//div[text()='" + month + "']");

 

              CommonMethods.clickElement("//button[contains(@class,'current_month')]//span[text()='" + day + "']");

              }

 

              public static Map<String, String> getCurrentDate() throws Throwable {

 

                             Map<String, String> calendarDate = new HashedMap<>();

                             LocalDate currentdate = LocalDate.now();

 

                             calendarDate.put("year", String.valueOf(currentdate.getYear()));

                             calendarDate.put("month", String.valueOf(currentdate.getMonth()).toLowerCase());

                             calendarDate.put("day", String.valueOf(currentdate.getDayOfMonth()));

 

                             return calendarDate;

              }

 

              public static void launchAppianApplicationURL(String applicationURL) throws Throwable {

                             CommonMethods.launchApplication(applicationURL);

              }

 

              public static void populateTokenPickerWithLabel(String labelName, String fieldValue) throws Throwable {

                             String tokenPickerRemoveXpath = "//*[text()='" + labelName

                                                          + "']/parent::div/following-sibling::div//a[@class='PickerTokenWidget---remove elements---global_a']";

              CommonMethods.waitForPresenceOfWebElement(tokenPickerRemoveXpath);

                             if (CommonMethods.isElementDisplayed(tokenPickerRemoveXpath)) {

                                           CommonMethods.clickElement(tokenPickerRemoveXpath);

                                           populateTextFieldWithPlaceHolderName("Type to search a user.", fieldValue);

 

                                           CommonMethods.clickElement("//strong[text()='" + fieldValue + "']");

                             } else {

                                           populateTextFieldWithLabelName(labelName, fieldValue);

 

                                           CommonMethods.clickElement("//strong[text()='" + fieldValue + "']");

 

                             }

              }

 

              public static String getTextFromTableCell(String rowNumber, String columnNumber) throws Throwable {

                             String tableCellXpath = "(//table//tr)[" + rowNumber + "]/td[" + columnNumber + "]//p";

                            CommonMethods.waitForPresenceOfWebElement(tableCellXpath);

                             return CommonMethods.getElementAttribute(tableCellXpath, "innerText");

              }

 

              public static String getTextFromTableCell_2(String rowNumber, String columnNumber) throws Throwable {

                             String tableCellXpath = "//table//tr[" + rowNumber + "]/td[" + columnNumber + "]//p";

                            CommonMethods.waitForPresenceOfWebElement(tableCellXpath);

                             return CommonMethods.getElementAttribute(tableCellXpath, "innerText");

              }

 

              // click on first request in the list

              public static void clickOnFirstRequestOrServiceInTheList() throws Throwable {

                             Thread.sleep(3000);

                             String firstRowXpath = "//table/tbody/tr[1]/td[1]/div/p/a";

                             CommonMethods.clickElement(firstRowXpath);

              }

 

              // Click on service

              public static void clickOnServiceTypeByServiceName(String serviceType) throws InterruptedException {

                             String xpath = "//table/tbody/tr/td[3]/p[contains(text(),'" + serviceType + "')]/parent::td/parent::tr/td[2]";

                             CommonMethods.waitForPresenceOfWebElement(xpath);

                             CommonMethods.clickElement(xpath);

              }

 

              // verification of auto populate label from dropdown

              public static String verifyTextValueFromDropDown(String lableName) throws Throwable {

                             String xpath = "//*[text()='" + lableName + "']/parent::div/following-sibling::div/div//span";

                             CommonMethods.waitForPresenceOfWebElement(xpath);

                             return CommonMethods.getElementText(xpath);

              }

 

              /////////////////////

 

              // Lable dispalyed or not - Hard

              public static boolean isLabelDisplayed(String labelName) throws Throwable {

                             String xpath = "//*[@class='FieldLayout---field_label' and text()='" + labelName + "']";

                             CommonMethods.waitForPresenceOfWebElement(xpath);

                             return CommonMethods.isElementDisplayed(xpath);

              }

 

              // Lable dispalyed or not - Soft

              public static boolean isLabelDisplayedWithDriver(String labelName) throws Throwable {

                             String xpath = "//*[@class='FieldLayout---field_label' and text()='" + labelName + "']";

                             CommonMethods.waitForPresenceOfWebElement(xpath);

                             return getDriver().findElements(By.xpath(xpath)).size() != 0;

              }

 

              // BoxeLayout Lable dispalyed or not - Hard

              public static boolean isLabelDisplayedForBoxLayout(String labelName) throws Throwable {

                             String xpath = "//*[@class='BoxLayout---box_heading' and text()='" + labelName + "']";

                             CommonMethods.waitForPresenceOfWebElement(xpath);

                             return CommonMethods.isElementDisplayed(xpath);

 

              }

 

              // BoxeLayout Lable dispalyed or not - Soft

              public static boolean isLabelDisplayedForBoxLayoutWithDriver(String labelName) throws Throwable {

                             String xpath = "//*[@class='BoxLayout---box_heading' and text()='" + labelName + "']";

                             CommonMethods.waitForPresenceOfWebElement(xpath);

                             return getDriver().findElements(By.xpath(xpath)).size() != 0;

              }

 

              // lable name validation

              public static void lableValidataion(String lableName) throws Throwable {

 

                             try {

                                           boolean actualLable = CommonAppianMethods.isLabelDisplayed(lableName);

 

                                           CommonMethods.addLogInfo("Lable Name Displayed correct for : " + lableName, false);

                             } catch (TimeoutException | NoSuchElementException e2) {

                                           CommonMethods.addLogInfo("Lable Name " + lableName + " is NOT showed    ================> ", false, 'y');

                                           // throw new Exception("Lable Name Not Displayed correct for " + lableName +"

                                           // ================> ");

 

                             }

 

              }

 

              public static void lableValidataionWithDriver(String lableName) throws Throwable {

 

                             try {

 

                                           boolean actualLable = CommonAppianMethods.isLabelDisplayedWithDriver(lableName);

                                           CommonMethods.addLogInfo("Lable Name Displayed correct for : " + lableName, false);

                             } catch (TimeoutException | NoSuchElementException e2) {

                                           CommonMethods.addLogInfo("Lable Name " + lableName + " is NOT showed    ================> ", false, 'y');

                                           // throw new Exception("Lable Name Not Displayed correct for " + lableName +"

                                           // ================> ");

                             }

 

              }

 

              public static void lableValidataionForBoxLayout(String lableName) throws Throwable {

 

                             try {

                                           boolean actualLable = CommonAppianMethods.isLabelDisplayedForBoxLayout(lableName);

                                           CommonMethods.addLogInfo("Lable Name Displayed correct for : " + lableName, false);

                             } catch (TimeoutException | NoSuchElementException e2) {

                                           CommonMethods.addLogInfo("Lable Name " + lableName + " is NOT showed    ================> ", false, 'y');

 

                             }

 

              }

 

              public static void lableValidataionForBoxLayoutWithDriver(String lableName) throws Throwable {

 

                             try {

                                           boolean actualLable = CommonAppianMethods.isLabelDisplayedForBoxLayoutWithDriver(lableName);

 

                                           CommonMethods.addLogInfo("Lable Name Displayed correct for : " + lableName, false);

                             } catch (TimeoutException | NoSuchElementException e2) {

                                           CommonMethods.addLogInfo("Lable Name " + lableName + " is NOT showed    ================> ", false, 'y');

 

                             }

 

              }

             

              public static void Verify_Fields_are_NOT_emptry(String lableName) {

        String xpath="//*[text()='"+lableName+"']/parent::div/following-sibling::div";

        System.out.println(CommonMethods.getElementText(xpath));

        try {

        assertTrue(CommonMethods.getElementText(xpath).length()>0);

            CommonMethods.addLogInfo(lableName+" field is NOT empty", false);

        }

        catch (Throwable e) {

            CommonMethods.addLogInfo(lableName+" field is empty", false, 'y');

        }

    }

             

              public static void clickOnClonedServiceTypeByServiceName(String serviceType) throws InterruptedException {

              String xpath = "//table/tbody/tr[2]/td[3]/p[contains(text(),'" + serviceType + "')]/parent::td/parent::tr/td[2]";

        CommonMethods.waitForPresenceOfWebElement(xpath);

        CommonMethods.clickElement(xpath);

           

        

    }

 

             

 

}