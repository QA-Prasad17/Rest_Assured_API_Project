package Pojo;

import static io.restassured.RestAssured.*;

import java.util.ArrayList;
import java.util.List;

import io.restassured.RestAssured;

public class SpecBuilder {

	public static void main(String[] args) {

		RestAssured.baseURI = "https://rahulshettyacademy.com";

		AddPlace p = new AddPlace();
		p.setName("Frontline house");
		p.setAccuracy(50);
		p.setAddress("29, side layout, cohen 09");
		p.setLanguage("French-IN");
		p.setPhone_number("(+91) 983 893 3937");
		p.setWebsite("http://google.com");

		List<String> mylist = new ArrayList<String>();
		mylist.add("shoe park");
		mylist.add("shop");
		p.setTypes(mylist);

		MiniLoc l = new MiniLoc();
		l.setLng(-38.383494);
		l.setLat(33.427362);

		String resp = given().log().all().queryParam("key", "=qaclick123").body(p).log().all()
				.post("/maps/api/place/add/json").then().assertThat().statusCode(200).extract().response().asString();

		System.out.println(p);

	}
}
