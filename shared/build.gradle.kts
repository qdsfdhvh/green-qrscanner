plugins {
    kotlin("multiplatform")
    kotlin("native.cocoapods")
    alias(libs.plugins.android.library)
    alias(libs.plugins.compose.multiplatform)
    alias(libs.plugins.kotlin.serialization)
    alias(libs.plugins.sqldelight)
    alias(libs.plugins.moko.resources)
    alias(libs.plugins.ksp)
}

kotlin {
    android()
    jvm("desktop")
    iosX64()
    iosArm64()
    iosSimulatorArm64()

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
                // implementation(compose.uiTooling)
                implementation(compose.materialIconsExtended)
                api(libs.bundles.kotlinx)
                api(libs.bundles.precompose)
                implementation(libs.bundles.sqldelight)
                api(libs.koject.core)
                implementation(libs.multiplatform.paging)
                implementation(libs.kermit)
                implementation(libs.moko.resources.core)
            }
        }
        val commonTest by getting {
            dependencies {
                implementation(kotlin("test"))
            }
        }
        val jvmMain by creating {
            dependsOn(commonMain)
        }
        // not build desktop app, just easy to test
        val desktopMain by getting {
            dependsOn(jvmMain)
            dependencies {
                implementation(compose.preview)
                implementation(libs.sqldelight.sqlite.driver)
            }
        }
        val androidMain by getting {
            dependsOn(jvmMain)
            dependencies {
                api(libs.androidx.core.ktx)
                api(libs.androidx.appcompat)
                api(libs.androidx.activity.compose)
                implementation(libs.bundles.androidx.camera)
                implementation(libs.bundles.android.barcode)
                implementation(libs.kotlinx.coroutines.android)
                implementation(libs.sqldelight.android.driver)
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
    sourceSets.forEach {
        if (it.name.endsWith("Main")) {
            it.kotlin.srcDir("src/${it.name}/third")
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

multiplatformResources {
    multiplatformResourcesPackage = "com.seiko.greenqrscanner"
}

dependencies {
    kspAll(libs.koject.processor.app)
}

fun DependencyHandlerScope.kspAll(dependencyNotation: Any) {
    add("kspCommonMainMetadata", dependencyNotation)
    add("kspAndroid", dependencyNotation)
    add("kspIosX64", dependencyNotation)
    add("kspIosArm64", dependencyNotation)
    add("kspIosSimulatorArm64", dependencyNotation)
}

// workaround for moko-resources
listOf(
    "iosSimulatorArm64",
    "iosArm64",
    "iosX64",
    "macosArm64",
    "macosX64",
).forEach { name ->
    tasks.matching { it.name == "kspKotlin${name.replaceFirstChar { char -> char.uppercase() }}" }.configureEach {
        dependsOn(tasks.matching { it.name == "generateMR${name}Main" })
    }
}
