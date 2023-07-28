import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure
import org.jetbrains.kotlin.gradle.dsl.KotlinMultiplatformExtension

class KotlinMultiplatformConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) = with(target) {
        with(pluginManager) {
            apply("org.jetbrains.kotlin.multiplatform")
        }
        extensions.configure<KotlinMultiplatformExtension> {
            jvm()
            android()
            iosX64()
            iosArm64()
            iosSimulatorArm64()
            sourceSets.apply {
                val commonMain = getByName("commonMain")
                val commonTest = getByName("commonTest")

                val androidMain = getByName("androidMain")
                val jvmMain = getByName("jvmMain")
                maybeCreate("jvmCommonMain").apply {
                    dependsOn(commonMain)
                    androidMain.dependsOn(this)
                    jvmMain.dependsOn(this)
                }

                val androidUnitTest = getByName("androidUnitTest")
                val jvmTest = getByName("jvmTest")
                maybeCreate("jvmCommonTest").apply {
                    dependsOn(commonTest)
                    androidUnitTest.dependsOn(this)
                    jvmTest.dependsOn(this)
                }

                val iosX64Main = getByName("iosX64Main")
                val iosArm64Main = getByName("iosArm64Main")
                val iosSimulatorArm64Main = getByName("iosSimulatorArm64Main")
                maybeCreate("iosMain").apply {
                    dependsOn(commonMain)
                    iosX64Main.dependsOn(this)
                    iosArm64Main.dependsOn(this)
                    iosSimulatorArm64Main.dependsOn(this)
                }

                val iosX64Test = getByName("iosX64Test")
                val iosArm64Test = getByName("iosArm64Test")
                val iosSimulatorArm64Test = getByName("iosSimulatorArm64Test")
                maybeCreate("iosTest").apply {
                    dependsOn(commonTest)
                    iosX64Test.dependsOn(this)
                    iosArm64Test.dependsOn(this)
                    iosSimulatorArm64Test.dependsOn(this)
                }
            }
        }
        configKotlin()
    }
}
