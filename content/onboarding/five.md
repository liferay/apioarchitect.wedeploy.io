---
title: Finalising the environment
description: Generating an OSGi container
stepNumber: 5
---

<!---
TODO: Remove the need for `javax.servlet` by making a credentials' provider not mandatory
-->

First we need to add the following class that will tell Apio Architect, how to obtain the user credentials. This step will not be required in future versions:

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

For this class to compile, you need to add `javax.servlet` dependency to your project's `build.gradle` `dependencies` block:

```groovy
//highlight-range{3}
dependencies {
    implementation group: "com.liferay", name: "com.liferay.apio.architect.api", version: "2.0.0-20181212.154022-16"
    implementation group: "javax.servlet", name: "javax.servlet-api", version: "3.0.1"
    implementation group: "org.osgi", name: "org.osgi.service.component.annotations", version: "1.3.0"
}
```

Now you are ready to run your example.

First, include the `biz.aQute.bnd.gradle` in your gradle's classpath by adding the following block at the beginning of your `build.gradle` file:

```groovy
buildscript {
    repositories {
        mavenCentral()
    }
    dependencies {
        classpath 'biz.aQute.bnd:biz.aQute.bnd.gradle:4.1.0'
    }
}
```

Then, add the following tasks to your `build.gradle` file:

```groovy
// These two imports should be placed at the beginning of 
// your `build.gradle` file
import aQute.bnd.gradle.Bndrun
import aQute.bnd.gradle.Resolve

task resolve(type: Resolve) {
    bndrun 'example.bndrun'
}

task run(type: Bndrun) {
    dependsOn resolve

    bndrun 'example.bndrun'
}
```

Last thing is to instruct gradle how to run an OSGi container with both Apio Architect and our example, easy as pie! First, create an `example.bndrun` file in your project's root directory with the following content:

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

And add all the `runtime` dependencies to your project's `build.gradle` `dependencies` block:

```groovy
//highlight-range{6-16}
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



