# --- R8 / ProGuard Optimization Rules for PokéDex ---

# 1. Debugging & Crash Reporting
-keepattributes SourceFile,LineNumberTable,Signature,InnerClasses,EnclosingMethod,*Annotation*
-renamesourcefileattribute SourceFile

# 2. Kotlinx Serialization
# Keep the serializer() and companion methods for @Serializable classes
-keepclassmembernames class * {
    *** Companion;
    *** serializer(...);
}
-keepnames class kotlinx.serialization.json.** { *; }
-dontwarn kotlinx.serialization.UnknownFieldException

# 3. Koin (Dependency Injection)
-keepnames class org.koin.** { *; }
-dontwarn org.koin.**

# 4. Ktor & OkHttp (Networking)
-keepnames class io.ktor.** { *; }
-dontwarn io.ktor.**
-dontwarn io.netty.**
-dontwarn org.slf4j.**
-dontwarn okio.**
-dontwarn javax.annotation.**

# 5. Coil 3 (Image Loading)
-keep class coil3.** { *; }
-dontwarn coil3.**
# Specifically for Ktor engine in Coil
-keep class coil3.network.ktor.** { *; }

# 6. Compottie (Lottie for Compose)
-keep class io.github.alexzhirkevich.compottie.** { *; }
-dontwarn io.github.alexzhirkevich.compottie.**

# 7. Napier (Logging)
-keep class io.github.aakira.napier.** { *; }
-dontwarn io.github.aakira.napier.**

# 8. Room & SQLite
-keepnames class androidx.room.** { *; }
-keep class androidx.sqlite.driver.bundled.** { *; }
-dontwarn androidx.room.**
-dontwarn androidx.sqlite.driver.bundled.**

# 9. Kotlinx Datetime
-keep class kotlinx.datetime.** { *; }
-dontwarn kotlinx.datetime.**

# 10. Compose Multiplatform
# Usually handled by the compiler, but some internal APIs need protection
-keep class androidx.compose.runtime.** { *; }
-keep class androidx.compose.ui.** { *; }
-dontwarn androidx.compose.**
