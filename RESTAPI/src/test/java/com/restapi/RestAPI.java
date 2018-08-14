package com.restapi;

import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;
import io.restassured.specification.RequestSpecification;

public class RestAPI {

	@Test
	public void restAPI() {
		RestAssured.baseURI = "http://restapi.demoqa.com/utilities/weather/city";
		RequestSpecification request = RestAssured.given();
		Response response = request.get("/Hyderabad");
		String body = response.getBody().asString();
		System.out.println("Body is...." + body);
	}

	@Test
	public void getStatusCode() {
		RestAssured.baseURI = "http://restapi.demoqa.com/utilities/weather/city";
		RequestSpecification httpRequest = RestAssured.given();
		Response response = httpRequest.get("/Hyderabad");
		System.out.println("Status Code is....." + response.getStatusCode());
		String statusLine = response.getStatusLine();
		// Assert.assertEquals(statusLine /*actual value*/, "HTTP/1.1 200 OK" ,"expected
		// value, "Correct status code returned");
		Assert.assertEquals(response.getStatusCode(), 200, "Verify Status code :");
	}

	@Test
	public void validateHeaders() {
		RestAssured.baseURI = "http://restapi.demoqa.com/utilities/weather/city";
		RequestSpecification httpRequest = RestAssured.given();
		Response response = httpRequest.get("/Hyderabad");
		String contentType = response.header("Content-Type");
		Assert.assertEquals(contentType /* actual value */, "application/json" /* expected value */);
		Headers header = response.headers();
		for (Header header2 : header) {
			System.out.println("Header Name is...." + header2.getName());
			System.out.println("Header Value is...." + header2.getValue());
		}
	}

	@Test
	public void GetBody() {
		RestAssured.baseURI = "http://restapi.demoqa.com/utilities/weather/city";
		RequestSpecification httpRequest = RestAssured.given();
		Response response = httpRequest.get("/Hyderabad");
		ResponseBody body = response.getBody();
		System.out.println("Response Body is...." + body.asString());
	}

	@Test
	public void GetJsonPath() {
		RestAssured.baseURI = "http://restapi.demoqa.com/utilities/weather/city";
		RequestSpecification httpRequest = RestAssured.given();
		Response response = httpRequest.get("/Hyderabad");
		JsonPath jsonPathEvaluator=response.jsonPath();
		System.out.println("City received from Response " + jsonPathEvaluator.get("City"));
		 
		// Print the temperature node
		System.out.println("Temperature received from Response " + jsonPathEvaluator.get("Temperature"));
	 
		// Print the humidity node
		System.out.println("Humidity received from Response " + jsonPathEvaluator.get("Humidity"));
	 
		// Print weather description
		System.out.println("Weather description received from Response " + jsonPathEvaluator.get("Weather"));
	 
		// Print Wind Speed
		System.out.println("City received from Response " + jsonPathEvaluator.get("WindSpeed"));
	 
		// Print Wind Direction Degree
		System.out.println("City received from Response " + jsonPathEvaluator.get("WindDirectionDegree"));

	}
	
	@SuppressWarnings("unchecked")
	@Test
	public void PostRequestUsingJSONObject() {
		RestAssured.baseURI ="http://restapi.demoqa.com/customer";
		RequestSpecification request = RestAssured.given();
		
		JSONObject requestParams =new JSONObject();
		requestParams.put("FirstName", "Virender11111123"); // Cast
		requestParams.put("LastName", "Singh1111112");
		requestParams.put("UserName", "sdimpleu1111s12e2r2dd20111");
		requestParams.put("Password", "password11111121");
		requestParams.put("Email",  "sample2ee2111116d29@gmail.com");
		request.body(requestParams.toJSONString());
		
		Response response=request.post("/register");
		System.out.println("Response Body is..."+response.asString());
		int statusCode=response.getStatusCode();
		System.out.println("Status code is...."+statusCode);
		Assert.assertEquals(statusCode, 201);
		
		String SuccessCode=response.jsonPath().get("SuccessCode");
		Assert.assertEquals(SuccessCode,"OPERATION_SUCCESS","Response Code ");
		
		
	}
	
	@Test
	public void PostRequestUsingJSONObject2() {
		RestAssured.baseURI ="http://restapi.demoqa.com/customer";
		RequestSpecification request = RestAssured.given();
		
		JSONObject requestparams=new JSONObject();
		requestparams.put("FirstName", "Virender11111123"); // Cast
		requestparams.put("LastName", "Singh1111112");
		
		request.body(requestparams.toJSONString());
		
		Response response=request.post("/register");
		System.out.println("Response Body is....."+response.body().toString());
		
		
		
		
	}
}
