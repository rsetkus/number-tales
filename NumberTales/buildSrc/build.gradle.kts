plugins {
    kotlin("jvm") version "1.3.60"
    `java-gradle-plugin`
}

gradlePlugin {
    plugins {
        register("frameworkPublish") {
            id = "lt.setkus.numbertales.publish.framework"
            implementationClass = "lt.setkus.numbertales.publish.framework.FrameworkPlugin"
        }
        register("jarPublish") {
            id = "lt.setkus.numbertales.publish.jar"
            implementationClass = "lt.setkus.numbertales.publish.jar.JarPlugin"
        }
        register("frameworkJarPublish") {
            id = "lt.setkus.numbertales.publish"
            implementationClass = "lt.setkus.numbertales.publish.PublishPlugin"
        }
    }
}

repositories {
    jcenter()
}

dependencies {
    implementation(kotlin("stdlib-jdk8"))
}