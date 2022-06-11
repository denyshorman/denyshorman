import org.jetbrains.kotlin.gradle.tasks.KotlinCompile
import com.google.protobuf.gradle.generateProtoTasks
import com.google.protobuf.gradle.id
import com.google.protobuf.gradle.plugins
import com.google.protobuf.gradle.protobuf
import com.google.protobuf.gradle.protoc

group = "tech.horman.denys"
version = "1.0.0"

plugins {
    application
    kotlin("jvm") version "1.7.0"
    id("com.google.protobuf") version "0.8.18"
}

application {
    mainClass.set("tech.horman.denys.MainKt")
}

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.6.2")
    implementation("ch.qos.logback:logback-classic:1.2.11")
    implementation("io.github.microutils:kotlin-logging-jvm:2.1.21")
    implementation("io.grpc:grpc-protobuf:1.47.0")
    implementation("io.grpc:grpc-stub:1.47.0")
    implementation("io.grpc:grpc-kotlin-stub:1.3.0")
    implementation("com.google.protobuf:protobuf-java-util:3.21.1")
    implementation("com.google.protobuf:protobuf-kotlin:3.21.1")

    runtimeOnly("io.grpc:grpc-netty:1.47.0")

    testImplementation("org.jetbrains.kotlin:kotlin-test-junit:1.7.0")
}

sourceSets {
    main {
        proto {
            srcDir("../../protocol")
        }
    }
}

tasks {
    withType<KotlinCompile>().all {
        with(kotlinOptions) {
            jvmTarget = "18"
        }
    }
}

protobuf {
    protoc {
        artifact = "com.google.protobuf:protoc:3.21.1"
    }

    plugins {
        id("grpc") {
            artifact = "io.grpc:protoc-gen-grpc-java:1.47.0"
        }
        id("grpckt") {
            artifact = "io.grpc:protoc-gen-grpc-kotlin:1.3.0:jdk8@jar"
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
