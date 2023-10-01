import org.springframework.boot.gradle.tasks.bundling.BootJar

plugins {
    id("org.springframework.boot")
    kotlin("plugin.spring")
}

dependencies {
    val springVersion by properties
    val jjwtVersion by properties

    api(project(":domain"))
    api(project(":port-out"))
    implementation(project(":support:jwt"))

    implementation("org.springframework.boot:spring-boot-starter-webflux:$springVersion")
    implementation("com.google.api-client:google-api-client:1.32.1")
}

tasks {
    withType<Jar> { enabled = true }
    withType<BootJar> { enabled = false }
}
