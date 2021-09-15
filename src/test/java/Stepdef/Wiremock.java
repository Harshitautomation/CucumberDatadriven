package Stepdef;

import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.junit.WireMockRule;
import io.restassured.RestAssured;
import io.restassured.matcher.RestAssuredMatchers;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.After;
import org.junit.Assert;
import org.junit.Rule;
import static io.restassured.RestAssured.*;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static com.github.tomakehurst.wiremock.core.WireMockConfiguration.options;


public class Wiremock
{
//   @Rule
//   static public WireMockRule wireMockRule = new WireMockRule(options().port(5566));




public static void main (String args[])
{
   WireMockServer wireMockServer = new WireMockServer(options().port(5566));
   wireMockServer.start();
   configureFor("localhost",5566);

   givenThat(get(urlMatching("/employees/1")).atPriority(0).
           willReturn(aResponse().withHeader("Content-Type","application/json").withBody("Hello There").withStatus(200))
           );

   Response res= given().get("http://localhost:5566/employees/1").then().extract().response();
   System.out.println(res.body().asString());
   Assert.assertEquals(res.body().asString(),"Hello There");
   Assert.assertTrue(res.statusCode()==200);
   System.out.println("verification Passed");

   wireMockServer.stop();

}

}
