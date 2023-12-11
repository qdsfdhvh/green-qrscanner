
import org.gradle.configurationcache.extensions.capitalized
import org.jetbrains.kotlin.gradle.dsl.KotlinMultiplatformExtension
import org.jetbrains.kotlin.gradle.plugin.KotlinPlatformType
import org.jetbrains.kotlin.gradle.plugin.mpp.BitcodeEmbeddingMode
import org.jetbrains.kotlin.gradle.plugin.mpp.KotlinNativeTarget

plugins {
    id("app.android.library")
    id("app.kotlin.multiplatform")
    id("app.kotlin.multiplatform.ios")
    id("app.licenses")
    id("org.jetbrains.compose")
    alias(libs.plugins.kotlin.serialization)
    alias(libs.plugins.sqldelight)
    alias(libs.plugins.ksp)
}

kotlin {
    sourceSets {
        commonMain {
            dependencies {
                implementation(compose.runtime)
                implementation(compose.foundation)
                implementation(compose.ui)
                implementation(compose.material3)
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
                implementation(libs.bundles.jvmCommon.androidx)
                implementation(libs.multiplatform.settings.datastore)
            }
        }
        // not build desktop app, just easy to test
        jvmMain {
            dependencies {
                implementation(libs.sqldelight.sqlite.driver)
            }
        }
        androidMain {
            dependencies {
                api(libs.androidx.core.ktx)
                api(libs.androidx.appcompat)
                implementation(libs.bundles.android.androidx)
                implementation(libs.bundles.android.barcode)
                implementation(libs.kotlinx.coroutines.android)
                implementation(libs.sqldelight.android.driver)
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
