plugins {
    alias(libs.plugins.kotlin.multiplatform)
    id("app.compose.multiplatform")
    kotlin("native.cocoapods")
}

kotlin {
    iosX64()
    iosArm64()
    iosSimulatorArm64()
    sourceSets {
        commonMain {
            dependencies {
                implementation(projects.shared)
            }
        }
    }
    cocoapods {
        name = "combine"
        version = "1.0.0"
        summary = "Combine"
        homepage = "Link to the Shared Module homepage"
        ios.deploymentTarget = "15.2"
        podfile = project.file("../iosApp/Podfile")
        framework {
            baseName = "combine"
            isStatic = true
        }
    }
}
