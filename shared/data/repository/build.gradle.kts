plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.androidLibrary)
    alias(libs.plugins.kotlinxSerialization)
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
            baseName = "DataRepository"
            isStatic = true
        }
    }

    sourceSets {
        commonMain.dependencies {
            implementation(projects.shared.core.common)
            // TODO:
            // implementation(projects.shared.domain.repository)
            // implementation(projects.shared.domain.models)
            implementation(projects.shared.data.models)
            implementation(projects.shared.data.datasources)

            implementation(libs.bundles.core.common)
        }

        androidMain.dependencies {
            implementation(libs.bundles.android.core)
        }

        commonTest.dependencies {
            implementation(kotlin("test"))
        }
    }
}


android {
    namespace = "${libs.versions.applicationId.get()}.data.repository"
    compileSdk = libs.versions.android.compileSdk.get().toInt()

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
}
