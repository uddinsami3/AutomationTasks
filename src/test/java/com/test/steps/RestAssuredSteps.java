package com.test.steps;

import com.task.base.RestAssuredBase;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.When;

import cucumber.api.java.en.Then;
import io.restassured.response.Response;
public class RestAssuredSteps extends RestAssuredBase {
    public static String requestBody;
    public static String id;
    public static String name;
    public static String job;
    Response response;
    //RestAssuredBase restAssuredBase = new RestAssuredBase();
    @Given("^\"([^\"]*)\" and \"([^\"]*)\" of the user to be created$")
    public void and_of_the_user_to_be_created(String arg1, String arg2) throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        name = arg1;
        job = arg2;
        requestBody = createUserRequestBody(arg1 , arg2);
        System.out.println("request body is: " + requestBody);
    }

    @When("^trigger request with method \"([^\"]*)\" and endpoint \"([^\"]*)\" and valid request body$")
    public void trigger_request_with_method_and_endpoint_and_valid_request_body(String arg1, String arg2) throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        response = createUserRequest(arg2 ,requestBody, arg1);
        System.out.println("status code is: " + response.getStatusCode());
        System.out.println("response body is: " + response.getBody().print());
    }

    @Then("^filter id generated in the response$")
    public void filter_id_generated_in_the_response() throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        id = response.jsonPath().getString("id");
        System.out.println("filtered id is: " + id);
    }

    @Then("^validate name and job of the user$")
    public void validate_name_and_job_of_the_user() throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        String filteredName = response.jsonPath().getString("name");
        String filteredJob = response.jsonPath().getString("job");
        System.out.println(filteredName.contentEquals(name));
        System.out.println(filteredJob.contentEquals(job));

    }

    @Then("^trigger request with method \"([^\"]*)\" and endpoint \"([^\"]*)\"$")
    public void trigger_request_with_method_and_endpoint(String arg1, String arg2) throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        response = retrieveUserRequest(arg2 , arg1 , "6");
        System.out.println("response body is: " + response.getBody().print());
        System.out.println("status code is: " + response.getStatusCode());

    }

    @Then("^validate user details in the response$")
    public void validate_user_details_in_the_response() throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        String filtedredId = response.jsonPath().getString("data.id").toString();
        System.out.println(filtedredId.contentEquals("6"));
    }

    @When("^trigger request with method \"([^\"]*)\" and endpoint \"([^\"]*)\" and no request body$")
    public void trigger_request_with_method_and_endpoint_and_no_request_body(String arg1, String arg2) throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        response = createUserRequest(arg2 ,"", arg1);
        System.out.println("status code is: " + response.getStatusCode());
        System.out.println("response body is: " + response.getBody().print());
    }

    @Then("^validate the invalid response and response code \"([^\"]*)\"$")
    public void validate_the_invalid_response_and_response_code(String arg1) throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        String statusCode = String.valueOf(response.getStatusCode());
        System.out.println(statusCode.contentEquals(arg1));
    }

    @When("^trigger request with method \"([^\"]*)\" and endpoint \"([^\"]*)\" and invalid json request$")
    public void trigger_request_with_method_and_endpoint_and_invalid_json_request(String arg1, String arg2) throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        response = createUserRequest(arg2 ,"{,}", arg1);
        System.out.println("status code is: " + response.getStatusCode());
        System.out.println("response body is: " + response.getBody().print());
    }

    @When("^trigger request with method \"([^\"]*)\" and endpoint \"([^\"]*)\" and null name field$")
    public void trigger_request_with_method_and_endpoint_and_null_name_field(String arg1, String arg2) throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        requestBody = createUserRequestBody("" , "john");
        response = createUserRequest(arg2 ,requestBody, arg1);
        System.out.println("status code is: " + response.getStatusCode());
        System.out.println("response body is: " + response.getBody().print());

    }


}
