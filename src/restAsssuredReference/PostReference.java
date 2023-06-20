package restAsssuredReference;

import java.time.LocalDateTime;

import org.testng.Assert;

import static io.restassured.RestAssured.given;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

public class PostReference {

	public static void main(String[] args) {
		//Step 1: Declare the BaseURI and RequestBody variables
		String BaseURI = "https://reqres.in/";
		String RequestBody = "{\r\n"
				+ "    \"name\": \"morpheus\",\r\n"
				+ "    \"job\": \"leader\"\r\n"
				+ "}";
		// Step2: Fetch the Request Params
		JsonPath jsp = new JsonPath(RequestBody);
		String req_name = jsp.getString("name");
		String req_job = jsp.getString("job");
		  // Declare the Date format
		LocalDateTime Date = LocalDateTime.now();
		String exp_date = Date.toString().substring(0,10);
		//Step3: Configure the API and fetch the statuscode & responseBody
		RestAssured.baseURI = BaseURI;
		int statuscode = given().header("Content-Type","application/json").body(RequestBody).when().post("/api/users")
		.then().extract().statusCode();
		String responseBody = given().header("Content-Type","application/json").body(RequestBody).when().post("/api/users")
			.then().extract().response().asString();
		
		System.out.println(statuscode);
		System.out.println(responseBody);
		
		//Step4: Parse the responseBody
		JsonPath jspres = new JsonPath(responseBody);
		String res_name = jspres.getString("name");
		String res_job = jspres.getString("job");
		String res_id = jspres.getString("id");
		String res_createdAt = jspres.getString("createdAt");
		res_createdAt = res_createdAt.substring(0,10);
		
		//Validate the responseBody
		Assert.assertEquals(res_name, req_name);
		Assert.assertEquals(res_job, req_job);
		Assert.assertNotNull(res_id);
		Assert.assertEquals(res_createdAt, exp_date);
		
	}

}
