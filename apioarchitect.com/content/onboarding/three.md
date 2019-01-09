---
title: Creating an Action Router
description: Implementing your first endpoints
stepNumber: 3
short: Router
---

Now that your resource's type is defined, you must create the *Router*. A Router is an Apio Architect component that represents the mapping of a type to its endpoint implementation. As such, you must implement your endpoints in the Router. Follow these steps to create your Router: 

1.  Implement the `ActionRouter` interface parameterized with your type. To create a Router for the example `Person` type, create the class `PersonActionRouter` in `src/main/java/apio/architect/example`. This class must implement `ActionRouter` parameterized with `Person`: 

```java
package apio.architect.example;

import com.liferay.apio.architect.router.ActionRouter;

public class PersonActionRouter implements ActionRouter<Person> {

}
```

```kotlin
package apio.architect.example

import com.liferay.apio.architect.router.ActionRouter

class PersonActionRouter : ActionRouter<Person>
```

2.  Create your endpoints in your Router class. If an endpoint method retrieves data, annotate it with `@Retrieve`. If the method defines a root endpoint, annotate it with `@EntryPoint`. For example, here's `PersonActionRouter` with an endpoint that gets all `Person` entities. Note that the method is annotated with `@EntryPoint` and `@Retrieve`. With this information, Apio Architect maps the endpoint to the URL `your_server_url/api/person`: 

```java
package apio.architect.example;

import com.liferay.apio.architect.annotation.Actions.Retrieve;
import com.liferay.apio.architect.annotation.EntryPoint;
import com.liferay.apio.architect.router.ActionRouter;

import java.util.Arrays;
import java.util.List;

public class PersonActionRouter implements
    ActionRouter<Person> {

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

Similarly, you can add another method to the Router class to create an endpoint for retrieving a person with a specific ID. This method must return `Person` and take a `long` parameter annotated with `@Id` (to use this annotation, import `com.liferay.apio.architect.annotation.Id`). You must also annotate the method with `@Retrieve`: 

```java
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
    
    @Retrieve
    public Person getPerson(@Id long personId) {
        return Person.of(1, "Alex", "Developer");
    }

}
```

```kotlin
package apio.architect.example

import com.liferay.apio.architect.router.ActionRouter
import com.liferay.apio.architect.annotation.Actions.Retrieve
import com.liferay.apio.architect.annotation.EntryPoint

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

This endpoint is accessible at the URL `your_server_url/api/person/{id}`, where `{id}` is the person's ID. 