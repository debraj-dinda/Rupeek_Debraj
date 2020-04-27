package com.sdet.rupeek.test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

import org.testng.annotations.Test;

import com.sdet.rupeek.genericLib.BaseLib;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

/**
 * 
 * @author Debraj
 *
 */
public class Authenticate extends BaseLib {

	@Test
	public void getTokenWithValidUNPWD() {

		given().contentType(ContentType.JSON).body("{ \"username\" : \"rupeek\", \"password\" : \"password\"}")
				.post(BASE_URI).then().assertThat().statusCode(200).and().contentType(ContentType.JSON).and()
				.time(lessThan(9000L));

		Response response = given().contentType(ContentType.JSON)
				.body("{ \"username\" : \"rupeek\", \"password\" : \"password\"}").post(BASE_URI);
		System.out.println(response.getBody().asString());
	}

	/**
	 * User_name related negative Scenarios
	 */

	@Test
	public void getTokenWithInvalidBlankUserName() {
		given().contentType(ContentType.JSON).body("{ \"username\" : \"\", \"password\" : \"password\"}").post(BASE_URI)
				.then().assertThat().statusCode(401).and().contentType("").and().time(lessThan(9000L));

	}

	@Test
	public void getTokenWithInvalidWrongUserName() {
		given().contentType(ContentType.JSON).body("{ \"username\" : \"google\", \"password\" : \"password\"}")
				.post(BASE_URI).then().assertThat().statusCode(401).and().contentType("").and().time(lessThan(9000L));
	}

	@Test
	public void getTokenWithInvalidRemoveUserNameField() {
		given().contentType(ContentType.JSON).body("{\"password\" : \"password\"}").post(BASE_URI).then().assertThat()
				.statusCode(401).and().contentType("").and().time(lessThan(9000L));
	}

	/**
	 * Password related negative Scenario
	 */
	@Test
	public void getTokenWithInvalidBlankPassword() {
		given().contentType(ContentType.JSON).body("{ \"username\" : \"\", \"password\" : \"\"}").post(BASE_URI).then()
				.assertThat().statusCode(401).and().contentType("").and().time(lessThan(9000L));

	}

	@Test
	public void getTokenWithInvalidWrongPassword() {
		given().contentType(ContentType.JSON).body("{ \"username\" : \"rupeek\", \"password\" : \"wrong\"}")
				.post(BASE_URI).then().assertThat().statusCode(401).and().contentType("").and().time(lessThan(9000L));
	}

	@Test
	public void getTokenWithInvalidRemovePasswordField() {
		given().contentType(ContentType.JSON).body("{\"username\" : \"rupeek\"}").post(BASE_URI).then().assertThat()
				.statusCode(401).and().contentType("").and().time(lessThan(9000L));
	}

}
