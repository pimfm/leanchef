
val ktorVersion: String by project
val kotlinVersion: String by project
val logbackVersion: String by project

plugins {
    kotlin("jvm") version "2.0.0"
    id("io.ktor.plugin") version "3.0.0-beta-1"
    id("org.jetbrains.kotlin.plugin.serialization") version "2.0.0"
    id("com.google.devtools.ksp") version "2.0.0-1.0.21"
}

group = "fm.pim"
version = "0.0.1"

application {
    mainClass.set("io.ktor.server.netty.EngineMain")

    val isDevelopment: Boolean = project.ext.has("development")
    applicationDefaultJvmArgs = listOf("-Dio.ktor.development=$isDevelopment")
}

repositories {
    google()
    mavenCentral()
}

dependencies {
    // Ktor
    implementation("io.ktor:ktor-server-core-jvm")
    implementation("io.ktor:ktor-server-netty-jvm")
    implementation("io.ktor:ktor-server-cors-jvm")
    implementation("io.ktor:ktor-server-content-negotiation")
    implementation("io.ktor:ktor-serialization-kotlinx-json")

    // Logging
    implementation("ch.qos.logback:logback-classic:$logbackVersion")

    // Arrow
    implementation("io.arrow-kt:arrow-optics:2.0.0-alpha.2")
    ksp("io.arrow-kt:arrow-optics-ksp-plugin:2.0.0-alpha.2")

    // Testing
    testImplementation("io.ktor:ktor-server-tests-jvm")
    testImplementation("org.jetbrains.kotlin:kotlin-test-junit:$kotlinVersion")
    testImplementation("org.testcontainers:postgresql:1.19.8")

    // FModel
    implementation("com.fraktalio.fmodel:domain:3.5.0")
    implementation("com.fraktalio.fmodel:application-vanilla:3.5.0")
}

tasks.compileKotlin {
    compilerOptions {
        freeCompilerArgs.add("-Xcontext-receivers")
    }
}
