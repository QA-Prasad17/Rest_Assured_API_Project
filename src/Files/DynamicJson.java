package Files;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;
import io.restassured.response.Response;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

public class DynamicJson {
	
	
	
	@Test(dataProvider = "Books")
	public void AddBook(String isbn, String aisle )
	{
		RestAssured.baseURI= "http://216.10.245.166";
	
		String resp = given().header("Content-Type","application/json").body(Payload.Addbook(isbn,aisle)).
		when().post("Library/Addbook.php")
		.then().assertThat().statusCode(200).extract().response().asString();
		JsonPath js = ReusableMthods.rawToJson(resp);
		String id = js.get("ID");
		System.out.println(id);
		
	
	}
	
	@DataProvider(name = "Books")
	public Object[][] getData()
	{
		return new Object[][]  {{"fdfre","4588"}, {"fgrg","7845"}, {"eree","5897"}};
	}

}
