@JREQ-EEB-4
Feature: Users should be able to see planets and moons added to the Planetarium

	@EEB-TC-2
	Scenario Outline: Planetarium Visibility - Only logged in Users can access Planetarium home page
		Given The User is on the Login Page
		When User clicks the login button
		Then The User is given an Alert with text "<Alert Text>"

	Examples: 
		| Alert Text                             |
		| login attempt failed: please try again |