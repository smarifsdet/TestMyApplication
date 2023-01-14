package genericlibrary;


import java.io.IOException;

import java.sql.Connection;

import java.sql.DriverManager;

import java.sql.ResultSet;

import java.sql.ResultSetMetaData;

import java.sql.SQLException;

import java.sql.Statement;

import java.util.HashMap;

import java.util.Map;

import org.openqa.selenium.support.PageFactory;

import util.DriverFactory;

 

public class DBConnections extends DriverFactory{

             

              public DBConnections() throws IOException {

                             PageFactory.initElements(getDriver(),this);

              }

             

              public static Map<Integer, Map<String, String>> retrieveDataFromSQLDatabase(String query) throws SQLException {

 

                             String env = propConfigurationFile.getProperty("environment");

                             String uname = propConfigurationFile.getProperty("db_user_Name_"+env);

                             String pwd = propConfigurationFile.getProperty("db_password_"+env);

                             String host = propConfigurationFile.getProperty("db_host_name_"+env);

                             String port = propConfigurationFile.getProperty("db_port_no_"+env);

                             String Servicename = propConfigurationFile.getProperty("Service_name_"+env);

                           

                             Map<Integer, Map<String, String>> databaseResult = new HashMap<>();

                             int count =0;

                             Connection con = null;

                            

                             //Establish connection with SQL Database

                             try {

                                           con = DriverManager.getConnection("jdbc:oracle:thin:@" + host + ":" + port + "/" + Servicename,

                                                                        uname, pwd);

                                           System.out.println("SQL Connection established");

                             } catch (Exception e) {

                                           CommonMethods.addLogInfo("SQL Connection did NOT establish", true);

                             }

 

                             //Execute the desired query and store in a hashmap

                             assert con != null;

                             Statement stmnt = con.createStatement();

                             ResultSet rs = stmnt.executeQuery(query);

                             System.out.println("Query executed: "+query);

                            

                             ResultSetMetaData rsmd = rs.getMetaData();

                                           int column_count = rsmd.getColumnCount();

                                           while (rs.next()) {

                                                          Map<String, String> result = new HashMap<>();

                                                          for (int i = 1; i <= column_count; i++) {

                                                                        if(rs.getString(i)==null) {

                                                                                      result.put(rsmd.getColumnName(i), "");

                                                                        }else {

                                                                                      result.put(rsmd.getColumnName(i), rs.getString(i));       

                                                                        }                          

                                                          }

                                                          ++count;

                                                          databaseResult.put(count, result);                                       

                                           }

 

                             System.out.println("Total records found in Database:"+count);

 

                                           for(int j=1;j<=databaseResult.size();j++) {

                                                          System.out.println(databaseResult.get(j));

                                           }

                                          

                                           con.close();

                             System.out.println("Database connection closed");

                                          

                             return databaseResult; 

              }

             

              public static void deleteQuery(String query) throws SQLException {

 

                             String uname = propConfigurationFile.getProperty("db_user_Name");

                             String pwd = propConfigurationFile.getProperty("db_password");

                             String host = propConfigurationFile.getProperty("db_host_name");

                             String port = propConfigurationFile.getProperty("db_port_no");

                             String Servicename = propConfigurationFile.getProperty("Service_name");

                           

                             Connection con = null;

                            

                             //Establish connection with SQL Database

                             try {

                                           con = DriverManager.getConnection("jdbc:oracle:thin:@" + host + ":" + port + "/" + Servicename,

                                                                        uname, pwd);

                                           CommonMethods.addLogInfo("SQL Connection established", false);

                             } catch (Exception e) {

                                           CommonMethods.addLogInfo("SQL Connection did NOT establish", true);

                             }

 

                             //Execute the desired query and store in a hashmap

                             assert con != null;

                             Statement stmnt = con.createStatement();

                             stmnt.executeQuery(query);

                             CommonMethods.addLogInfo("Query executed: "+query, false);

                            

                             con.close();

                             CommonMethods.addLogInfo("Database connection closed", false);

              }

}