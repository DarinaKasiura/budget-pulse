package tests;

import static org.testng.Assert.*;

import java.util.HashMap;
import java.util.Map;

import org.hamcrest.Matchers;
import org.testng.annotations.Test;

import beans.Country;
import beans.CountryResponse;
import beans.Region;
import beans.RegionResponse;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import utilities.Config;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class HrrestAPIPostRequest {
	/*
	 * Given Content type is Json
	 * And Accept type is Json
	 * When I send POST request to http://34.223.219.142:1212/ords/hr/regions
	 * with request body:
	 * {
	 *      "region_id" : 9800,
	 *      "region_name" : "Dniprodzerzhins'k"
	 * }
	 * Then status code should be 200
	 * And response body should match request body
	 * */
	@Test(enabled=false)
	public void postRequest() {
	//	String postJson = "{\"region_id\" : 9800, \"region_name\" : \"Dnipro\"}";
		Map requestMap = new HashMap<>();
		requestMap.put("region_id", 555);
		requestMap.put("region_name", "Ukraine");
		
	Response response = given().accept(ContentType.JSON)
			            .and().contentType(ContentType.JSON)
			//            .and().body(postJson)
			            .and().body(requestMap)
		                .when().post(Config.getProperty("hrapp.baseresturl") + "\regions");
		                
	System.out.println(response.statusLine());
	response.prettyPrint();
	
	// store response in map
	Map responseMap = response.body().as(Map.class); 
	
	assertEquals(response.statusCode(), 201);
	assertEquals(responseMap.get("region_id"), requestMap.get("region_id"));
	assertEquals(responseMap.get("region_name"), requestMap.get("region_name"));
	}
	
	@Test(enabled=false)
	public void postUsingPOJO() {
		String url = Config.getProperty("hrapp.baseresturl") + "/regions/";
		
		Region region = new Region();
		region.setRegionId(5);
		region.setRegionName("Ukraine");
		
		Response response = given().accept(ContentType.JSON)
			           	    .and().contentType(ContentType.JSON)
				            .and().body(region)
				            .when().post(url);
		
		assertEquals(response.statusCode(), 201);
		
		// de-serialization
		RegionResponse responseRegion = response.body().as(RegionResponse.class);
		
		// match request and response body
		assertEquals(responseRegion.getRegionId(), region.getRegionId());
		assertEquals(responseRegion.getRegionName(), region.getRegionName());
	}
	@Test
	public void postUsingPOJO2() {
		String url = Config.getProperty("hrapp.baseresturl") + "/countries/";
		
		Country country = new Country();
		country.setCountry_id("000");
		country.setCountry_name("Ukraine");
		country.setRegion_id(2);
		
		Response response = given().log().all()
				            .accept(ContentType.JSON)
				            .and().contentType(ContentType.JSON)
				            .and().body(country)
				            .when().post(url);
		
		assertEquals(response.statusCode(), 201);
		
		CountryResponse countryResponse = response.body().as(CountryResponse.class);
		
		assertEquals(country.getCountry_id(), countryResponse.getCountry_id());
		assertEquals(country.getCountry_name(), countryResponse.getCountry_name());
		assertEquals(country.getRegion_id(), countryResponse.getRegion_id());
	}
	/*
	 * Given Content type and Accept type is Json
	 * When I post a new Employee with "1234" id 
	 * Then Status code is 201
	 * And Response Json should contain Emloyee info
	 * When I send a GET request with "1234" id
	 * Then status code is 200
	 * And employee Json Response Data should match the posted Json data
	 * */
	@Test
	public void postEmployeeId() {
		String url = Config.getProperty("hrapp.baserresturl") + "/employees/";
		
		Map postEmployee = new HashMap<>();
		postEmployee.put("employee_id", "1234");
		postEmployee.put("first_name", "Molly");
		postEmployee.put("job_id", "IT_PROG");
		
		Response response = given().accept(ContentType.JSON)
				            .and().contentType(ContentType.JSON)
				            .and().body(postEmployee)
				            .and().post(url);
		
		JsonPath json = response.jsonPath();
		assertEquals(response.statusCode(), 201);
		assertEquals(json.get("employee_id"), postEmployee.get("employee_id"));
		assertEquals(json.getString("first_name"), postEmployee.get("employee_name"));
		assertEquals(json.get("job_id"), postEmployee.get("job_id"));

		// assert if response is equal to request values
		// does the same thing as above JsonPath
		Map responseMap = response.body().as(Map.class);
		for (Object key: responseMap.keySet()) {
			assertEquals(responseMap.get(key), postEmployee.get(key));
		}
		
		when().get(url+"1234")
		.then().assertThat().statusCode(200)
		.and().assertThat().body("items.employee_id", Matchers.equalTo(postEmployee.get("employee_id")))
		.and().assertThat().body("items.employee_name", Matchers.equalTo(postEmployee.get("employee_name")))
		.and().assertThat().body("items.job_id", Matchers.equalTo(postEmployee.get("job_id")));
		
	}
}
