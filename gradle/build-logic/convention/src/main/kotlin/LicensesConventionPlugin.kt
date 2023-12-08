import app.cash.licensee.LicenseeExtension
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure

class LicensesConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) = with(target) {
        with(pluginManager) {
            apply("app.cash.licensee")
        }
        extensions.configure<LicenseeExtension> {
            allow("Apache-2.0")
            allow("MIT")
            allowUrl("https://developer.android.com/studio/terms.html")
            allowUrl("https://developers.google.com/ml-kit/terms")
            allowUrl("https://github.com/jordond/materialkolor/blob/master/LICENSE") {
                because("this is MIT")
            }
            allowUrl("https://raw.githubusercontent.com/unicode-org/icu/main/LICENSE") {
                // TODO
            }
            ignoreDependencies("junit", "junit") {
                because("junit is used in tests only")
            }
            ignoreDependencies("org.hamcrest", "hamcrest-core") {
                because("hamcrest-core is used in tests only")
            }
        }
        configureAndroidLicensesTasks()
        configureIosLicensesTasks()
    }
}
