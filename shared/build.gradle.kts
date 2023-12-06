import org.gradle.configurationcache.extensions.capitalized
import org.jetbrains.kotlin.gradle.dsl.KotlinMultiplatformExtension
import org.jetbrains.kotlin.gradle.plugin.KotlinPlatformType
import org.jetbrains.kotlin.gradle.plugin.mpp.BitcodeEmbeddingMode
import org.jetbrains.kotlin.gradle.plugin.mpp.KotlinNativeTarget

plugins {
    id("app.android.library")
    id("app.kotlin.multiplatform")
    id("app.kotlin.multiplatform.ios")
    id("org.jetbrains.compose")
    alias(libs.plugins.kotlin.serialization)
    alias(libs.plugins.sqldelight)
    alias(libs.plugins.libres)
    alias(libs.plugins.ksp)
    alias(libs.plugins.licensee)
    alias(libs.plugins.aboutlibraries)
}

kotlin {
    sourceSets {
        commonMain {
            dependencies {
                implementation(compose.runtime)
                implementation(compose.foundation)
                implementation(compose.material3)
                implementation(compose.ui)
                implementation(compose.materialIconsExtended)
                @OptIn(org.jetbrains.compose.ExperimentalComposeLibrary::class)
                implementation(compose.components.resources)
                implementation(libs.bundles.kotlinx)
                implementation(libs.bundles.common.androidx)
                implementation(libs.bundles.common.thirdParty)
                implementation(projects.thirdParty.composeMaterialDialogsDatetime)
            }
        }
        commonTest {
            dependencies {
                implementation(kotlin("test"))
                implementation(libs.assertk)
            }
        }
        jvmCommonMain {
            dependencies {
                implementation(libs.multiplatform.settings.datastore)
                implementation(libs.androidx.datastore.preferences.core)
            }
        }
        // not build desktop app, just easy to test
        jvmMain {
            dependencies {
                implementation(compose.preview)
                implementation(libs.sqldelight.sqlite.driver)
            }
        }
        androidMain {
            dependencies {
                api(libs.androidx.core.ktx)
                api(libs.androidx.appcompat)
                implementation(libs.bundles.android.androidx.camera)
                implementation(libs.bundles.android.barcode)
                implementation(libs.kotlinx.coroutines.android)
                implementation(libs.sqldelight.android.driver)
                implementation(libs.zxing.core)
                implementation(libs.androidx.datastore.preferences)
            }
        }
        iosMain {
            dependencies {
                implementation(libs.sqldelight.native.driver)
            }
        }
    }
    targets.withType<KotlinNativeTarget>().configureEach {
        binaries.framework {
            baseName = "shared"
            isStatic = true
            embedBitcode(BitcodeEmbeddingMode.DISABLE)
            binaryOption("bundleId", "com.seiko.greenqrscanner.ios.shared")
            binaryOption("bundleVersion", version.toString())
            binaryOption("bundleShortVersionString", version.toString())
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
            srcDirs("src/commonMain/sqldelight/app/")
            packageName.set("com.seiko.greenqrscanner.data.model")
            generateAsync.set(true)
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
