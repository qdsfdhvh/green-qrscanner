plugins {
    alias(libs.plugins.kotlin.multiplatform).apply(false)
    alias(libs.plugins.android.application).apply(false)
    alias(libs.plugins.android.library).apply(false)
    alias(libs.plugins.compose.multiplatform).apply(false)
    alias(libs.plugins.spotless)
}

spotless {
    kotlin {
        target("**/*.kt")
        targetExclude("**/build/")
        ktlint(libs.versions.ktlint.get())
    }
    kotlinGradle {
        target("**/*.gradle.kts")
        targetExclude("**/build/")
        ktlint(libs.versions.ktlint.get())
    }
}

allprojects {
    tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile> {
        kotlinOptions {
            allWarningsAsErrors = false
            freeCompilerArgs = freeCompilerArgs + buildComposeMetricsParameters() + listOf(
                "-Xcontext-receivers",
                "-Xskip-prerelease-check",
            )
        }
    }
}

fun Project.buildComposeMetricsParameters(): List<String> {
    return if (findProperty("myapp.enableComposeCompilerReports") == "true") {
        val metricsFolder = File(rootProject.buildDir, "compose_metrics")
        buildList {
            add("-P")
            add("plugin:androidx.compose.compiler.plugins.kotlin:reportsDestination=" + metricsFolder.absolutePath)
            add("-P")
            add("plugin:androidx.compose.compiler.plugins.kotlin:metricsDestination=" + metricsFolder.absolutePath)
        }
    } else {
        emptyList()
    }
}
