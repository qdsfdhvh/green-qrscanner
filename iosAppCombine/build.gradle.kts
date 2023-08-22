import org.jetbrains.kotlin.gradle.plugin.mpp.BitcodeEmbeddingMode
import org.jetbrains.kotlin.gradle.plugin.mpp.KotlinNativeTarget

plugins {
    alias(libs.plugins.kotlin.multiplatform)
    id("app.compose.multiplatform")
    id("app.kotlin.multiplatform.ios")
}

kotlin {
    sourceSets {
        commonMain {
            dependencies {
                implementation(projects.shared)
            }
        }
    }
    targets.withType<KotlinNativeTarget>().configureEach {
        binaries.framework {
            baseName = "combine"
            isStatic = true
            embedBitcode(BitcodeEmbeddingMode.DISABLE)
            binaryOption("bundleId", "com.seiko.greenqrscanner.ios.shared")
            binaryOption("bundleVersion", version.toString())
            binaryOption("bundleShortVersionString", version.toString())
        }
    }
}
