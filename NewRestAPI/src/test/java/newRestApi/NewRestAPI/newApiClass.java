package newRestApi.NewRestAPI;

import org.json.simple.JSONObject;
import org.testng.ITestContext;
import org.testng.annotations.Test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import POJO.pojoclass;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;


public class newApiClass {
	
	@Test()
	public void postMethod(ITestContext val) throws JsonProcessingException {
		RestAssured.baseURI="http://localhost:3000/";
		
		pojoclass obj = new pojoclass();
		
		String namee = obj.SetName("Ankit");
		String respp = obj.SetWeapon("M416");
		String id =obj.SetIdName("LOKI");
		String passww = obj.SetPassword("Ankit@1234");
		
		ObjectMapper objmapp = new ObjectMapper();
		
		Response newRes = given().contentType(ContentType.JSON)
				.body(objmapp.writeValueAsString(obj))
				.when().post("/NewArea/").then().statusCode(201).log().all().extract().response();
		
		
		
		String pass = newRes.jsonPath().getString("password");
		val.setAttribute("password", pass);
		
		String repid = newRes.jsonPath().getString("id");
		val.setAttribute("id", repid);
		
		
		
	}
	
	@Test(dependsOnMethods = "postMethod")
	public void putMethod(ITestContext val) {
		RestAssured.baseURI="http://localhost:3000/";
		
		
		JSONObject details = new JSONObject();
		
		
		details.put("name", "Shivam");
		details.put("weapon", "m7");
		details.put("IdName","LEON");
		details.put("password", "Ankit@123");
		
		String id = val.getAttribute("id").toString();
		given().contentType(ContentType.JSON).body(details.toJSONString())
		.when().put("/NewArea/"+id)
		.then().statusCode(200).log().all();
	}
	@Test(dependsOnMethods = "putMethod")
	public void logInMethod(ITestContext val) {
		RestAssured.baseURI="http://localhost:3000/";
		
		String id1 = val.getAttribute("id").toString();
		
		given()
		.queryParam("id",id1)
		.get("/NewArea/").
		then()
		.statusCode(200)
		.log().all();
	}
	
	@Test(dependsOnMethods = "logInMethod")
	public void logOutMethod() {
		RestAssured.baseURI="http://localhost:3000/";
		given()
		.get("/NewArea/").
		then()
		.statusCode(200)
		.log().all();
	}
	
	@Test(dependsOnMethods = "putMethod")
	public void deleteMethod(ITestContext val) {
		RestAssured.baseURI="http://localhost:3000/";
		String id = val.getAttribute("id").toString();
		
		given().delete("/NewArea/"+id).then().statusCode(200).log().all();
	}
	
	
	
	
}
