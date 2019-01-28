package eu.elixir.ega.ebi.it.key;

import java.util.InputMismatchException;

import com.jayway.restassured.RestAssured;
import com.jayway.restassured.http.ContentType;
import com.jayway.restassured.specification.RequestSpecification;

public class KeyBase {
    RequestSpecification REQUEST;
    String fileId;
    String keyId;

    public KeyBase() {
        try {
            RestAssured.baseURI = System.getProperty("key.host");

            if (RestAssured.baseURI == null) {
                throw new InputMismatchException("Key service host url is null. Pls check configuration in pom.xml.");
            }

            RestAssured.port = Integer.parseInt(System.getProperty("key.port"));
            fileId = System.getProperty("fileId");
            keyId = System.getProperty("keyId");

        } catch (Exception ex) {
            ex.printStackTrace();
        }

        REQUEST = RestAssured.given().contentType(ContentType.JSON);
    }

}
