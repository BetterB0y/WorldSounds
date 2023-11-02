@file:Suppress("FunctionName")

package pl.polsl.worldsounds

import org.gradle.api.Project
import org.gradle.kotlin.dsl.DependencyHandlerScope

interface ImplementationScope {
    fun JUnit()
    fun navigation()
    fun timber()
    fun hilt()
    fun datastore()
    fun room()
}

internal open class ImplementationScopeImpl(
    private val dependencyHandlerScope: DependencyHandlerScope,
    project: Project
) :
    ImplementationScope {

    private val libs = project.libs

    private fun DependencyHandlerScope.implementation(dependency: String) {
        "implementation"(libs.findLibrary(dependency).get().get())
    }

    private fun DependencyHandlerScope.ksp(dependency: String) {
        "ksp"(libs.findLibrary(dependency).get().get())
    }

    private fun DependencyHandlerScope.kapt(dependency: String) {
        "kapt"(libs.findLibrary(dependency).get().get())
    }

    private fun DependencyHandlerScope.kaptTest(dependency: String) {
        "kaptTest"(libs.findLibrary(dependency).get().get())
    }

    private fun DependencyHandlerScope.testPlatformImplementation(dependency: String) {
        "testImplementation"(platform(libs.findLibrary(dependency).get().get()))
    }

    private fun DependencyHandlerScope.testImplementation(dependency: String) {
        "testImplementation"(libs.findLibrary(dependency).get().get())
    }

    override fun navigation() = dependencyHandlerScope.run {
        implementation("compose.destinations.core")
        ksp("compose.destinations.ksp")
        implementation("hilt.navigation.compose")
    }

    override fun hilt(): Unit = dependencyHandlerScope.run {
        implementation("hilt.android")
        kapt("hilt.compiler")
        kaptTest("hilt.compiler")
    }

    override fun timber(): Unit = dependencyHandlerScope.run {
        implementation("timber")
    }

    override fun JUnit(): Unit = dependencyHandlerScope.run {
        testPlatformImplementation("junit.bom")
        testImplementation("junit.jupiter")
    }

    override fun datastore(): Unit = dependencyHandlerScope.run {
        implementation("datastore-preferences")
    }

    override fun room(): Unit = dependencyHandlerScope.run {
        implementation("room.ktx")
        kapt("room.compiler")
        implementation("room.runtime")
    }
}
