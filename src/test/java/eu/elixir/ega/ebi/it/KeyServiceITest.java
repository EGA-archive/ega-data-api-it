package eu.elixir.ega.ebi.it;

import static eu.elixir.ega.ebi.constants.Constants.KEY_FILEKEYS;
import static eu.elixir.ega.ebi.constants.Constants.KEY_RETRIEVE;
import static org.apache.http.HttpStatus.SC_OK;
import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;

import org.json.JSONObject;
import org.junit.Test;

import com.jayway.restassured.response.Response;

/**
 * Class to do the integration test for KeyController in ega-data-api-key.
 * 
 * @author amohan
 *
 */
public class KeyServiceITest extends KeyBase {

    /**
     * Verify the api call /keys/filekeys/{fileId} and check status is {@link org.apache.http.HttpStatus#SC_OK}.
     */
    @Test
    public void testGetFileKey() {
        final Response response = REQUEST.get(KEY_FILEKEYS + fileId);
        response.then().assertThat().statusCode(SC_OK);
        assertNotNull(response.body().asString());
    }

    /**
     * Verify the api call /keys/retrieve/{keyId}/public and check status is {@link org.apache.http.HttpStatus#SC_OK}.
     */
    @Test
    public void testGetPublicKeyFromPrivate() {
        final Response response = REQUEST.get(KEY_RETRIEVE + keyId + "/public/");
        response.then().assertThat().statusCode(SC_OK);
        assertOutputKeyId(response.body().asString());
    }

    /**
     * Verify the api call /keys/retrieve/{keyId}/private/object and check status is {@link org.apache.http.HttpStatus#SC_OK}.
     */
    @Test
    public void testGetPrivateKey() {
        final Response response = REQUEST.get(KEY_RETRIEVE + keyId + "/private/object");
        response.then().assertThat().statusCode(SC_OK);
        assertOutputKeyId(response.body().asString());
    }

    /**
     * Verify the api call /keys/retrieve/{keyId}/private/path and check status is {@link org.apache.http.HttpStatus#SC_OK}.
     */
    @Test
    public void testGetPrivateKeyPath() {
        final Response response = REQUEST.get(KEY_RETRIEVE + keyId + "/private/path");
        response.then().assertThat().statusCode(SC_OK);

        final JSONObject jsonObject = new JSONObject(response.body().asString());
        assertNotNull(jsonObject.getString("keyPath"));
        assertNotNull(jsonObject.getString("keyPassPath"));
    }

    /**
     * Verify the api call /keys/retrieve/{keyId}/private/bin and check status is {@link org.apache.http.HttpStatus#SC_OK}.
     */
    @Test
    public void testGetPrivateKeyByte() {
        final Response response = REQUEST.get(KEY_RETRIEVE + keyId + "/private/bin");
        response.then().assertThat().statusCode(SC_OK);
        assertNotNull(response.body().asString());
    }

    /**
     * Verify the api call /keys/retrieve/{keyId}/private/key and check status is {@link org.apache.http.HttpStatus#SC_OK}.
     */
    @Test
    public void testGetPrivateKeyString() {
        final Response response = REQUEST.get(KEY_RETRIEVE + keyId + "/private/key");
        response.then().assertThat().statusCode(SC_OK);
        assertNotNull(response.body().asString());
    }

    /**
     * Verify the api call /keys/retrieve/{keyType}/ids and check status is {@link org.apache.http.HttpStatus#SC_OK}.
     */
    @Test
    public void testGetPublicKeyIds() {
        final Response response = REQUEST.get(KEY_RETRIEVE + "id" + "/ids");
        response.then().assertThat().statusCode(SC_OK);
        assertThat(response.body().asString(), containsString(keyId));
    }

    private void assertOutputKeyId(String jsonStringOutput) {
        final JSONObject jsonObject = new JSONObject(jsonStringOutput);
        final Long keyIdOutput = jsonObject.getLong("keyID");
        assertThat(Long.valueOf(keyId), equalTo(keyIdOutput));
    }
}
