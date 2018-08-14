package com.restapi;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

import org.testng.annotations.Test;

import com.restapi.post.Info;
import com.restapi.post.InfoArray;
import com.restapi.post.POST;
import com.restapi.post.Post1;
import com.restapi.post.PostArray;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class NodeJsPostRequest {
	
	
	//Start node server
	// https://github.com/typicode/json-server
	// https://github.com/typicode/json-server#add-custom-routes
	
	// https://jsonplaceholder.typicode.com/
	@Test
	public void getRequest() {
		Response resp = given().when().get("http://localhost:3000/posts");
		System.out.println("Response is...." + resp.asString());
	}

	@Test
	public void postRequest() {
		RestAssured.baseURI = "http://localhost:3000";
		Response response = given()
				.body("{\"id\": 4," + "\"title\": \"json-server\"," + "\"author\": \"Sudheer\"\n" + "  }").when()
				.contentType(ContentType.JSON).post("/POSTS");

		System.out.println("Post Data is..." + response.asString());
	}

	@Test
	public void postRequest2() {
		RestAssured.baseURI = "http://localhost:3000";
		POST post = new POST();
		post.setId("10");
		post.setAuthor("Sudheer Reddy");
		post.setTitle("Godavarikhani");

		Response response = given().when().contentType(ContentType.JSON).body(post).post("/posts");
		System.out.println("Response is..." + response.asString());

	}

	@Test
	public void postRequestComplex() {
		RestAssured.baseURI = "http://localhost:3000";
		Info info = new Info();
		info.setAddress("Hyderabad");
		info.setNumber("2");
		info.setEmail("Sudheer@kasarlaenterprises.com");

		Post1 post = new Post1();
		post.setAuthor("Sudheer Reddy");
		post.setId("505209");
		post.setTitle("Industrialist");
		post.setInfo(info);

		Response response = given().when().contentType(ContentType.JSON).body(post).post("/posts");
		System.out.println("Response is...." + response.asString());

	}

	@Test
	public void postRequestWithArrays() {
		RestAssured.baseURI = "http://localhost:3000";

		InfoArray info = new InfoArray();
		info.setNumber("10");
		info.setEmail("Test email");
		info.setAddress("GDK in GDK");

		InfoArray info1 = new InfoArray();
		info1.setNumber("20");
		info1.setEmail("Test email 2");
		info.setAddress("KNR in KNR");

		InfoArray info2 = new InfoArray();
		info2.setNumber("30");
		info2.setEmail("Test email 3");
		info2.setAddress("LAX in KNR");

		InfoArray info3 = new InfoArray();
		info3.setNumber("40");
		info3.setEmail("Test email 4");
		info3.setAddress("KMR in KNR");

		PostArray post = new PostArray();
		post.setId("20");
		post.setAuthor("Sudheer Reddy");
		post.setTitle("Godavarikhani");
		post.setInfo(new InfoArray[] { info, info1, info2, info3 });

		Response response = given().when().contentType(ContentType.JSON).body(post).post("/posts");
		System.out.println("Response is..." + response.asString());

	}

	@Test
	public void ResponseTime() {
		RestAssured.baseURI = "http://localhost:3000";

		Response resp = given().when().get("/posts");
		Long time = resp.then().extract().time();
		System.out.println("Response Time is..."+time);
		
		given().when().get("/posts").then().and().time(lessThan(100L));

	}
}
