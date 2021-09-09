Feature: Validating Place APIs
@AddPlace @Regression
Scenario Outline: Verify if place is being successfully added using addPlace API
	Given Add place payload with "<name>" "<language>" "<address>"
	When user calls "addPlaceAPI" with "POST" http request
	Then API call got success with status code 200
	And "status" in response body is "OK"
	And "scope" in response body is "APP"
	And verify place_Id created maps to "<name>" using "getPlaceAPI"

Examples: 
	|name 	   |language	|address		 |
	|tom cruise|english 	|worldfare center|
	|Beacon    |spanish		|seafarer center |

@DeletePlace @Regression
Scenario: Verify if deletePlace functionality is working

	Given DeletePlace Payload
	When  user calls "deletePlaceAPI" with "POST" http request
	Then  API call got success with status code 200
	And   "status" in response body is "OK"
		