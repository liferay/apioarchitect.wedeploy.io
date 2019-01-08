---
title: Creating a Resource's Type
description: Starting your contract
stepNumber: 2
short: Type
---

To define an API in Apio Architect, start by defining and exposing a resource model. Follow these steps to do so: 

1.  Define the resource model that the API will expose. In Apio Architect, you do this by creating an interface with the following annotations: 

    -   `@Type("InsertYourType")`: On the interface's declaration
    -   `@Field("insertYourFieldName")`: On methods that return a field
    -   `@Id`: On the method that returns the type's ID

    For example, to create an example `Person` resource, create the following `Person` interface inside `src/main/java/apio/architect/example`. The interface's `@Type` annotation defines the `Person` type. The `getName` and `getJobTitle` methods are annotated with `@Field` and define the `name` and `jobTitle` fields, respectively. And since the `getId` method will return the `Person`'s ID, that method is annotated with `@Id`: 

        // Java
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


        // Kotlin
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

2.  Expose the resource in your API. You'll typically do this by implementing your type interface in a separate class, but for simplicity this example adds a static utility method in the `Person` interface: 

        // Java
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


        // Kotlin
        fun Person.Companion.of(id: Long, name: String, jobTitle: String) = object : Person {
            override val name: String = name

            override val jobTitle: String = jobTitle

            override val id: Long = id
        }
