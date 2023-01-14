package pages;

 

import java.io.IOException;

import org.openqa.selenium.support.PageFactory;

import genericlibrary.CommonAppianMethods;

import genericlibrary.CommonMethods;

import util.DriverFactory;

 

public class LoginPage extends DriverFactory {

 

              public LoginPage() throws IOException {

                             PageFactory.initElements(getDriver(), this);

              }

             

              public void enterUserIDAndPasswordInLoginPage(String userIDType, String passwordType) throws Throwable {

 

                             //String username = prop.getProperty("username").trim();

                             //String password = prop.getProperty("password").trim();

                            

              CommonAppianMethods.populateTextFieldWithPlaceHolderName("Username",userIDType);

              CommonAppianMethods.populateTextFieldWithPlaceHolderName("Password",passwordType);             

                             CommonAppianMethods.selectButtonUsingValue("Sign In");

                             CommonMethods.addLogInfo("User Logged In", false);

              }

 

              public void clickLoginButtonInLoginPage() throws Throwable {

                             CommonAppianMethods.selectButtonUsingValue("Sign In");                            

              }                          

             

              public void loginTOPDMApplication() throws Throwable {

                             String username = propConfigurationFile.getProperty("username").trim();

                             String password = propConfigurationFile.getProperty("password").trim();

                            

              CommonAppianMethods.populateTextFieldWithPlaceHolderName("Username",username);

              CommonAppianMethods.populateTextFieldWithPlaceHolderName("Password",password);

                             CommonAppianMethods.selectButtonUsingValue("Sign In");

                             CommonMethods.addLogInfo("User Logged In", false);

              }

             

              public void logoutFromAppianApplication() throws Throwable {

                             CommonAppianMethods.logout();

                             CommonMethods.addLogInfo("User Logged Out", false);

              }

             

 

}