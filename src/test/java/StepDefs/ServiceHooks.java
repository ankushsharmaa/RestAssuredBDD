package StepDefs;

import io.cucumber.java.Before;

public class ServiceHooks {
    @Before("@DeletePlace")
    public void before() throws Exception {
        if(MyStepdefs.placeId==null) {
            MyStepdefs stepdefs = new MyStepdefs();
            stepdefs.add_place_payload_with("ankush", "Hindi", "Monaco");
            stepdefs.user_calls_with_http_request("AddPlaceApi", "Post");
            stepdefs.verifyPlaceIdCreatedMapsToUsing("ankush", "GetPlaceApi");

        }
    }
}
