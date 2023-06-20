package restAsssuredReference;

import io.restassured.RestAssured;

import static io.restassured.RestAssured.given;

public class DeleteReference {

	public static void main(String[] args) {
		
		//Step1: Declare Base URI
		RestAssured.baseURI="https://reqres.in/";
		
		//Step2: Configure Request body
		int statusCode=given().header("Content-Type","application/json").when()
				.delete("/api/users/2").then().extract().statusCode();
		
		//Step3: Fetch the StatusCode
		System.out.println("The status code is: "+ statusCode + " No Content");
		
	}
	
}