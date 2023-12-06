plugins {
    id("app.android.library")
    id("app.kotlin.multiplatform")
    id("app.kotlin.multiplatform.ios")
    id("org.jetbrains.compose")
}

kotlin {
    sourceSets {
        commonMain {
            dependencies {
                implementation(libs.kotlinx.datetime)
                implementation(compose.foundation)
                implementation(compose.material3)
            }
        }
    }
}

android {
    namespace = "com.vanpra.composematerialdialogs.datetime"
}
