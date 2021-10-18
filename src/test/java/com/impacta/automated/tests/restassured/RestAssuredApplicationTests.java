package com.impacta.automated.tests.restassured;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.apache.http.HttpStatus;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.get;

import io.restassured.RestAssured;
import io.restassured.response.Response;

import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class RestAssuredApplicationTests {

	@BeforeAll
	public static void setBaseURI() {
		RestAssured.baseURI = "https://age-of-empires-2-api.herokuapp.com/api/v1/";
	}

	@Test
	public void testGetCivilizationSuccess() {
		Response response = get("civilization/4").then().extract().response();
		assertEquals(HttpStatus.SC_OK, response.statusCode());
		assertEquals("Celts", response.jsonPath().getString("name"));
	}

	@Test
	public void testGetNonExistCivilization() {
		Response response = get("civilization/0").then().extract().response();
		assertEquals(HttpStatus.SC_NOT_FOUND, response.statusCode());
		assertEquals("Civilization not found", response.jsonPath().getString("message"));
	}

}
