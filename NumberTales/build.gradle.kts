import lt.setkus.numbertales.Versions
import org.jetbrains.kotlin.gradle.tasks.FatFrameworkTask

repositories {
    google()
    jcenter()
    mavenCentral()
}

plugins {
    kotlin("multiplatform") version "1.3.60"
    kotlin("plugin.serialization") version "1.3.60"
    id("com.android.library") version "3.2.1"
    id("lt.setkus.numbertales.publish")
}
val frameworkName = "number-tales"
version = "1.0.3"
group = "lt.setkus.numbertales"

android {
    compileSdkVersion(28)

    defaultConfig {
        minSdkVersion(16)
        targetSdkVersion(28)
    }

    sourceSets {
        val main by getting {
            setRoot("src/androidMain")
        }
        val test by getting {
            setRoot("src/androidTest")
        }
    }

    lintOptions {
        isAbortOnError = false
    }

    packagingOptions {
        exclude("META-INF/*.kotlin_module")
    }
}

kotlin {
    android()
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

val jvmMainImplementation by configurations
val jvmTestImplementation by configurations

dependencies {
    //Kotlin
    commonMainImplementation(kotlin("stdlib-common"))

    //Coroutines
    commonMainImplementation("org.jetbrains.kotlinx:kotlinx-coroutines-core-common:${Versions.kotlinCoroutines}")

    //Ktor
    commonMainImplementation("io.ktor:ktor-client-core:${Versions.ktor}")
    commonMainImplementation("io.ktor:ktor-client-json:${Versions.ktor}")
    commonMainImplementation("io.ktor:ktor-client-logging:${Versions.ktor}")
    commonMainImplementation("io.ktor:ktor-client-serialization:${Versions.ktor}")

    //Serialize
    commonMainImplementation("org.jetbrains.kotlinx:kotlinx-serialization-runtime-common:${Versions.kotlinxSerialization}")

    commonTestImplementation(kotlin("test-common"))
    commonTestImplementation(kotlin("test-annotations-common"))

    //Jvm Coroutines
    jvmMainImplementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:${Versions.kotlinCoroutines}")

    //Jvm Ktor
    jvmMainImplementation("io.ktor:ktor-client-android:${Versions.ktor}")
    jvmMainImplementation("io.ktor:ktor-client-core-jvm:${Versions.ktor}")
    jvmMainImplementation("io.ktor:ktor-client-json-jvm:${Versions.ktor}")
    jvmMainImplementation("io.ktor:ktor-client-serialization-jvm:${Versions.ktor}")

    //Jvm Serialize
    jvmMainImplementation("org.jetbrains.kotlinx:kotlinx-serialization-runtime:${Versions.kotlinxSerialization}")

    jvmMainImplementation(kotlin("stdlib-jdk8"))

    jvmTestImplementation(kotlin("test:1.3.60"))
    jvmTestImplementation(kotlin("test-junit:1.3.60"))
}

tasks.register("releaseFatFramework", FatFrameworkTask::class) {
    baseName = frameworkName

    from(
        kotlin.iosArm64().binaries.getFramework("RELEASE"),
        kotlin.iosX64().binaries.getFramework("RELEASE")
    )
}