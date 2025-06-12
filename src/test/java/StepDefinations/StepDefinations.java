package StepDefinations;

import static io.restassured.RestAssured.given;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import resources.APIResources;
import resources.TestDataBuild;
import resources.Utils;

import java.io.IOException;
import java.util.Arrays;

import Pojo.AddPet;
import Pojo.Addcategory;
import Pojo.Addtags;
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
import static org.junit.Assert.*;
import static io.restassured.RestAssured.*;
import io.restassured.RestAssured;

public class StepDefinations extends Utils {
	
	    ResponseSpecification resSpec;
	    RequestSpecification request;
	    Response response;
	    private int petId;
	    TestDataBuild data = new TestDataBuild();

	    @Given("I have a valid pet payload with {int} {string} {string}")
	    public void i_have_a_valid_pet_payload_with(int id, String name, String status) throws IOException {
	        // Prepare payload for POST or PUT
	        request = given().spec(requestSpecification()).body(data.AddPet(id, name, status));
	        petId = id;
	        resSpec = new ResponseSpecBuilder()
	                .expectStatusCode(200)
	                .expectContentType(ContentType.JSON)
	                .build();
	    }

	    @Given("I have added a pet with id {int} name {string} status {string}")
	    public void i_have_added_a_pet_with_id_name_status(int id, String name, String status) throws IOException {
	        // Create pet to ensure it exists
	        AddPet petPayload = data.AddPet(id, name, status);
	        response = given().spec(requestSpecification()).body(petPayload).when().post("/pet");
	        assertEquals(200, response.getStatusCode());
	        petId = id;
	    }
	    
	    @Given("I have a valid pet payload with {int} {string} {string} for update")
	    public void i_have_a_valid_pet_payload_with_for_update(int id, String name, String status) throws IOException {
	        // Prepare updated payload for PUT
	        request = given().spec(requestSpecification()).body(data.AddPet(id, name, status));
	        petId = id;
	        resSpec = new ResponseSpecBuilder()
	                .expectStatusCode(200)
	                .expectContentType(ContentType.JSON)
	                .build();
	    }

	    
	    @Given("a pet with ID {int} exists with name {string} and status {string}")
	    public void a_pet_with_id_exists_with_name_and_status(Integer id, String name, String status) throws IOException {
	        // Build the pet payload using your existing TestDataBuild class
	        AddPet petPayload = data.AddPet(id, name, status);

	        // Create a new request specification for the POST call
	        request = given().spec(requestSpecification()).body(petPayload);

	        // Send POST request to create the pet before update
	        response = request.when().post("/pet");
	        assertEquals(200, response.getStatusCode());

	        petId = id;  // Set petId so other steps can use it
	    }


	    @Given("I have a valid pet ID {int}")
	    public void i_have_a_valid_pet_id(Integer id) throws IOException {
	        request = given().spec(requestSpecification());
	        petId = id;
	    }

	    @When("user sends {string} with {string} http request")
	    public void user_sends_with_post_http_request(String resource, String method) throws InterruptedException {
	        APIResources resourceAPI = APIResources.valueOf(resource);
	        String finalResource = resourceAPI.getResource();

	        // Append petId for GET and DELETE
	        if ((method.equalsIgnoreCase("GET") || method.equalsIgnoreCase("DELETE"))
	                && finalResource.equals("/pet")) {
	            finalResource = finalResource + "/" + petId;
	        }

	        System.out.println("Request URL: " + finalResource);

	        if (method.equalsIgnoreCase("POST")) {
	            response = request.when().post(finalResource);
	            Thread.sleep(500); // ensure server processes the creation
	        } else if (method.equalsIgnoreCase("GET")) {
	            response = request.when().get(finalResource);
	        } else if (method.equalsIgnoreCase("DELETE")) {
	            response = request.when().delete(finalResource);
	        } else if (method.equalsIgnoreCase("PUT")) {
	            // Update uses PUT /pet
	            response = request.when().put(finalResource);
	        }

	        System.out.println("Response Status Code: " + response.getStatusCode());
	        System.out.println("Response Body: " + response.asString());
	    }

	    @Then("the response status code should be {string}")
	    public void the_response_status_code_should_be(String expectedStatusCode) {
	        assertEquals(Integer.parseInt(expectedStatusCode), response.getStatusCode());
	    }

	    @Then("the response should contain the {string} and {string}")
	    public void the_response_should_contain_the_and(String key, String expectedValue) {
	        System.out.println("Checking key: " + key + ", Expected: " + expectedValue + ", Actual: " + getJsonPath(response, key));
	        assertEquals(expectedValue, getJsonPath(response, key));
	    }
	}
