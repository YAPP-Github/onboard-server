import org.springframework.boot.gradle.tasks.bundling.BootJar

plugins {
    id("org.springframework.boot")
    id("io.spring.dependency-management")
    kotlin("plugin.spring")
    kotlin("plugin.jpa")
    kotlin("kapt")
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

    api(project(":domain"))
    api(project(":port-out"))

    implementation("org.springframework.boot:spring-boot-starter-data-jpa:$springVersion")
    implementation("org.springframework.cloud:spring-cloud-starter-aws:2.2.6.RELEASE")
    implementation("org.jetbrains.kotlin:kotlin-reflect")
    runtimeOnly("mysql:mysql-connector-java:8.0.33")

    val kapt by configurations
    implementation("com.querydsl:querydsl-jpa:5.0.0:jakarta")
    kapt("com.querydsl:querydsl-apt:5.0.0:jakarta")

    testImplementation("org.springframework.boot:spring-boot-starter-test:$springVersion")
    testRuntimeOnly("com.h2database:h2")
}

tasks {
    withType<Jar> { enabled = true }
    withType<BootJar> { enabled = false }
}
