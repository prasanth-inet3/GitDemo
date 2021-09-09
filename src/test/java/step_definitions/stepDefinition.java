package step_definitions;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import pojo.Add_Place;
import pojo.Location;
import resources.APIResources;
import resources.TestDataBuild;
import resources.Utils;
import resources.demo;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

public class stepDefinition extends Utils {
	RequestSpecification res;
	ResponseSpecification resp;
	Response response;
	
	TestDataBuild td_build = new TestDataBuild();
	static String place_id;
	//demo d1 = new demo();
	
	@Given("Add place payload with {string} {string} {string}")
	public void add_place_payload_with(String name, String language, String address) throws IOException   {

		res = given().spec(requestSpecifications()).body(td_build.addPlacePayLoad(name, language, address));

	}

	@When("user calls {string} with {string} http request")
	public void user_calls_with_http_request(String resource,String method) {
		
		APIResources resourceAPI = APIResources.valueOf(resource);
		System.out.println(resourceAPI.getAPIResources());
		//constructor will be called with value of resource you pass.
		
		if (method.equalsIgnoreCase("POST")) {
			response = res.when().post(resourceAPI.getAPIResources());
		}else if (method.equalsIgnoreCase("GET")) {
			response = res.when().get(resourceAPI.getAPIResources());
					
		}
		//response = res.when().post(resourceAPI.getAPIResources()).then().spec(resp).extract().response();

	}

	@Then("API call got success with status code {int}")
	public void api_call_got_success_with_status_code(Integer int1) {
		
		resp = new ResponseSpecBuilder().expectStatusCode(200)
				.expectContentType(ContentType.JSON).build();
		assertEquals(response.getStatusCode(), 200);

	}
	
	@Then("{string} in response body is {string}")
	public void in_response_body_is(String keyValue, String expValue) {
		assertEquals(getJsonPath(response, keyValue), expValue);
	}

	
	@Then("verify place_Id created maps to {string} using {string}")
	public void verify_place_id_created_maps_to_using(String exp_name, String resource) throws IOException {
	    // Write code here that turns the phrase above into concrete actions
		
		place_id = getJsonPath(response, "place_id");
		res  = given().spec(requestSpecifications()).queryParam("place_id", place_id);
		user_calls_with_http_request(resource, "GET");
		String actualname = getJsonPath(response, "name");
		assertEquals(actualname, exp_name);
	  		
	}

	@Given("DeletePlace Payload")
	public void delete_place_payload() throws IOException {
	    // Write code here that turns the phrase above into concrete actions
	    given().spec(requestSpecifications()).body(td_build.deletePlacePayload(place_id));
	    
	    
	    
	    
	    
	}
}
