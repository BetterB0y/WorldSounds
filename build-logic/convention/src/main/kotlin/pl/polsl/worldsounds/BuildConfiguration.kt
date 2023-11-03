@file:Suppress("UnstableApiUsage")

package pl.polsl.worldsounds

import com.android.build.api.dsl.ApplicationBuildType
import com.android.build.api.dsl.CommonExtension

@Suppress("EnumEntryName")
enum class BuildType(
    val useCodeShrinking: Boolean,
    val useShrinkingResources: Boolean,
    val signingConfig: String? = null
) {
    release(useCodeShrinking = false, useShrinkingResources = false),
    debug(
        useCodeShrinking = false,
        useShrinkingResources = false,
        signingConfig = "debug"
    )
}

fun configureBuildTypes(
    commonExtension: CommonExtension<*, *, *, *, *>,
) {
    commonExtension.apply {
        buildTypes {
            BuildType.values().forEach {
                getByName(it.name) {
                    isMinifyEnabled = it.useCodeShrinking
                    isShrinkResources = it.useShrinkingResources
                    if (it.signingConfig != null && this is ApplicationBuildType) {
                        signingConfig = signingConfigs.getByName(it.signingConfig)
                    }
                    if (it == BuildType.release) {
                        proguardFiles(
                            getDefaultProguardFile("proguard-android-optimize.txt"),
                            "proguard-rules.pro"
                        )
                    }
                }
            }
        }
    }
}