package restAsssuredReference;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import static io.restassured.RestAssured.given;
import org.testng.*;
import java.time.LocalDate;

public class PutReference {

	public static void main(String[] args) {
        
        //Configure Request Body & request body variables
        String BaseURI = "https://reqres.in/";
        String requestBody = "{\r\n"
        		+ "    \"name\": \"morpheus\",\r\n"
        		+ "    \"job\": \"zion resident\"\r\n"
        		+ "}";   
        
        //Fetch request body parameter values
        
        JsonPath jsprequest = new JsonPath(requestBody);
        String req_name = jsprequest.getString("name");
        String req_job = jsprequest.getString("job");
		
        //Declare BaseURL
        RestAssured.baseURI = BaseURI ;
        
        //Configure Request Body
        int statusCode = given().header("Content-Type","application/json").body(requestBody).when().put("/api/users/2").then().extract().statusCode();
        String responseBody = given().header("Content-Type","application/json").body(requestBody).when().put("/api/users/2").then().extract().response().asString();
        
        JsonPath jsp = new JsonPath(responseBody);
        String res_name = jsp.getString("name");
        String res_job = jsp.getString("job");
        String res_updatedAt = jsp.getString("updatedAt");
        
        String actualDate = res_updatedAt.substring(0,10);
        String currentDate = LocalDate.now().toString(); 
        
        System.out.println("The status code is: "+ statusCode + " OK");  
        System.out.println("name : "+res_name);
        System.out.println("job : "+res_job);
        System.out.println("updatedAt : "+res_updatedAt);
        
        //Validate response body
        Assert.assertEquals(res_name , req_name);
        Assert.assertEquals(res_job , req_job);
        Assert.assertEquals(actualDate , currentDate);
    }
}