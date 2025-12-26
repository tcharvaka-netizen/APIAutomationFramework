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
import com.api.models.response.GetBookingdateResponse;
import com.api.services.AuthServices;
import com.api.services.BookingServices;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import net.minidev.json.JSONObject;
@Listeners(com.ap.listeners.TestListener.class)
public class EndTOEndBookingAPIUsingPOJO {
	
public Integer booking_id;
	@Test
	public void e2eBookingAPI()  {

		CreateBookingDatesRequest bookingDates = new CreateBookingDatesRequest("2023-03-25", "2023-03-30");
		CreateBookingRequest booking = new CreateBookingRequest("fname","lname",1000,true,"bowls",bookingDates);

		//serialization
		ObjectMapper objectMapper = new ObjectMapper();
		String requestBody;
		String authRequestBody;
		try {
			
			//*********************Create Booking API*********************************\\
			requestBody = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(booking);
			
			
			CreateBookingRequest createBookingRequest=new CreateBookingRequest();
			CreateBookingRequest bookingObj = objectMapper.readValue(requestBody, CreateBookingRequest.class);
			String fname=bookingObj.getFirstname();
			String lname=bookingObj.getLastname();
			Integer totalPrice=bookingObj.getTotalprice();
			
			BookingServices bookingServices=new BookingServices();
			Response response=bookingServices.createBooking(requestBody);
			
			CreateBookingResponse createBookingResponse=response.as(CreateBookingResponse.class);
			booking_id=createBookingResponse.getBookingid();
			//Assertios
			Assert.assertEquals(response.statusCode(), 200);
			Assert.assertEquals(booking_id!=null, true);
			Assert.assertEquals(fname, createBookingResponse.getBooking().getFirstname());
			Assert.assertEquals(lname, createBookingResponse.getBooking().getLastname());
			Assert.assertEquals(totalPrice, createBookingResponse.getBooking().getTotalprice());
			
			
			
			
			
			
			
			
			//*********************Get Booking API*********************************\\
			
			
			Response getBookingDetailsResponse=bookingServices.getBooking(String.valueOf(booking_id));	
			
			
			GetBookingResponse getbookingResponse=getBookingDetailsResponse.as(GetBookingResponse.class);
			
			//Get Booking Assertions//////		
			Assert.assertEquals(fname, getbookingResponse.getFirstname());
			Assert.assertEquals(lname, getbookingResponse.getLastname());
			Assert.assertEquals(totalPrice, getbookingResponse.getTotalprice());
			
			//*********************Get Authid for Update Booking API*********************************\\
			
			
			
			AuthRequest authRequest = new AuthRequest("admin","password123");
			authRequestBody = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(authRequest);
			
			AuthServices authServices=new AuthServices();
			Response authResponse=authServices.createToken(authRequestBody);
			
			AuthResponse  authResponses= authResponse.as(AuthResponse.class);
						
			Map<String,Object>headers=new HashMap<>();
			headers.put("Content-Type","application/json");
			headers.put("Cookie", "token="+authResponses.getToken());
			
			
			//*************************Update Booking API*************************
			
			

			UpdateBookingDatesRequest updatebookingDates =new UpdateBookingDatesRequest("2023-03-28", "2023-03-30");
			UpdateBookingRequest updateBooking =new UpdateBookingRequest("super","hero",2000,true,"food",updatebookingDates);
			requestBody = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(updateBooking);
			System.out.println("updated request body"+requestBody);			
			
			UpdateBookingRequest updateBookingObj = objectMapper.readValue(requestBody, UpdateBookingRequest.class);
			String fnameUpdate=updateBookingObj.getFirstname();
			String lnameUpdate=updateBookingObj.getLastname();
			Integer totalPriceUpdate=updateBookingObj.getTotalprice();
			
			
			Response updateresponse=bookingServices.updateBooking(headers,updateBooking, String.valueOf(booking_id));
			System.out.println(updateresponse);	
			
			GetBookingResponse updateBookingResponse=response.as(GetBookingResponse.class);
			System.out.println("update respons e is "+updateresponse);
			System.out.println("***********************UPDATE BOOKING ASSERTIONS*************************************");
				
			//Assert.assertEquals(updateresponse.getBooking()!=null, true);
			System.out.println(fnameUpdate +" "+updateBookingResponse.getFirstname());
			Assert.assertEquals(fnameUpdate, updateBookingResponse.getFirstname());
			Assert.assertEquals(lnameUpdate, updateBookingResponse.getLastname());
			Assert.assertEquals(totalPriceUpdate, updateBookingResponse.getTotalprice());
			
			//*****************************************Partial Update*****************************
			
			Map<String,Object>reqBody=new HashMap<>();
			reqBody.put("firstname","partial");
			reqBody.put("lastname", "update");
			
			Response partialUpdateresponse=bookingServices.partialUpdateBooking(headers,reqBody, String.valueOf(booking_id));
			GetBookingResponse partialUpdateBookingResponse=response.as(GetBookingResponse.class);
			System.out.println("update respons  is "+partialUpdateresponse);
			
			
			//**************************Delete Booking API******************************
			
			Response deleteBookingDetailsResponse=bookingServices.getBooking(String.valueOf(booking_id));	
			
			System.out.println(getBookingDetailsResponse.asPrettyString());
			
			
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		}


}
