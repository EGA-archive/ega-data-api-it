package eu.elixir.ega.ebi.it;

import java.io.IOException;
import java.util.Properties;

import com.jayway.restassured.RestAssured;
import com.jayway.restassured.http.ContentType;
import com.jayway.restassured.specification.RequestSpecification;

public class KeyBase {
    public RequestSpecification REQUEST;
    String fileId;
    String keyId;

    public KeyBase() {
        try {
            Properties props = new Properties();
            props.load(getClass().getClassLoader().getResourceAsStream("config.properties"));

            RestAssured.baseURI = props.getProperty("api.key.uri");
            RestAssured.port = Integer.valueOf(props.getProperty("api.key.port"));
            fileId = props.getProperty("api.key.fileId");
            keyId = props.getProperty("api.key.keyId");

        } catch (IOException ex) {
            ex.printStackTrace();
        }

        REQUEST = RestAssured.given().contentType(ContentType.JSON);
    }

}
