package app.futured.academyproject.tools.serialization

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import kotlinx.serialization.KSerializer
import kotlinx.serialization.builtins.serializer
import kotlinx.serialization.descriptors.PrimitiveKind
import kotlinx.serialization.descriptors.PrimitiveSerialDescriptor
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder

//class MutableStateSerializer<T> (private val dataSerializer: KSerializer<T>) : KSerializer<MutableState<T>> {
//    override val descriptor: SerialDescriptor = dataSerializer.descriptor
//    override fun serialize(encoder: Encoder, value: MutableState<T>) = dataSerializer.serialize(encoder, value.value)
//    override fun deserialize(decoder: Decoder) = mutableStateOf(dataSerializer.deserialize(decoder))
//}

object BoolMutableStateSerializer : KSerializer<MutableState<Boolean>> {
    override val descriptor: SerialDescriptor = PrimitiveSerialDescriptor("BoolMutableState", PrimitiveKind.BOOLEAN)
    override fun serialize(encoder: Encoder, value: MutableState<Boolean>) = encoder.encodeBoolean(value.value)
        //dataSerializer.serialize(encoder, value.value)
    override fun deserialize(decoder: Decoder) = mutableStateOf(decoder.decodeBoolean())
}

//val booleanSerializer = MutableStateSerializer(Boolean.serializer())