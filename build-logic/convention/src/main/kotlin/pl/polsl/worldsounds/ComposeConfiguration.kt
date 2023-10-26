@file:Suppress("UnstableApiUsage")

package pl.polsl.worldsounds

import com.android.build.api.dsl.CommonExtension
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies

internal fun Project.configureAndroidCompose(
    commonExtension: CommonExtension<*, *, *, *, *>,
) {
    commonExtension.apply {
        buildFeatures {
            compose = true
            buildConfig = true
        }

        composeOptions {
            kotlinCompilerExtensionVersion = provider { libs.findVersion("composeCompiler").get().toString() }.get()
        }

        dependencies {
            addProvider(
                "implementation",
                provider { libs.findLibrary("compose.activity").get().get() }
            )
            addProvider(
                "implementation",
                provider { libs.findLibrary("compose.material").get().get() }
            )
            addProvider(
                "implementation",
                provider { libs.findLibrary("compose.runtime.livedata").get().get() }
            )
            addProvider(
                "implementation",
                provider { libs.findLibrary("compose.ui").get().get() }
            )
            addProvider(
                "implementation",
                provider { libs.findLibrary("compose.ui.tooling").get().get() }
            )
            addProvider(
                "implementation",
                provider { libs.findLibrary("compose.ui.tooling.preview").get().get() }
            )
            addProvider(
                "implementation",
                provider { libs.findLibrary("compose.material.icons.extended").get().get() }
            )
            addProvider(
                "implementation",
                provider { libs.findLibrary("appcompat").get().get() }
            )
        }
    }
}
