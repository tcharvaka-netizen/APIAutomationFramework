package com.api.tests;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.testng.Assert;
import org.testng.annotations.Test;



import io.restassured.RestAssured;
import io.restassured.http.ContentType;

import com.api.services.BaseService;
import com.jayway.jsonpath.JsonPath;
import io.restassured.response.Response;
import net.minidev.json.JSONArray;
import utils.FileNameConstants;

public class CreateBookingUsingTextFile extends BaseService{
	@Test
	public void e2eBookingAPI()  {
		String PostAPIRequestBody;
		try {
			 PostAPIRequestBody = FileUtils.readFileToString(new File(FileNameConstants.POST_API_REQUEST_BODY),"UTF-8");
			
			Response response =
					RestAssured
							.given()
								.contentType(ContentType.JSON)
								.body(PostAPIRequestBody)
								.baseUri("https://restful-booker.herokuapp.com/booking")
							.when()
								.post()
							.then()
								.assertThat()
								.statusCode(200)
							.extract()
								.response();
			
			int bookingID=JsonPath.read(response.body().asString(),"$.bookingid");
					String jsonArrayFname=JsonPath.read(response.body().asString(),"$.booking.firstname");
					Assert.assertEquals(jsonArrayFname, "through");
					int totapprice=JsonPath.read(response.body().asString(),"$.booking.totalprice");
					Assert.assertEquals(totapprice, 1000);
					
					
					RestAssured.given()
					.contentType(ContentType.JSON)
					.baseUri("https://restful-booker.herokuapp.com/booking")
					.when()
					.get("/{bookingId}",bookingID)
					.then()
					.assertThat()
					.statusCode(200);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
}
}
