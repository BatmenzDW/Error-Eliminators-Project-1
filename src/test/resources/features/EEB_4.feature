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

	Scenario Outline: Planetarium Visibility - API Response Status
	Validating that the application sends the appropriate API response based on the given request for a user attempting to access the Planetarium Home Page.
		Given The User is on the Login Page
		When User is <Session Result>
		And User directs to the planetarium home page through <URL>
		Then A response status of <Status> should be given
		And User is <URL Result> to the Planetarium

		Examples:
			| Session Result    | URL                               | Status | URL Result                    |
			| already logged in | http://localhost:8080/planetarium | 201    | redirected and granted access |
			| not logged in yet | http://localhost:8080/planetarium | 400    | redirected but denied access  |

	Scenario Outline: User Planetarium - User's planetarium is displayed correctly
	The user planetarium is displayed with the correct user and celestial body
		Given The User is on the Login Page
			"""
			http://localhost:8080
			"""
		When User enters username <Username> and password <Password>
		Then User clicks the login button
		Then Elements are returned with 200 status
		And Header for the home page with <Username> is there
		And The background image for the home page is there
		And Drop down for planet selector, celestial body deletion field, and deletion is there
		When Selecting the dropdown for moon or planet the fields for the corresponding one is there
		And Attach image is there with the submit button
		And Associated celestial bodies for the user is there
		And Log out button appears on the webpage

		Examples:
			| Username  | Password       |
			| TheBatman | I am the night |