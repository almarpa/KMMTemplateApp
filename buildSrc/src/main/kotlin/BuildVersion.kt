import org.gradle.api.JavaVersion

object BuildVersion {
    object environment {
        // TODO: use them
        const val applicationId = "com.almarpa.kmptemplateapp"
        const val appName = "KMMTemplateApp"
        const val appVersion = 1
        const val appVersionCode = "1.0.0"
        val javaVersion = JavaVersion.VERSION_17
        const val jvmTarget = "17"
        const val appDatabaseName = "KMMTemplateAppDB"
    }

    object android {
        const val minSdk = 24
        const val compileSdk = 34
    }
}