package com.api.services;
import static io.restassured.RestAssured.*;

import java.util.Map;
import com.api.filters.logfilter;
import io.restassured.specification.RequestSpecification;
import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class BaseService {
private static final String BASE_URL="https://restful-booker.herokuapp.com/booking";

private RequestSpecification requestSpecification;

public BaseService() {
	requestSpecification=given().baseUri(BASE_URL);	
	
}
static {
	RestAssured.filters(new logfilter());
}
protected Response postRequest(Object  payLoad) {
	return requestSpecification.contentType(ContentType.JSON).body(payLoad).post();
}
protected Response postRequest(String baseUrl, Object payLoad) {
	return requestSpecification.baseUri(baseUrl).contentType(ContentType.JSON).body(payLoad).post();
}
protected Response putRequest(Map<String,Object>headers,Object  payLoad,String endPoint) {
	return requestSpecification.headers(headers).body(payLoad).put(endPoint);
}
protected Response patchRequest(Map<String,Object>headers,Map<String,Object>payLoad,String endPoint) {
	return requestSpecification.headers(headers).body(payLoad).patch(endPoint);
}
protected Response getRequest(String  endPoint) {
return requestSpecification.get(endPoint);
}

protected Response deleteRequest(String  endPoint) {
return requestSpecification.delete(endPoint);
}

//Post Request multiple base url & headers parameters need to add methods
}