package restAPI;

import static io.restassured.RestAssured.given;

import java.util.List;
import java.util.Random;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.jayway.jsonpath.JsonPath;

import io.restassured.RestAssured;

public class GetReq {

	static int answer = 0;

	public static int getRandom() {
		Random rn = new Random();

		for (int i = 0; i < 10; i++) {
			answer = rn.nextInt(10) + 1;

		}
		return answer;
	}

	static int userID = getRandom();

	@BeforeClass
	public void setup() {
		RestAssured.baseURI = "https://jsonplaceholder.typicode.com/";
	}

	@Test
	public void getEmailRequest() {

		System.out.println(userID);
		String a = given().

				when().get("users" + "/" + userID + "/").then().statusCode(200).extract().path("email");
		System.out.println(a);

	}

	@Test
	public void verifyPostIds() {
		int valid = 0;
		int invalid = 0;
		List<Integer> resp = given().when().get("posts?userId=" + userID).then().statusCode(200).extract().path("id");
		// int list=JsonPath.read(resp,"$.id");
		System.out.println(resp);
		for (int i = 1; i <= resp.size() - 1; i++) {
			if (resp.get(i) > 1 && resp.get(i) <= 100) {
				valid = 1;
			} else {
				invalid = 1;
			}
		}
		if (invalid == 1) {
			System.out.println("User id contains invalid post ids");
		} else {
			System.out.println("User id contains valid post ids");
		}

	}

}
