package com.restapi;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.hasItems;
import static org.hamcrest.Matchers.is;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.testng.annotations.Test;

import groovy.time.BaseDuration.From;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class BasicFeatureJSON {
	@Test
	public void testStatusCode() {
		given().get("https://jsonplaceholder.typicode.com/posts/3").then().statusCode(200);
	}

	@Test
	public void testStatusCode2() {
		given().get("https://jsonplaceholder.typicode.com/posts/3").then().statusCode(200).log().all();
	}

	@Test
	public void testEqualToFunction() {
		given().get("http://services.groupkt.com/country/get/iso2code/IN").then().body("RestResponse.result.name",
				equalTo("India"));
		// body("RestResponse.result.name", "India");
	}

	@Test
	public void testHasItemsFunction() {
		given().get("http://services.groupkt.com/country/get/all").then()
				.body("RestResponse.result.name", hasItem("India"))
				.body("RestResponse.result.name", hasItems("India", "Afghanistan", "Australia"));
		// body("RestResponse.result.name", "India");
	}

	@Test
	public void testParametersAndHeaders() {
		given().param("Key1", "Value1").header("Header1", "ValueA").when()
				.get("http://services.groupkt.com/country/get/all").then()
				.body("RestResponse.result.name", hasItem("India")).statusCode(200).log().all();
	}

	@Test
	public void testWithOutRoot() {
		given().get("http://services.groupkt.com/country/get/iso2code/IN").then()
				.body("RestResponse.result.name", is("India")).body("RestResponse.result.alpha2_code", is("IN"))
				.body("RestResponse.result.alpha3_code", is("IND")).statusCode(200).log().all();
	}

	@Test
	public void testWithRoot() {
		given().get("http://services.groupkt.com/country/get/iso2code/IN").then().root("RestResponse.result.")
				.body("name", is("India")).body("alpha2_code", is("IN")).body("alpha3_code", is("IND")).statusCode(200)
				.log().all();
	}

	@Test
	public void testDetachRoot() {
		given().get("http://services.groupkt.com/country/get/iso2code/IN").then().root("RestResponse.result")
				.body("name", is("India")).body("alpha2_code", is("IN")).detachRoot("result")
				.body("result.alpha3_code", is("IND")).statusCode(200).log().all();
	}

	@Test
	public void testResponseAsString() {
		String response = given().get("http://services.groupkt.com/country/search?text=lands").asString();
		System.out.println("Response is..." + response);
	}

	@Test
	public void testResponseAsInputStream() throws IOException {
		InputStream stream = given().get("http://services.groupkt.com/country/search?text=lands").asInputStream();
		System.out.println("Stream Length is..." + stream.toString().length());
		stream.close();
	}

	@Test
	public void testResponseAsByteArray() throws IOException {
		byte[] byteArray = given().get("http://services.groupkt.com/country/search?text=lands").asByteArray();
		System.out.println("byteArray Length is..." + byteArray.length);

	}

	@Test
	public void getResponseAsXpath() throws IOException {
		String href = given().when().get("https://jsonplaceholder.typicode.com/photos/1").then()
				.contentType(ContentType.JSON).body("albumId", equalTo(1)).extract().path("url");
		System.out.println("Href is...." + href);

		given().when().get(href).then().statusCode(200).log().all();

	}

	@Test
	public void getResponseAsXpath2() throws IOException {
		String href = given().get("https://jsonplaceholder.typicode.com/photos/1").path("thumbnailUrl");
		System.out.println("Href is...." + href);
		given().when().get(href).then().statusCode(200);

		String href2 = given().get("https://jsonplaceholder.typicode.com/photos/1").andReturn().jsonPath()
				.getString("thumbnailUrl");
		System.out.println("Href is...." + href2);

		given().when().get(href2).then().statusCode(200);
	}

	@Test
	public void testextractDeatilsUsingResponse() throws IOException {
		Response response = given().get("https://jsonplaceholder.typicode.com/photos/1").then().extract().response();

		System.out.println("Response is..." + response.contentType());
		System.out.println("Response is..." + response.path("url"));
		System.out.println("Response is..." + response.statusCode());
	}

	@Test
	public void testContentType() throws IOException {
		given().get("http://services.groupkt.com/state/search/IND?text=pradesh").then().statusCode(200)
				.contentType(ContentType.JSON);

	}

	@Test
	public void testSchema() throws IOException {
		/*
		 * given().get("http://geo.groupkt.com/ip/172.217.3.14/json").then().
		 * assertThat().body(matchesJsonSchemaInClass)
		 */
	}
	
	@Test
	public void testPresenceOfElement() throws IOException {
		given().get("http://services.groupkt.com/country/search?text=lands").then().
		body("RestResponse.result.alpha3_code",hasItems("ALA","CYM")).log().all();				

	}
	@Test
	public void testLengthOfResponse() throws IOException {
		given().get("http://services.groupkt.com/country/search?text=lands").then().
		body("RestResponse.result.alpha3_code*.length().sum",greaterThan(10));			
	}
	
	@Test
	public void testGetResponseAsList() throws IOException {
		RequestSpecification httpRequest=RestAssured.given();
		String response=httpRequest.get("http://services.groupkt.com/country/search?text=lands").asString();
		
	}

}
