rootProject.name = "green-qrscanner"

pluginManagement {
    repositories {
        gradlePluginPortal()
        google()
        maven("https://maven.pkg.jetbrains.space/public/p/compose/dev")
    }
}

@Suppress("UnstableApiUsage")
dependencyResolutionManagement {
    repositories {
        google()
        mavenCentral()
        maven("https://maven.pkg.jetbrains.space/public/p/compose/dev")
    }
}

include(":androidApp")
include(":shared")
