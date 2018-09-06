package step_def;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import static io.restassured.RestAssured.*;
import static org.testng.Assert.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import utilities.Config;

public class API_PostAnEmployee {
	
	// interface
	RequestSpecification request;
	int employeeId;
	Response response;
	String url = Config.getProperty("hrapp.baseresrurl") + "/employees/";	
	Map reqEmployee;
	
	@Given("Content type and Accept type is Json")
	public void content_type_and_Accept_type_is_Json() {
	  request = given().accept(ContentType.JSON)
			    .and().contentType(ContentType.JSON);
	}

	@When("I post a new Employee with \"([^\"]*)\" id")
	public void i_post_a_new_Employee_with_id(String id) {
	   if(id.equals("random")) {
		   employeeId = new Random().nextInt(999999);
	   }else {
		   employeeId = Integer.parseInt(id);
	   }
	   
	   reqEmployee = new HashMap();
	   reqEmployee.put("employee_id", employeeId);
	   reqEmployee.put("first_name", "PostName");
	   reqEmployee.put("last_name", "PostLastName");
	   reqEmployee.put("email", "EM"+employeeId);
	   reqEmployee.put("manager_id", null);
	   
	   response = request.body(reqEmployee)
			      .post(url);
	}

	@Then("Status code is (\\d+)")
	public void status_code_is(int statusCode) {
	   assertEquals(response.statusCode(), statusCode);
	}

	@Then("Response Json should contain Emloyee info")
	public void response_Json_should_contain_Emloyee_info() {
	   Map postResEmployee = response.body().as(Map.class);  
	   for(Object key: reqEmployee.keySet()) {
		//   System.out.println(postResEmployee.get(key) + " <> " + reqEmployee.get(key));
		   assertEquals(postResEmployee.get(key), reqEmployee.get(key));
	   }
	}

	@When("I send a GET request with \"([^\"]*)\" id")
	public void i_send_a_GET_request_with_id(String id) {
	    if(!id.equals("random")) {
	    	employeeId = Integer.parseInt(id);
	    }
		response = given().accept(ContentType.JSON)
	    		   .when().get(url+employeeId);
	}

	@Then("employee Json Response Data should match the posted Json data")
	public void employee_Json_Response_Data_should_match_the_posted_Json_data() {
	    Map getResMap = response.body().as(Map.class);
	    
	    for(Object key: reqEmployee.keySet()) {
	    	assertEquals(getResMap.get(key), reqEmployee.get(key));
	    }
	}
}
