@JREQ-EEB-2
Feature: Users should be able to open a new User account with the Planetarium

	@EEB-TC-1
	Scenario Outline: User Registration - Unique Username
		Given User is on the Login Page
		And User with username "<Username>" <User Exists> in database
		When User clicks Create Account
		Then User is taken to the Account Creation Page
		When User provides a username of "<Username>"
		And User provides a password of "<Password>"
		And User clicks the Register button
		Then The User is given an Alert with text "<Alert Text>"
		And Account Creation <Result>

	Examples: 
		| Username | User Exists   | Password       | Alert Text                                 | Result                               |
		| Batman   | Doesn't Exist | I am the night | Account created successfully with username | Success and User Redirected to Login |
		| Batman   | Exists        | I am the night | Account creation failed with username      | Failed                               |

	@EEB-TC-5
	Scenario Outline: User Login - Password Visibility
	Validating users can login to their account securely with their password not being visible in plaintext.
		Given User is on the Login Page
		Given User with username "<Username>" Exists in database
		When The user provides username "<Username>"
		And User provides password "<Password>"
		And User clicks the login button
		Then Password should not be visible in plaintext
		Then The user should receive the result <Result>

	Examples: 
		| Username | Password       | Result                                          |
		| Batman   | I am the night | Logged in successfully, redirected to home page |
		| Batman   | JokerAndHarley | Alert -  login attempt failed: please try again |

	@EEB-TC-6
	Scenario Outline: User Registration - Username/Password Length
		Given User is on the Login Page
		When User clicks Create Account
		Then User is taken to the Account Creation Page
		When User provides a username of "<Username>"
		And User provides a password of "<Password>"
		And User clicks the Register button
		Then The User is given an Alert with text "<Alert Text>"
		And Account Creation <Result>

	Examples: 
		| Username                                   | Password                                   | Alert Text                                 | Result                               |
		| BatmanAndRobin                             | JokerAndHarley                             | Account created successfully with username | Success and User Redirected to Login |
		| BatmanAndRobinNaNaNaNaNaNaNaNaNaNaNaNaNaNa | JokerAndHarley                             | Account creation failed with username      | Failed                               |
		| BatmanAndRobin                             | JokerAndHarleyHaHaHaHaHaHaHaHaHaHaHaHaHaHa | Account creation failed with username      | Failed                               |
		|                                            | JokerAndHarley                             | Account created successfully with username | Success and User Redirected to Login |
		| BatmanAndRobin                             |                                            | Account created successfully with username | Success and User Redirected to Login |