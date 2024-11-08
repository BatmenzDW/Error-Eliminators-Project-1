@EEB-4
Feature: EEB-4

	@EEB-TC-2 @JREQ-EEB-12 @AIO-FOLDER-EEB-4
	Scenario Outline: Planetarium Visibility - Only logged in Users can access Planetarium home page
	Users must log in to see their Planetarium home page
		Given The User is on the Login Page
		When The user provides username "<Username>"
		And User provides password "<Password>"
		And User clicks the login button
		Then User is logged in <Login Successful>, if not then they receive an alert due to invalid credidential input
		Then User is able to see their Planets and Moons added to their Planetarium : <Planetarium Visibility>

	Examples: 
		| Username | Password       | Login Successful | Planetarium Visibility |
		| Batman   | I am the night | Yes              | Yes                    |
		| John     | Doe            | No               | No                     |