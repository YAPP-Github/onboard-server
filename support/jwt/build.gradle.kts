import org.springframework.boot.gradle.tasks.bundling.BootJar

dependencies {
    val springVersion by properties
    val jjwtVersion by properties

    implementation(project(":support:yaml"))

    implementation("org.springframework.boot:spring-boot-starter:$springVersion")
    api("io.jsonwebtoken:jjwt-api:$jjwtVersion")
    runtimeOnly("io.jsonwebtoken:jjwt-impl:$jjwtVersion")
    runtimeOnly("io.jsonwebtoken:jjwt-jackson:$jjwtVersion")

    testImplementation("org.springframework.boot:spring-boot-starter-test:$springVersion")
}

tasks {
    withType<Jar> { enabled = true }
    withType<BootJar> { enabled = false }
}
