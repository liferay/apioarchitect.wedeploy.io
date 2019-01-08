---
title: Running the example
description: Run the OSGi container
stepNumber: 6
short: Run
---

Now you are ready to run your example. Just execute the following command from your project's root directory:

```bash
./gradlew run
```

Wait a few seconds while the container gets up and try your API by calling your two endpoints.

```bash /person
curl localhost:8080/api/person -H "Accept: application/json"

{
  "totalNumberOfItems" : 2,
  "numberOfItems" : 2,
  "self" : "localhost:8080/api/person?page=1&per_page=2",
  "pages" : {
    "first" : "localhost:8080/api/person?page=1&per_page=2",
    "last" : "localhost:8080/api/person?page=1&per_page=2"
  },
  "collection" : "localhost:8080/api/person",
  "elements" : [ 
      {
        "name" : "Alex",
        "jobTitle" : "Developer",
        "self" : "localhost:8080/api/person/1"
      }, {
        "name" : "David",
        "jobTitle" : "Developer",
        "self" : "localhost:8080/api/person/2"
      } 
  ]
}
```

```bash /person/{id}
curl localhost:8080/api/person/1 -H "Accept: application/json"

{
  "name" : "Alex",
  "jobTitle" : "Developer",
  "self" : "localhost:8080/api/person/1"
}
```

Furthermore, if you want to try your API with a quick JUnit test, first add the following code to your project's `build.gradle`:

```groovy gradle
dependencies {
    // ... the other dependencies
    
    testImplementation group: "khttp", name: "khttp", version: "0.1.0"
    testImplementation group: "org.junit.jupiter", name: "junit-jupiter-api", version: "5.3.1"
    testRuntimeOnly group: "org.junit.jupiter", name: "junit-jupiter-engine", version: "5.3.1"
}
    
test {
    useJUnitPlatform()
}
```

```kotlin kotlin-dsl
dependencies {
    // ... the other dependencies
    
    testImplementation("khttp:khttp:0.1.0")
    testCompile("org.junit.jupiter:junit-jupiter-api:5.3.2")
    testRuntime("org.junit.jupiter:junit-jupiter-engine:5.3.2")

}

tasks.withType<Test> {
    useJUnitPlatform()
}
```

And create a file named `APITests` in `src/test/java/apio/architect/example` containing the following:

```java
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
```

```kotlin
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
```

Then run `./gradlew run` in a terminal session, wait for launching, and run `./gradlew test` in another terminal:

```bash
./gradlew test

BUILD SUCCESSFUL in 2s
5 actionable tasks: 4 executed, 1 up-to-date
```

Congratulations! You have created your first API with Apio Architect!

The final code from this guide can be find [here](https://github.com/liferay/apioarchitect.wedeploy.io/tree/master/on-boarding-samples).

# 🎉🎉🎉

If you want to go deeper, feel free to browse the rest of the [documentation](/docs/), where you will find examples by use case and other features of the library.

![end](/images/onboarding/the_end.gif)