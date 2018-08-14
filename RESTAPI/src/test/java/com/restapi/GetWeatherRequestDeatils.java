package com.restapi;

import static io.restassured.RestAssured.given;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class GetWeatherRequestDeatils {
	@Test
	public void getWeatherDeatils() {

		RestAssured.baseURI = "http://api.openweathermap.org";
		given().param("id", "2172797").param("appid", "6932c8d31ddba87e8ffed9c941d5a76d").when()
				.get("/data/2.5/weather").then().assertThat().statusCode(200).and().contentType(ContentType.JSON);
	}

	@Test
	public void Test1() {
		RestAssured.baseURI = "http://api.openweathermap.org";
		Response response = given().param("id", "2172797").param("appid", "6932c8d31ddba87e8ffed9c941d5a76d").when()
				.get("/data/2.5/weather");
		if (response.getStatusCode() == 200) {
			System.out.println("Valid Response");
		} else {
			System.out.println("Invalid Response");
		}
		System.out.println(response.asString());
	}

	@Test
	public void Test2() {
		RestAssured.baseURI = "api.openweathermap.org";
		given().param("id", "2172797").param("appid", "6932c8d31ddba87e8ffed9c941d5a76d").when()
				.get("/data/2.5/weather").then().assertThat().statusCode(200);
	}

	@Test
	public void weatherReport() {
		RestAssured.baseURI = "http://api.openweathermap.org";
		String weatherReport = given().param("id", "2172797").param("appid", "6932c8d31ddba87e8ffed9c941d5a76d")
				.get("/data/2.5/weather").then().assertThat().statusCode(200).and().contentType(ContentType.JSON)
				.extract().path("weather[0].description");
		System.out.println("Weather Report" + weatherReport);
	}

	@Test
	public void weatherReport2() {
		RestAssured.baseURI = "http://api.openweathermap.org";

		Response resp = given().param("id", "2172797").param("appid", "6932c8d31ddba87e8ffed9c941d5a76d").when()
				.get("/data/2.5/weather");

		String path = resp.then().contentType(ContentType.JSON).extract().path("weather[0].description");
		System.out.println("Path is..." + path);

	}

	@Test
	public void getLatLong() {
		RestAssured.baseURI = "http://api.openweathermap.org";
		Response response = given().param("id", "2172797").param("appid", "6932c8d31ddba87e8ffed9c941d5a76d").when()
				.get("/data/2.5/weather");
		String latitude = String.valueOf(response.then().contentType(ContentType.JSON).extract().path("coord.lat"));
		System.out.println("Latitude is...." + latitude);

		String longitude = String.valueOf(response.then().contentType(ContentType.JSON).extract().path("coord.lon"));
		System.out.println("Longitude is...." + longitude);

		Response resp = given().param("lat", latitude).param("lon", longitude).param("appid", "6932c8d31ddba87e8ffed9c941d5a76d").when().get("/data/2.5/weather");
		String description = resp.then().contentType(ContentType.JSON).extract().path("weather[0].description");
		System.out.println("Latititude is..."+description);
		
	

	}

}
