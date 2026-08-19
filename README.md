# KMMTemplateApp

[![Kotlin](https://img.shields.io/badge/Kotlin-2.1.20-blue.svg?logo=kotlin)](https://kotlinlang.org)
[![Compose Multiplatform](https://img.shields.io/badge/Compose%20Multiplatform-1.8.0-blue)](https://www.jetbrains.com/lp/compose-multiplatform/)
[![License](https://img.shields.io/badge/license-Apache%202.0-green.svg)](https://opensource.org/licenses/Apache-2.0)

A multiplatform project for Android and iOS developed with Kotlin Multiplatform Mobile (KMM). It shares business logic, models and UI code across platforms using Compose Multiplatform framework.

## 🚀 Features
- Shared code for business logic and UI.
- Clean modular architecture (data, domain and presentation layers).
- Data persistence with Room and Datastore.
- REST Api integration with Ktor.
- Shared UI with Jetpack Compose (Compose Multiplatform).
- Dependency injection with Koin.
- User preferences management.

## ⚙️ Project Structure
```plaintext
/
├── androidApp             # Android application
├── iosApp                 # iOS application
└── shared                 # Shared code
    ├── build.gradle.kts
    ├── src
    │   ├── commonMain     # Common code for all platforms
    │   │   ├── core       # Utilities and extensions
    │   │   ├── di         # Dependency injection
    │   │   ├── domain
    │   │   │   ├── models      # Data models
    │   │   │   ├── repository  # Repository interfaces
    │   │   │   └── usecases    # Use cases
    │   │   ├── data
    │   │   │   ├── datasources # Data sources (local and remote)
    │   │   │   └── repository  # Repository implementations
    │   │   └── presentation
    │   │       └── ui      # Compose Multiplatform UI components
    │   ├── androidMain    # Android-specific implementations
    │   └── iosMain       # iOS-specific implementations
    └── build.gradle.kts
```

## Snapshots

<h3>
<img src="https://github.com/almarpa/KMMTemplateApp/blob/develop/composeApp/src/main/snapshots/Home.png?raw=true" width=30%>
<img src="https://github.com/almarpa/KMMTemplateApp/blob/develop/composeApp/src/main/snapshots/Drawer.png?raw=true" width=30%>
<img src="https://github.com/almarpa/KMMTemplateApp/blob/develop/composeApp/src/main/snapshots/Settings.png?raw=true" width=30%>
<img src="https://github.com/almarpa/KMMTemplateApp/blob/develop/composeApp/src/main/snapshots/Search.png?raw=true" width=30%>
<img src="https://github.com/almarpa/KMMTemplateApp/blob/develop/composeApp/src/main/snapshots/Detail.png?raw=true" width=30%>
<img src="https://github.com/almarpa/KMMTemplateApp/blob/develop/composeApp/src/main/snapshots/Team.png?raw=true" width=30%>
<img src="https://github.com/almarpa/KMMTemplateApp/blob/develop/composeApp/src/main/snapshots/NewMember?raw=true" width=30%>
</h3>

