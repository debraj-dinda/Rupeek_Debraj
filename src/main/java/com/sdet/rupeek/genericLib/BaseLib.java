package com.sdet.rupeek.genericLib;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.port;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import io.restassured.http.ContentType;
import io.restassured.internal.util.IOUtils;
import io.restassured.response.Response;

/**
 * 
 * @author Debraj
 *
 */
public class BaseLib implements IAutoConst {

	public static Response response;
	public static String token;

	/*
	 * to initialize the base URI, port and authentication
	 */
	@BeforeSuite
	public void config() {
		baseURI = "http://13.126.80.194";
		port = 8080;

	}

	@BeforeMethod

	public void authenticate() {

		try {

			FileInputStream fis = new FileInputStream(new String("./src/test/resources/json/authenticate.json"));
			response = given().contentType(ContentType.JSON).and().body(IOUtils.toByteArray(fis)).when()
					.post(IAutoConst.CREATE_TOKEN);
			response.then().assertThat().contentType(ContentType.JSON).and().statusCode(200);

		} catch (FileNotFoundException e) {
			e.printStackTrace();

		} catch (IOException e) {

			e.printStackTrace();

		}
	}
}
