plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.androidKmpLibrary)
    alias(libs.plugins.jetbrainsCompose)
    alias(libs.plugins.kotlinSerialization)
    alias(libs.plugins.composeCompiler)
    alias(libs.plugins.ksp)
}

kotlin {
    jvmToolchain(21)

    android {
        namespace = "${libs.versions.applicationId.get()}.presentation.ui"
        compileSdk = libs.versions.android.compileSdk.get().toInt()
        minSdk = libs.versions.android.minSdk.get().toInt()

        compilerOptions {
            jvmTarget.set(org.jetbrains.kotlin.gradle.dsl.JvmTarget.JVM_21)
        }

        androidResources {
            enable = true
        }
    }

    listOf(
        iosX64(),
        iosArm64(),
        iosSimulatorArm64()
    ).forEach {
        it.binaries.framework {
            baseName = "PresentationUi"
            isStatic = true
        }
    }

    sourceSets {
        applyDefaultHierarchyTemplate()

        commonMain.dependencies {
            implementation(projects.shared.core.common)
            implementation(projects.shared.core.di)
            implementation(projects.shared.core.presentation)
            implementation(projects.shared.domain.models)
            implementation(projects.shared.domain.usecases)

            implementation(libs.bundles.core.presentation)

            implementation(compose.runtime)
            implementation(compose.material3)
            implementation(compose.materialIconsExtended)
            implementation(compose.components.resources)
            implementation(compose.components.uiToolingPreview)
        }

        androidMain {
            dependencies {
                implementation(libs.bundles.android.core)
                implementation(libs.bundles.android.presentation)
                implementation(libs.ktor.client.okhttp)

                implementation(compose.uiTooling)
                implementation(compose.preview)
            }
        }

        iosMain.dependencies {}

        commonTest.dependencies {
            implementation(libs.kotlin.test)
        }
    }
}
