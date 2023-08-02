plugins {
    id("app.android.library")
    id("app.kotlin.multiplatform")
    kotlin("native.cocoapods")
    alias(libs.plugins.compose.multiplatform)
    alias(libs.plugins.kotlin.serialization)
    alias(libs.plugins.sqldelight)
    alias(libs.plugins.libres)
    alias(libs.plugins.ksp)
}

kotlin {
    cocoapods {
        version = "1.0.0"
        summary = "Some description for the Shared Module"
        homepage = "Link to the Shared Module homepage"
        ios.deploymentTarget = "15.2"
        podfile = project.file("../iosApp/Podfile")
        framework {
            baseName = "shared"
            isStatic = true
        }
    }

    @Suppress("UNUSED_VARIABLE")
    sourceSets {
        val commonMain by getting {
            dependencies {
                implementation(compose.runtime)
                implementation(compose.foundation)
                implementation(compose.material3)
                implementation(compose.ui)
                implementation(compose.materialIconsExtended)

                api(libs.bundles.kotlinx)
                api(libs.bundles.precompose)

                implementation(projects.thirdParty.composeMaterialDialogsDatetime)

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
}

fun DependencyHandlerScope.kspAll(dependencyNotation: Any) {
    // add("kspCommonMainMetadata", dependencyNotation)
    add("kspAndroid", dependencyNotation)
    add("kspIosX64", dependencyNotation)
    add("kspIosArm64", dependencyNotation)
    add("kspIosSimulatorArm64", dependencyNotation)
}

tasks.matching { it.name == "packageDebugResources" }.configureEach {
    dependsOn(tasks.matching { it.name == "libresGenerateImages" })
}
