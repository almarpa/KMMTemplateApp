plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.androidKmpLibrary)
    alias(libs.plugins.ksp)
}
kotlin {
    jvmToolchain(21)

    android {
        namespace = "${libs.versions.applicationId.get()}.core.di"
        compileSdk = libs.versions.android.compileSdk.get().toInt()
        minSdk = libs.versions.android.minSdk.get().toInt()

        compilerOptions {
            jvmTarget.set(org.jetbrains.kotlin.gradle.dsl.JvmTarget.JVM_21)
        }
    }

    listOf(
        iosX64(),
        iosArm64(),
        iosSimulatorArm64()
    ).forEach {
        it.binaries.framework {
            baseName = "coreDi"
            isStatic = true
        }
    }

    sourceSets {
        commonMain.dependencies {
            implementation(projects.shared.core.common)
            implementation(projects.shared.data.datasources)
            implementation(projects.shared.data.repository)
            implementation(projects.shared.domain.usecases)

            implementation(libs.bundles.core.common)
        }

        androidMain.dependencies {
            implementation(libs.bundles.android.core)
        }
    }
}
