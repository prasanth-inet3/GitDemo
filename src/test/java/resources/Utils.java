package resources;

import static io.restassured.RestAssured.given;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Properties;

import io.restassured.RestAssured;

import static io.restassured.RestAssured.*;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

public class Utils {
	
	public static RequestSpecification req;
	public RequestSpecification requestSpecifications() throws IOException {
		if(req==null) {
			PrintStream ps = new PrintStream(new FileOutputStream("logging.txt"));
			
			// Here the authenticity for utility classes are done using the reqnResp spec
			// builder.
			//"https://rahulshettyacademy.com"
			req = new RequestSpecBuilder().setContentType(ContentType.JSON).addFilter(RequestLoggingFilter.logRequestTo(ps)).
					addFilter(ResponseLoggingFilter.logResponseTo(ps)).
					setBaseUri(getGlobalValue("baseurl")).addQueryParam("key", "qaclick123").build();

			return req;
			
		}
		
		return req;
	}
	
	public static String getGlobalValue(String string) throws IOException {
		
		Properties prop = new Properties();
		FileInputStream fis = new FileInputStream("F:\\frameworks_RestAPIs\\api_framework\\src\\test\\java\\resources\\global.properties");
		prop.load(fis);
		return prop.getProperty("baseurl");
		
	}
	
	public String getJsonPath(Response response,String key) {
		
		String respn = response.asString();
		JsonPath js = new JsonPath(respn);
		
		return js.get(key).toString();
		
		
	}
	

}
