package app.futured.academyproject.domain

import app.futured.academyproject.data.model.api.mapToTank
import app.futured.academyproject.data.model.local.Tank
import app.futured.academyproject.data.persistence.TanksPersistence
import app.futured.academyproject.data.store.TanksStore
import app.futured.arkitekt.crusecases.FlowUseCase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine
import javax.inject.Inject

class GetTanksFlowUseCase @Inject constructor (
    private val tanksPersistence: TanksPersistence,
    private val tanksStore: TanksStore,
) : FlowUseCase<Unit, List<Tank>>() {
    override fun build(args: Unit): Flow<List<Tank>> = combine(
        tanksPersistence.observeTankIds(),
        tanksStore.getTanksFlow()
    ) { favouriteTankIds, apiTanks ->
        apiTanks.data.map {
            val isFavoriteTank = it.value.id in favouriteTankIds
            it.value.mapToTank(isFavoriteTank)
        }
    }
}