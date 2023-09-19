package app.futured.academyproject.data.model.local

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import app.futured.academyproject.tools.serialization.BoolMutableStateSerializer
//import app.futured.academyproject.tools.serialization.MutableStateSerializer
//import app.futured.academyproject.tools.serialization.booleanSerializer
import kotlinx.serialization.Serializable
import kotlinx.serialization.Serializer

@Serializable
data class Tank(
    val id: Int,
    var isFavourite: Boolean = false,
    @Serializable(with = BoolMutableStateSerializer::class)
    var isSelected: MutableState<Boolean> = mutableStateOf(false),
    val isPremium: Boolean,
    val tankType: String,
    val tier: Int,
    val nation: String,
    val name: String,
    val shortName: String,
    val description: String,

    val smallIcon: String,
    val contourIcon: String,
    val bigIcon: String,
)
