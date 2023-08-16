plugins {
    id("app.android.library")
    id("app.kotlin.multiplatform")
    alias(libs.plugins.compose.multiplatform)
    alias(libs.plugins.kotlin.serialization)
    alias(libs.plugins.sqldelight)
    alias(libs.plugins.libres)
    alias(libs.plugins.ksp)
}

kotlin {
    @Suppress("UNUSED_VARIABLE")
    sourceSets {
        val commonMain by getting {
            dependencies {
                api(compose.runtime)
                api(compose.foundation)
                api(compose.material3)
                api(compose.ui)
                api(compose.materialIconsExtended)

                api(libs.bundles.kotlinx)

                implementation(projects.thirdParty.composeMaterialDialogsDatetime)

                api(libs.precompose.runtime)
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

dependencies {
    kspAll(libs.koject.processor.app)
    kspAll(libs.precompose.ksp)
}

fun DependencyHandlerScope.kspAll(dependencyNotation: Any) {
    add("kspAndroid", dependencyNotation)
    add("kspIosX64", dependencyNotation)
    add("kspIosArm64", dependencyNotation)
    add("kspIosSimulatorArm64", dependencyNotation)
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
