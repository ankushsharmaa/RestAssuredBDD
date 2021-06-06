package StepDefs;

import POJO.Place;
import POJO.addLocation;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import resources.APIResources;
import resources.TestData;
import resources.Utils;


import static io.restassured.RestAssured.given;
import static org.junit.Assert.*;

public class MyStepdefs extends Utils {
    RequestSpecification res;
    ResponseSpecification responseSpecification;
    Response response;
    TestData data = new TestData();
    static String placeId;

    @Given("add place payload")
    public void addPlacePayload() throws Exception {


    }
    @When("user calls {string} with {string} http request")
    public void user_calls_with_http_request(String resource, String method) {
        APIResources apiResources = APIResources.valueOf(resource);
        if (method.equalsIgnoreCase("post")) {
            response =
                    res.when().post(apiResources.getResource());
        }
        else if (method.equalsIgnoreCase("put")){
            response =
                    res.when().put(apiResources.getResource());

        }
        else if (method.equalsIgnoreCase("get")){
            response =
                    res.when().get(apiResources.getResource());

        }
    }
    @Then("api call is success with status code {int}")
    public void apiCallIsSuccessWithStatusCode(int arg0) {
        assertEquals(arg0, response.getStatusCode());
    }


    @And("{string} in response body is {string}")
    public void inResponseBodyIs(String arg0, String arg1) {


        assertEquals(getJsonPath(response,arg0), arg1);
    }

    @Given("add place payload with {string} {string} {string}")
    public void add_place_payload_with(String string, String string2, String string3) throws Exception {
    	//responseSpecification =
               // new ResponseSpecBuilder().expectStatusCode(200).expectContentType(ContentType.JSON).build();
    	
   	        res = given().spec(requestSpecification()).log().all().body(data.addPlacePayload(string, string2, string3));
    }

    @And("verify place id created maps to {string} using {string}")
    public void verifyPlaceIdCreatedMapsToUsing(String expectedname, String resource) throws Exception {
         placeId=getJsonPath(response,"place_id");
        res = given().spec(requestSpecification()).queryParam("place_id",placeId).log().all();
        user_calls_with_http_request(resource,"get");
        String actualName=getJsonPath(response,"name");
        assertEquals(actualName,expectedname);

    }

    @Given("deleteplace payload")
    public void deleteplacePayload() throws Exception {
res=given().spec(requestSpecification()).body(data.deletePlacePayload(placeId));

    }





    
}