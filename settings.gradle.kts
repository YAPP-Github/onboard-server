rootProject.name = "BoardGameRating"

pluginManagement {
    val springVersion: String by settings
    val kotlinPluginVersion: String by settings
    
    plugins {
        id("org.springframework.boot") version springVersion
        
        kotlin("plugin.spring") version kotlinPluginVersion
        kotlin("plugin.jpa") version kotlinPluginVersion
        kotlin("plugin.allopen") version kotlinPluginVersion
        kotlin("plugin.noarg") version kotlinPluginVersion
    }
}

include(
    "domain",
    "core",
    "port-in",
    "port-out",
    "adapter-in:web",
    "adapter-out:rdb",
    "support:yaml",
)
