package app.futured.academyproject.data.model.api

import app.futured.academyproject.data.model.local.TankComparable
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ApiTankComparable (
    @SerialName("tank_id") val id: Int,
    @SerialName("engine") val engine: Engine,
    @SerialName("max_ammo") val maxAmmo: Int,
    @SerialName("suspension") val suspension: Suspension,
    @SerialName("weight") val weight: Int,
    @SerialName("armor") val armor: Armor,
    @SerialName("hp") val hp: Int,
    //@SerialName("modules") val modules: Modules,
    @SerialName("gun") val gun: Gun,


    ) {

    @Serializable
    data class Engine (
        @SerialName("name") val name: String,
        @SerialName("power") val power: Int,
        @SerialName("weight") val weight: Int,
        @SerialName("tag") val tag: String,
        @SerialName("fire_chance") val fireChance: Float,
        @SerialName("tier") val tier: Int,
    )

    @Serializable
    data class Suspension (
        @SerialName("name") val name: String,
        @SerialName("weight") val weight: Int,
        @SerialName("load_limit") val loadLimit: Int,
        @SerialName("tag") val tag: String,
        @SerialName("traverse_speed") val traverseSpeed: Int,
        @SerialName("tier") val tier: Int,
        @SerialName("steering_lock_angle") val steeringLockAngle: Int
    )

    @Serializable
    data class Armor (
        @SerialName("turret") val turret: Turret?,
        @SerialName("hull") val hull: Hull
    ) {
        @Serializable
        data class Turret (
            @SerialName("front") val front: Int,
            @SerialName("sides") val sides: Int,
            @SerialName("rear") val rear: Int,
        )
        @Serializable
        data class Hull (
            @SerialName("front") val front: Int,
            @SerialName("sides") val sides: Int,
            @SerialName("rear") val rear: Int,
        )
    }
    @Serializable
    data class Gun (
        @SerialName("move_down_arc") val moveDownArc: Int,
        @SerialName("move_up_arc") val moveUpArc: Int,
        @SerialName("caliber") val caliber: Int,
        @SerialName("name") val name: String,
        @SerialName("weight") val weight: Int,
        @SerialName("fire_rate") val fireRate: Float,
        @SerialName("dispersion") val dispersion: Float,
        @SerialName("tag") val tag: String,
        @SerialName("traverse_speed") val traverseSpeed: Int,
        @SerialName("reload_time") val reloadTime: Float,
        @SerialName("tier") val tier: Int,
        @SerialName("aim_time") val aimTime: Float
    )
}

fun ApiTankComparable.mapToTankComparable() = TankComparable(
    id = id,
    engineName = engine.name,
    enginePower = engine.power,
    engineWeight = engine.weight,
    engineFireChance = engine.fireChance,
    maxAmmo = maxAmmo,
    armor = TankComparable.Armor(
        turretFront = armor.turret?.front ?: -1,
        turretSides = armor.turret?.sides ?: -1,
        turretRear = armor.turret?.rear ?: -1,
        hullFront = armor.hull.front,
        hullSides = armor.hull.sides,
        hullRear = armor.hull.rear
    ),
    gunMoveDownArc = gun.moveDownArc,
    gunMoveUpArc = gun.moveUpArc,
    gunCaliber = gun.caliber,
    gunName = gun.name,
    gunFireRate = gun.fireRate,
    gunDispersion = gun.dispersion,
    gunTraverseSpeed = gun.traverseSpeed,
    gunReloadTime = gun.reloadTime,
    gunAimTime = gun.aimTime
)
