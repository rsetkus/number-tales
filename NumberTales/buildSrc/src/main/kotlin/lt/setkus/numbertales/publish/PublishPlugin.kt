package lt.setkus.numbertales.publish

import org.gradle.api.Plugin
import org.gradle.api.Project

class PublishPlugin : Plugin<Project> {
    override fun apply(target: Project): Unit = with(target) {
        pluginManager.apply("lt.setkus.numbertales.publish.framework")
        pluginManager.apply("lt.setkus.numbertales.publish.jar")

        tasks.register("publishToArtifactory") {
            it.dependsOn(
                    tasks.named("publishFatFrameworkToArtifactory"),
                    tasks.named("publishFrameworkJsonToArtifactory"),
                    tasks.named("publishJarToArtifactory")
            )
        }
    }
}