import org.springframework.boot.gradle.tasks.bundling.BootJar

dependencies {
    val springVersion by properties

    implementation("org.springframework.boot:spring-boot-starter:$springVersion")
}

tasks {
    withType<Jar> { enabled = true }
    withType<BootJar> { enabled = false }
}
