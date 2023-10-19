@file:Suppress("unused")

import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.DependencyHandlerScope
import pl.polsl.worldsounds.ImplementationScopeImpl

class DependencyConventionPlugin : Plugin<Project> {
    override fun apply(project: Project) {
        project.extensions.create(
            "dependencyImplementation",
            ImplementationScopeImpl::class.java,
            DependencyHandlerScope.of(project.dependencies),
            project
        )
    }
}