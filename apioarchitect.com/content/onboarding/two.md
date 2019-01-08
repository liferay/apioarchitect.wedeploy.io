---
title: Creating a resource's type
description: Starting our contract
stepNumber: 2
short: Type
---

When defining an API, we should start by defining the resource model that our API will expose. With Apio Architect we do this by creating an interface and we will use annotations to specify that it is a `@Type`, its `@Field`s and which is its `@Id`.

In our case, let's start defining our `Person` Resource: create a `Person` interface inside `src/main/java/apio/architect/example` with the following contents:

```java
package apio.architect.example;

import com.liferay.apio.architect.annotation.Id;
import com.liferay.apio.architect.annotation.Vocabulary.Field;
import com.liferay.apio.architect.annotation.Vocabulary.Type;
import com.liferay.apio.architect.identifier.Identifier;

@Type("Person")
public interface Person extends Identifier<Long> {

    @Field("name")
    String getName();

    @Field("jobTitle")
    String getJobTitle();

    @Id
    long getId();

}
```

```kotlin
package apio.architect.example

import com.liferay.apio.architect.annotation.Id
import com.liferay.apio.architect.annotation.Vocabulary
import com.liferay.apio.architect.annotation.Vocabulary.Field
import com.liferay.apio.architect.identifier.Identifier

@Type("Person")
interface Person : Identifier<Long> {

    @get:Field("name")
    val name: String

    @get:Field("jobTitle")
    val jobTitle: String

    @get:Id
    val id: Long

    companion object
}
```

With this, you are ready to expose persons in your API, containing their `Id` as a `Long` value, their name and their job title.

In our case, to keep things simple, we will add an static utility method in your `Person` interface to create new persons easily (we will use this method in the following step). In a more real code, you will probably have a separate class with the actual implementation, but for now let's keep it simple:

```java
static Person of(long id, String name, String jobTitle) {
    return new Person() {
        @Override
        public String getName() {
            return name;
        }

        @Override
        public String getJobTitle() {
            return jobTitle;
        }

        @Override
        public long getId() {
            return id;
        }
    };
}
```

```kotlin
fun Person.Companion.of(id: Long, name: String, jobTitle: String) = object : Person {
    override val name: String = name

    override val jobTitle: String = jobTitle

    override val id: Long = id
}
```
