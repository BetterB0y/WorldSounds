package pl.polsl.worldsounds

import com.android.build.api.dsl.CommonExtension
import org.gradle.api.JavaVersion
import org.gradle.api.Project
import org.gradle.kotlin.dsl.withType
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile


fun Project.configureKotlin(
    commonExtension: CommonExtension<*, *, *, *, *>,
) {
    val javaVersion = libs.findVersion("java").get().toString()

    commonExtension.apply {
        compileOptions {
            JavaVersion.toVersion(javaVersion).let {
                sourceCompatibility = it
                targetCompatibility = it
            }
        }
    }

    tasks.withType<KotlinCompile>().configureEach {
        kotlinOptions {
            jvmTarget = javaVersion
        }
    }
}