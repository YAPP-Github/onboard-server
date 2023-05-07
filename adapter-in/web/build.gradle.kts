import org.springframework.boot.gradle.tasks.bundling.BootJar

dependencies {
    val springVersion by properties
    implementation(project(":support:yaml"))
    implementation(project(":core"))

    implementation("org.springframework.boot:spring-boot-starter-web:$springVersion")

    testImplementation("org.springframework.boot:spring-boot-starter-test:$springVersion")
}


tasks {
    withType<Jar> { enabled = false }
    withType<BootJar> { enabled = true }
}
