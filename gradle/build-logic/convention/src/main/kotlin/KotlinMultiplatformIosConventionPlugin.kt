import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.withType
import org.jetbrains.kotlin.gradle.ExperimentalKotlinGradlePluginApi
import org.jetbrains.kotlin.gradle.dsl.KotlinMultiplatformExtension
import org.jetbrains.kotlin.gradle.plugin.mpp.KotlinNativeTarget
import java.util.Properties

class KotlinMultiplatformIosConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) = with(target) {
        extensions.configure<KotlinMultiplatformExtension> {
            val activeArch = Arch.findByArch(
                rootProject.layout.projectDirectory.file("local.properties").asFile.takeIf { it.exists() }
                    ?.let {
                        Properties().apply {
                            load(it.reader(Charsets.UTF_8))
                        }.getProperty("arch")
                    } ?: System.getProperty("os.arch"),
            )
            when (activeArch) {
                Arch.X86 -> iosX64()
                Arch.ARM -> iosArm64()
                Arch.AARCH -> iosSimulatorArm64()
                Arch.ALL -> {
                    iosArm64()
                    iosX64()
                    iosSimulatorArm64()
                }
            }
            @OptIn(ExperimentalKotlinGradlePluginApi::class)
            applyHierarchyTemplate {
                common {
                    group("ios") {
                        when (activeArch) {
                            Arch.X86 -> withIosX64()
                            Arch.ARM -> withIosArm64()
                            Arch.AARCH -> withIosSimulatorArm64()
                            Arch.ALL -> {
                                withIosX64()
                                withIosArm64()
                                withIosSimulatorArm64()
                            }
                        }
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

                        // Various opt-ins
                        freeCompilerArgs.addAll(
                            "-opt-in=kotlinx.cinterop.ExperimentalForeignApi",
                            "-opt-in=kotlinx.cinterop.BetaInteropApi",
                        )
                    }
                }
            }
        }
    }
}

enum class Arch(val arch: String) {
    ARM("arm64"),
    AARCH("aarch64"),
    X86("x86_64"),
    ALL("all");

    companion object {
        fun findByArch(arch: String?): Arch {
            return values().firstOrNull { it.arch == arch } ?: ALL
        }
    }
}
