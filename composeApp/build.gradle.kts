plugins {
    alias(libs.plugins.androidApplication)
    alias(libs.plugins.jetbrainsCompose)
    alias(libs.plugins.kotlinAndroid)
    alias(libs.plugins.composeCompiler)
}

android {
    namespace = "com.almarpa.kmmtemplateapp"
    compileSdk = libs.versions.android.compileSdk.get().toInt()

    defaultConfig {
        minSdk = libs.versions.android.minSdk.get().toInt()
        applicationId = libs.versions.applicationId.get()
        versionCode = 1
        versionName = "1.0.0"
    }

    buildFeatures {
        compose = true
    }

    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
}

dependencies {
    implementation(projects.shared.presentation.ui)
    implementation(projects.shared.core.common)
    implementation(projects.shared.core.presentation)
    implementation(projects.shared.domain.models)

    implementation(libs.bundles.android.core)

    implementation(libs.androidx.activityCompose)

    implementation(libs.compose.runtime.android)
    implementation(libs.compose.foundation)
}