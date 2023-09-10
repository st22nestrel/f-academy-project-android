package app.futured.academyproject.util.comparable

data class TanksComparableValueOutliers (
    var gunAimTime: Outliers<Float> = Outliers(0f,100f),
    var gunReloadTime: Outliers<Float> = Outliers(0f,100f)
)

//data class TanksComparableValueOutliers (
//    var gunAimTime: Pair<Float, Float> = Pair(0f,100f),
//    var gunReloadTime: Pair<Float, Float> = Pair(0f, 100f)
//)

data class Outliers<T>(var max: T, var min: T)

//class TanksComparableValueOutliers {
//    lateinit var gunAimTime: Pair<Float, Float>
//    lateinit var gunReloadTime: Pair<Float, Float>
//}