package com.almarpa.kmmtemplateapp.core.ui.utils

import androidx.core.bundle.Bundle
import androidx.navigation.NavType
import com.eygraber.uri.UriCodec
import kotlinx.serialization.json.Json

@Suppress("DEPRECATION")
inline fun <reified T : Any> serializableType(
    isNullableAllowed: Boolean = true,
    json: Json = Json,
) = object : NavType<T>(isNullableAllowed = isNullableAllowed) {

    override fun get(bundle: Bundle, key: String): T? {
        return json.decodeFromString(bundle.getString(key) ?: return null)
    }

    override fun parseValue(value: String): T {
        return json.decodeFromString(UriCodec.decode(value))
    }

    override fun put(bundle: Bundle, key: String, value: T) {
        bundle.putString(key, json.encodeToString(value))
    }

    override fun serializeAsValue(value: T): String {
        return UriCodec.encode(json.encodeToString(value))
    }
}