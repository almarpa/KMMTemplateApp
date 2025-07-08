rootProject.name = "KMMTemplateApp"
enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")

pluginManagement {
    repositories {
        google {
            mavenContent {
                includeGroupAndSubgroups("androidx")
                includeGroupAndSubgroups("com.android")
                includeGroupAndSubgroups("com.google")
            }
        }
        mavenCentral()
        maven("https://maven.pkg.jetbrains.space/public/p/compose/dev")
        gradlePluginPortal()
    }
}

dependencyResolutionManagement {
    repositories {
        google {
            mavenContent {
                includeGroupAndSubgroups("androidx")
                includeGroupAndSubgroups("com.android")
                includeGroupAndSubgroups("com.google")
            }
        }
        mavenCentral()
    }
}

// Modules
include(":composeApp")

include(":shared:core:common")
include(":shared:core:di")
include(":shared:core:presentation")

include(":shared:data:datasources")
include(":shared:data:repository")

include(":shared:domain:models")
include(":shared:domain:repository")
include(":shared:domain:usecases")

include(":shared:presentation:ui")
