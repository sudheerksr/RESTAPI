package com.restapi;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;
public class NodeJSDeleteRequest {
  @Test
  public void DeleteRequest(){
	  RestAssured.baseURI="http://localhost:3000";
	  Response response=given().when().delete("/posts/1");
	  System.out.println("Response is...."+response.asString());
  }
}
