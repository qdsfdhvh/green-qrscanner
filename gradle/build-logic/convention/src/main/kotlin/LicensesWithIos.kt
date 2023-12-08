
import org.gradle.api.Project
import org.gradle.configurationcache.extensions.capitalized
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.register
import org.jetbrains.kotlin.gradle.dsl.KotlinMultiplatformExtension

fun Project.configureIosLicensesTasks() {
    kotlin {
        targets.filter { it.name.contains("ios", ignoreCase = true) }
            .forEach { target ->
                val capitalizedTargetName = target.name.capitalized()
                tasks.register<AssetCopyTask>("copy${capitalizedTargetName}Licensee") {
                    group = "verification"

                    inputFile.set(
                        layout.buildDirectory
                            .file("reports/licensee/${target.name}/artifacts.json"),
                    )

                    outputDirectory.set(layout.projectDirectory.dir("src/${target.name}Main/resources"))
                    outputFilename.set("licenses.json")

                    dependsOn("licensee$capitalizedTargetName")
                }
            }
    }
}

private fun Project.kotlin(action: KotlinMultiplatformExtension.() -> Unit) =
    extensions.configure(action)
