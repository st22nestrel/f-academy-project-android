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

class GetTanksSelectedFlowUseCase @Inject constructor (
    private val tanksPersistence: TanksPersistence,
    private val tanksStore: TanksStore,
) : FlowUseCase<Unit, List<Tank>>() {
    override fun build(args: Unit): Flow<List<Tank>> = combine(
        tanksPersistence.observeTankIdsFavorite(),
        tanksPersistence.observeTankIdsSelected(),
        tanksStore.getTanksFlow()
        //flowOf(TanksProvider().values.first())
    ) { favouriteTankIds, selectedTankIds, apiTanks  ->

        apiTanks.data.filter { it.value.id in selectedTankIds }
            .map {
            val isFavourite = it.value.id in favouriteTankIds
            val isSelected = it.value.id in selectedTankIds
            it.value.mapToTank(isFavourite, isSelected)
        }
            .sortedWith( compareByDescending(Tank::tier)
                .thenBy(Tank::tankType).thenBy(Tank::nation) )
    }
}