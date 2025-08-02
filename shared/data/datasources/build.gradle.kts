import de.jensklingenberg.ktorfit.gradle.ErrorCheckingMode

plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.androidLibrary)
    alias(libs.plugins.kotlinSerialization)
    alias(libs.plugins.room)
    alias(libs.plugins.ksp)
    alias(libs.plugins.ktorfit)
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
            baseName = "DataDatasources"
            isStatic = true
        }
    }

    sourceSets {
        commonMain.dependencies {
            implementation(projects.shared.core.common)
            implementation(projects.shared.domain.models)

            implementation(libs.bundles.core.common)
            implementation(libs.bundles.core.data)
        }

        androidMain.dependencies {
            implementation(libs.bundles.android.data.core)
            implementation(libs.bundles.android.core)
        }

        iosMain.dependencies {
            implementation(libs.bundles.ios.data.core)
        }

        commonTest.dependencies {
            implementation(kotlin("test"))
        }

        dependencies {
            // ksp(libs.androidx.room.compiler) Not working, alternative below
            add("kspAndroid", libs.androidx.room.compiler)
            add("kspIosSimulatorArm64", libs.androidx.room.compiler)
            add("kspIosX64", libs.androidx.room.compiler)
            add("kspIosArm64", libs.androidx.room.compiler)
        }

        room {
            schemaDirectory("$projectDir/schemas")
        }

        ktorfit {
            errorCheckingMode = ErrorCheckingMode.ERROR
            generateQualifiedTypeName = true
        }
    }
}

android {
    namespace = "${libs.versions.applicationId.get()}.data.datasources"
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

afterEvaluate {
    tasks.named("kspDebugKotlinAndroid") {
        dependsOn("kspCommonMainKotlinMetadata")
    }
    tasks.named("kspReleaseKotlinAndroid") {
        dependsOn("kspCommonMainKotlinMetadata")
    }
    tasks.named("kspKotlinIosSimulatorArm64") {
        dependsOn("kspCommonMainKotlinMetadata")
    }
    tasks.named("kspKotlinIosArm64") {
        dependsOn("kspCommonMainKotlinMetadata")
    }
    tasks.named("kspKotlinIosX64") {
        dependsOn("kspCommonMainKotlinMetadata")
    }
}
