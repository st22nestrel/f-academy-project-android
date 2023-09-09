package app.futured.academyproject.domain

import app.futured.academyproject.data.model.api.mapToTank
import app.futured.academyproject.data.model.api.mapToTankComparable
import app.futured.academyproject.data.model.local.Tank
import app.futured.academyproject.data.model.local.TankComparable
import app.futured.academyproject.data.persistence.TanksPersistence
import app.futured.academyproject.data.store.TanksComparableStore
import app.futured.academyproject.data.store.TanksStore
import app.futured.academyproject.tools.preview.TanksProvider
import app.futured.arkitekt.crusecases.FlowUseCase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.zip
import javax.inject.Inject

class GetTanksSelectedFlowUseCase @Inject constructor (
    private val tanksPersistence: TanksPersistence,
    private val tanksStore: TanksStore,
    private val tanksComparableStore: TanksComparableStore,
) : FlowUseCase<Unit, List<Tank>>() {
    override fun build(args: Unit): Flow<List<Tank>> = combine(
        tanksPersistence.observeTankIdsSelected(),
        tanksStore.getTanksFlow(),
        //tanksComparableStore.getTanksComparableFlow()
        //flowOf(TanksProvider().values.first())
    ) { selectedTankIds, apiTanks ->


//        apiTanks
//            .forEach{
//            it.isSelected = it.id in selectedTankIds
//        }
        val tanksComparableList: MutableList<TankComparable> = mutableListOf()
        val tanksList = apiTanks.data.filter {
            it.value.id in selectedTankIds
        }
        .map {
            val isFavoriteTank = it.value.id in selectedTankIds
            it.value.mapToTank(isFavoriteTank)
        }
        .sortedWith( compareByDescending(Tank::tier).thenBy(Tank::nation).thenBy(Tank::tankType) )

//        tanksList.forEach{
//            val tank = tanksComparableStore.getTank(it.id)
//            tanksComparableList.add(tank!!.mapToTankComparable())
//        }


        //return@combine (tanksList zip tanksComparableList)
        return@combine tanksList
    }
}