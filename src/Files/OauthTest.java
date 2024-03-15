package Files;

import static io.restassured.RestAssured.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.testng.Assert;

import Pojo.API;
import Pojo.GetCourse;
import Pojo.WebAutomation;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

public class OauthTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		String[] CourseTtile = {"Selenium Webdriver Java","Cypress","Protractor"};

		RestAssured.baseURI = "https://rahulshettyacademy.com";

		
		String AuthPrint = given()
				.formParam("client_id", "692183103107-p0m7ent2hk7suguv4vq22hjcfhcr43pj.apps.googleusercontent.com")
				.formParam("client_secret", "erZOWM9g3UtwNRj340YYaK_W").formParam("grant_type", "client_credentials")
				.formParam("scope", "trust").when().log().all().post("/oauthapi/oauth2/resourceOwner/token").asString();

		System.out.println(AuthPrint);
		JsonPath js = new JsonPath(AuthPrint);
		String at = js.getString("access_token");

		GetCourse gc = given().queryParam("access_token", at).when().log().all()
				.get("/oauthapi/getCourseDetails").as(GetCourse.class);
		System.out.println(gc.getLinkedIn());
		System.out.println(gc.getInstructor());
		System.out.println(gc.getCourses().getApi().get(1).getCourseTitle());

		
		List<API>  APICourse = gc.getCourses().getApi();
		
		for(int i=0;i<APICourse.size();i++)
		{
			if(APICourse.get(i).getCourseTitle().equalsIgnoreCase("SoapUI Webservices testing"))
			{
				System.out.println(APICourse.get(i).getPrice());
			}
		}
		
		//get the course name of web automation
		
		ArrayList<String>  a = new ArrayList<String>();
		
		
		
		List<WebAutomation> wa = gc.getCourses().getWebAutomation();
		for(int j= 0;j<wa.size();j++)
		{
			a.add(wa.get(j).getCourseTitle());
			
			
		}
		List<String> expectedList  = Arrays.asList(CourseTtile);
		
		Assert.assertTrue(a.equals(expectedList));
	}

}
