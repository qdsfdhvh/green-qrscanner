import org.gradle.configurationcache.extensions.capitalized
import org.jetbrains.kotlin.gradle.dsl.KotlinMultiplatformExtension
import org.jetbrains.kotlin.gradle.plugin.KotlinPlatformType

plugins {
    id("app.android.library")
    id("app.kotlin.multiplatform")
    id("app.kotlin.multiplatform.ios")
    id("app.compose.multiplatform")
    alias(libs.plugins.kotlin.serialization)
    alias(libs.plugins.sqldelight)
    alias(libs.plugins.libres)
    alias(libs.plugins.ksp)
    alias(libs.plugins.licensee)
    alias(libs.plugins.aboutlibraries)
}

kotlin {
    @Suppress("UNUSED_VARIABLE")
    sourceSets {
        val commonMain by getting {
            dependencies {
                implementation(compose.runtime)
                implementation(compose.foundation)
                implementation(compose.material3)
                implementation(compose.ui)
                implementation(compose.materialIconsExtended)
                @OptIn(org.jetbrains.compose.ExperimentalComposeLibrary::class)
                implementation(compose.components.resources)

                implementation(libs.bundles.kotlinx)

                implementation(projects.thirdParty.composeMaterialDialogsDatetime)

                implementation(libs.precompose.runtime)
                implementation(libs.precompose.annotation) // native not support compileOnly
                implementation(libs.molecule.runtime)
                implementation(libs.koject.core)
                implementation(libs.sqldelight.coroutines.extensions)
                implementation(libs.multiplatform.paging)
                implementation(libs.multiplatform.settings.core)
                implementation(libs.multiplatform.settings.coroutines)
                implementation(libs.kermit)
                implementation(libs.compose.material3.windowsizeclass)
                implementation(libs.okio)
                implementation(libs.material.kolor)
                implementation(libs.aboutlibraries.core)
                implementation(libs.aboutlibraries.compose)
            }
        }
        val commonTest by getting {
            dependencies {
                implementation(kotlin("test"))
                implementation(libs.assertk)
            }
        }
        val jvmCommonMain by getting {
            dependencies {
                implementation(libs.multiplatform.settings.datastore)
                implementation(libs.androidx.datastore.preferences.core)
            }
        }
        // not build desktop app, just easy to test
        val jvmMain by getting {
            dependencies {
                implementation(compose.preview)
                implementation(libs.sqldelight.sqlite.driver)
            }
        }
        val androidMain by getting {
            dependencies {
                api(libs.androidx.core.ktx)
                api(libs.androidx.appcompat)
                api(libs.androidx.activity.compose)
                implementation(libs.bundles.androidx.camera)
                implementation(libs.bundles.android.barcode)
                implementation(libs.kotlinx.coroutines.android)
                implementation(libs.sqldelight.android.driver)
                implementation(libs.zxing.core)
                implementation(libs.androidx.datastore.preferences)
            }
        }
        val iosMain by getting {
            dependencies {
                implementation(libs.sqldelight.native.driver)
            }
        }
    }
    sourceSets.forEach {
        if (it.name.endsWith("Main")) {
            it.kotlin.srcDir("src/${it.name}/third")
        }
    }
}

android {
    namespace = "com.seiko.greenqrscanner"
}

sqldelight {
    databases {
        create("AppDatabase") {
            packageName.set("com.seiko.greenqrscanner.data.model")
        }
    }
}

libres {
    generatedClassName = "MR"
}

licensee {
    allow("Apache-2.0")
    allow("MIT")
    allowUrl("https://developer.android.com/studio/terms.html")
    allowUrl("https://developers.google.com/ml-kit/terms")
    ignoreDependencies("junit", "junit") {
        because("junit is used in tests only")
    }
    ignoreDependencies("org.hamcrest", "hamcrest-core") {
        because("hamcrest-core is used in tests only")
    }
}

aboutLibraries {
    registerAndroidTasks = false
}

dependencies {
    kspAll(libs.koject.processor.app)
    kspAll(libs.precompose.ksp)
}

fun DependencyHandlerScope.kspAll(dependencyNotation: Any) {
    val kmpExtension = project.extensions.getByType<KotlinMultiplatformExtension>()
    kmpExtension.targets.asSequence()
        .filter { target ->
            // Don't add KSP for common target, only final platforms
            target.platformType != KotlinPlatformType.common
        }
        .forEach { target ->
            add("ksp${target.targetName.capitalized()}", dependencyNotation)
        }
}

// workaround for libres
listOf(
    "packageDebugResources",
    "packageReleaseResources",
    "mergeDebugResources",
    "mergeReleaseResources",
    "mapDebugSourceSetPaths",
    "mapReleaseSourceSetPaths",
    "iosX64ProcessResources",
    "iosArm64ProcessResources",
    "iosSimulatorArm64ProcessResources",
    "syncPodComposeResourcesForIos",
    "jvmProcessResources",
).forEach { name ->
    tasks.matching { it.name == name }.configureEach {
        dependsOn(tasks.matching { it.name == "libresGenerateImages" })
    }
}
