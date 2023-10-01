import org.springframework.boot.gradle.tasks.bundling.BootJar

plugins {
    kotlin("plugin.spring")
}

dependencies {
    val springVersion by properties

    api(project(":port-in"))
    implementation(project(":adapter-out:rdb"))
    implementation(project(":adapter-out:social"))
    implementation(project(":support:jwt"))

    implementation("org.springframework.boot:spring-boot-starter:$springVersion")

    testImplementation("org.springframework.boot:spring-boot-starter-test:$springVersion")
}

tasks {
    withType<Jar> { enabled = true }
    withType<BootJar> { enabled = false }
}
