package restAsssuredReference;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import static io.restassured.RestAssured.given;
import org.testng.Assert;
public class GetReference {

		public static void main(String[] args) {
	        //Step1: declare base URI
			RestAssured.baseURI="https://reqres.in/";
			
			//Step2: configure the API and fetch responseBody
			int statusCode=given().header("Content-Type","application/json").when().get("api/users?page=2").then().extract().statusCode();
			String responseBody=given().header("Content-Type","application/json").when().get("api/users?page=2").then().extract().response().asString();
			System.out.println(statusCode);
			System.out.println(responseBody);
			
			//Step3: expected result 
			int id [] = {7, 8, 9, 10, 11, 12};
		    String[] email = {"michael.lawson@reqres.in", "lindsay.ferguson@reqres.in", "tobias.funke@reqres.in", "byron.fields@reqres.in",
		    		"george.edwards@reqres.in", "rachel.howell@reqres.in"};
			 
			//Step4: Parse the responseBody
			JsonPath jspresponse=new JsonPath(responseBody);
			int count = jspresponse.getList("data").size();
			System.out.println(count);
			
			//Step5: validate each object 
			 for(int i=0;i<count; i++)
			 {

				 int exp_id = id[i];
			     String exp_email = email[i];
				 
				 String res_id=jspresponse.getString("data["+i+"].id");
				 int  res_int_id=Integer.parseInt(res_id);
				 String res_email=jspresponse.getString("data["+i+"].email");
				 
				 //validate the responseBody param
				 Assert.assertEquals(res_int_id, exp_id,"ID at index " + i);
		         Assert.assertEquals(res_email, exp_email, "email at index " + i);

			 }
			 
		}

	}