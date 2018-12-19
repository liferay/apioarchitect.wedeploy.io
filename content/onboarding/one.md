---
title: Preparing the environment
description: Initializing a new Java Gradle project
stepNumber: 1
---

First, you must initialize a Java gradle project the way you prefer, for example by using the `gradle init` command:

```bash
gradle init
```

Second, you just need to apply the `java` plugin inside the `build.gradle` file:

```groovy
plugins {
    id 'java'
}
```

Third, add a `repositories` block to your project's `build.gradle` to use the Liferay Public Snapshots repository:

```groovy
repositories {
    mavenCentral()

    maven {
        url "https://repository-cdn.liferay.com/nexus/content/groups/public"
    }
}
```

Finally, create a `dependencies` block in the project's `build.gradle` and add Apio Architect API dependency:

```groovy
dependencies {
    implementation group: "com.liferay", name: "com.liferay.apio.architect.api", version: "2.0.0-20181212.154022-16"
}
```