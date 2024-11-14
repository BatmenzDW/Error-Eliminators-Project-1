Feature: Home

	Scenario Outline: Planetarium Visibility - Only logged in Users can access Planetarium home page -
	Users must log in to see their Planetarium home page
		Given The User is on the Login Page
		Given User with username "<Username>" <User Exists> in database
		When The user provides username "<Username>"
		And User provides password "<Password>"
		And User clicks the login button
		Then User is logged in <Login Successful>, if not then they receive an alert due to invalid credential input
		Then User is able to see their Planets and Moons added to their Planetarium : <Planetarium Visibility>

	Examples: 
		| Username | Password       | User Exists   | Login Successful | Planetarium Visibility |
		| Batman   | I am the night | Exists        | Yes              | Yes                    |
		| John     | Doe            | Doesn't Exist | No               | No                     |