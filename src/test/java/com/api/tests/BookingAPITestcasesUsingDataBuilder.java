package com.api.tests;

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
import com.api.models.request.UpdateBookingDatesRequest;
import com.api.models.request.UpdateBookingRequest;
import com.api.models.response.AuthResponse;
import com.api.models.response.CreateBookingResponse;
import com.api.models.response.GetBookingResponse;
import com.api.services.AuthServices;
import com.api.services.BookingServices;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.restassured.response.Response;

@Listeners(com.ap.listeners.TestListener.class)
public class BookingAPITestcasesUsingDataBuilder {

	public Integer booking_id;

	@Test
	public void ceateBookingAPI() throws JsonProcessingException {

		// serialization by Builder concept

		CreateBookingDatesRequest dates = new CreateBookingDatesRequest();
		dates.setCheckin("2024-01-01");
		dates.setCheckout("2024-01-10");

		CreateBookingRequest requestBody1 = CreateBookingRequest.Builder.builder().firstname("John").lastname("Doe")
				.totalprice(1000).depositpaid(true).additionalneeds("Breakfast").bookingdates(dates).build();

		String fname = requestBody1.getFirstname();
		String lname = requestBody1.getLastname();
		Integer totalPrice = requestBody1.getTotalprice();

		BookingServices bookingServices = new BookingServices();
		Response response = bookingServices.createBooking(requestBody1);

		CreateBookingResponse createBookingResponse = response.as(CreateBookingResponse.class);
		booking_id = createBookingResponse.getBookingid();
		// Assertios
		Assert.assertEquals(response.statusCode(), 200);
		Assert.assertEquals(booking_id != null, true);
		Assert.assertEquals(fname, createBookingResponse.getBooking().getFirstname());
		Assert.assertEquals(lname, createBookingResponse.getBooking().getLastname());
		Assert.assertEquals(totalPrice, createBookingResponse.getBooking().getTotalprice());

	}

	@Test(description = "Verify Create Booking and retrive Created booking(Get Method API) Details is working...")
	public void getBookingDetails() {

		CreateBookingDatesRequest dates = new CreateBookingDatesRequest();
		dates.setCheckin("2024-01-01");
		dates.setCheckout("2024-01-10");

		CreateBookingRequest requestBody1 = CreateBookingRequest.Builder.builder().firstname("John").lastname("Doe")
				.totalprice(1000).depositpaid(true).additionalneeds("Breakfast").bookingdates(dates).build();

		String fname = requestBody1.getFirstname();
		String lname = requestBody1.getLastname();
		Integer totalPrice = requestBody1.getTotalprice();

		BookingServices bookingServices = new BookingServices();
		Response response = bookingServices.createBooking(requestBody1);

		CreateBookingResponse createBookingResponse = response.as(CreateBookingResponse.class);
		booking_id = createBookingResponse.getBookingid();
		// Get base URI
		Response getBOOKINGReq = bookingServices.getBooking(String.valueOf(booking_id));
		GetBookingResponse getBookingRes = getBOOKINGReq.as(GetBookingResponse.class);
		// Assertios

		Assert.assertEquals(getBOOKINGReq.statusCode(), 200);
		Assert.assertEquals(booking_id != null, true);
		Assert.assertEquals(fname, getBookingRes.getFirstname());
		Assert.assertEquals(lname, getBookingRes.getLastname());
		Assert.assertEquals(totalPrice, getBookingRes.getTotalprice());
	}

	@Test(description = "Verify Updated Booking API is working...")
	public void updateBookingDetails() {

		// serialization
		ObjectMapper objectMapper = new ObjectMapper();

		String authRequestBody;

		CreateBookingDatesRequest dates = new CreateBookingDatesRequest();
		dates.setCheckin("2024-01-01");
		dates.setCheckout("2024-01-10");

		CreateBookingRequest updateRequestBody = CreateBookingRequest.Builder.builder().firstname("John")
				.lastname("Doe").totalprice(1000).depositpaid(true).additionalneeds("Breakfast").bookingdates(dates)
				.build();

		BookingServices bookingServices = new BookingServices();
		Response response = bookingServices.createBooking(updateRequestBody);

		CreateBookingResponse createBookingResponse = response.as(CreateBookingResponse.class);
		booking_id = createBookingResponse.getBookingid();

		// Auth request Id

		AuthRequest authRequest = new AuthRequest("admin", "password123");
		try {
			authRequestBody = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(authRequest);

			AuthServices authServices = new AuthServices();
			Response authResponse = authServices.createToken(authRequestBody);

			AuthResponse authResponses = authResponse.as(AuthResponse.class);

			Map<String, Object> headers = new HashMap<>();
			headers.put("Content-Type", "application/json");
			headers.put("Cookie", "token=" + authResponses.getToken());

			// Updating Response

			UpdateBookingRequest requestBody1 = UpdateBookingRequest.Builder.builder().firstname("venkat")
					.lastname("Sree").totalprice(2000).depositpaid(true).additionalneeds("Diner")
					.bookingdates(new UpdateBookingDatesRequest("2025-01-01", "2025-01-05")).build();

			String fnameUpdate = requestBody1.getFirstname();
			String lnameUpdate = requestBody1.getLastname();
			Integer totalPriceUpdate = requestBody1.getTotalprice();

			Response updateresponse = bookingServices.updateBooking(headers, requestBody1, String.valueOf(booking_id));

			GetBookingResponse updateBookingResponse = updateresponse.as(GetBookingResponse.class);

			System.out.println("***********************UPDATE BOOKING ASSERTIONS*************************************");
			Assert.assertEquals(updateresponse.statusCode(), 200);
			Assert.assertEquals(fnameUpdate, updateBookingResponse.getFirstname());
			Assert.assertEquals(lnameUpdate, updateBookingResponse.getLastname());
			Assert.assertEquals(totalPriceUpdate, updateBookingResponse.getTotalprice());

		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
