package restAPI;

import static io.restassured.RestAssured.given;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.jayway.jsonpath.JsonPath;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;

public class Post {
	@BeforeClass
	public void setup() {
		RestAssured.baseURI="https://jsonplaceholder.typicode.com/";
		RestAssured.basePath="/posts";
	}
	@Test
	public void postRequest() {
		POJO payload=new POJO();
		payload.setUserId(GetReq.userID);
		payload.setTitle("FyndActivity");
		payload.setBody("Fynd API automation assignment");
		
		given().contentType(ContentType.JSON).body(payload).when().post().then().statusCode(201).log().all();
		
	}

}
