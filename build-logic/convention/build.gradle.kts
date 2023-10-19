import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    `kotlin-dsl`
}

group = "pl.polsl.worldsounds.buildlogic"

// Configure the build-logic plugins to target JDK 1.8
// This matches the JDK used to build the project, and is not related to what is running on device.
val javaVersion = libs.versions.java.get()

java {
    JavaVersion.toVersion(javaVersion).let {
        sourceCompatibility = it
        targetCompatibility = it
    }
}
tasks.withType<KotlinCompile>().configureEach {
    kotlinOptions {
        jvmTarget = javaVersion
    }
}

dependencies {
    compileOnly(libs.android.gradlePlugin)
    compileOnly(libs.kotlin.gradlePlugin)
}

gradlePlugin {
    plugins {
        register("androidApplication") {
            id = "worldsounds.android.application"
            implementationClass = "AndroidApplicationConventionPlugin"
        }
        register("androidCompose") {
            id = "worldsounds.android.compose"
            implementationClass = "ComposeConventionPlugin"
        }
        register("kotlinDependencies") {
            id = "worldsounds.dependencies"
            implementationClass = "DependencyConventionPlugin"
        }
    }
}
