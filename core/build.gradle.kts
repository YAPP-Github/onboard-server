import org.springframework.boot.gradle.tasks.bundling.BootJar

dependencies {
    val springVersion by properties

    api(project(":port-in"))
    implementation(project(":adapter-out:rdb"))

    implementation("org.springframework.boot:spring-boot-starter:$springVersion")
    implementation("io.jsonwebtoken:jjwt-api:0.11.5")
    runtimeOnly("io.jsonwebtoken:jjwt-impl:0.11.5")
    runtimeOnly("io.jsonwebtoken:jjwt-jackson:0.11.5")

    testImplementation("org.springframework.boot:spring-boot-starter-test:$springVersion")
}

tasks {
    withType<Jar> { enabled = true }
    withType<BootJar> { enabled = false }
}
