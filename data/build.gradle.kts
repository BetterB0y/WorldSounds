@Suppress("DSL_SCOPE_VIOLATION") // TODO: Remove once KTIJ-19369 is fixed
plugins {
    id("worldsounds.android.library")
    id("worldsounds.dependencies")
    id("kotlin-kapt")
    alias(libs.plugins.android.kotlin)
}

android {
    namespace = "pl.polsl.worldsounds.data"

    defaultConfig {
        consumerProguardFiles("consumer-rules.pro")
        /* javaCompileOptions {
             annotationProcessorOptions {
                 arguments += mapOf(
                     "room.schemaLocation" to "$projectDir/schemas",
                     "room.incremental" to "true"
                 )
             }
         }*/
    }

    buildTypes.getByName("release").proguardFiles(
        getDefaultProguardFile("proguard-android-optimize.txt"),
        "proguard-rules.pro"
    )
}

kapt {
    correctErrorTypes = true
    useBuildCache = true
}

dependencyImplementation {
    datastore()
    hilt()
}

dependencies {
    implementation(project(":domain"))
}
