package resources;

//enum is a special class in java that has a collection of constants and methods.
public enum APIResources {
	
	addPlaceAPI("/maps/api/place/add/json"),
	getPlaceAPI("/maps/api/place/get/json"),
	deletePlaceAPI("/maps/api/place/delete/json");
	private String resource;
	
	APIResources(String resource) {
		this.resource = resource;
		//Here the resource is passed as constructor to the local field variable and
		//updated with the string arguments. resource'
	}
	
	public String getAPIResources() {
		
		return resource;
	}
		
}
