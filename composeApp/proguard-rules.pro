# ProGuard Rules for KMMTemplateApp

# Kotlin Serialization
-keepattributes *Annotation*, EnclosingMethod, Signature, InnerClasses
-keepclassmembernames class kotlinx.serialization.json.** {
    *** serializer(...);
}

# Koin
-keep class org.koin.** { *; }
-dontwarn org.koin.**

# Ktorfit (Reglas específicas para evitar "Missing class")
-keep class de.jensklingenberg.ktorfit.** { *; }
-dontwarn de.jensklingenberg.ktorfit.**
-keepattributes *Annotation*

# Mantener las interfaces de los servicios de Ktorfit (las que usas para las APIs)
-keep interface * {
    @de.jensklingenberg.ktorfit.http.* <methods>;
}

# Ktor
-keep class io.ktor.** { *; }
-dontwarn io.ktor.**
-dontwarn io.netty.**
-dontwarn org.slf4j.**

# Compose
-keep class androidx.compose.** { *; }
-dontwarn androidx.compose.**