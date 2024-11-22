Feature: User Login

	Scenario Outline: User Login - Unique Username -
	Validating users can log in to their account utilizing their unique username with matching password, and if a different existing username is provided with their password they should not be logged in.
		Given User is on the Login Page
		Given User with username "<Username>" Exists in database
		When The user provides username "<Username>"
		And User provides password "<Password>"
		And User clicks the login button
		Then The user should receive the result <Result>

	Examples: 
		| Username       | Password       | Result                                          |
		| Batman         | I am the night | Logged in successfully, redirected to home page |
		| BatmanAndRobin | I am the night | Alert -  login attempt failed: please try again |

	Scenario Outline: User Login - Password Visibility -
	Validating users can login to their account securely with their password not being visible in plaintext.
		Given User is on the Login Page
		Given User with username "<Username>" Exists in database
		When The user provides username "<Username>"
		And User provides password "<Password>"
		And User clicks the login button
		Then "<Password>" should not be visible in plaintext
		Then The user should receive the result <Result>

	Examples: 
		| Username | Password       | Result                                          |
		| Batman   | I am the night | Logged in successfully, redirected to home page |
		| Batman   | JokerAndHarley | Alert -  login attempt failed: please try again |

	Scenario: User Login  - Login Elements are correctly displayed
	NotAssigned;	All the login elements are correctly displayed in the webpage
		When User navigates to Login page
			"""
			http://localhost:8080/
			"""
		Then The localhost document returns a 200 status
			"""
			200
			"""
		And The Planetarium Login header is there
		And Username and Password fields are there
		And Login button is there on the page
		And Create an Account link is there on the page

	Scenario Outline: User Login - API Response Status
	Validating the application gives the appropriate response status based on the given login request.
		Given A User is attempting to login to their account
		When Username "<Username>" is entered in the POST call
			"""
			"<Username>"
			"""
		And Password <"Password>" is entered in the POST call
			"""
			"<Password>"
			"""
		Then A response status of <Status> should be given
		And The user is <Login Result> logged in

		Examples:
			| Username       | Password       | Status | Login Result     |
			| TheBatman      | I am the night | 201    | successfully     |
			| BatmanAndRobin | JokerAndHarley | 400    | not successfully |

	Scenario Outline: User Logout - API Response Status
	Validating the application gives the appropriate response status based on the given user logout request
		Given A User is attempting to logout of their account
		When A logout request is sent to the application
		Then A response status of <Status> should be given
		And The user is <Logout Result> logged out

		Examples:
			| Status | Logout Result    |
			| 201    | successfully     |
			| 400    | not successfully |