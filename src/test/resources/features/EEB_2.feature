Feature: User Registration

	Scenario Outline: User Registration - Unique Username -
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

	Scenario Outline: User Registration - Username/Password Length -
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