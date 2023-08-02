plugins {
    alias(libs.plugins.kotlin.multiplatform).apply(false)
    alias(libs.plugins.android.application).apply(false)
    alias(libs.plugins.android.library).apply(false)
    alias(libs.plugins.android.cache.fix).apply(false)
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
    tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompilationTask<*>>().configureEach {
        compilerOptions {
            allWarningsAsErrors.set(false)

            freeCompilerArgs.add("-Xcontext-receivers")
            freeCompilerArgs.add("-Xskip-prerelease-check")

            if (findProperty("myapp.enableComposeCompilerReports") == "true") {
                freeCompilerArgs.addAll(
                    "-P",
                    "plugin:androidx.compose.compiler.plugins.kotlin:reportsDestination=" +
                        project.buildDir.absolutePath + "/compose_metrics",
                )
                freeCompilerArgs.addAll(
                    "-P",
                    "plugin:androidx.compose.compiler.plugins.kotlin:metricsDestination=" +
                        project.buildDir.absolutePath + "/compose_metrics",
                )
            }
        }
    }
}

apply(from = "gradle/projectDependencyGraph.gradle")

gradle.taskGraph.whenReady {
    // workaround error of: Cannot access class 'app.cash.sqldelight.db.SqlSchema'
    // https://github.com/cashapp/sqldelight/issues/4473
    if (project.hasProperty("noIosMetadata")) {
        allTasks.filter {
            it.path.startsWith(":shared:compileIosMainKotlinMetadata")
        }.forEach {
            it.enabled = false
        }
    }
}
