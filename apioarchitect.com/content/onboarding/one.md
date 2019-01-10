---
title: Preparing the Environment
description: Initializing a new Java Gradle project
stepNumber: 1
short: Setup
---

Follow these steps to prepare your project:

1\.  Initialize a Java Gradle project the way you prefer. For example, you can do this by using the `gradle init` command for Groovy or Kotlin: 

```bash
# For using Gradle with Groovy scripts
gradle init --type basic --dsl groovy

# For using Gradle with Kotlin scripts
gradle init --type basic --dsl kotlin
```

2\.  Apply the `java` plugin in your project's `build.gradle` file:

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

3\.  Configure your project's dependency repositories by adding this `repositories` block in your project's `build.gradle`:

```groovy gradle
repositories {
    mavenCentral()
    jcenter()
}
```

```kotlin kotlin-dsl
repositories {
    mavenCentral()
    jcenter()
}
```

4\.  Add the Apio Architect API dependency to your project by adding this `dependencies` block in your project's `build.gradle`: 

```groovy gradle
dependencies {
    implementation group: "com.liferay", name: "com.liferay.apio.architect.api", version: "2.0.0-20181212.154022-16"
}
```

```kotlin kotlin-dsl
dependencies {
    // These dependencies are needed to develop your APIs with Kotlin
    implementation(kotlin("stdlib"))
    implementation(kotlin("osgi-bundle"))
    implementation("com.liferay:com.liferay.apio.architect.api:2.0.0-20181212.154022-16")
}
```