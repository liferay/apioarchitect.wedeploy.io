buildscript {
    repositories {
        mavenCentral()
    }
    dependencies {
        classpath("biz.aQute.bnd:biz.aQute.bnd.gradle:4.1.0")
    }
}

plugins {
    java
    kotlin("jvm") version "1.3.10"

    id("biz.aQute.bnd.builder").version("4.1.0")
}

tasks {

    val resolve by registering(aQute.bnd.gradle.Resolve::class) {
        setBndrun("example.bndrun")
    }

    val run by registering(aQute.bnd.gradle.Bndrun::class) {
        dependsOn(resolve)
        setBndrun("example.bndrun")
    }

}

repositories {
    mavenCentral()

}

tasks.withType<Jar> {
    manifest {
        attributes(
                "Bundle-Name" to "Apio Architect Example",
                "Bundle-SymbolicName" to "apio.architect.example",
                "Bundle-Version" to "1.0.0"
        )
    }
}

dependencies {
    implementation(kotlin("stdlib"))
    implementation(kotlin("osgi-bundle"))

    implementation("javax.servlet:javax.servlet-api:3.0.1")
    implementation("org.osgi:org.osgi.service.component.annotations:1.3.0")
    implementation("com.liferay:com.liferay.apio.architect.api:1.9.0")

    runtime("ch.qos.logback:logback-classic:1.2.3")
    runtime("com.liferay:com.liferay.apio.architect.exception.mapper.impl:1.0.9")
    runtime("com.liferay:com.liferay.apio.architect.impl:1.0.18")
    runtime("com.liferay:com.liferay.apio.architect.uri.mapper.impl:1.0.5")
    runtime("io.vavr:vavr:0.9.2")
    runtime("org.apache.aries.jax.rs:org.apache.aries.jax.rs.whiteboard:1.0.1")
    runtime("org.apache.felix:org.apache.felix.eventadmin:1.4.8")
    runtime("org.apache.felix:org.apache.felix.http.jetty:3.4.0")
    runtime("org.apache.felix:org.apache.felix.scr:2.0.8")
    runtime("org.eclipse.platform:org.eclipse.osgi:3.13.0")
    runtime("org.osgi:org.osgi.service.cm:1.5.0")

    testImplementation("khttp:khttp:0.1.0")
    testCompile("org.junit.jupiter:junit-jupiter-api:5.3.2")
    testRuntime("org.junit.jupiter:junit-jupiter-engine:5.3.2")

}

tasks.withType<Test> {
    useJUnitPlatform()
}