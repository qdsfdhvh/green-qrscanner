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
    configurations.all {
        resolutionStrategy {
            exclude("io.github.aakira", "napier")
            exclude("androidx.fragment", "fragment")
            exclude("androidx.lifecycle", "lifecycle-livedata")
            exclude("androidx.vectordrawable")
        }
    }
    tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompilationTask<*>>().configureEach {
        compilerOptions {
            allWarningsAsErrors.set(false)

            freeCompilerArgs.add("-Xcontext-receivers")
            freeCompilerArgs.add("-Xskip-prerelease-check")

            if (findProperty("myapp.enableComposeCompilerReports") == "true") {
                freeCompilerArgs.addAll(
                    "-P",
                    "plugin:androidx.compose.compiler.plugins.kotlin:reportsDestination=" +
                        project.layout.buildDirectory.dir("compose_metrics").get().asFile.absolutePath,
                )
                freeCompilerArgs.addAll(
                    "-P",
                    "plugin:androidx.compose.compiler.plugins.kotlin:metricsDestination=" +
                        project.layout.buildDirectory.dir("compose_metrics").get().asFile.absolutePath,
                )
            }
        }
    }
}

gradle.taskGraph.whenReady {
    // workaround error of: Cannot access class 'app.cash.sqldelight.db.SqlSchema'
    // https://github.com/cashapp/sqldelight/issues/4473
    if (project.hasProperty("noIosMetadata")) {
        allTasks.asSequence().filter {
            it.path.startsWith(":shared:compileIosMainKotlinMetadata")
        }.forEach {
            it.enabled = false
        }
    }
    if (project.hasProperty("noLinkPodDebug")) {
        allTasks.asSequence().filter {
            it.path.startsWith(":iosAppCombine:linkPodDebug")
        }.forEach {
            it.enabled = false
        }
    }
}
