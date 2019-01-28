package eu.elixir.ega.ebi.it.filedatabase;

import java.util.InputMismatchException;

import com.jayway.restassured.RestAssured;
import com.jayway.restassured.http.ContentType;
import com.jayway.restassured.specification.RequestSpecification;

public class FileDatabaseBase {

    final static String ID_ZERO = "000";
    RequestSpecification REQUEST;
    String datasetId;
    String fileId;
    String indexId;
    String keyId;

    public FileDatabaseBase() {
        try {
            RestAssured.baseURI = System.getProperty("file.host");

            if (RestAssured.baseURI == null) {
                throw new InputMismatchException("fileservice host url is null. Pls check configuration in pom.xml.");
            }

            RestAssured.port = Integer.parseInt(System.getProperty("file.port"));
            datasetId = System.getProperty("datasetId");
            fileId = System.getProperty("fileId");
            indexId = System.getProperty("indexId");

        } catch (Exception ex) {
            ex.printStackTrace();
        }

        REQUEST = RestAssured.given().contentType(ContentType.JSON);
    }

}
