---
title: Finalizing the Environment
description: Generating an OSGi container
stepNumber: 5
short: Build
---

<!---
TODO: Remove the need for `javax.servlet` by making a credentials' provider not mandatory
-->

Now you're ready to finalize your environment. The first two steps here show you how to use credentials with Apio Architect. The remaining steps show you how to generate an OSGi container for running your API. Note that you don't need to generate an OSGi container if you already have one (e.g., Liferay CE Portal 7.1 and Liferay DXP 7.1 already have an OSGi container). 

1\.  Create a class that tells Apio Architect how to get the user credentials necessary for making authenticated requests. Do this by implementing the `Provider` interface parameterized with `Credentials`: 

```java
package apio.architect.example;

import com.liferay.apio.architect.credentials.Credentials;
import com.liferay.apio.architect.provider.Provider;
import org.osgi.service.component.annotations.Component;

import javax.servlet.http.HttpServletRequest;

@Component
public class CredentialsProvider implements Provider<Credentials> {

    @Override
    public Credentials createContext(HttpServletRequest httpServletRequest) {
        return () -> "";
    }

}
```

```kotlin
package apio.architect.example

import com.liferay.apio.architect.credentials.Credentials
import com.liferay.apio.architect.provider.Provider
import org.osgi.service.component.annotations.Component

import javax.servlet.http.HttpServletRequest

@Component
class CredentialsProvider : Provider<Credentials> {

    override fun createContext(httpServletRequest: HttpServletRequest) = Credentials { "" }

}
```

2\.  For this class to compile, you must add the `javax.servlet` dependency to your project's `build.gradle` file in the `dependencies` block:

```groovy gradle
dependencies {
    implementation group: "com.liferay", name: "com.liferay.apio.architect.api", version: "2.0.0-20181212.154022-16"
    implementation group: "javax.servlet", name: "javax.servlet-api", version: "3.0.1"
    implementation group: "org.osgi", name: "org.osgi.service.component.annotations", version: "1.3.0"
}
```

```kotlin kotlin-dsl
dependencies {
    implementation(kotlin("stdlib"))
    implementation(kotlin("osgi-bundle"))
    implementation("javax.servlet:javax.servlet-api:3.0.1")
    implementation("org.osgi:org.osgi.service.component.annotations:1.3.0")
    implementation("com.liferay:com.liferay.apio.architect.api:2.0.0-20181212.154022-16")
}
```

3\.  If you already have an OSGi container (e.g., Liferay CE Portal 7.1 and Liferay DXP 7.1 already have an OSGi container), then you can skip the rest of these steps. Otherwise, you must generate an OSGi container for your API. To do so, first include `biz.aQute.bnd.gradle` in your Gradle classpath by adding the following block at the beginning of your `build.gradle` file: 

```groovy gradle
buildscript {
    repositories {
        mavenCentral()
    }
    dependencies {
        classpath 'biz.aQute.bnd:biz.aQute.bnd.gradle:4.1.0'
    }
}
```

```kotlin kotlin-dsl
buildscript {
    repositories {
        mavenCentral()
    }
    dependencies {
        classpath("biz.aQute.bnd:biz.aQute.bnd.gradle:4.1.0")
    }
}
```

4\.  Then add the following tasks to your `build.gradle` file:

```groovy gradle
task resolve(type: aQute.bnd.gradle.Resolve) {
    bndrun 'example.bndrun'
}

task run(type: aQute.bnd.gradle.Bndrun) {
    dependsOn resolve

    bndrun 'example.bndrun'
}
```

```kotlin kotlin-dsl
tasks {

    val resolve by registering(aQute.bnd.gradle.Resolve::class) {
        setBndrun("example.bndrun")
    }

    val run by registering(aQute.bnd.gradle.Bndrun::class) {
        dependsOn(resolve)
        setBndrun("example.bndrun")
    }

}
```

5\.  Now you must tell Gradle how to run an OSGi container with Apio Architect and your example. First, create the following `example.bndrun` file in your project's root directory: 

```properties
-runee: JavaSE-1.8

-runfw: org.eclipse.osgi;version=3.13.0

-runrequires: \
    osgi.identity;filter:='(osgi.identity=apio.architect.example)',\
    osgi.identity;filter:='(osgi.identity=org.apache.aries.jax.rs.whiteboard)',\
    osgi.identity;filter:='(osgi.identity=org.apache.felix.scr)',\
    osgi.identity;filter:='(osgi.identity=com.liferay.apio.architect.exception.mapper.impl)',\
    osgi.identity;filter:='(osgi.identity=com.liferay.apio.architect.impl)',\
    osgi.identity;filter:='(osgi.identity=com.liferay.apio.architect.uri.mapper.impl)',\

-runsystemcapabilities: ${native_capability}
```

6\.  Then add these `runtime` dependencies to the `dependencies` block in your project's `build.gradle`: 

```groovy gradle
dependencies {
    implementation group: "com.liferay", name: "com.liferay.apio.architect.api", version: "2.0.0-20181212.154022-16"
    implementation group: "javax.servlet", name: "javax.servlet-api", version: "3.0.1"
    implementation group: "org.osgi", name: "org.osgi.service.component.annotations", version: "1.3.0"

    runtime group: "ch.qos.logback", name: "logback-classic", version: "1.2.3"
    runtime group: "com.liferay", name: "com.liferay.apio.architect.exception.mapper.impl", version: "2.0.0-20181212.154037-7"
    runtime group: "com.liferay", name: "com.liferay.apio.architect.impl", version: "2.0.0-20181212.154108-25"
    runtime group: "com.liferay", name: "com.liferay.apio.architect.uri.mapper.impl", version: "2.0.0-20181212.154207-5"
    runtime group: "io.vavr", name: "vavr", version: "0.9.2"
    runtime group: "org.apache.aries.jax.rs", name: "org.apache.aries.jax.rs.whiteboard", version: "1.0.1"
    runtime group: "org.apache.felix", name: "org.apache.felix.eventadmin", version: "1.4.8"
    runtime group: "org.apache.felix", name: "org.apache.felix.http.jetty", version: "3.4.0"
    runtime group: "org.apache.felix", name: "org.apache.felix.scr", version: "2.0.8"
    runtime group: "org.eclipse.platform", name: "org.eclipse.osgi", version: "3.13.0"
    runtime group: "org.osgi", name: "org.osgi.service.cm", version: "1.5.0"
}
```

```kotlin kotlin-dsl
dependencies {
    implementation(kotlin("stdlib"))
    implementation(kotlin("osgi-bundle"))
    implementation("javax.servlet:javax.servlet-api:3.0.1")
    implementation("org.osgi:org.osgi.service.component.annotations:1.3.0")
    implementation("com.liferay:com.liferay.apio.architect.api:2.0.0-20181212.154022-16")

    runtime("ch.qos.logback:logback-classic:1.2.3")
    runtime("com.liferay:com.liferay.apio.architect.exception.mapper.impl:2.0.0-20181212.154037-7")
    runtime("com.liferay:com.liferay.apio.architect.impl:2.0.0-20181212.154108-25")
    runtime("com.liferay:com.liferay.apio.architect.uri.mapper.impl:2.0.0-20181212.154207-5")
    runtime("io.vavr:vavr:0.9.2")
    runtime("org.apache.aries.jax.rs:org.apache.aries.jax.rs.whiteboard:1.0.1")
    runtime("org.apache.felix:org.apache.felix.eventadmin:1.4.8")
    runtime("org.apache.felix:org.apache.felix.http.jetty:3.4.0")
    runtime("org.apache.felix:org.apache.felix.scr:2.0.8")
    runtime("org.eclipse.platform:org.eclipse.osgi:3.13.0")
    runtime("org.osgi:org.osgi.service.cm:1.5.0")
}
```