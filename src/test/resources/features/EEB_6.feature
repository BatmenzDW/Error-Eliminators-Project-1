@JREQ-EEB-6
Feature: Users should be able to remove Planets from the Planetarium

	@EEB-TC-12
	Scenario Outline: Deleting Planets - Unique Name
	Validating users can delete a planet only when they provide the name of planet that exists in the Planetarium, and the action is rejected when they provide the name of a unique planet not in the database.
		Given User is on the Home Page
			"""
			http://localhost:8080/planetarium
			"""
		When User clicks Planet from the dropdown
		And User enters planet name "<Planet Name>"
		And User clicks the delete button
		Then Planet "<Planet Name>" is "<Result>"

	Examples: 
		| Planet Name | Result               |
		| Nessus      | not deleted          |
		| Earth       | deleted successfully |

	@EEB-TC-13
	Scenario Outline: Deleting Planets - Planet Ownership
	Validating that only the user who "owns" the planet is able to delete a planet and the process is rejected when a different user attempts to do so.
		Given User "<Username>" is on the Home Page
			"""
			http://localhost:8080/planetarium
			"""
		When User clicks Planet from the dropdown
		And User enters planet name "<Planet Name>" having Owner Id 1
		And User clicks the delete button
		Then Planet "<Planet Name>" is "<Result>"

	Examples: 
		| Username       | Planet Name | Result               |
		| Batman         | Earth       | deleted successfully |
		| BatmanAndRobin | Earth       | not deleted          |

	@EEB-TC-14
	Scenario Outline: Deleting Planets - Planet Ownership Pt. 2
	Validating that after a user creates a planet, that planet can only be deleted by said user and the action is prevented if attempted by another user.
		Given User "<Username>" is on the Home Page
			"""
			http://localhost:8080/planetarium
			"""
		When User clicks Planet from the dropdown
		And User enters planet name "<Planet Name>" having Owner Id 1
		And User clicks the delete button
		Then Planet "<Planet Name>" is "<Result>"

	Examples: 
		| Username       | Planet Name | Result               |
		| Batman         | Nessus      | deleted successfully |
		| BatmanAndRobin | Nessus      | not deleted          |