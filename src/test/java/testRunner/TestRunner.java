package testRunner;

import org.junit.AfterClass;
import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(features = "src\\test\\java\\features\\", glue ="stepdefinition",
tags ="@Test",
dryRun = false,
monochrome = true,
      plugin= {"pretty", "html:Automation_Report/Cucumber_Report/HTML_TestResult.html",
    		  "json:Automation_Report/Cucumber_Report/cucumber.json", "junit:Automation_Report/Cucumber_Report/cucumber.xml",
    		  "com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:", "timeline:test-output-thread",
    		  "rerun:FailedRerunScenarios/failed_scenarios.txt"})



public class TestRunner {
 @AfterClass
 public static void writeExtentReport() {
    }
}
