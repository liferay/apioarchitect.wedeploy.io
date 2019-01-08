package apio.architect.example;

import khttp.KHttp;
import khttp.responses.Response;
import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;

import static java.util.Collections.singletonMap;
import static org.junit.jupiter.api.Assertions.assertEquals;

class APITests {

    @Test
    void personEndpointShouldReturnPersonList() {
        Response response = KHttp.get(
            "http://localhost:8080/api/person",
            singletonMap("Accept", "application/json"));

        assertEquals(200, response.getStatusCode());

        JSONObject jsonObject = response.getJsonObject();

        assertEquals(2, jsonObject.get("totalNumberOfItems"));

        JSONArray elements = (JSONArray) jsonObject.get("elements");

        JSONObject alex = (JSONObject) elements.get(0);

        assertEquals("Alex", alex.get("name"));
        assertEquals("Developer", alex.get("jobTitle"));

        JSONObject david = (JSONObject) elements.get(1);

        assertEquals("David", david.get("name"));
        assertEquals("Developer", david.get("jobTitle"));
    }

    @Test
    void personIdEndpointShouldReturnSinglePerson() {
        Response response = KHttp.get(
            "http://localhost:8080/api/person/1",
            singletonMap("Accept", "application/json"));

        assertEquals(200, response.getStatusCode());

        JSONObject jsonObject = response.getJsonObject();

        assertEquals("Alex", jsonObject.get("name"));
        assertEquals("Developer", jsonObject.get("jobTitle"));
    }

}