package app.futured.academyproject.domain

import app.futured.academyproject.data.model.api.mapToTank
import app.futured.academyproject.data.model.local.Tank
import app.futured.academyproject.data.persistence.TanksPersistence
import app.futured.academyproject.data.store.TanksStore
import app.futured.arkitekt.crusecases.FlowUseCase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class GetTankFlowUseCase @Inject constructor(
    private val tanksStore: TanksStore,
    private val persistence: TanksPersistence,
) : FlowUseCase<GetTankFlowUseCase.Args, Tank>() {

    override fun build(args: Args): Flow<Tank> = persistence.observeTankIdsFavorite().map { favouriteTanks ->
        val apiTank = tanksStore.getTank(args.tankId) ?: throw IllegalArgumentException("Tank with id ${args.tankId} not found")
        apiTank.mapToTank( apiTank.id in favouriteTanks)
    }

    data class Args(val tankId: Int)
}
