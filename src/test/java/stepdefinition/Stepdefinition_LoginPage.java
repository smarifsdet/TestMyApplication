package stepdefinition;

 

import java.io.IOException;

import java.util.HashMap;

import java.util.Map;

import org.openqa.selenium.By;

import org.openqa.selenium.WebElement;

import org.openqa.selenium.support.PageFactory;

import genericlibrary.CommonAppianMethods;

import genericlibrary.CommonMethods;

import io.cucumber.java.en.And;

import io.cucumber.java.en.Given;

import io.cucumber.java.en.Then;

import pages.LoginPage;

import util.DriverFactory;

 

 

public class Stepdefinition_LoginPage extends DriverFactory {

 

              public Stepdefinition_LoginPage() throws IOException {

                             PageFactory.initElements(getDriver(), this);

              }

 

              LoginPage loginPage = new LoginPage();

              CommonMethods commonMethods = new CommonMethods();

 

              @Then("^user login to \"([^\"]*)\" application user as \"([^\"]*)\"$")

              public void user_login_to_something_application_user_as_something(String applicationName, String credentialtype)

                                           throws Throwable {

                             String envType = propConfigurationFile.getProperty("typeOfenvironment");

                             String userName = propCredentialTestData

                                                          .getProperty("username_" + envType + "_" + applicationName + "_" + credentialtype);

                             String passWord = propCredentialTestData

                                                          .getProperty("password_" + envType + "_" + applicationName + "_" + credentialtype);

              CommonAppianMethods.populateTextFieldWithPlaceHolderName("Username", userName);

              CommonAppianMethods.populateTextFieldWithPlaceHolderName("Password", passWord);

                             CommonAppianMethods.selectButtonUsingValue("Sign In");

                             CommonMethods.addLogInfo("User Logged In", false);

 

              }

             

 

 

 

              @And("^user clicks logout button$")

              public void userClicksLogoutButton() throws Throwable {

                             Thread.sleep(10000);

                             loginPage.logoutFromAppianApplication();

              }            

              @Given("^user lanuch the \"([^\"]*)\" application$")

              public void user_lanuch_the_something_application(String applicationURL) throws Throwable {

                             commonMethods.launchURL(applicationURL);

              }

 

}