Feature: Functional Scenarios for ASK Login and Logout

 

 

@Test
Scenario Outline: Validation of labels in Create Request page in ASK application

Given user lanuch the "APP" application

Then user login to "APP" application user as "<CredentialType>"

And user clicks logout button

 

Examples:

|CredentialType|

|REQUESTER|