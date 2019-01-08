package apio.architect.example

import org.json.JSONArray
import org.json.JSONObject
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class APITests {

    @Test
    fun `person endpoint should return 🧔🧔`() {
        val response = khttp.get(
                url = "http://localhost:8080/api/person",
                headers = mapOf("Accept" to "application/json"))

        assertEquals(200, response.statusCode)

        assertEquals(2, response.jsonObject["totalNumberOfItems"])

        (response.jsonObject["elements"] as JSONArray).apply {
            with(get(0) as JSONObject) {
                assertEquals("Alex", get("name"))
                assertEquals("Developer", get("jobTitle"))
            }
            with(get(1) as JSONObject) {
                assertEquals("David", get("name"))
                assertEquals("Developer", get("jobTitle"))
            }
        }
    }

    @Test
    fun `person|{id} endpoint should return 🧔`() {
        val response = khttp.get(
                url = "http://localhost:8080/api/person/1",
                headers = mapOf("Accept" to "application/json"))

        assertEquals(200, response.statusCode)

        assertEquals("Alex", response.jsonObject["name"])
        assertEquals("Developer", response.jsonObject["jobTitle"])
    }

}