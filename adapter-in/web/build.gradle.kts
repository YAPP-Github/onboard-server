import groovy.lang.Closure
import io.swagger.v3.oas.models.servers.Server
import org.springframework.boot.gradle.tasks.bundling.BootJar

plugins {
    id("com.epages.restdocs-api-spec") version "0.18.0"
}

dependencies {
    val springVersion by properties

    implementation(project(":support:yaml"))
    implementation(project(":core"))

    implementation("org.springframework.boot:spring-boot-starter-web:$springVersion")

    testImplementation("org.springframework.boot:spring-boot-starter-test:$springVersion")
    testImplementation("org.springframework.restdocs:spring-restdocs-mockmvc:3.0.0")
    testImplementation("com.epages:restdocs-api-spec-mockmvc:0.18.0")
}

tasks {
    withType<Jar> { enabled = false }
    withType<BootJar> {
        enabled = true
//        mainClass.set("yapp.rating.boardgame.WebApplicationKt")
    }
}

tasks {
    openapi3 {
        setServers(
            listOf(
                toServer("http://localhost:8080"),
            )
        )
        title = "My API"
        description = "My API description"
//    tagDescriptionsPropertiesFile = "src/docs/tag-descriptions.yaml"
        version = "0.1.0"
        format = "yaml"
    }

    postman {
        title = "My API"
        version = "0.1.0"
        baseUrl = "https://localhost:8080"
    }
}

fun toServer(url: String): Closure<Server> = closureOf<Server> { this.url = url } as Closure<Server>
