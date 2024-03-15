import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import org.testng.Assert;

import Files.Payload;

public class Basics {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		// Validate if Add palce api is working as expected

		// given - all the input nmethod
		// when - submit the api
		// there - validate the response
		RestAssured.baseURI = "https://rahulshettyacademy.com";
		String res = given().log().all().queryParam("key", "qaclick123").header("Content-Type", "application/json")
				.body(Payload.AddPlace()).
				when().post("maps/api/place/add/json").then().assertThat().statusCode(200)
				.body("scope", equalTo("APP")).header("Server", "Apache/2.4.52 (Ubuntu)").extract().response()
				.asString();
		
//		{\r\n" + "\"location\": {\r\n" + "\"lat\": -38.383494,\r\n" + "\"lng\": 33.427362\r\n" + "},\r\n"
//				+ "\"accuracy\": 50,\r\n" + "\"name\": \"Prasad\",\r\n"
//				+ "\"phone_number\": \"(+91) 9874563210\",\r\n"
//				+ "\"address\":\"29, side layout, cohen 09\",\r\n" + "\"types\": [\r\n" + "\"shoe park\",\r\n"
//				+ "\"shop\"\r\n" + "],\r\n" + "\"website\": \"http://google.com\",\r\n"
//				+ "\"language\": \"French-IN\"\r\n" + "}

		System.out.println(res);
		JsonPath js = new JsonPath(res);
		String placeId = js.getString("place_id");
		System.out.println("Place id = " + placeId);

		// Update place

		String newAddress = "985 winter walk, USA";

		given().log().all().queryParam("key", "qaclick123").header("Content-Type", "application/json")
				.body("{\r\n" + "\"place_id\": \"" + placeId + "\",\r\n" + "\"address\":\"" + newAddress + "\",\r\n"
						+ "\"key\":\"qaclick123\"\r\n" + "}")
				.when().put("maps/api/place/update/json").then().assertThat().log().all().statusCode(200)
				.body("msg", equalTo("Address successfully updated"));

		// Get Place

		String getPlaceResponse = given().log().all().queryParam("key", "qaclick123").queryParam("place_id", placeId)
				.when().get("maps/api/place/get/json").then().assertThat().log().all().statusCode(200).extract()
				.asString();

		JsonPath js1 = new JsonPath(getPlaceResponse);
		String actAdd = js1.getString("address");
		System.out.println("address = " + actAdd);
		Assert.assertEquals(actAdd, newAddress);

	}

}
