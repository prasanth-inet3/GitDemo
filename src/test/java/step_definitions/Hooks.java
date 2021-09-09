package step_definitions;

import java.io.IOException;
import io.cucumber.java.Before;

public class Hooks {
	
	@Before("@DeletePlace")
	public void beforeScenario() throws IOException {
		//Here execute the code only if the place_id is null.
		//Write code that gives you the place_id
		
		if(stepDefinition.place_id==null) {
			
			stepDefinition m = new stepDefinition();
			m.add_place_payload_with("rhea", "french", "palkStrait");
			m.user_calls_with_http_request("addPlaceAPI", "POST");
			m.verify_place_id_created_maps_to_using("rhea", "getPlaceAPI");
			
		}
		
	}
	
	
	
}
