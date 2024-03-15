package Files;

import io.restassured.path.json.JsonPath;

public class ComplexJson {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		JsonPath js = new JsonPath(Payload.CoursePrice());

		// print number of courses present

		int count = js.getInt("courses.size()");
		System.out.println(count);

		// print purchase amount

		int TotalAmount = js.getInt("dashboard.purchaseAmount");
		System.out.println(TotalAmount);

		// print title of thr first course

		String titlefirst = js.get("courses[0].title");
		System.out.println(titlefirst);

		// print title of thr last course

		String titlesecond = js.get("courses[2].title");
		System.out.println(titlesecond);

		// print all course title with respective prices

		for (int i = 0; i < count; i++) {
			String courseTitle = js.get("courses[" + i + "].title");
			System.out.println(js.get("courses[" + i + "].price").toString());
			System.out.println(courseTitle);
		}
		// print number of copies sold by RPA course
		System.out.println("print number of copies sold by RPA course");
		for (int i = 0; i < count; i++) {
			String courseTitle = js.get("courses[" + i + "].title");
			if (courseTitle.equalsIgnoreCase("RPA")) {
				System.out.println(js.get("courses[" + i + "].copies").toString());
				break;
			}
		}

	}

}
