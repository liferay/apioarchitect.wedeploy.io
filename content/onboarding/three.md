---
title: Creating an action router
description: Implementing our first endpoints
stepNumber: 3
---

First, you must create a class `PersonActionRouter` in `src/main/java/apio/architect/example` that implements `ActionRouter`. And provide `Person` as its generic param:

```java
package apio.architect.example;

import com.liferay.apio.architect.router.ActionRouter;

public class PersonActionRouter implements ActionRouter<Person> { }
```

```kotlin
package apio.architect.example

import com.liferay.apio.architect.router.ActionRouter

class PersonActionRouter : ActionRouter<Person>
```

Then, you can create your first endpoint, that will serve a list of all the persons in your API:

```java
//highlight-range{12-18}
package apio.architect.example;

import com.liferay.apio.architect.annotation.Actions.Retrieve;
import com.liferay.apio.architect.annotation.EntryPoint;
import com.liferay.apio.architect.router.ActionRouter;

import java.util.Arrays;
import java.util.List;

public class PersonActionRouter implements ActionRouter<Person> {

    @EntryPoint
    @Retrieve
    public List<Person> getPersons() {
        return Arrays.asList(
            Person.of(1, "Alex", "Developer"),
            Person.of(2, "David", "Developer"));
    }

}
```

```kotlin
//highlight-range{9-10}
package apio.architect.example

import com.liferay.apio.architect.router.ActionRouter
import com.liferay.apio.architect.annotation.Actions.Retrieve
import com.liferay.apio.architect.annotation.EntryPoint

class PersonActionRouter : ActionRouter<Person> {

    @EntryPoint @Retrieve
    fun getPersons() = listOf(
        Person.of(1, "Alex", "Developer"), 
        Person.of(2, "David", "Developer"))

}
```

As you can see, this endpoint must be annotated with both `@Retrieve` and `@EntryPoint`. The first one indicates that this method contains the logic for retrieving a list of persons, while the latter informs Apio Architect that this is a root endpoint. This endpoint will be served in `your_server_url/api/person`.

Similarly, you can create an endpoint for retrieving a person with a specific ID, that will respond to a request like `your_server_url/api/person/{id}`. For this, you just need to create a new method that must receive a `long` value, and return a `Person`. Then annotate the method with `@Retrieve` (like the previous one) and annotate its parameter with `@Id`:

```java
//highlight-range{21-24}
package apio.architect.example;

import com.liferay.apio.architect.annotation.Actions.Retrieve;
import com.liferay.apio.architect.annotation.EntryPoint;
import com.liferay.apio.architect.annotation.Id;
import com.liferay.apio.architect.router.ActionRouter;

import java.util.Arrays;
import java.util.List;

public class PersonActionRouter implements ActionRouter<Person> {

    @EntryPoint
    @Retrieve
    public List<Person> getPersons() {
        return Arrays.asList(
            Person.of(1, "Alex", "Developer"),
            Person.of(2, "David", "Developer"));
    }

    @Retrieve
    public Person getPerson(@Id long personId) {
        return Person.of(1, "Alex", "Developer");
    }

}
```

```kotlin
//highlight-range{13-14}
package apio.architect.example

import com.liferay.apio.architect.router.ActionRouter
import com.liferay.apio.architect.annotation.Actions.Retrieve
import com.liferay.apio.architect.annotation.EntryPoint
import com.liferay.apio.architect.annotation.Id

class PersonActionRouter : ActionRouter<Person> {

    @EntryPoint @Retrieve
    fun getPersons() = listOf(
        Person.of(1, "Alex", "Developer"), 
        Person.of(2, "David", "Developer"))

    @Retrieve
    fun getPerson(@Id personId: Long) = 
        Person.of(1, "Alex", "Developer")

}
```