package com.sdet.rupeek.utils;

import io.restassured.response.Response;
/**
 * 
 * @author Debraj
 *
 */
public class JsonUtils {
	/**
	 * getJsonString returns the Json Value from the specified Json path
	 * @param response
	 * @param jsonPath
	 * @return
	 */
	public static String getJsonString(Response response, String jsonPath) {
		return response.getBody().jsonPath().get(jsonPath).toString();
	}

}
