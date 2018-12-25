---
title: Generating an OSGi bundle
description: Using Bndtools
stepNumber: 4
short: Bundle
---

You need to annotate your newly created `ActionRouter` with `@Component` annotation. For that, first, add the OSGi annotations dependency to your `build.gradle`'s `dependencies` block:

```groovy
//highlight-range{3}
dependencies {
    implementation group: "com.liferay", name: "com.liferay.apio.architect.api", version: "2.0.0-20181212.154022-16"
    implementation group: "org.osgi", name: "org.osgi.service.component.annotations", version: "1.3.0"
}
```

```kotlin
//highlight-range{4}
dependencies {
    implementation(kotlin("stdlib"))
    implementation(kotlin("osgi-bundle"))
    implementation("org.osgi:org.osgi.service.component.annotations:1.3.0")
    implementation("com.liferay:com.liferay.apio.architect.api:2.0.0-20181212.154022-16")
}
```

Afterwards, annotate your `ActionRouter`:

```java
//highlight-range{7,12}
package apio.architect.example;

import com.liferay.apio.architect.annotation.Actions.Retrieve;
import com.liferay.apio.architect.annotation.EntryPoint;
import com.liferay.apio.architect.annotation.Id;
import com.liferay.apio.architect.router.ActionRouter;
import org.osgi.service.component.annotations.Component;

import java.util.Arrays;
import java.util.List;

@Component
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
//highlight-range{7,9}
package apio.architect.example

import com.liferay.apio.architect.router.ActionRouter
import com.liferay.apio.architect.annotation.Actions.Retrieve
import com.liferay.apio.architect.annotation.EntryPoint
import com.liferay.apio.architect.annotation.Id
import org.osgi.service.component.annotations.Component

@Component
class PersonActionRouter : ActionRouter<Person> {

    @EntryPoint @Retrieve
    fun getPersons() = listOf(Person.of(1, "Alex", "Developer"), Person.of(2, "David", "Developer"))

    @Retrieve
    fun getPerson(@Id personId: Long) = Person.of(1, "Alex", "Developer")

}
```

With that you will expose `PersonActionRouter` as an `ActionRouter` to the OSGi machinery. Then, you need to convert your generated `jar` to an OSGi bundle. Achieving this is as simple as adding the `bndtools` Gradle plugin:

```groovy
//highlight-range{3}
plugins {
    id 'java'
    id 'biz.aQute.bnd.builder' version '4.1.0'
}
```

```kotlin
//highlight-range{4}
plugins {
    java
    kotlin("jvm") version "1.3.10"
    id("biz.aQute.bnd.builder").version("4.1.0")
}
```

And, finally, create a `bnd.bnd` file in your project's root folder with the following content:

```properties
Bundle-Name: Apio Architect Example
Bundle-SymbolicName: apio.architect.example
Bundle-Version: 1.0.0
```

Consequently, everytime you invoke the `jar` task in the project, all the necesary BND properties will be present in your `META-INF/MANIFEST.MF`.

If you want to try it, execute the following command (after doing a `./gradlew jar`):

```bash
unzip -p build/libs/*.jar META-INF/MANIFEST.MF
```

And you should see an output like this:

```properties
//highlight-range{4-6,8-12}
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
Service-Component: OSGI-INF/apio.architect.example.PersonActionRouter.xml
Tool: Bnd-4.1.0.201810181252
```