package tests;
import static io.restassured.RestAssured.*;
import static org.testng.Assert.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.testng.annotations.Test;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import utilities.Config;

public class API_GSON {
	
	@Test
	public void testWithJsonToHashMap() {
		Response response = given().accept(ContentType.JSON)
		.when().get(Config.getProperty("hrapp.baseresturl")+"/employees/120");
		
		// convert the response object to a HashMap class
		HashMap<String, String> map = response.as(HashMap.class);
		
		// to get all the keys of map
	//	System.out.println(map.keySet());
		// to get all the values of map
	//	System.out.println(map.values());
		
		assertEquals(map.get("employee_id"), 120);
	}
	@Test
	public void convertJsonToListOfMaps() {
		Response response = given().accept(ContentType.JSON)
				.when().get(Config.getProperty("hrapp.baseresturl")+"/employees");
		// convert the response that contains department information into list of maps
		// List<Map<String, String>>
		
		List<Map> items = response.jsonPath().getList("items", Map.class);
		System.out.println(items);
		
//		ObjectMapper object = new ObjectMapper();
//		List<Map> items = object.convertValue(response, List.class);
		
		// assert that first job_id is "IT_PROG"
		// you go inside of the list and then inside of the map
		assertEquals(items.get(0).get("job_id"), "IT_PROG");
	}
}
