package app.futured.academyproject.data.model.local

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf

data class Tank(
    val id: Int,
    var isFavourite: Boolean = false,
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
