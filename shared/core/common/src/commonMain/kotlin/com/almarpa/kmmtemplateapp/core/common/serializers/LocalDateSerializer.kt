package com.almarpa.kmmtemplateapp.core.common.serializers

import com.almarpa.kmmtemplateapp.core.common.utils.format
import com.almarpa.kmmtemplateapp.core.common.utils.supportedDateFormat
import com.almarpa.kmmtemplateapp.core.common.utils.toLocalDate
import kotlinx.datetime.LocalDate
import kotlinx.serialization.KSerializer
import kotlinx.serialization.descriptors.PrimitiveKind
import kotlinx.serialization.descriptors.PrimitiveSerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder

class LocalDateSerializer : KSerializer<LocalDate> {
    override val descriptor = PrimitiveSerialDescriptor("LocalDate", PrimitiveKind.STRING)

    override fun deserialize(decoder: Decoder): LocalDate {
        for (format in supportedDateFormat) {
            try {
                return decoder.decodeString().toLocalDate(format)!!
            } catch (_: Exception) {

            }
        }

        throw RuntimeException("Error parsing date")
    }

    override fun serialize(encoder: Encoder, value: LocalDate) {
        encoder.encodeString(value = value.format())
    }
}
