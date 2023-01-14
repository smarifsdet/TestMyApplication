package util;

 

import java.util.Properties;

 

import org.openqa.selenium.chrome.ChromeOptions;

import org.openqa.selenium.edge.EdgeOptions;

import org.openqa.selenium.firefox.FirefoxOptions;

import org.openqa.selenium.safari.SafariOptions;

 

public class OptionsManager {

 

              private Properties prop;

              private ChromeOptions co;

              private FirefoxOptions fo;

              private EdgeOptions eo;

              private SafariOptions so;

 

              public OptionsManager(Properties prop) throws Throwable {

                             this.prop = prop;

              }

 

              public ChromeOptions getChromeOptions() {

                             co = new ChromeOptions();

 

                             if (Boolean.parseBoolean(prop.getProperty("remote"))) {

                                           co.setCapability("enableVNC", true);

 

                             }

 

                             if (Boolean.parseBoolean(prop.getProperty("headless")))

                                           co.setHeadless(true);

                                           co.addArguments("--window-size=1280,800");

                                           //co.addArguments("--window-size=1920,1080");

                                           co.addArguments("--disable-gpu");

 

                             if (Boolean.parseBoolean(prop.getProperty("incognito")))

                                           co.addArguments("--incognito");

                                           co.addArguments("--window-size=1280,800");

                                           co.addArguments("--disable-gpu");

 

                             if (Boolean.parseBoolean(prop.getProperty("securityPopups")))

                                           co.addArguments("--disable-notifications");

 

                             if (Boolean.parseBoolean(prop.getProperty("popups")))

                                           co.addArguments("--disable-popup-blocking");

 

                             if (Boolean.parseBoolean(prop.getProperty("allowInsecureLocalHost")))

                                           co.addArguments("--allow-insecure-localhost");

 

                             return co;

              }

 

              public FirefoxOptions getFirefoxOptions() {

                             fo = new FirefoxOptions();

                             if (Boolean.parseBoolean(prop.getProperty("remote"))) {

                                           fo.setCapability("enableVNC", true);

 

                             }

                             if (Boolean.parseBoolean(prop.getProperty("headless")))

                                           fo.setHeadless(true);

                                           fo.addArguments("--window-size=1280,800");

                                           fo.addArguments("--disable-gpu");

                                          

                             if (Boolean.parseBoolean(prop.getProperty("incognito")))

                                           fo.addArguments("-private");

                                           fo.addArguments("--window-size=1280,800");

                                           fo.addArguments("--disable-gpu");

                                          

                             if (Boolean.parseBoolean(prop.getProperty("securityPopups")))

                                           fo.addArguments("--disable-notifications");

 

                             if (Boolean.parseBoolean(prop.getProperty("popups")))

                                           fo.addArguments("--disable-popup-blocking");

 

                             if (Boolean.parseBoolean(prop.getProperty("allowInsecureLocalHost")))

                                           fo.addArguments("--allow-insecure-localhost");

                            

                             return fo;

              }

 

              public EdgeOptions getEdgeOptions() {

                             eo = new EdgeOptions();

                             if (Boolean.parseBoolean(prop.getProperty("remote"))) {

                                           eo.setCapability("enableVNC", true);

 

                             }

                             if (Boolean.parseBoolean(prop.getProperty("headless")))

                                           eo.setHeadless(true);

                                           eo.addArguments("--window-size=1280,800");

                                           eo.addArguments("--disable-gpu");

                                          

                             if (Boolean.parseBoolean(prop.getProperty("incognito")))

                                           eo.addArguments("-private");

                                           eo.addArguments("--window-size=1280,800");

                                           eo.addArguments("--disable-gpu");

 

                             if (Boolean.parseBoolean(prop.getProperty("securityPopups")))

                                           eo.addArguments("--disable-notifications");

 

                             if (Boolean.parseBoolean(prop.getProperty("popups")))

                                           eo.addArguments("--disable-popup-blocking");

                            

                             if (Boolean.parseBoolean(prop.getProperty("allowInsecureLocalHost")))

                                           eo.addArguments("--allow-insecure-localhost");

 

                             return eo;

              }

 

              public SafariOptions getSafariOptions() {

                             so = new SafariOptions();

                             if (Boolean.parseBoolean(prop.getProperty("remote"))) {

                                           so.setCapability("enableVNC", true);

                             }

 

                             System.out.println("Safari Browser wont support Headless mode and Incognito mode");

                             // if(Boolean.parseBoolean(prop.getProperty("headless"))) so.setHeadless(true);

                             // if(Boolean.parseBoolean(prop.getProperty("incognito")))

                             // so.addArguments("-private");

                             return so;

              }

 

}