package Files;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.path.json.JsonPath;

public class SumValidation {

	@Test
	public void Sumcourse()
	{
		int sum = 0;
		JsonPath js = new JsonPath(Payload.CoursePrice());
		int count = js.getInt("courses.size()");
		for(int i=0;i<count;i++)
		{
			int price = js.get("courses["+i+"].price");
			int copies = js.get("courses["+i+"].copies");
			int amt = price * copies;
			System.out.println(amt);
			sum = sum + amt;
			
		}
		System.out.println(sum);
		int PA = js.getInt("dashboard.purchaseAmount");
		//System.out.println(PA);
		Assert.assertEquals(sum, PA);
	}
}
