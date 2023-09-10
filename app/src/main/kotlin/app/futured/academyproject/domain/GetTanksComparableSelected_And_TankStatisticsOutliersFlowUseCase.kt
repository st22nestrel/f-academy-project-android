package app.futured.academyproject.domain

import app.futured.academyproject.data.model.api.mapToTankComparable
import app.futured.academyproject.data.model.local.TankComparable
import app.futured.academyproject.data.persistence.TanksPersistence
import app.futured.academyproject.data.store.TanksComparableStore
import app.futured.academyproject.util.comparable.TanksComparableValueOutliers
import app.futured.arkitekt.crusecases.FlowUseCase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine
import javax.inject.Inject

class GetTanksComparableSelected_And_TankStatisticsOutliersFlowUseCase @Inject constructor (
    private val tanksPersistence: TanksPersistence,
    private val tanksComparableStore: TanksComparableStore,
) : FlowUseCase<Unit, Pair<TanksComparableValueOutliers,List<TankComparable>>>() {
    override fun build(args: Unit): Flow<Pair<TanksComparableValueOutliers, List<TankComparable>>> = combine(
        tanksPersistence.observeTankIdsSelected(),
        tanksComparableStore.getTanksComparableFlow(tanksPersistence.getTankIdsSelected()),
        //tanksComparableStore.getTanksComparableFlow()
        //flowOf(TanksProvider().values.first())
    ) { _, apiTanksComparable ->
        //val tanksComparable: MutableList = mutableListOf()

        var tankStatisticsOutliers = TanksComparableValueOutliers()

        val tanksComparable = apiTanksComparable.map{
            val tankComparable = it.data.values.first().mapToTankComparable()

            if (tankStatisticsOutliers.gunAimTime.max < tankComparable.gunAimTime) {
                tankStatisticsOutliers.gunAimTime.max = tankComparable.gunAimTime
            } else if (tankStatisticsOutliers.gunAimTime.min > tankComparable.gunAimTime) {
                tankStatisticsOutliers.gunAimTime.min = tankComparable.gunAimTime
            }

            if (tankStatisticsOutliers.gunReloadTime.max < tankComparable.gunReloadTime) {
                tankStatisticsOutliers.gunReloadTime.max = tankComparable.gunReloadTime
            } else if (tankStatisticsOutliers.gunReloadTime.min > tankComparable.gunReloadTime) {
                tankStatisticsOutliers.gunReloadTime.min = tankComparable.gunReloadTime
            }

            tankComparable
        }
        Pair(tankStatisticsOutliers, tanksComparable)
    }
}