package app.futured.academyproject.domain

import app.futured.academyproject.data.model.local.TankComparable
import app.futured.academyproject.util.comparable.Outliers
import app.futured.academyproject.util.comparable.TanksComparableValueOutliers
import kotlinx.collections.immutable.PersistentList
import javax.inject.Inject

class CompareTanksComparableUseCase @Inject constructor() {
    operator fun invoke(
        comparableTanks: PersistentList<TankComparable>
    ): TanksComparableValueOutliers =
        TanksComparableValueOutliers(
            gunAimTime = Outliers(
                comparableTanks.maxOf(TankComparable::gunAimTime ),
                comparableTanks.minOf(TankComparable::gunAimTime )
                ),
            gunReloadTime = Outliers(
                comparableTanks.maxOf(TankComparable::gunReloadTime ),
                comparableTanks.minOf(TankComparable::gunReloadTime )
            ),
        )

}