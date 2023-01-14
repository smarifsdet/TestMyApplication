package util;

 

import java.io.IOException;

import java.util.Properties;

import org.openqa.selenium.WebDriver;

import org.openqa.selenium.chrome.ChromeDriver;

import org.openqa.selenium.edge.EdgeDriver;

import org.openqa.selenium.firefox.FirefoxDriver;

import org.openqa.selenium.ie.InternetExplorerDriver;

import org.openqa.selenium.safari.SafariDriver;

 

import io.github.bonigarcia.wdm.WebDriverManager;

 

public class DriverFactory {

 

              private static WebDriver driver;

              public static Properties propConfigurationFile = new Properties();

 

              public static Properties propCredentialTestData = new Properties();

              public static Properties propHomePage = new Properties();

              static OptionsManager optionsManager;

 

              public static ThreadLocal<WebDriver> threadLocallDriver = new ThreadLocal<WebDriver>();

 

              public DriverFactory() throws IOException {

                             InitializePropertyFiles.initializeAllPropertyFiles();

              }

 

              // Used to initialize the ThreadLocal driver on the basis of given browser

              // @browser param

              public static void intialization() throws Throwable {

                             String browser = propConfigurationFile.getProperty("browser").trim();

                             System.out.println("Browser name is  :" + browser);

 

                             optionsManager = new OptionsManager(propConfigurationFile);

 

                             switch (browser.toLowerCase()) {

                             case "chrome":

                                           driver = WebDriverManager.chromedriver().capabilities(optionsManager.getChromeOptions()).create();

                                           //driver = new ChromeDriver(optionsManager.getChromeOptions());

                                           threadLocallDriver.set(driver);

                                           System.out.println("Chrome Driver Initiated");

                                           break;

 

                             case "firefox":

                                           driver = WebDriverManager.firefoxdriver().capabilities(optionsManager.getFirefoxOptions()).create();

                                           //driver = new FirefoxDriver(optionsManager.getFirefoxOptions());

                                           threadLocallDriver.set(driver);

                                           System.out.println("Firefox Driver Initiated");

                                           break;

                             case "edge":

                                           driver = WebDriverManager.edgedriver().capabilities(optionsManager.getEdgeOptions()).create();

                                           //driver = new EdgeDriver(optionsManager.getEdgeOptions());

                                           threadLocallDriver.set(driver);

                                           System.out.println("Edge Driver Initiated");

                                           break;

 

                             case "safari":

                                           driver = WebDriverManager.safaridriver().capabilities(optionsManager.getSafariOptions()).create();

                                           //driver = new SafariDriver(optionsManager.getSafariOptions());

                                           threadLocallDriver.set(driver);

                                           System.out.println("Safari Driver Initiated");

                                           break;

                             }

 

                             getDriver().manage().window().maximize();

                             getDriver().manage().deleteAllCookies();

                             getDriver().manage().timeouts().getPageLoadTimeout();

                             //return getDriver();

              }
              
              // This is used get the driver with ThreadLocal @return to avoid using same

              // driver in parallel execution.

              public static synchronized WebDriver getDriver() {

 

                             return threadLocallDriver.get();

 

              }

             

              public void setDriver() throws Throwable {

 

                             intialization();

 

              }

 

}


