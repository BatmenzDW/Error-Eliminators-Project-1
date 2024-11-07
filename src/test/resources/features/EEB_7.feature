@JREQ-EEB-7
Feature: Users should be able to add Moons to the Planetarium associated with a Planet

	@EEB-TC-7
	Scenario Outline: User Interaction - Add Moons
	
 | Planet and Moon names should not have more than 30 characters | 
 | Planets and moons should have unique names | 
 | Moons should be "owned" by the Planet and the User adding the moon associated with it | 
 | Planets and Moons should allow adding an associated image, but an image should not be required for the data to be added to the database | 
		Given User is on the Home Page
			"""
			http://localhost:8080/planetarium
			"""
		And User is logged in
		When User selects moon from Dropdown
		When User inputs "<Moon Name>" in the moon name section with assiociated planet ID  "<Planet ID>"
		When User attaches moon image: <photo attached>
		Then Moon is created in DB with image in DB

	Examples: 
		| Moon Name     | Planet ID | Photo Attached |
		| moonerEclipse | 1         | yes            |
		| Luna          | 1         | yes            |
		| Luna          | 2         | yes            |
		| <null>        | 1         | yes            |
		| Titan         | 2         | yes            |
		| Sun           | 1         | no             |