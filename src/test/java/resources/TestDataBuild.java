package resources;

import java.util.ArrayList;
import java.util.List;

import pojo.Add_Place;
import pojo.Location;

public class TestDataBuild {
	
	public Add_Place addPlacePayLoad(String name, String language, String address) {
		// body is wrapped using the POJO classes.
					Add_Place add_place = new Add_Place();
					add_place.setAccuracy(50);
					add_place.setAddress(address);
					add_place.setLanguage(language);
					add_place.setName(name);

					// array object the classes creation is done with return type be List<String>
					List<String> myList = new ArrayList<String>();
					myList.add("shoe park");
					myList.add("shop");
					add_place.setTypes(myList);

					// class creation is done for the nestedJSON and with return type in the main
					// object class
					// be Location' class.
					Location loc = new Location();
					loc.setLat(-38.383494);
					loc.setLng(33.427362);
					add_place.setLocation(loc);

					add_place.setPhone_number("(+91) 983 893 3937");
					add_place.setWebsite("http://google.com");
					return add_place;
							
	}
	
	public String deletePlacePayload(String placeId) {
		
		return "{\r\n"
				+ "     \"place_id\": \""+placeId+"\"\r\n"
				+ "\r\n"
				+ "}";
	}

	public String updatePlacePayload(String id) {

		return id;
	}
	
	public String updateBookAisle(String bookID) {
		
		return bookID;
	}
	
	public int getBookAisle(int count) {

		return count;
	}
	
}
