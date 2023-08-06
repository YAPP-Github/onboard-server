import org.springframework.boot.gradle.tasks.bundling.BootJar

dependencies {
    val springVersion by properties
    implementation(project(":support:yaml"))

    implementation("org.springframework.boot:spring-boot-starter:$springVersion")
    implementation("ca.pjer:logback-awslogs-appender:1.6.0")
}

tasks {
    withType<Jar> { enabled = true }
    withType<BootJar> { enabled = false }
}
