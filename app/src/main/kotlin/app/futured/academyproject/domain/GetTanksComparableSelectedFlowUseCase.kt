package app.futured.academyproject.domain

import app.futured.academyproject.data.model.api.mapToTankComparable
import app.futured.academyproject.data.model.local.TankComparable
import app.futured.academyproject.data.persistence.TanksPersistence
import app.futured.academyproject.data.store.TanksComparableStore
import app.futured.arkitekt.crusecases.FlowUseCase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine
import javax.inject.Inject

class GetTanksComparableSelectedFlowUseCase @Inject constructor (
    private val tanksPersistence: TanksPersistence,
    private val tanksComparableStore: TanksComparableStore,
) : FlowUseCase<Unit, List<TankComparable>>() {
    override fun build(args: Unit): Flow<List<TankComparable>> = combine(
        tanksPersistence.observeTankIdsSelected(),
        tanksComparableStore.getTanksComparableFlow(tanksPersistence.getTankIdsSelected()),
        //tanksComparableStore.getTanksComparableFlow()
        //flowOf(TanksProvider().values.first())
    ) { _, apiTanksComparable ->
        //val tanksComparable: MutableList = mutableListOf()

        apiTanksComparable.map{
            it.data.values.first().mapToTankComparable()
        }
    }
}