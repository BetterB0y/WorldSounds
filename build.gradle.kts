buildscript {
    repositories {
        mavenCentral()
        maven {
            url = uri("https://maven.fabric.io/public")
        }
    }
}

plugins {
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.android.kotlin) apply false
}

tasks.withType<Delete> {
    delete(rootProject.buildDir)
}