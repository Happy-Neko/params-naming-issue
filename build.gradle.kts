plugins {
    id("org.jetbrains.kotlin.jvm") version "1.4.20"
    id("org.jetbrains.kotlin.kapt") version "1.4.20"
    id("org.jetbrains.kotlin.plugin.allopen") version "1.4.20"
    id("io.micronaut.application") version "1.2.0"
}

version = "0.1"
group = "com.github.issue"

repositories {
    jcenter()
}

micronaut {
    runtime("netty")
    testRuntime("junit5")
    processing {
        incremental(true)
        annotations("com.github.issue.*")
    }
}

dependencies {
    implementation("io.micronaut:micronaut-validation")
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8:1.4.20")
    implementation("org.jetbrains.kotlin:kotlin-reflect:1.4.20")
    implementation("io.micronaut.kotlin:micronaut-kotlin-runtime")
    implementation("io.micronaut:micronaut-runtime")
    implementation("javax.annotation:javax.annotation-api")
    implementation("io.micronaut:micronaut-http-client")
    runtimeOnly("ch.qos.logback:logback-classic")
    runtimeOnly("com.fasterxml.jackson.module:jackson-module-kotlin")
}

application {
    mainClass.set("com.github.issue.ApplicationKt")
}

java {
    sourceCompatibility = JavaVersion.toVersion("11")
}

tasks {
    compileKotlin {
        kotlinOptions {
            jvmTarget = "11"
        }
    }
    compileTestKotlin {
        kotlinOptions {
            jvmTarget = "11"
        }
    }
}
