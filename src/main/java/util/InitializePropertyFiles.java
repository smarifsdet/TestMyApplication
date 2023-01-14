package util;

 

import org.apache.logging.log4j.LogManager;

import org.apache.logging.log4j.core.LoggerContext;

import org.openqa.selenium.support.PageFactory;

 

import java.io.File;

import java.io.FileInputStream;

import java.io.IOException;

 

public class InitializePropertyFiles extends DriverFactory {

 

              public InitializePropertyFiles() throws IOException {

                             PageFactory.initElements(getDriver(), this);

              }

 

              public static void initializeAllPropertyFiles() throws IOException {

                             initializeConfigurationPropertiesFile();

                             initializeLog4JFile();

                             initializePagePropertiesFile();

              }

 

              public static void initializeLog4JFile() {

                             LoggerContext context = (org.apache.logging.log4j.core.LoggerContext) LogManager.getContext(false);

                             File file = new File("/src/main/java/config/log4j2.xml");

                             context.setConfigLocation(file.toURI());

              }

 

              public static void initializePagePropertiesFile() throws IOException {

//                   FileInputStream file = new FileInputStream(

//                           System.getProperty("user.dir") + "/src/test/java/properties/HomePage.properties");

//                   propHomePage.load(file);

              }

 

              public static void initializeConfigurationPropertiesFile() throws IOException {

                             FileInputStream fis = new FileInputStream(

                                                          System.getProperty("user.dir") + "/src/main/java/config/config.properties");

                             propConfigurationFile.load(fis);

 

                             fis = new FileInputStream(

                                                          System.getProperty("user.dir") + "/src/test/java/externalData/Credentials.properties");

                             propCredentialTestData.load(fis);

              }

}