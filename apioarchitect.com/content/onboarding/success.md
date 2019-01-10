---
title: Running the Example
description: Running the OSGi container
stepNumber: 6
short: Run
---

Now you're ready to run your example! 

Start it up by running `./gradlew run` in your project's root directory.

Wait a few seconds for the container to start up, then try your API by calling your two endpoints:

```bash
//display-name{/person}
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

```bash
//display-name{/person/{id}}
curl localhost:8080/api/person/1 -H "Accept: application/json"

{
  "name" : "Alex",
  "jobTitle" : "Developer",
  "self" : "localhost:8080/api/person/1"
}
```

Note that you can also try your API with a quick JUnit test. Follow these steps to do so: 

1\.  Add this code to your project's `build.gradle`:

```groovy
//display-name{gradle}
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

```kotlin
//display-name{kotlin-dsl}
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

2\.  Create a file named `APITests` in `src/test/java/apio/architect/example` that contains the following:

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

3\.  Run `./gradlew run` in a terminal session, and wait for the container to launch. Then run the following command in another terminal:

```bash
./gradlew test
```

Congratulations! You created your first API with Apio Architect! You can find this example's final code [here](https://github.com/liferay/apioarchitect.wedeploy.io/tree/master/on-boarding-samples).

To learn more, see the rest of the [documentation](/docs/), which contains examples by use case and other features of the library.

![end](/images/onboarding/the_end.gif)