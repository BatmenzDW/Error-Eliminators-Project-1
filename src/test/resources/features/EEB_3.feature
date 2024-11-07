@JREQ-EEB-3
Feature: Users should be able to securely access their account

	@EEB-TC-3
	Scenario Outline: User Login - Unique Username
	Validating users can log in to their account utilizing their unique username with matching password, and if a different existing username is provided with their password they should not be logged in.
		Given User is on the Login Page
			"""
			<Login Page URL>
			"""
		When The user provides username "<Username>"
			"""
			Username: <Username>
			"""
		And User provides password "<Password>"
			"""
			Password: <Password>
			"""
		And User clicks the login button
		Then The user should receive the result "<Result>"

	Examples: 
		| Username       | Password       | Result                                          |
		| Batman         | I am the night | Logged in successfully, redirected to home page |
		| BatmanAndRobin | I am the night | Alert -  login attempt failed: please try again |

	@EEB-TC-5
	Scenario Outline: User Login - Password Visibility
	Validating users can login to their account securely with their password not being visible in plaintext.
		Given User is on the Login Page
			"""
			<Login Page URL>
			"""
		When The user provides username "<Username>"
			"""
			Username: <Username>
			"""
		And User provides password "<Password>"
			"""
			Password: <Password>
			"""
		And User clicks the login button
		Then The user should receive the result <Result>

	Examples: 
		| Username | Password       | Result                                          |
		| Batman   | I am the night | Success and User Redirect to Home Page          |
		| Batman   | JokerAndHarley | Alert -  login attempt failed: please try again |