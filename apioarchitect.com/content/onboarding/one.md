---
title: Preparing the Environment
description: Initializing a new Java Gradle project
stepNumber: 1
short: Setup
---

Follow these steps to prepare your project:

1.  Initialize a Java Gradle project the way you prefer. For example, you can do this by using the `gradle init` command for Groovy or Kotlin. Run the command that corresponds to the language you want to use for your project: 

        # For using Gradle with Groovy scripts
        gradle init --type basic --dsl groovy

        # For using Gradle with Kotlin scripts
        gradle init --type basic --dsl kotlin

    Note that the rest of the steps in this guide contain example code for both Groovy/Java and Kotlin. You should use the code that corresponds to the language specified by your `gradle init` command. 

2.  Apply the `java` plugin in your project's `build.gradle` file:

        // Groovy
        plugins {
            id 'java'
        }

        // Kotlin
        plugins {
            java
            kotlin("jvm") version "1.3.10"
        }

3.  Configure your project to use the Liferay Public Snapshots repository by adding this `repositories` block in your project's `build.gradle` :

        // Groovy
        repositories {
            mavenCentral()

            maven {
                url "https://repository-cdn.liferay.com/nexus/content/groups/public"
            }
        }

        // Kotlin
        repositories {
            mavenCentral()

            maven("https://repository-cdn.liferay.com/nexus/content/groups/public")
        }

4.  Add the Apio Architect API dependency to your project by adding this `dependencies` block in your project's `build.gradle`: 

        // Groovy
        dependencies {
            implementation group: "com.liferay", name: "com.liferay.apio.architect.api", version: "2.0.0-20181212.154022-16"
        }

        // Kotlin
        dependencies {
            // These dependencies are needed to develop your APIs with Kotlin
            implementation(kotlin("stdlib"))
            implementation(kotlin("osgi-bundle"))
            implementation("com.liferay:com.liferay.apio.architect.api:2.0.0-20181212.154022-16")
        }
