package com.task.base;


import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.internal.common.assertion.Assertion;
import io.restassured.response.Response;
import static io.restassured.RestAssured.given;

public class RestAssuredBase {
    public static void main(String[] args){

        Response response = createUserRequest("/api/users" , "{,}" , "POST");
        System.out.println(response.getBody().print());
    }
    public static void restAssuredSetUp(){
        RestAssured.baseURI = "https://reqres.in";
    }
    public static Response createUserRequest(String URI , String body, String method){
        restAssuredSetUp();
        Response response = given()
                .log()
                .all()
                .header("Content-type", "application/json")
                .and()
                .body(body)
                .when()
                .post(URI)
                .then()
                .extract().response();
        System.out.println(response.getStatusCode());
        //Assertions.assertEquals(200, response.statusCode());


        return response;
    }

    public static Response retrieveUserRequest(String URI , String method, String pathParam){
        restAssuredSetUp();
        Response response = given()
                .log()
                .all()
                .contentType(ContentType.JSON)
                .pathParam("id", pathParam)
                .when()
                .get(URI)
                .then()
                .extract().response();
        return response;
    }

    public static String createUserRequestBody(String name, String job){
        return "{\n" +
                " \"name\": \n" +
                "\""+ name+"\",\n" +
                " \"job\": \n" +
                "\"" + job+"\"\n" +
                "}\n";
    }
}
