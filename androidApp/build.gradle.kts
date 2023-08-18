plugins {
    id("app.android.application")
    id("app.kotlin.android")
    id("app.compose.multiplatform")
}

kotlin {
    sourceSets {
        commonMain {
            dependencies {
                implementation(projects.shared)
                implementation(libs.precompose.runtime)
            }
        }
    }
}

android {
    namespace = "com.seiko.greenqrscanner.app"
    defaultConfig {
        applicationId = "com.seiko.greenqrscanner.app"
        versionCode = 1
        versionName = "1.0"
    }
}
