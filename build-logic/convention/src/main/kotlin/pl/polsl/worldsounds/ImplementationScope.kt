@file:Suppress("FunctionName")

package pl.polsl.worldsounds

import org.gradle.api.Project
import org.gradle.kotlin.dsl.DependencyHandlerScope

interface ImplementationScope {
    fun JUnit()
    fun navigation()
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

    private fun DependencyHandlerScope.testPlatformImplementation(dependency: String) {
        "testImplementation"(platform(libs.findLibrary(dependency).get().get()))
    }

    private fun DependencyHandlerScope.testImplementation(dependency: String) {
        "testImplementation"(libs.findLibrary(dependency).get().get())
    }

    override fun navigation() = dependencyHandlerScope.run {
        implementation("compose.destinations.core")
        ksp("compose.destinations.ksp")
    }

    override fun JUnit(): Unit = dependencyHandlerScope.run {
        testPlatformImplementation("junit.bom")
        testImplementation("junit.jupiter")
    }

}
