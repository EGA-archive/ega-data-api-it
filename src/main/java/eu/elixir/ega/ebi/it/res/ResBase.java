package eu.elixir.ega.ebi.it.res;

import com.jayway.restassured.RestAssured;
import com.jayway.restassured.http.ContentType;
import com.jayway.restassured.specification.RequestSpecification;

public class ResBase {
    RequestSpecification REQUEST;
    String unencryptedChecksum;
    String fileId;

    public ResBase() {

        if (System.getProperty("res.url") == null) {
            throw new IllegalArgumentException("Res service host url is null. Pls check configuration in pom.xml.");
        } else if (System.getProperty("res.file.checksum") == null) {
            throw new IllegalArgumentException(
                    "Unencrypted checksum value is null. Pls check configuration in pom.xml.");
        } else if (System.getProperty("fileId") == null) {
            throw new IllegalArgumentException("FileId value is null. Pls check configuration in pom.xml.");
        } else if (System.getProperty("res.port") == null) {
            throw new IllegalArgumentException("Port value is null. Pls check configuration in pom.xml.");
        }

        RestAssured.baseURI = System.getProperty("res.url");
        RestAssured.port = Integer.parseInt(System.getProperty("res.port"));
        unencryptedChecksum = System.getProperty("res.file.checksum");
        fileId = System.getProperty("fileId");

        REQUEST = RestAssured.given().contentType(ContentType.JSON);
    }

}
