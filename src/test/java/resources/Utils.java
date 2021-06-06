package resources;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import java.io.*;
import java.util.Properties;

public class Utils {
    public static RequestSpecification req;

    public RequestSpecification requestSpecification() throws Exception {
        if (req==null){
        PrintStream log=new PrintStream(new FileOutputStream("logging.txt"));

         req= new RequestSpecBuilder().setBaseUri(getPropertyValue("baseUrl")).addQueryParam("key",
                "qaclick123").addFilter(RequestLoggingFilter.logRequestTo(log)).
                 addFilter(ResponseLoggingFilter.logResponseTo(log)).setContentType(ContentType.JSON).build();
         return req;}

            return req;

    }
    public String getPropertyValue(String key) throws Exception {
        Properties prop=new Properties();
        FileInputStream fis=new FileInputStream(System.getProperty("user.dir")+"/src/test/java/resources/config" +
                ".properties");
        prop.load(fis);
        String a=prop.getProperty(key);
        return a;
    }

    public  String getJsonPath(Response response, String key){

    String res=response.asString();
        JsonPath js=new JsonPath(res);
        return js.get(key).toString();

    }
}
