plugins {
    kotlin("multiplatform")
    kotlin("native.cocoapods")
    alias(libs.plugins.android.library)
    alias(libs.plugins.compose.multiplatform)
    alias(libs.plugins.kotlin.serialization)
    alias(libs.plugins.sqldelight)
    alias(libs.plugins.ksp)
}

kotlin {
    android()
    iosX64()
    iosArm64()
    iosSimulatorArm64()

    cocoapods {
        version = "1.0.0"
        summary = "Some description for the Shared Module"
        homepage = "Link to the Shared Module homepage"
        ios.deploymentTarget = "14.1"
        podfile = project.file("../iosApp/Podfile")
        framework {
            baseName = "shared"
            isStatic = true
        }
    }

    @Suppress("UNUSED_VARIABLE")
    sourceSets {
        val commonMain by getting {
            kotlin.srcDir("src/commonMain/third")
            dependencies {
                implementation(compose.runtime)
                implementation(compose.foundation)
                implementation(compose.material3)
                api(libs.bundles.kotlinx)
                api(libs.bundles.precompose)
                implementation(libs.tabler.icons)
                api(libs.bundles.sqldelight)
                api(libs.koject.core)
                implementation(libs.multiplatform.paging)
            }
        }
        val androidMain by getting {
            dependencies {
                api(libs.androidx.core.ktx)
                api(libs.androidx.appcompat)
                api(libs.androidx.activity.compose)
                implementation(libs.kotlinx.coroutines.android)
                implementation(libs.sqldelight.android.driver)
                implementation(libs.accompanist.permissions)
                implementation(libs.bundles.androidx.camera)
                implementation(libs.mlkit.barcode.scanning)
            }
        }
        val iosX64Main by getting
        val iosArm64Main by getting
        val iosSimulatorArm64Main by getting
        val iosMain by creating {
            dependsOn(commonMain)
            iosX64Main.dependsOn(this)
            iosArm64Main.dependsOn(this)
            iosSimulatorArm64Main.dependsOn(this)
            dependencies {
                implementation(libs.sqldelight.native.driver)
            }
        }
    }
}

android {
    compileSdk = (findProperty("android.compileSdk") as String).toInt()
    namespace = "com.seiko.greenqrscanner"
    defaultConfig {
        minSdk = (findProperty("android.minSdk") as String).toInt()
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlin {
        jvmToolchain(17)
    }
}

sqldelight {
    databases {
        create("AppDatabase") {
            packageName.set("com.seiko.greenqrscanner.data.model")
        }
    }
}

dependencies {
    // kspAll(libs.koject.processor.lib)
    kspAll(libs.koject.processor.app)
}

fun DependencyHandlerScope.kspAll(dependencyNotation: Any) {
    add("kspCommonMainMetadata", dependencyNotation)
    add("kspAndroid", dependencyNotation)
    add("kspIosX64", dependencyNotation)
    add("kspIosArm64", dependencyNotation)
    add("kspIosSimulatorArm64", dependencyNotation)
}
