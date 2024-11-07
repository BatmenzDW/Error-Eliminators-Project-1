@JREQ-EEB-3
Feature: Users should be able to securely access their account

	@EEB-TC-3
	Scenario Outline: User Login - Unique Username
	Validating users can log in to their account utilizing their unique username with matching password, and if a different existing username is provided with their password they should not be logged in.
		Given User is on the Login Page
			"""
			<Login Page URL>
			"""
		When The user provides username <Username> and password <Password>
			"""
			Username: <Username>
			Password: <Password>
			"""
		And User clicks the login button
		Then The user should receive the result <Account Login Result> and <Redirect>

	Examples: 
		| Login Page URL         |
		| http://localhost:8080/ |

	@EEB-TC-5
	Scenario: User Login - Password Visibility
	Validating users can login to their account securely with their password not being visible in plaintext.
		Given The User is on the Login Page
			"""
			http://localhost:8080/
			"""
		When The user provides username <Username> and password <Password>
			"""
			Username: <Username>
			Password: <Password>
			"""
		And User clicks the login button
		Then The user should receive the result <Account Login Result> and <Redirect>