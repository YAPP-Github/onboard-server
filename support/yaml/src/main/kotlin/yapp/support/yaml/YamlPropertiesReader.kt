package yapp.support.yaml

import org.springframework.boot.SpringApplication
import org.springframework.boot.context.event.ApplicationEnvironmentPreparedEvent
import org.springframework.boot.env.EnvironmentPostProcessor
import org.springframework.boot.env.YamlPropertySourceLoader
import org.springframework.core.Ordered
import org.springframework.core.annotation.Order
import org.springframework.core.env.ConfigurableEnvironment
import org.springframework.core.io.DefaultResourceLoader
import org.springframework.core.io.support.PathMatchingResourcePatternResolver
import kotlin.streams.asSequence
import java.io.IOException

@Order(Ordered.LOWEST_PRECEDENCE)
class YamlEnvironmentPostProcessor : EnvironmentPostProcessor {
    private val loader = YamlPropertySourceLoader()

    override fun postProcessEnvironment(environment: ConfigurableEnvironment, application: SpringApplication) {
        val activeProfiles = environment.activeProfiles
        val resourceLoader = application.resourceLoader ?: DefaultResourceLoader()
        val resourcePatternResolver = PathMatchingResourcePatternResolver(resourceLoader)
        val resources = resourcePatternResolver.getResources("classpath*:/*.yml")

        resources.forEach { resource ->
            if (!resource.exists())
                throw IllegalArgumentException("Resource $resource does not exist")

            loader.load(resource.filename, resource)
                .stream().asSequence()
                .filter { propertySource -> propertySource.source is Map<*, *> }
                .forEach { propertySource ->
                    val sourceMap = propertySource.source as Map<*, *>
                    val profiles = sourceMap[SPRING_PROFILES]
                    if (profiles == null) {
                        environment.propertySources.addFirst(propertySource)
                    } else {
                        profiles.toString().split(",")
                            .forEach { profile ->
                                if (activeProfiles.contains(profile.trim())) {
                                    environment.propertySources.addFirst(propertySource)
                                }
                            }
                    }
                }
        }
    }

    companion object {
        private const val SPRING_PROFILES = "spring.config.activate.on-profile"
    }
}
