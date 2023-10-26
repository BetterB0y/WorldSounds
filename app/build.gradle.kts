plugins {
    id("worldsounds.android.application")
    id("worldsounds.android.compose")
    id("worldsounds.dependencies")
}

android {
    namespace = "pl.polsl.worldsounds"
    defaultConfig {
        applicationId = "pl.polsl.worldsounds"
    }

    buildTypes.getByName("release").proguardFiles(
        getDefaultProguardFile("proguard-android-optimize.txt"),
        "proguard-rules.pro"
    )

    packaging {
        resources.excludes += "META-INF/AL2.0"
        resources.excludes += "META-INF/LGPL2.1"
    }
}

tasks.withType<Test> {
    useJUnitPlatform()
}

dependencyImplementation {
    JUnit()
    navigation()
}