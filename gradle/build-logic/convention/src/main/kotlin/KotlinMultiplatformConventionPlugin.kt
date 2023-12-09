
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure
import org.jetbrains.kotlin.gradle.ExperimentalKotlinGradlePluginApi
import org.jetbrains.kotlin.gradle.dsl.KotlinMultiplatformExtension

class KotlinMultiplatformConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) = with(target) {
        with(pluginManager) {
            apply("org.jetbrains.kotlin.multiplatform")
        }
        extensions.configure<KotlinMultiplatformExtension> {
            jvm()
            androidTarget()
            @OptIn(ExperimentalKotlinGradlePluginApi::class)
            applyHierarchyTemplate {
                common {
                    group("jvmCommon") {
                        withJvm()
                        withAndroidTarget()
                    }
                }
            }
            targets.configureEach {
                compilations.configureEach {
                    compilerOptions.configure {
                        freeCompilerArgs.add("-Xexpect-actual-classes")

                        // Various opt-ins
                        freeCompilerArgs.addAll(
                            "-opt-in=org.jetbrains.compose.resources.ExperimentalResourceApi",
                        )
                    }
                }
            }
        }
        configureKotlin()
    }
}
