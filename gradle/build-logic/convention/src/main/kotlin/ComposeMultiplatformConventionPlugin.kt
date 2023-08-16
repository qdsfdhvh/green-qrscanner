
import org.gradle.api.Plugin
import org.gradle.api.Project

class ComposeMultiplatformConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) = with(target) {
        pluginManager.apply("org.jetbrains.compose")
        configureCompose()
    }
}

fun Project.configureCompose() {
    // with(extensions.getByType<ComposeExtension>()) {
    //     kotlinCompilerPlugin.set(
    //         "androidx.compose.compiler:compiler:${libs.findVersion("compose-compiler").get()}",
    //     )
    // }
}
