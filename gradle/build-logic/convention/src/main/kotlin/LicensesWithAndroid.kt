import com.android.build.api.variant.LibraryAndroidComponentsExtension
import org.gradle.api.Project
import org.gradle.configurationcache.extensions.capitalized
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.register

fun Project.configureAndroidLicensesTasks() {
    androidComponents {
        onVariants { variant ->
            val capitalizedVariantName = variant.name.capitalized()

            tasks.register<AssetCopyTask>(
                "copyAndroid${capitalizedVariantName}Licensee",
            ) {
                group = "verification"

                inputFile.set(
                    layout.buildDirectory
                        .file("reports/licensee/android$capitalizedVariantName/artifacts.json"),
                )

                outputDirectory.set(layout.projectDirectory.dir("src/androidMain/resources"))
                outputFilename.set("licenses.json")

                dependsOn("licenseeAndroid$capitalizedVariantName")
            }
        }
    }
}

private fun Project.androidComponents(action: LibraryAndroidComponentsExtension.() -> Unit) =
    extensions.configure(action)
