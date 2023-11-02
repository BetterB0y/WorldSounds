@file:Suppress("UnstableApiUsage", "unused")

import com.android.build.gradle.LibraryExtension
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure
import pl.polsl.worldsounds.configureBuildTypes
import pl.polsl.worldsounds.configureKotlin

class AndroidLibraryConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {

            with(pluginManager) {
                apply("com.android.library")
                apply("org.jetbrains.kotlin.android")
            }

            extensions.configure<LibraryExtension> {
                compileSdk = ModuleConfig.compileSdk
                defaultConfig {
                    minSdk = ModuleConfig.minSdk
                    targetSdk = ModuleConfig.targetSdk
                }
                configureKotlin(this)
                configureBuildTypes(this)
            }
        }
    }

}