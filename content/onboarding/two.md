---
title: Creating a resource's type
description: Starting our contract
stepNumber: 2
---

Let's create a `Person` interface inside `src/main/java/apio/architect/example` representing a resource. For example, you could create something like this for representing persons:

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

With this, you are ready to expose persons in your API, containing their `Id` as a `Long` value, their name and their job title.

Also, provide an utility method in your `Person` interface for easily create new persons (this method will be used in the following step):

```java
static Person of(int id, String name, String jobTitle) {
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