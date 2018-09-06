package tests;

import org.testng.annotations.Test;

import bsh.org.objectweb.asm.Type;

import static org.testng.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.hamcrest.Matchers;

import static io.restassured.RestAssured.*;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import utilities.Config;

public class API_JsonPath {
	
	/*
	 * Given Accept type is JSON
	 * When I send a GET request to REST URL: http://34.223.219.142:1212/ords/hr/regions
	 * Then status code is 200
	 * And Response content should be JSON
	 * And 4 regions should be returned
	 * And Americas is one of the regions names
	 * */
  @Test(enabled=false)
  public void test() {
	  given().accept(ContentType.JSON) 
	  .when().get(Config.getProperty("hrapp.baseresturl")+"/regions") 
	  .then().assertThat().statusCode(200) 
	  .and().assertThat().contentType(ContentType.JSON) 
	  .and().assertThat().body("items.region_id", Matchers.hasSize(25))
	  .and().assertThat().body("items.region_name", Matchers.hasItem("NOT Murodil's Region"))
	  .and().assertThat().body("items.region_name", Matchers.hasItems("NOT Murodil's Region", "murodil's region"));
  }
  /*
   * Given Accept type is JSON
   * When I send a GET request to REST URL: http://34.223.219.142:1212/ords/hr/regions
   * Then status code is 200
   * And Response content should be json
   * And 100 employees data should be in json response body
   * */
  @Test(enabled=false)
  public void testWithQueryParameterAndList() {
	  given().accept(ContentType.JSON)
	  .and().params("limit", 100)
	  .when().get(Config.getProperty("hrapp.baseresturl")+"/employees")
	  .then().statusCode(200)
	  .and().contentType(ContentType.JSON)
	  .and().assertThat().body("items.employee_id", Matchers.hasSize(100));
  }
  /*
   * Given Accept type is Json
   * And Params are limit=100
   * And path param is 110
   * When I send GET request to http://34.223.219.142:1212/ords/hr/employees
   * Then status code is 200
   * And Response content should be json
   * And following data should be returned:
   * "employee_id": 110,
   * "first_name": "John",
   * "last_name": "Chen",
   * "email": "JCHEN",
   * */
  @Test(enabled=false)
  public void testWithParameter() {
	  given().accept(ContentType.JSON)
	  .and().param("limit", 100)
	  .and().pathParam("employee_id", 110)
	  .when().get(Config.getProperty("hrapp.baseresturl")+ "/employees/{employee_id}")
	  .then().assertThat().statusCode(200)
	  .and().assertThat().contentType(ContentType.JSON)
	  .and().assertThat().body("employee_id", Matchers.equalTo(110))
	  .and().assertThat().body("first_name", Matchers.equalTo("Ahmet"))
	  .and().assertThat().body("last_name", Matchers.equalTo("Turkkahraman"))
	  .and().assertThat().body("email", Matchers.equalTo("EM110"));
  }
  /*
   * Given Accept type is Json
   * And Params are limit=100
   * When I send get request to http://34.223.219.142:1212/ords/hr/employees
   * Then status code is 200
   * And Response content should be json
   * all emolyee_ids should be returned
   * */
  @Test(enabled=false)
  public void testWithJsonPath() {
	  Map<String, Integer> rParamMap = new HashMap<>();
	  rParamMap.put("limit", 100);
	  
	  Response response = given().accept(ContentType.JSON) //header
	                      .and().params(rParamMap) // query/request parameter
	                      .and().pathParam("employee_id", 177) // path parameter
	                      // getting the response and storing it in 'response' variable
	                      .when().get(Config.getProperty("hrapp.baseresturl")+"/employees/{employee_id}");
	  
	  JsonPath json = response.jsonPath(); // get json body and assign to jsonPath object
	  
	  System.out.println(json.getInt("employee_id"));
	  System.out.println(json.getString("first_name"));
	  System.out.println(json.getString("last_name"));
	  System.out.println(json.getInt("salary"));
	// get the first index value from links array
	  System.out.println(json.getString("links[0].href")); 
	  
	// assign all hrefs into a list of strings
	  List<String> hrefs = json.getList("links.href");
	  System.out.println(hrefs);
  }
  /*
   * Given Accept type is Json
   * And Params are limit=100
   * When I send get request to http://34.223.219.142:1212/ords/hr/employees
   * Then status code is 200
   * And Response content should be json
   * All employee data should be returned
   * */
  @Test(enabled=false)
  public void testJsonWithLists() {
	  Map<String, Integer> rParamMap = new HashMap<>();
	  rParamMap.put("limit", 100);
	  
	  Response response = given().accept(ContentType.JSON) //header
	                      .and().params(rParamMap) // query/request parameter
	                      // getting the response and storing it in 'response' variable
	                      .when().get(Config.getProperty("hrapp.baseresturl")+"/employees");
	  assertEquals(response.statusCode(), 200); // check status code
// JsonPath json = new JsonPath(response.asString()); 
//JsonPath json = new JsonPath(new File(FilePath.json)); <-- it will store the json object in json file
// or!!!
	  JsonPath json = response.jsonPath();
	  
	  // store all emplyee_ids in a list
	  List<Integer> employeeIds = json.getList("items.employee_id");
	  System.out.println(employeeIds);
	  
	  // assert that there are 100 employee_ids
	  assertEquals(employeeIds.size(), 100);
	  
	  // store all the emails into list
	  List<String> emails = json.getList("items.email");
	  System.out.println(emails);
	  
	  //get all employee ids that are grater than 150
	  List<String> empIdList = json.getList("items.findAll{it.employee_id > 150}.employee_id");
	  System.out.println(empIdList);
	  
	  // get all emmployee last names, whose salary is more than 7000
	  List<String> empLastNamesList = json.getList("items.findAll{it.salary > 7000}.last_name");
	  System.out.println(empLastNamesList);
  }
  /*
   * Given Content Type is Json
   * And Limit is 10
   * When I send request to Rest API url: http://34.223.219.142:1212/ords/hr/regions
   * Then I should see following data
   * */
  @Test
  public void warmUpTask() {
	  Response response = given().accept(ContentType.JSON)
			              .and().params("limit", 10)
			              .when().get(Config.getProperty("hrapp.baseresturl")+"/regions");
	  JsonPath json = response.jsonPath();
	  
	  // version 1
	  assertEquals(json.getInt("items[0].region_id"), 1);
	  assertEquals(json.getString("items[0].region_name"), "Europe");
	  // and so on
	  
	  // version 2
	  Map<Integer, String> expectedResult = new HashMap<>();
	  expectedResult.put(349, "Americas");
	  expectedResult.put(4023,"Asia");
	  expectedResult.put(1110,"NOT Murodil's Region");
	  expectedResult.put(2301,"NOT Murodil's Region");
	  expectedResult.put(25642,"NOT Murodil's Region");
	  expectedResult.put(232323,"NOT Murodil's Region");
	  expectedResult.put(55153,"NOT Murodil's Region");
	  expectedResult.put(11123,"NOT Murodil's Region");
	  expectedResult.put(23687,"NOT Murodil's Region");
	  expectedResult.put(-304652931,"NOT Murodil's Region");
	 
	  List<Map> regions = json.getList("items", Map.class);
	  
		for(Integer each: expectedResult.keySet()) {  
		  for(Map map: regions) {
		  if(map.get("region_id") == each) {
			  assertEquals(map.get("region_name"), expectedResult.get(each));
		  } } } 
		
		// version 3
		List<String> testingData = Arrays.asList("1 Europe", "2 Americas");
		List<String> regionNames = new ArrayList<>();
		
		for(Object item: json.getList("items")) {
			regionNames.add(((HashMap) item).get("region_id").toString() + " " + ((HashMap) item).get("region_name").toString());
		}
		assertTrue(regionNames.containsAll(testingData));
		
		// version 4
		Map<Integer, String> expectedValues = new HashMap<>();
		expectedValues.put(1, "Europe");
		expectedValues.put(2, "Americas");
		expectedValues.put(3, "Asia");
		expectedValues.put(3, "Middle East and Africa");
		assertEquals(response.statusCode(), 200);
		
		for(int i=0; i < 4; i++) {
			assertEquals(json.getInt("items[" + i + "].region_id"), i+1);
			assertEquals(json.getString("items[" + i + "].region_name"), expectedValues.get(i + 1));
		}
		
  }
  
}
