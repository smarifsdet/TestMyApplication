package genericlibrary;

 

import java.io.File;

import java.io.IOException;

import java.util.Properties;

import javax.mail.Authenticator;

import javax.mail.Message;

import javax.mail.MessagingException;

import javax.mail.Multipart;

import javax.mail.PasswordAuthentication;

import javax.mail.Session;

import javax.mail.Transport;

import javax.mail.internet.InternetAddress;

import javax.mail.internet.MimeBodyPart;

import javax.mail.internet.MimeMessage;

import javax.mail.internet.MimeMultipart;

 

import org.openqa.selenium.support.PageFactory;

 

import util.DriverFactory;

 

public class EmailEngine extends DriverFactory {

 

              public EmailEngine() throws IOException {

                             PageFactory.initElements(getDriver(), this);

              }

 

 

             

              private static final String hostName = "appmail.fitchratings.com";

              private static final int smtpPort = 25;

              private static final String defaultAuthenticatorUserName = DriverFactory.propConfigurationFile.getProperty("fromEmailID");

              private static final String defaultAuthenticatorUserPassword = "";

 

              private final String fromMailID = DriverFactory.propConfigurationFile.getProperty("fromEmailID");

              private final String toMailID = DriverFactory.propConfigurationFile.getProperty("toEmailID");

 

              public void sendEmail(String mailBody, String subject, String environment, String attachmentFiilePath) throws IOException {

                            

                             final Session session = Session.getInstance(this.getEmailProperties(), new Authenticator() {

                                          

                                           @Override

                                           protected PasswordAuthentication getPasswordAuthentication() {

                                                          return new PasswordAuthentication(defaultAuthenticatorUserName, defaultAuthenticatorUserPassword);

                                           }

 

                             });

 

                             try {

 

                                           final MimeMessage message = new MimeMessage(session);

                                          

                                           final MimeBodyPart attachmentPart = new MimeBodyPart();

                                           attachmentPart.attachFile(new File(attachmentFiilePath));

                                          

                                          

                                           message.setFrom(new InternetAddress(fromMailID));

                                           message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(toMailID));

                                           message.setSubject(environment + "_" + subject, "UTF-8");

                                           Multipart mp = new MimeMultipart();

                                           MimeBodyPart htmlPart = new MimeBodyPart();

                                           htmlPart.setContent(mailBody, "text/html");

                                           mp.addBodyPart(htmlPart);

                                          

                                           if (attachmentFiilePath != ""){

                                           mp.addBodyPart(attachmentPart);

                                           }

                                          

                                          

                                           message.setContent(mp);

                                           Transport.send(message);

                             }

 

                             catch (final MessagingException ex) {

                                           ex.printStackTrace();

                             }

              }

 

              //sending mails for failed scenarios to the Test Lead

public void sendEmail(String toMail, String mailBody, String subject, String environment, String attachmentFiilePath) throws IOException {

                            

                             final Session session = Session.getInstance(this.getEmailProperties(), new Authenticator() {

                                          

                                           @Override

                                           protected PasswordAuthentication getPasswordAuthentication() {

                                                          return new PasswordAuthentication(defaultAuthenticatorUserName, defaultAuthenticatorUserPassword);

                                           }

 

                             });

 

                             try {

 

                                           final MimeMessage message = new MimeMessage(session);

                                          

                                           final MimeBodyPart attachmentPart = new MimeBodyPart();

                                           attachmentPart.attachFile(new File(attachmentFiilePath));

                                          

                                          

                                           message.setFrom(new InternetAddress(fromMailID));

                                           message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(toMail));

                                           message.setSubject(environment + "_" + subject, "UTF-8");

                                           Multipart mp = new MimeMultipart();

                                           MimeBodyPart htmlPart = new MimeBodyPart();

                                           htmlPart.setContent(mailBody, "text/html");

                                           mp.addBodyPart(htmlPart);

                                          

                                           if (attachmentFiilePath != ""){

                                           mp.addBodyPart(attachmentPart);

                                           }

                                          

                                          

                                           message.setContent(mp);

                                           Transport.send(message);

                             }

 

                             catch (final MessagingException ex) {

                                           ex.printStackTrace();

                             }

              }

 

 

              public Properties getEmailProperties() {

                             final Properties config = new Properties();

                             config.put("mail.smtp.auth", "true");

                             config.put("mail.smtp.starttls.enable", "true");

                             config.put("mail.smtp.host", hostName);

                             config.put("mail.smtp.port", smtpPort);

                             return config;

              }

 

}