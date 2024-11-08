@JREQ-EEB-4
Feature: Users should be able to see planets and moons added to the Planetarium

	@EEB-TC-2
	Scenario Outline: Planetarium Visibility - Only logged in Users can access Planetarium home page
	Users must log in to see their Planetarium home page
		Given The User is on the Login Page
			"""
			http://localhost:8080/
			"""
		When User inputs <Valid Username> and <Valid Password> into the login fields
		And User cicks on the login button
		Then User is logged in <Login Successful>, if not then they receive an alert due to invalid credidential input
		Then User is able to see their Planets and Moons added to their Planetarium : <Planetarium Visibility>

	Examples: 
		| Valid Username | Valid Password | Login Successful | Planetarium Visibility |
		| Batman         | I am the night | Yes              | Yes                    |
		| John           | Doe            | No               | No                     |