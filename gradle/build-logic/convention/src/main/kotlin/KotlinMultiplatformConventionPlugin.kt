import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.withType
import org.jetbrains.kotlin.gradle.ExperimentalKotlinGradlePluginApi
import org.jetbrains.kotlin.gradle.dsl.KotlinMultiplatformExtension
import org.jetbrains.kotlin.gradle.plugin.mpp.KotlinNativeTarget

class KotlinMultiplatformConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) = with(target) {
        with(pluginManager) {
            apply("org.jetbrains.kotlin.multiplatform")
        }
        extensions.configure<KotlinMultiplatformExtension> {
            jvm()
            androidTarget()
            iosX64()
            iosArm64()
            iosSimulatorArm64()
            @OptIn(ExperimentalKotlinGradlePluginApi::class)
            targetHierarchy.custom {
                common {
                    group("jvmCommon") {
                        withJvm()
                        withAndroidTarget()
                    }
                    group("ios") {
                        withIosX64()
                        withIosArm64()
                        withIosSimulatorArm64()
                    }
                }
            }
            targets.withType<KotlinNativeTarget>().configureEach {
                binaries.all {
                    // Add linker flag for SQLite. See:
                    // https://github.com/touchlab/SQLiter/issues/77
                    linkerOpts("-lsqlite3")
                }

                compilations.configureEach {
                    compilerOptions.configure {
                        // Try out preview custom allocator in K/N 1.9
                        // https://kotlinlang.org/docs/whatsnew19.html#preview-of-custom-memory-allocator
                        freeCompilerArgs.add("-Xallocator=custom")

                        // Enable debug symbols:
                        // https://kotlinlang.org/docs/native-ios-symbolication.html
                        freeCompilerArgs.add("-Xadd-light-debug=enable")
                    }
                }
            }
        }
        configKotlin()
    }
}
