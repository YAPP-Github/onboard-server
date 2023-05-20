import org.springframework.boot.gradle.tasks.bundling.BootJar

dependencies {
    val springVersion by properties

    api(project(":domain"))
    api(project(":port-out"))

    implementation("org.springframework.boot:spring-boot-starter-webflux:$springVersion")
}

tasks {
    withType<Jar> { enabled = true }
    withType<BootJar> { enabled = false }
}
