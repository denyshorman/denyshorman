import com.github.jengelman.gradle.plugins.shadow.tasks.ShadowJar
import com.google.protobuf.gradle.id
import org.gradle.kotlin.dsl.application
import org.gradle.kotlin.dsl.dependencies
import org.gradle.kotlin.dsl.implementation
import org.gradle.kotlin.dsl.invoke
import org.gradle.kotlin.dsl.kotlin
import org.gradle.kotlin.dsl.main
import org.gradle.kotlin.dsl.proto
import org.gradle.kotlin.dsl.repositories
import org.gradle.kotlin.dsl.sourceSets
import org.gradle.kotlin.dsl.testImplementation
import org.gradle.kotlin.dsl.version
import org.gradle.kotlin.dsl.withType

group = "tech.horman.denys"
version = "1.0.0"

plugins {
    application
    kotlin("jvm") version "1.9.23"
    id("com.google.protobuf") version "0.9.4"
    id("com.github.johnrengelman.shadow") version "8.1.1"
}

kotlin {
    jvmToolchain {
        languageVersion.set(JavaLanguageVersion.of(21))
    }
}

application {
    mainClass.set("tech.horman.denys.MainKt")
}

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.8.0")
    implementation("ch.qos.logback:logback-classic:1.5.3")
    implementation("io.github.microutils:kotlin-logging-jvm:3.0.5")
    implementation("io.grpc:grpc-netty:1.62.2")
    implementation("io.grpc:grpc-protobuf:1.62.2")
    implementation("io.grpc:grpc-services:1.62.2")
    implementation("io.grpc:grpc-stub:1.62.2")
    implementation("io.grpc:grpc-kotlin-stub:1.4.1")
    implementation("com.google.protobuf:protobuf-java-util:3.25.3")
    implementation("com.google.protobuf:protobuf-kotlin:3.25.3")

    testImplementation("org.jetbrains.kotlin:kotlin-test-junit:1.9.22")
}

sourceSets {
    main {
        proto {
            srcDir("../../protocol")
        }
    }
}

tasks {
    withType<ShadowJar> {
        archiveAppendix.set("")
        archiveVersion.set("")
        archiveClassifier.set("")
    }
}

protobuf {
    protoc {
        artifact = "com.google.protobuf:protoc:3.25.3"
    }

    plugins {
        id("grpc") {
            artifact = "io.grpc:protoc-gen-grpc-java:1.62.2"
        }
        id("grpckt") {
            artifact = "io.grpc:protoc-gen-grpc-kotlin:1.4.1:jdk8@jar"
        }
    }

    generateProtoTasks {
        all().forEach {
            it.plugins {
                id("grpc")
                id("grpckt")
            }
            it.builtins {
                id("kotlin")
            }
        }
    }
}
