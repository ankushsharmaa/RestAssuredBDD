package resources;

import POJO.Place;
import POJO.addLocation;

import java.util.Arrays;

public class TestData {
    public Place addPlacePayload(String name,String language, String address){
        Place p= new Place();
        addLocation loc=new addLocation();
        p.setAccuracy(20);
        p.setAddress(address);
        p.setLanguage(language);
        p.setPhone_number("9123487654");
        p.setWebsite("google.com");
        p.setName(name);
        p.setTypes(Arrays.asList("Mall","Khoka"));
        loc.setLat(36.43);
        loc.setLng(32.32);
        p.setLocation(loc);
        return p;


    }
    public String deletePlacePayload(String placeId){
return "{\r\n     \"place_id\": \""+placeId+"\"\r\n}";
    }
}
