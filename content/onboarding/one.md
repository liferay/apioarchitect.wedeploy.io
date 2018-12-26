---
title: Preparing the environment
description: Initializing a new Java Gradle project
stepNumber: 1
short: Setup
---

First, you must initialize a Java gradle project the way you prefer, for example by using the `gradle init` command:

```bash
# For using Gradle with Groovy scripts
gradle init --type basic --dsl groovy

# For using Gradle with Kotlin scripts
gradle init --type basic --dsl kotlin
```

Second, you just need to apply the `java` plugin inside the `build.gradle` file:

```groovy gradle
plugins {
    id 'java'
}
```

```kotlin kotlin-dsl
plugins {
    java
    kotlin("jvm") version "1.3.10"
}
```

Third, add a `repositories` block to your project's `build.gradle` to use the Liferay Public Snapshots repository:

```groovy gradle
repositories {
    mavenCentral()

    maven {
        url "https://repository-cdn.liferay.com/nexus/content/groups/public"
    }
}
```

```kotlin kotlin-dsl
repositories {
    mavenCentral()

    maven("https://repository-cdn.liferay.com/nexus/content/groups/public")
}
```

Finally, create a `dependencies` block in the project's `build.gradle` and add Apio Architect API dependency:

```groovy gradle
dependencies {
    implementation group: "com.liferay", name: "com.liferay.apio.architect.api", version: "2.0.0-20181212.154022-16"
}
```

```kotlin kotlin-dsl
dependencies {
    // These two dependencies are needed if you want
    // to develop your APIs with Kotlin
    implementation(kotlin("stdlib"))
    implementation(kotlin("osgi-bundle"))
    implementation("com.liferay:com.liferay.apio.architect.api:2.0.0-20181212.154022-16")
}
```