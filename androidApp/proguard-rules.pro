# ProGuard Rules for KMMTemplateApp

# Kotlin Serialization
-keepattributes *Annotation*, EnclosingMethod, Signature, InnerClasses
-keepclassmembernames class kotlinx.serialization.json.** {
    *** serializer(...);
}

# Koin
-keep class org.koin.** { *; }
-dontwarn org.koin.**

# Ktor
-keep class io.ktor.** { *; }
-dontwarn io.ktor.**
-dontwarn io.netty.**
-dontwarn org.slf4j.**

# Compose
-keep class androidx.compose.** { *; }
-dontwarn androidx.compose.**

# Room and SQLite Bundled
-keep class androidx.room.** { *; }
-keep class androidx.sqlite.driver.bundled.** { *; }
-keep class androidx.sqlite.db.** { *; }
-dontwarn androidx.sqlite.driver.bundled.**
