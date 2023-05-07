plugins {
    kotlin("jvm") version "1.7.22"
    kotlin("kapt") version "1.7.22"
    id("org.jlleitschuh.gradle.ktlint") version "10.2.0"
}

repositories {
    mavenCentral()
    google()
}

subprojects {
    apply(plugin = "kotlin")
    apply(plugin = "kotlin-kapt")

    repositories {
        mavenCentral()
        google()
    }

    dependencies {
        val kotestVersion: String by properties

        testImplementation("io.kotest:kotest-runner-junit5-jvm:$kotestVersion")
        testImplementation("io.kotest:kotest-assertions-core:$kotestVersion")
    }

    tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile> {
        kotlinOptions {
            freeCompilerArgs = listOf("-Xjsr305=strict")
            jvmTarget = "17"
        }
    }

    tasks.withType<Test> {
        useJUnitPlatform()
    }
}
