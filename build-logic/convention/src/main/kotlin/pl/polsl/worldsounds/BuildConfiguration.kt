@file:Suppress("UnstableApiUsage")

package pl.polsl.worldsounds

import com.android.build.api.dsl.ApplicationBuildType
import com.android.build.api.dsl.CommonExtension

@Suppress("EnumEntryName")
enum class BuildType(val useCodeShrinking: Boolean, val signingConfig: String? = null) {
    release(useCodeShrinking = false),
    debug(
        useCodeShrinking = false,
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
                    if (it.signingConfig != null && this is ApplicationBuildType) {
                        signingConfig = signingConfigs.getByName(it.signingConfig)
                    }
                }
            }
        }
    }
}