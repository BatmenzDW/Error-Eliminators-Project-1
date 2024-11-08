@EEB-8
Feature: EEB-8

	@EEB-TC-4 @JREQ-EEB-21 @AIO-FOLDER-EEB-8
	Scenario Outline: User Interaction - Remove Moons
	
 | Users should only be able to interact with resources they have added to the Planetarium | 
 | Planets and moons should have unique names | 
 | Moons should be "owned" by the Planet and the User adding the moon associated with it | 
 |  | 
		Given User is on the Home Page
			"""
			http://localhost:8080/planetarium
			"""
		When User selects moon from Dropdown
		When User enters moon name "<Moon Name>"
		And User clicks the delete button
		Then Moon "<Moon Name>" is deleted "<Result>"

	Examples: 
		| Moon Name | Result  |
		| Luna      | Success |
		| Jira      | Fail    |
		| <null>    | Fail    |