import org.springframework.boot.gradle.tasks.bundling.BootJar

plugins {
    kotlin("plugin.spring")
    kotlin("plugin.jpa")
    kotlin("plugin.allopen")
    kotlin("plugin.noarg")
}

allOpen {
    annotation("javax.persistence.Entity")
    annotation("javax.persistence.MappedSuperclass")
    annotation("javax.persistence.Embeddable")
}

noArg {
    annotation("javax.persistence.Entity")
    annotation("javax.persistence.MappedSuperclass")
    annotation("javax.persistence.Embeddable")
}

dependencies {
    val springVersion by properties

    api(project(":port-out"))

    implementation("org.springframework.boot:spring-boot-starter-data-jpa:$springVersion")
    runtimeOnly("mysql:mysql-connector-java:8.0.33")

    testImplementation("org.springframework.boot:spring-boot-starter-test:$springVersion")
    testRuntimeOnly("com.h2database:h2")
}

tasks {
    withType<Jar> { enabled = true }
    withType<BootJar> { enabled = false }
}
