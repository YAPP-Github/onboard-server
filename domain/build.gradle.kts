import org.springframework.boot.gradle.tasks.bundling.BootJar

tasks {
    withType<Jar> { enabled = true }
    withType<BootJar> { enabled = false }
}
