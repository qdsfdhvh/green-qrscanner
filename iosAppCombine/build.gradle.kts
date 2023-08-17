plugins {
    alias(libs.plugins.kotlin.multiplatform)
    id("app.compose.multiplatform")
    id("app.kotlin.multiplatform.ios")
    kotlin("native.cocoapods")
}

kotlin {
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
