package com.restapi;

import static io.restassured.RestAssured.given;
import org.testng.annotations.Test;

import com.restapi.post.POST;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class NodeJsPutRequest {
	@Test
	public void PutRequest() {
		RestAssured.baseURI = "http://localhost:3000";
		Response response = given().when().get("/posts/1");
		System.out.println("Response is..." + response.asString());

	}

	@Test
	public void putRequest2() {
		RestAssured.baseURI = "http://localhost:3000";
		POST post = new POST();
		post.setId("1");
		post.setAuthor("Sudheer");
		post.setTitle("Reddy");
		RestAssured.baseURI = "http://localhost:3000";
		Response response = given().when().contentType(ContentType.JSON).body(post).put("/posts/1");
		System.out.println("Response is...." + response.asString());
	}

	@Test
	public void patchRequest() {
		RestAssured.baseURI = "http://localhost:3000";
		Response response=given().body("{ \"title\": \"Reddy From Godavarikhani\"}").when().contentType(ContentType.JSON).patch("/posts/1");
		System.out.println("Response is..."+response.asString());

	}
}
