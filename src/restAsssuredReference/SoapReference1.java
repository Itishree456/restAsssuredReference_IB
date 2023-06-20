package restAsssuredReference;
import io.restassured.RestAssured;
import io.restassured.path.xml.*;

import static io.restassured.RestAssured.given;

import org.testng.Assert;

public class SoapReference1 {

	public static void main(String[] args) {
		//Step1: declare baseURI and request body variables
		String BaseURI = "https://www.dataaccess.com";
		String requestBody = "<?xml version=\"1.0\" encoding=\"utf-8\"?>\r\n"
				+ "<soap:Envelope xmlns:soap=\"http://schemas.xmlsoap.org/soap/envelope/\">\r\n"
				+ "  <soap:Body>\r\n"
				+ "    <NumberToDollars xmlns=\"http://www.dataaccess.com/webservicesserver/\">\r\n"
				+ "      <dNum>61</dNum>\r\n"
				+ "    </NumberToDollars>\r\n"
				+ "  </soap:Body>\r\n"
				+ "</soap:Envelope>";
		
		//Step2 : Fetch request param
		XmlPath xmlrequest = new XmlPath(requestBody);
		String req_param = xmlrequest.getString("dNum");
		System.out.println(req_param);
		
		//Step3 : Configure the API and Fetch the Response body
		RestAssured.baseURI = BaseURI;
		String responseBody = given().header("Content-Type","text/xml; charset=utf-8").body(requestBody)
				.when().post("webservicesserver/NumberConversion.wso")
		.then().extract().response().getBody().asString();
		System.out.println(responseBody);
		
		//Step4: Parse the response body and fetch the response params
		XmlPath xml_res= new XmlPath(responseBody);
		String Result = xml_res.getString("NumberToDollars");
		System.out.println(Result);
		
		//Step5: Validate response body parameter
		Assert.assertEquals(Result,"sixty one dollars"); 

		
	}

}
