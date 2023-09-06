package app.futured.academyproject.domain

import app.futured.academyproject.data.model.api.mapToTank
import app.futured.academyproject.data.model.local.Tank
import app.futured.academyproject.data.persistence.TanksPersistence
import app.futured.academyproject.data.store.TanksStore
import app.futured.academyproject.tools.preview.TanksProvider
import app.futured.arkitekt.crusecases.FlowUseCase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flowOf
import javax.inject.Inject

class GetTanksFlowUseCase @Inject constructor (
    private val tanksPersistence: TanksPersistence,
    private val tanksStore: TanksStore,
) : FlowUseCase<Unit, List<Tank>>() {
    override fun build(args: Unit): Flow<List<Tank>> = combine(
        tanksPersistence.observeTankIds(),
        //tanksStore.getTanksFlow()
        flowOf(TanksProvider().values.first())
    ) { favouriteTankIds, apiTanks ->

        apiTanks
//        apiTanks.data.map {
//            val isFavoriteTank = it.value.id in favouriteTankIds
//            it.value.mapToTank(isFavoriteTank)
//        }
        .sortedWith( compareByDescending(Tank::tier).thenBy(Tank::nation).thenBy(Tank::tankType) )
        //sortedWith( compareByDescending(Tank::tier).thenBy(Tank::tankType).thenBy(Tank::nation) )
        //sortedWith( compareBy(Tank::tier, Tank::nation, Tank::tankType))
    }
}