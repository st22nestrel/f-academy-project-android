package app.futured.academyproject.data.model.api

import androidx.compose.runtime.mutableStateOf
import app.futured.academyproject.data.model.local.Tank
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ApiTank(
    @SerialName("is_wheeled") val wheeled: Boolean,
    @SerialName("is_premium") val premium: Boolean,
    @SerialName("images") val images: Images,
    @SerialName("type") val tankType: String,
    @SerialName("description") val description: String,
    @SerialName("short_name") val shortName: String,
    @SerialName("nation") val nation: String,
    @SerialName("tier") val tier: Int,
    @SerialName("name") val name: String,
    @SerialName("tank_id") val id: Int,
    ) {

    @Serializable
    data class Images (
        @SerialName("small_icon") val smallIcon: String,
        @SerialName("contour_icon") val contourIcon: String,
        @SerialName("big_icon") val bigIcon: String,
    )
}

fun ApiTank.mapToTank(isFavourite: Boolean,
                      isSelected: Boolean = false) = Tank(
    id = id,
    isFavourite = isFavourite,
    isSelected = mutableStateOf(isSelected),
    isPremium = premium,
    tankType = tankType,
    tier = tier,
    nation = nation,
    name = name,
    shortName = shortName,
    description = description,
    smallIcon = images.smallIcon,
    contourIcon = images.contourIcon,
    bigIcon = images.bigIcon
)