import io.restassured.RestAssured;
import io.restassured.filter.session.SessionFilter;
import io.restassured.path.json.JsonPath;

import static io.restassured.RestAssured.*;

import java.io.File;

public class jiraTest {

	public static void main(String[] args) {
		// Setting base URI for all requests
		RestAssured.baseURI = "http://localhost:8080";

		// Creating a session filter for authentication
		SessionFilter sessionFilter = new SessionFilter();

		// Logging in and capturing the response
		String response = given().relaxedHTTPSValidation().header("Content-Type", "application/json")
				.body("{ \"username\": \"gorivaleprasad42\", \"password\": \"Prasad171292\" }").log().all()
				.filter(sessionFilter).when().post("rest/auth/1/session").then().log().all().extract().response()
				.asString();

		// Adding a comment to the issue
		String AddNewComment = given().pathParam("key", "10006") // Specifying the issue key
				.log().all().header("Content-Type", "application/json")
				.body("{ \"body\": \"This is my first comment.\", \"visibility\": { \"type\": \"role\", \"value\": \"Administrators\" } }")
				.filter(sessionFilter) // Applying the session filter for authentication
				.when().post("/rest/api/2/issue/{key}/comment") // Using path parameter placeholder
				.then().log().all().assertThat().statusCode(201).extract().response().asString();// Asserting the response status code

		JsonPath js = new JsonPath(AddNewComment);
		String CommentId = js.getString("id");
		
		
		// Add Attachment

		given().header("X-Atlassian-Token", "no-check").filter(sessionFilter).pathParam("key", "10006")
				.header("Content-Type", "multipart/form-data")
				.multiPart("file", new File("C:\\Users\\Admin\\eclipse-workspace\\RestAssuredProject\\src\\Jira.txt"))
				.when().post("/rest/api/2/issue/10006/attachments").then().log().all().assertThat().statusCode(200);

		// Get issue

		String IssueDetails = given().filter(sessionFilter).pathParam("key", "10006").queryParam("fields", "comment").log().all()
				.when().get("/rest/api/2/issue/{Key}").then().log().all().extract().response().asString();

		System.out.println(IssueDetails);
		
		
		JsonPath JS = new JsonPath(IssueDetails);
		int commentCount = js.get("fields.comment.comments.size()");
		for(int i=0;i<commentCount;i++)
{
			js.get("fields.comment.comments["+i+"].id");
			
}
}
		
		
	}

