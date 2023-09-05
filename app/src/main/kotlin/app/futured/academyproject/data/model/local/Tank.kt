package app.futured.academyproject.data.model.local

data class Tank(
    val id: Int,
    val isFavourite: Boolean = false,
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
