package app.futured.academyproject.data.model.local

data class Tank(
    val id: Int,
    var isFavourite: Boolean = false,
    var isSelected: Boolean = false,
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
