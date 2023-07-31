import org.springframework.boot.gradle.tasks.bundling.BootJar

dependencies {
    api(project(":domain"))
}

tasks {
    withType<Jar> { enabled = true }
    withType<BootJar> { enabled = false }
}
