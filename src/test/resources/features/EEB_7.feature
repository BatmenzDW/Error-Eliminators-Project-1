Feature: Add Moons

	Scenario Outline: User Interaction - Add Moons -
	
 | Planet and Moon names should not have more than 30 characters | 
 | Planets and moons should have unique names | 
 | Moons should be "owned" by the Planet and the User adding the moon associated with it | 
 | Planets and Moons should allow adding an associated image, but an image should not be required for the data to be added to the database | 
		Given User is on the Home Page
		When User selects Planet from Dropdown
		When User selects moon from Dropdown
		When User inputs "<Moon Name>" in the moon name section
		When User inputs associated planet ID "<Planet ID>"
		When User optionally attaches moon image: "<Photo Attached>"
		When User clicks Submit Moon
		Then The moon "<Moon Name>" creation is a <Result>

	Examples: 
		| Moon Name     | Planet ID | Photo Attached | Result  |
		| moonerEclipse | 1         | yes            | Success |
		| Luna          | 1         | yes            | Fail    |
		| Luna          | 2         | yes            | Fail    |
		| <null>        | 1         | yes            | Fail    |
		| Titan         | 2         | yes            | Fail    |
		| Sun           | 1         | no             | Success |