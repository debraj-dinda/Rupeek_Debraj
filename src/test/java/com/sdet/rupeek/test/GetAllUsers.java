package com.sdet.rupeek.test;

import static io.restassured.RestAssured.given;

import org.testng.annotations.Test;

import com.sdet.rupeek.genericLib.BaseLib;
import com.sdet.rupeek.genericLib.IAutoConst;
import com.sdet.rupeek.utils.JsonUtils;

import io.restassured.http.ContentType;

public class GetAllUsers extends BaseLib {

	/**
	 * validation of User record with actual data in list
	 */
	@Test(priority = 0)
	public void getUserDetailsWithValidInput() {

		token = JsonUtils.getJsonString(response, "token");

		given().auth().oauth2(token).when().get(IAutoConst.GET_ALL_USERS).then().assertThat().statusCode(200).and()
				.contentType(ContentType.JSON);

	}

	/**
	 * Validation with Wrong Token.
	 */
	@Test(priority = 1)
	public void getUsersDetailsWithWrongToken() {
		token = JsonUtils.getJsonString(response, "token");

		given().auth().oauth2(token).when().get(IAutoConst.GET_ALL_USERS).then().assertThat().statusCode(401).and()
				.contentType("");

	}

	/**
	 * validation when Giving random body request to the server
	 */
	@Test(priority = 2)
	public void getUsersRecordWithRandomBodyField() {
		given().auth().oauth2(token).when().contentType(ContentType.JSON)
				.body("{ \"title\" : \"rupeek\", \"google\" : \"pass\"}").get(GET_ALL_USERS).then().assertThat()
				.statusCode(200).and().contentType("");
	}

}
