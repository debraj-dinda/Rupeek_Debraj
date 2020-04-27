package com.sdet.rupeek.test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import org.testng.annotations.Test;

import com.sdet.rupeek.genericLib.BaseLib;
import com.sdet.rupeek.genericLib.IAutoConst;
import com.sdet.rupeek.utils.JsonUtils;

import io.restassured.http.ContentType;

/**
 * 
 * @author Debraj
 *
 */
public class GetUserByPhone extends BaseLib {
	/**
	 * To get Single user by using Valid Phone Number
	 */
	@Test(priority = 0)
	public void getSingleUserWithValidPhoneNum() {

		token = JsonUtils.getJsonString(response, "token");

		given().auth().oauth2(token).pathParam("phone", 8037602400l).when().get(IAutoConst.GET_USER_PHONE).then()
				.assertThat().statusCode(200).and().contentType(ContentType.JSON).and()
				.body("first_name", equalTo("Aliko")).and().body("last_name", equalTo("Dangote")).and()
				.body("career", equalTo("Billionaire Industrialist"));

	}

	/**
	 * To get Single user without authentication
	 */
	@Test(priority = 1)
	public void getSingleRecordWithoutAuth() throws Throwable {
		given().pathParam("phone", 8037602400l).when().get(IAutoConst.GET_USER_PHONE).then().assertThat()
				.statusCode(401);
	}

	/**
	 * To get Single user by using Invalid Phone Number
	 */

	@Test(priority = 2)

	public void getSingleUserWithInValidPhoneNum() {

		token = JsonUtils.getJsonString(response, "token");

		given().auth().oauth2(token).pathParam("phone", 803760l).when().get(IAutoConst.GET_USER_PHONE).then()
				.assertThat().statusCode(200);

	}

	/**
	 * To get Single user by using Invalid Phone Number
	 */

	@Test(priority = 3)

	public void getSingleUserWithInValidPhoneNum2() {

		token = JsonUtils.getJsonString(response, "token");

		given().auth().oauth2(token).pathParam("phone", 803.760).when().get(IAutoConst.GET_USER_PHONE).then()
				.assertThat().statusCode(200);

	}

	/**
	 * To get Single user by using Invalid Phone Number
	 */

	@Test(priority = 4)

	public void getSingleUserWithInValidPhoneNum3() {

		token = JsonUtils.getJsonString(response, "token");

		given().auth().oauth2(token).pathParam("phone", "830e345").when().get(IAutoConst.GET_USER_PHONE).then()
				.assertThat().statusCode(200);

	}
}
