plugins {
    id("worldsounds.android.application")
    id("worldsounds.android.compose")
    id("worldsounds.dependencies")
}

android {
    namespace = "pl.polsl.worldsounds"
    defaultConfig {
        applicationId = "pl.polsl.worldsounds"
        resourceConfigurations += listOf("en", "pl")
    }

    packaging {
        resources.excludes += "META-INF/AL2.0"
        resources.excludes += "META-INF/LGPL2.1"
    }
}

kapt {
    correctErrorTypes = true
    useBuildCache = true
}

hilt {
    enableAggregatingTask = true
}

tasks.withType<Test> {
    useJUnitPlatform()
}

dependencyImplementation {
    accompanist()
    coroutines()
    coil()
    JUnit()
    navigation()
    timber()
    hilt()
}

dependencies {
    implementation(project(":data"))
    implementation(project(":domain"))
}