package lt.setkus.numbertales.publish.jar

import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.publish.PublishingExtension

class JarPlugin : Plugin<Project> {
    override fun apply(target: Project): Unit = with (target) {
        pluginManager.apply("maven-publish")


        val artifactoryUsername = target.property("artifactory_username") as String
        val artifactoryPassword = target.property("artifactory_password") as String

        extensions.getByType(PublishingExtension::class.java).apply {
            repositories { repositoryHandler ->
                repositoryHandler.maven {
                    it.name = "MyArtifactory-releases"
                    it.url = project.uri("http://localhost:8081/artifactory/libs-release-local")
                    it.credentials { credentials ->
                        credentials.username = artifactoryUsername
                        credentials.password = artifactoryPassword
                    }
                }
            }
        }

        tasks.register("publishJarToArtifactory") {
            it.dependsOn(
                    tasks.named("publishMetadataPublicationToMyArtifactory-releasesRepository"),
                    tasks.named("publishJvmPublicationToMyArtifactory-releasesRepository")
            )
        }
    }
}