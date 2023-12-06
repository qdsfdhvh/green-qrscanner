plugins {
    id("app.android.application")
    id("app.kotlin.android")
    id("org.jetbrains.compose")
}

kotlin {
    sourceSets {
        androidMain {
            dependencies {
                implementation(projects.shared)
                implementation(libs.precompose.runtime)
                implementation(libs.androidx.activity.compose)
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
