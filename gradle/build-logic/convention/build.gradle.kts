plugins {
    `kotlin-dsl`
    alias(libs.plugins.spotless)
}

kotlin {
    jvmToolchain(17)
}

spotless {
    // Workaround for https://github.com/diffplug/spotless/issues/1644
    lineEndings = com.diffplug.spotless.LineEnding.PLATFORM_NATIVE

    kotlin {
        target("src/**/*.kt")
        targetExclude("**/build/")
        ktlint(libs.versions.ktlint.get())
    }
    kotlinGradle {
        target("*.kts")
        targetExclude("**/build/")
        ktlint(libs.versions.ktlint.get())
    }
}

dependencies {
    compileOnly(libs.bundles.logic.plugins)
}

gradlePlugin {
    plugins {
        register("kotlinMultiplatform") {
            id = "app.kotlin.multiplatform"
            implementationClass = "KotlinMultiplatformConventionPlugin"
        }
        register("kotlinMultiplatformIos") {
            id = "app.kotlin.multiplatform.ios"
            implementationClass = "KotlinMultiplatformIosConventionPlugin"
        }
        register("kotlinAndroid") {
            id = "app.kotlin.android"
            implementationClass = "KotlinAndroidConventionPlugin"
        }
        register("androidApplication") {
            id = "app.android.application"
            implementationClass = "AndroidApplicationConventionPlugin"
        }
        register("androidLibrary") {
            id = "app.android.library"
            implementationClass = "AndroidLibraryConventionPlugin"
        }
        register("composeMultiplatform") {
            id = "app.compose.multiplatform"
            implementationClass = "ComposeMultiplatformConventionPlugin"
        }
    }
}
