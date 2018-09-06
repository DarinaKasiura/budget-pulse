package tests;

import static org.testng.Assert.*;

import java.net.URI;
import java.net.URISyntaxException;

import org.hamcrest.Matchers;
import org.testng.annotations.Test;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

// static import of all static members of the RestAssured class
import static io.restassured.RestAssured.*;

public class HrRestAPIGetRequest {
 
	/*
	 *  When I send a GET request to REST url http://34.223.219.142:1212/ords/hr/employees
	 *  Then response status should be 200
	 *  
	 * */
  @Test(enabled=false)
  public void simpleGet() {
	  when().get("http://34.223.219.142:1212/ords/hr/employees")
	  .then().statusCode(200);
  }
  /*
   * When I send a GET request to REST url http://34.223.219.142:1212/ords/hr/countries
   * Then I should see JSON response
   * */
  @Test(enabled=true)
  public void printResponse() {
	  when()
	        .get("http://34.223.219.142:1212/ords/hr/employees")
	        .andReturn().body().prettyPrint();
  }
  /*
   * When I send a GET request to REST API url http://34.223.219.142:1212/ords/hr/countries
   * And Accept type (HEADER) is "application/json"
   * Then response status should be 200
   * */
  @Test(enabled=false)
  public void getWithHeaders() {
	  // inside of headers we usually set passwords, usernames
	  // when().get() is sending request to the API that's running on 34.223.219.142 machine.
	  // and API is listening to the port number :1212, if there is a wrong one, then no access
	  // ords --> ords service, hr --> schema, countries --> method, US --> parameter 
	   with().accept(ContentType.JSON) // with this header --> accept - application/json
	                                   // ContentType is enum
	                                   // if you put accept() after get() it means that 
	                                   // it's not belong to request
	  .when()
	        .get("http://34.223.219.142:1212/ords/hr/countries/US")
	        .then().statusCode(200);
  }
  /*
   * When I send a GET request to REST URL: http://34.223.219.142:1212/ords/hr/employees/1234
   * Then status code is 404 
   * */
  @Test(enabled=false)
  public void negativeGet() {
//	  when().get("http://34.223.219.142:1212/ords/hr/employees/1234")
//	        .then().statusCode(404);
	  // Response --> interface: we are sending GET, it's returning response
	  //                         we are storing it in a variable of type Response
	  // Then with an object of Response you can go to inside of your response body, check status, etc.
	  Response response = when().get("http://34.223.219.142:1212/ords/hr/employees/1234");
	  assertEquals(response.statusCode(), 404);
	  assertTrue(response.asString().contains("Not Found"));
	  response.prettyPrint();
  }
  /*
   * When I send a GET request to REST URL: http://34.223.219.142:1212/ords/hr/employees/100
   * And Accept type is json
   * Then status code is 200
   * And Response content should be json
   * */
  @Test
  public void verifyContentTypeWithAssertThat() {
	  String url = "http://34.223.219.142:1212/ords/hr/employees/100";
	  given().accept(ContentType.JSON)
	  .when().get(url)
	  .then().assertThat().statusCode(200)
	  .and().contentType(ContentType.JSON);
  }
  /*
   * Given Accept type is JSON
   * When I send a GET request to REST URL: http://34.223.219.142:1212/ords/hr/employees/100
   * Then status code is 200
   * and Response content should be json
   * and first name should be "Steven"
   * */
  @Test
  public void getFirstName() throws URISyntaxException {
	  URI uri = new URI("http://34.223.219.142:1212/ords/hr/employees/100");
	 
	  given().accept(ContentType.JSON)
	  .when().get(uri)
	                  .then().assertThat().statusCode(200)
	                  .and().contentType(ContentType.JSON)
	                  .and().assertThat().body("first_name", Matchers.equalTo("Ahmet"))
	                  .and().assertThat().body("employee_id", Matchers.equalTo("100")); //Matchers is a class, equalTo() - static method
	  
  }
}









