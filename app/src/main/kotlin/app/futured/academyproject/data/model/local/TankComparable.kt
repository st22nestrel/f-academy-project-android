package app.futured.academyproject.data.model.local

data class TankComparable(
    val id: Int,
    val engineName: String,
    val enginePower: Int,
    val engineWeight: Int,
    val engineFireChance: Float,
    //val engineTier: Int,

    val maxAmmo: Int,
    val armor: Armor,

    ///TODO

    val gunMoveDownArc: Int,
    val gunMoveUpArc: Int,
    val gunCaliber: Int,
    val gunName: String,
    //val gunWeight: Int,
    val gunFireRate: Float,
    val gunDispersion: Float,
    //val gunTag
    val gunTraverseSpeed: Int,
    val gunReloadTime: Float,
    //val gunTier: Int,
    val gunAimTime: Float
){
    data class Armor(
        val turretFront: Int,
        val turretSides: Int,
        val turretRear: Int,

        val hullFront: Int,
        val hullSides: Int,
        val hullRear: Int,
    )
}