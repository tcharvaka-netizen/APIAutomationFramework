package com.api.services;

import com.api.models.request.AuthRequest;

import io.restassured.response.Response;
import net.minidev.json.JSONObject;

public class AuthServices extends BaseService{
private static final String BASE_PATH="https://restful-booker.herokuapp.com/auth";
private String userName="admin";
private String password="password123";


	public Response createToken(String payLoad) {
		return postRequest(BASE_PATH,payLoad);
		
	}
}

