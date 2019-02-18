package eu.elixir.ega.ebi.it.res;

import static eu.elixir.ega.ebi.it.common.Common.getMd5DigestFromResponse;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

import org.apache.http.HttpStatus;
import org.json.JSONObject;
import org.junit.Test;

import com.jayway.restassured.response.Response;

/**
 * Class to do the integration test for FileController in ega-data-api-key.
 * 
 * @author amohan
 *
 */
public class FileControllerIntgTest extends ResBase {

    private final static String FILE_PATH = "/file/archive/";
    private final static String KEY_ID_ZERO = "000";

    /**
     * Verify the api call /file/archive/{id} and check status is
     * {@link org.apache.http.HttpStatus#SC_OK}. Also checks the response body
     * should not be null.
     * 
     * @throws Exception
     */
    @Test
    public void testGetArchiveFile() throws Exception {
        final Response response = REQUEST.get(FILE_PATH + fileId + "?destinationFormat=plain");
        assertTrue(getMd5DigestFromResponse(response).equalsIgnoreCase(unencryptedChecksum));
    }

    /**
     * Verify the api call /file/archive/{id} passing wrong keyId 000 and expecting
     * the response body status code to be
     * {@link org.apache.http.HttpStatus#SC_NOT_FOUND}.
     * 
     * @throws Exception
     */
    @Test
    public void testGetArchiveFileForZeroKeyId() throws Exception {
        final Response response = REQUEST.get(FILE_PATH + KEY_ID_ZERO + "?destinationFormat=plain");
        final JSONObject jsonObject = new JSONObject(response.body().asString());
        final int status = jsonObject.getInt("status");

        assertThat(status, equalTo(HttpStatus.SC_NOT_FOUND));
    }

}
