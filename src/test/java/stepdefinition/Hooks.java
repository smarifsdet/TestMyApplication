package stepdefinition;

 

import java.io.File;

import java.io.IOException;

 

import org.openqa.selenium.OutputType;

import org.openqa.selenium.TakesScreenshot;

import org.openqa.selenium.support.PageFactory;

import com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter;

 

import genericlibrary.CommonAppianMethods;

import genericlibrary.CommonMethods;

import genericlibrary.EmailEngine;

import io.cucumber.java.After;

import io.cucumber.java.AfterStep;

import io.cucumber.java.Before;

import io.cucumber.java.Scenario;

import util.DriverFactory;

 

public class Hooks extends DriverFactory {

              String testLeadEmailIDs = DriverFactory.propConfigurationFile.getProperty("testLeadEmailID");

              String environment = propConfigurationFile.getProperty("typeOfenvironment");

 

              public Hooks() throws IOException {

                             PageFactory.initElements(getDriver(), this);

              }

 

              @Before

              public void intializeTest() throws Throwable {

                             DriverFactory.intialization();

              }

 

              @After(order = 1)

              public void emailReport(Scenario sc) throws Exception {

 

                             if (DriverFactory.propConfigurationFile.getProperty("emailFlag").equalsIgnoreCase("true")) {

                                           File[] files = new File(System.getProperty("user.dir") + "\\Automation_Report").listFiles();

                                           String environment = propConfigurationFile.getProperty("typeOfenvironment");

 

                                           String expectedFilePath = files[files.length - 2].getAbsolutePath();

 

                                           /// System.out.println(expectedFilePath);

 

                                           String path = expectedFilePath + "\\HTML_Report";

                                          

                                           /*

                                           * String path = expectedFilePath + "\\Cucumber_Report\\advanced-reports";

                                           * CommonAppianMethods.zipFiles(path);

                                           */

 

                                           File[] reportFiles = new File(path).listFiles();

                                           if (reportFiles != null) {

                                                          for (File file : reportFiles) {

                                                                        // System.out.println(f1.getAbsolutePath());

                                                                        new EmailEngine().sendEmail("Execution Summary details (body)", "ExecutionSummary", environment,

                                                                                                    file.getAbsolutePath());

 

                                                          }

                                           }

                             }

 

              }

 

              @AfterStep

              public void stepFailure(Scenario scenario) throws Exception {

                                                         

                             if (scenario.isFailed()) {

                                           String screenShotName = scenario.getName().replaceAll(" ", "_");

                                           byte[] sourcePath = ((TakesScreenshot) getDriver()).getScreenshotAs(OutputType.BYTES);

                                           scenario.attach(sourcePath, "image/png", screenShotName);

 

                                           // Sending mail for the failed scenario

 

                                           if (DriverFactory.propConfigurationFile.getProperty("emailFlag").equalsIgnoreCase("true")) {

                                                          File[] files = new File(System.getProperty("user.dir") + "\\Automation_Report").listFiles();

                                                          String environment = propConfigurationFile.getProperty("typeOfenvironment");

 

                                                          String expectedFilePath = files[files.length - 2].getAbsolutePath();

 

                                                          /// System.out.println(expectedFilePath);

 

                                                          String path = expectedFilePath + "\\HTML_Report";

                                                          //String path = expectedFilePath + "\\Cucumber_Report\\advanced-reports";

 

                                                          File[] reportFiles = new File(path).listFiles();

                                                          if (reportFiles != null) {

                                                                        for (File file : reportFiles) {

                                                                                      new EmailEngine().sendEmail(testLeadEmailIDs, "Failed Scenarios details (body)",

                                                                                                                   "FailedScenario", environment, file.getAbsolutePath());

                                                                        }

                                                          }

 

                                           }

                                           }else {

                                                  System.out.println(ExtentCucumberAdapter.getCurrentStep().getStatus());

 

                             }

              }

 

              @After

              public void AfterSteps(Scenario scenario) {

 

                             //CommonMethods.quitDriver();

                             getDriver().quit();

                             threadLocallDriver.remove();

              }

 

}