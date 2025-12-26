package com.api.tests;

import static org.testng.Assert.assertEquals;

import java.util.HashMap;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.api.models.request.AuthRequest;
import com.api.models.request.CreateBookingDatesRequest;
import com.api.models.request.CreateBookingRequest;
import com.api.models.response.AuthResponse;
import com.api.models.response.CreateBookingResponse;
import com.api.services.AuthServices;
import com.api.services.BaseService;
import com.api.services.BookingServices;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import com.jayway.jsonpath.JsonPath;
import io.restassured.response.Response;
import net.minidev.json.JSONObject;

@Listeners(com.ap.listeners.TestListener.class)
@Epic("Epic-01")
@Feature("Create update delete")
public class BookingAPITestcasesUsingPOJO extends BaseService {
//	private static final Logger logger = LogManager.getLogger(PostAPIRequest.class);
	private static final Logger logger = LogManager.getLogger(BookingAPITestcasesUsingPOJO.class);
	public Integer booking_id;

	@Test(description = "Verify Create Booking API is working...")
	public void createBooking() {

		CreateBookingDatesRequest bookingDates = new CreateBookingDatesRequest("2023-03-25", "2023-03-30");
		CreateBookingRequest booking = new CreateBookingRequest("fname", "lname", 1000, true, "bowls", bookingDates);

		// serialization
		ObjectMapper objectMapper = new ObjectMapper();
		String requestBody;
		try {
			requestBody = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(booking);

			CreateBookingRequest bookingObj = objectMapper.readValue(requestBody, CreateBookingRequest.class);
			String fname = bookingObj.getFirstname();
			String lname = bookingObj.getLastname();
			Integer totalPrice = bookingObj.getTotalprice();

			BookingServices bookingServices = new BookingServices();
			Response response = bookingServices.createBooking(requestBody);

			CreateBookingResponse createBookingResponse = response.as(CreateBookingResponse.class);
			booking_id = createBookingResponse.getBookingid();
			// Assertios
			Assert.assertEquals(response.statusCode(), 200);
			Assert.assertEquals(booking_id != null, true);
			Assert.assertEquals(fname, createBookingResponse.getBooking().getFirstname());
			Assert.assertEquals(lname, createBookingResponse.getBooking().getLastname());
			Assert.assertEquals(totalPrice, createBookingResponse.getBooking().getTotalprice());

		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Test(description = "Verify Create Booking and retrive Created booking  API Details is working...")
	public void getBookingDetails() {

		CreateBookingDatesRequest bookingDates = new CreateBookingDatesRequest("2023-03-25", "2023-03-30");
		CreateBookingRequest booking = new CreateBookingRequest("fname", "lname", 1000, true, "bowls", bookingDates);

		// serialization
		ObjectMapper objectMapper = new ObjectMapper();
		String requestBody;
		try {
			requestBody = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(booking);

			BookingServices bookingServices = new BookingServices();
			Response response = bookingServices.createBooking(requestBody);
			System.out.println(response.asString());
			CreateBookingResponse createBookingResponse = response.as(CreateBookingResponse.class);
			Integer booking_id = createBookingResponse.getBookingid();
			String str = String.valueOf(booking_id);
			System.out.println(str);
			Response getBookingDetailsResponse = bookingServices.getBooking(str);

			System.out.println(getBookingDetailsResponse.asString());

		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Test(description = "Verify Updated Booking API is working...")
	public void updateBookingDetails() {

		CreateBookingDatesRequest bookingDates = new CreateBookingDatesRequest("2023-03-25", "2023-03-30");
		CreateBookingRequest booking = new CreateBookingRequest("fname", "lname", 1000, true, "bowls", bookingDates);

		// serialization
		ObjectMapper objectMapper = new ObjectMapper();
		String requestBody;
		String authRequestBody;
		try {
			requestBody = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(booking);

			BookingServices bookingServices = new BookingServices();
			Response response = bookingServices.createBooking(requestBody);

			CreateBookingResponse createBookingResponse = response.as(CreateBookingResponse.class);
			Integer booking_id = createBookingResponse.getBookingid();
			String str = String.valueOf(booking_id);

			CreateBookingRequest updateBooking = new CreateBookingRequest("super", "hero", 2000, true, "bowls",
					bookingDates);

			requestBody = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(updateBooking);

			AuthRequest authRequest = new AuthRequest("admin", "password123");
			authRequestBody = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(authRequest);

			AuthServices authServices = new AuthServices();
			Response authResponse = authServices.createToken(authRequestBody);

			AuthResponse authResponses = authResponse.as(AuthResponse.class);

			Map<String, Object> headers = new HashMap<>();
			headers.put("Content-Type", "application/json");
			headers.put("Cookie", "token=" + authResponses.getToken());

			Response updateresponse = bookingServices.updateBooking(headers, requestBody, str);
			System.out.println("************************************************************");
			Assert.assertEquals(updateresponse.statusCode(), 200);
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Story("Story 1")
	@Severity(SeverityLevel.CRITICAL)
	@Test(description = "partial update")
	public void partialUpdateBookingDetails() {

		logger.info("Partial info Booking details updated script started");
		CreateBookingDatesRequest bookingDates = new CreateBookingDatesRequest("2023-03-25", "2023-03-30");
		CreateBookingRequest booking = new CreateBookingRequest("fname", "lname", 555, true, "bowls", bookingDates);

		// serialization
		ObjectMapper objectMapper = new ObjectMapper();
		String requestBody;
		String authRequestBody;
		try {
			requestBody = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(booking);
			System.out.println(requestBody);
			BookingServices bookingServices = new BookingServices();
			Response response = bookingServices.createBooking(requestBody);
			System.out.println("first booking***********************");
			System.out.println(response.asString());
			CreateBookingResponse createBookingResponse = response.as(CreateBookingResponse.class);
			Integer booking_id = createBookingResponse.getBookingid();
			String str = String.valueOf(booking_id);
			System.out.println("Booking id " + str);
			Response getBookingDetailsResponse = bookingServices.getBooking(str);
			System.out.println("get booking details " + getBookingDetailsResponse.asString());

			AuthRequest authRequest = new AuthRequest("admin", "password123");
			authRequestBody = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(authRequest);
			System.out.println("Auth Request" + authRequestBody);
			AuthServices authServices = new AuthServices();
			Response authResponse = authServices.createToken(authRequestBody);
			System.out.println(authResponse.asString());
			AuthResponse authResponses = authResponse.as(AuthResponse.class);

			Map<String, Object> reqBody = new HashMap<>();
			reqBody.put("firstname", "partial");
			reqBody.put("lastname", "update");

			System.out.println("partial update body" + reqBody);
			Map<String, Object> headers = new HashMap<>();
			headers.put("Content-Type", "application/json");
			headers.put("Cookie", "token=" + authResponses.getToken());
			System.out.println("partial update headers" + headers);
			Response updateresponse = bookingServices.partialUpdateBooking(headers, reqBody, str);
			System.out.println("************************************************************");
			System.out.println(updateresponse.asPrettyString());
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		logger.info("Partial info Booking details updated script closed ");
	}

}
