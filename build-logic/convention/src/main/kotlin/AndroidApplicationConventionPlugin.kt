@file:Suppress("UnstableApiUsage", "unused")

import com.android.build.api.dsl.ApplicationExtension
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure
import pl.polsl.worldsounds.configureBuildTypes
import pl.polsl.worldsounds.configureKotlin

class AndroidApplicationConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            with(pluginManager) {
                apply("com.android.application")
                apply("org.jetbrains.kotlin.android")
            }

            extensions.configure<ApplicationExtension> {
                with(defaultConfig) {
                    compileSdk = ModuleConfig.compileSdk
                    minSdk = ModuleConfig.minSdk
                    targetSdk = ModuleConfig.targetSdk
                    versionCode = ModuleConfig.versionCode
                    versionName = ModuleConfig.versionName
                    vectorDrawables.useSupportLibrary = true
                }
                configureKotlin(this)
                configureBuildTypes(this)
            }
        }
    }

}