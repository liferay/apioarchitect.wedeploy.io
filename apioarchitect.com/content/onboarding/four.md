---
title: Generating an OSGi Bundle
description: Using Bndtools to create the OSGi bundle
stepNumber: 4
short: Bundle
---

Now you're ready to create the OSGi bundle for your API. Follow these steps to do so: 

1\.  Add the OSGi annotations dependency to the `dependencies` block in your `build.gradle`:

```groovy gradle
//highlight-range{3}
dependencies {
    implementation group: "com.liferay", name: "com.liferay.apio.architect.api", version: "2.0.0-20181212.154022-16"
    implementation group: "org.osgi", name: "org.osgi.service.component.annotations", version: "1.3.0"
}
```

```kotlin kotlin-dsl
//highlight-range{5}
dependencies {
    implementation(kotlin("stdlib"))
    implementation(kotlin("osgi-bundle"))
    implementation("com.liferay:com.liferay.apio.architect.api:2.0.0-20181212.154022-16")
    implementation("org.osgi:org.osgi.service.component.annotations:1.3.0")
}
```

2\.  To expose your Router to OSGi, annotate your Router class with `@Component`. For example, here's the example `PersonRouter` class with the `@Component` annotation: 

```java
//highlight-range{7,12}
package apio.architect.example;

import com.liferay.apio.architect.annotation.Actions.Retrieve;
import com.liferay.apio.architect.annotation.EntryPoint;
import com.liferay.apio.architect.router.ActionRouter;
import com.liferay.apio.architect.annotation.Id;
import org.osgi.service.component.annotations.Component;

import java.util.Arrays;
import java.util.List;

@Component
public class PersonRouter implements ActionRouter<Person> {

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
//highlight-range{7,9}
package apio.architect.example

import com.liferay.apio.architect.router.ActionRouter
import com.liferay.apio.architect.annotation.Actions.Retrieve
import com.liferay.apio.architect.annotation.EntryPoint
import com.liferay.apio.architect.annotation.Id
import org.osgi.service.component.annotations.Component

@Component
class PersonRouter : ActionRouter<Person> {

    @EntryPoint @Retrieve
    fun getPersons() = listOf(
        Person.of(1, "Alex", "Developer"), 
        Person.of(2, "David", "Developer"))

    @Retrieve
    fun getPerson(@Id personId: Long) = 
        Person.of(1, "Alex", "Developer")
        
}
```

3\.  Add the Bndtools Gradle plugin to your `build.gradle` file. When you package your API in a JAR file, Bndtools ensures that JAR file is also an OSGi bundle: 

```groovy gradle
//highlight-range{3}
plugins {
    id 'java'
    id 'biz.aQute.bnd.builder' version '4.1.0'
}
```

```kotlin kotlin-dsl
//highlight-range{4}
plugins {
    java
    kotlin("jvm") version "1.3.10"
    id("biz.aQute.bnd.builder").version("4.1.0")
}
```

4\.  The JAR file's manifest (`MANIFEST.MF`) must contain the OSGi bundle's name, symbolic name, and version. To ensure that the manifest contains these properties every time you run the `jar` task in your project, add the following to your `build.gradle` file: 

```groovy gradle
jar {
    manifest {
        attributes(
            "Bundle-Name": "Apio Architect Example",
            "Bundle-SymbolicName": "apio.architect.example",
            "Bundle-Version": "1.0.0"
        )
    }
}
```

```kotlin kotlin-dsl
tasks.withType<Jar> {
    manifest {
        attributes(
            "Bundle-Name" to "Apio Architect Example",
            "Bundle-SymbolicName" to "apio.architect.example",
            "Bundle-Version" to "1.0.0"
        )
    }
}
```

5\.  To test this, run `./gradlew jar` and then execute this command: 

```bash
unzip -p build/libs/*.jar META-INF/MANIFEST.MF
```

You should see an output similar to this: 

```properties
Manifest-Version: 1.0
Bnd-LastModified: 1545147628012
Bundle-ManifestVersion: 2
Bundle-Name: Apio Architect Example
Bundle-SymbolicName: apio.architect.sample
Bundle-Version: 1.0.0
Created-By: 1.8.0_181 (Oracle Corporation)
Import-Package: com.liferay.apio.architect.annotation;version="[2.0,3)",com.liferay.apio.architect.identifier;version="[2.0,3)",com.liferay.apio.architect.router;version="[2.0,3)"
Private-Package: apio.architect.example
Provide-Capability: osgi.service;objectClass:List<String>="com.liferay.apio.architect.router.ActionRouter"
Require-Capability: osgi.extender;filter:="(&(osgi.extender=osgi.component)(version>=1.3.0)(!(version>=2.0.0)))",osgi.ee;filter:="(&(osgi.ee=JavaSE)(version=1.8))"
Service-Component: OSGI-INF/apio.architect.example.PersonRouter.xml
Tool: Bnd-4.1.0.201810181252
```