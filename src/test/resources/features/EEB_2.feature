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


	Scenario Outline: User Registration - API Response Status
	Validating the application gives the appropriate response status based on the given user registration request.
		Given A User is attempting to create an account
		When Username "<Username>" is entered in the POST call
			"""
			"<Username>"
			"""
		And Password <"Password>" is entered in the POST call
			"""
			<"Password>"
			"""
		Then A response status of <Status> should be given
		And The user account is <Creation Result>

		Examples:
			| Username                                   | Password                                   | Status | Creation Result |
			| TheBatman                                  | I am the night                             | 400    | not created     |
			| BatmanAndRobin                             | JokerAndHarley                             | 201    | created         |
			|                                            | JokerAndHarley                             | 201    | created         |
			| BatmanAndRobin                             |                                            | 201    | created         |
			| BatmanAndRobinNaNaNaNaNaNaNaNaNaNaNaNaNaNa | JokerAndHarley                             | 400    | not created     |
			| BatmanAndRobin                             | JokerAndHarleyHaHaHaHaHaHaHaHaHaHaHaHaHaHa | 400    | not created     |

	Scenario: User Registration - All the elements on the browser are displayed correctly
	NotAssigned;	The elements on the page for user registration are there and displayed correctly
		Given User is on the registration page
			"""
			http://localhost:8080/register
			"""
		Then The register document returns a 200 status
			"""
			200
			"""
		Then The Create Account header is there
		And Username and Password fields are there
		And The Create button is there for the user to submit their new account details

	Scenario Outline: User Registration - Correct Database Implementation
	Validating the application adds a newly registered user to the database correctly. (Stretch: the password is not in plaintext)
		Given A User is attempting to create an account
		When User provides a username of "<Username>"
		And User provides a password of "<Password>"
		And User clicks the Register button
		Then The user account is <Database Result>

		Examples:
			| Username       | Password       | Database Result                                  |
			| TheBatman      | I am the night | added successfully and correctly to the database |
			| BatmanAndRobin | JokerAndHarley | not added to the database                        |