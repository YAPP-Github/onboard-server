import org.springframework.boot.gradle.tasks.bundling.BootJar

dependencies {
    val springVersion by properties

    api(project(":port-in"))
    implementation(project(":adapter-out:rdb"))

    implementation("org.springframework.boot:spring-boot-starter:$springVersion")

    testImplementation("org.springframework.boot:spring-boot-starter-test:$springVersion")
}

tasks {
    withType<Jar> { enabled = true }
    withType<BootJar> { enabled = false }
}
