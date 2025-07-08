# KMMTemplateApp

[![Kotlin](https://img.shields.io/badge/Kotlin-1.9.0-blue.svg?logo=kotlin)](https://kotlinlang.org)
[![Compose Multiplatform](https://img.shields.io/badge/Compose%20Multiplatform-1.6.0-blue)](https://www.jetbrains.com/lp/compose-multiplatform/)
[![License](https://img.shields.io/badge/license-Apache%202.0-green.svg)](https://opensource.org/licenses/Apache-2.0)

Proyecto multiplataforma para Android e iOS desarrollado con Kotlin Multiplatform Mobile (KMM). Comparte lógica de negocio, modelos y código UI entre plataformas utilizando Compose Multiplatform.

## 🚀 Características
- Código compartido para lógica de negocio y UI
- Arquitecta modular limpia (capa de datos, dominio y presentación)
- Inyección de dependencias multiplataforma
- UI unificada con Jetpack Compose (Android) y SwiftUI (iOS)

## ⚙️ Estructura del Proyecto

```plaintext
/
├── androidApp              # Aplicación Android
├── iosApp                 # Aplicación iOS
└── shared                 # Código compartido
    ├── build.gradle.kts
    ├── src
    │   ├── commonMain     # Código común a todas las plataformas
    │   │   ├── core       # Utilidades y extensiones
    │   │   ├── di        # Inyección de dependencias (Koin/Kodein)
    │   │   ├── domain
    │   │   │   ├── models      # Modelos de datos
    │   │   │   ├── repository  # Interfaces de repositorios
    │   │   │   └── usecases    # Casos de uso
    │   │   ├── data
    │   │   │   ├── datasources # Orígenes de datos (local/remoto)
    │   │   │   └── repository  # Implementaciones de repositorios
    │   │   └── presentation
    │   │       └── ui      # Componentes UI Compose Multiplatform
    │   └── androidMain    # Implementaciones específicas Android
    └── build.gradle.kts
