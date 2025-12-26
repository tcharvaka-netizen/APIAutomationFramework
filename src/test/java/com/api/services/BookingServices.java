package com.api.services;

import java.util.Map;

import com.api.models.request.CreateBookingDatesRequest;
import com.api.models.request.CreateBookingRequest;
import com.api.models.request.UpdateBookingRequest;

import io.restassured.response.Response;

public class BookingServices extends BaseService{

	private static final String BASE_PATH="/booking/";
	
	public Response createBooking(String payload) {
		return postRequest(payload);
		
	}
	public Response createBooking(CreateBookingRequest payload) {
		return postRequest(payload);
		
	}
//	public Response updateBooking(Map<String,Object>headers,String payload,String endPoint) {
//		return putRequest(headers,payload, endPoint);
//		
//	}
	public Response updateBooking(Map<String,Object>headers,Object payload,String endPoint) {
		return putRequest(headers,payload, endPoint);
		
	}
	public Response getBooking(String endPoint) {
		return getRequest(endPoint);
		
	}
	
	public Response partialUpdateBooking(Map<String,Object>headers,Map<String,Object> payload,String endPoint) {
		return patchRequest(headers,payload, endPoint);
		
	}
	
	public Response deleteBooking(String endPoint) {
		return deleteRequest(endPoint);
		
	}
}

