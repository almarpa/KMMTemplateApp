plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.androidLibrary)
    alias(libs.plugins.jetbrainsCompose)
    alias(libs.plugins.kotlinxSerialization)
    alias(libs.plugins.composeCompiler)
    alias(libs.plugins.ksp)
}

kotlin {
    androidTarget {
        compilations.all {
            kotlinOptions {
                jvmTarget = "17"
            }
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
            implementation(projects.shared.core.ui)
            implementation(projects.shared.domain.models)
            implementation(projects.shared.domain.usecases)

            implementation(libs.bundles.core.ui)

            implementation(compose.runtime)
            implementation(compose.material3)
            implementation(compose.materialIconsExtended)
            implementation(compose.components.resources)
        }

        androidMain {
            dependencies {
                implementation(libs.bundles.android.core)
                implementation(libs.bundles.android.ui)
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

android {
    namespace = "${libs.versions.applicationId.get()}.presentation.ui"
    compileSdk = libs.versions.android.compileSdk.get().toInt()

    sourceSets["main"].res.srcDirs("src/androidMain/res")
    sourceSets["main"].resources.srcDirs("src/commonMain/resources")

    defaultConfig {
        minSdk = libs.versions.android.minSdk.get().toInt()
    }

    sourceSets["main"].apply {
        manifest.srcFile("src/androidMain/AndroidManifest.xml")
        res.srcDirs("src/androidMain/resources")
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }

    buildFeatures {
        compose = true
    }

    dependencies {
        debugImplementation(compose.uiTooling)
        debugImplementation(compose.preview)
    }
}
