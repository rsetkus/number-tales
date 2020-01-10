import org.jetbrains.kotlin.gradle.tasks.FatFrameworkTask

plugins {
    kotlin("multiplatform") version "1.3.60"
}

val frameworkName = "NumberTales"

kotlin {
    jvm()
    iosArm64 {
        binaries.framework {
            baseName = frameworkName
        }
    }
    iosX64 {
         binaries.framework {
            baseName = frameworkName
        }
    }
}

repositories {
    mavenCentral()
}

val jvmMainImplementation by configurations
dependencies {
    commonMainImplementation(kotlin("stdlib-common"))
    jvmMainImplementation(kotlin("stdlib-jdk8"))
}

tasks.register("releaseFatFramework", FatFrameworkTask::class) {
    baseName = frameworkName

    from(
        kotlin.iosArm64().binaries.getFramework("RELEASE"),
        kotlin.iosX64().binaries.getFramework("RELEASE")
    )
}