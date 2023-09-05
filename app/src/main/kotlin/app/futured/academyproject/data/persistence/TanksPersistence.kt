package app.futured.academyproject.data.persistence

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class TanksPersistence @Inject constructor(
    private val persistence: Persistence,
) {
    companion object {
        private const val TANK_IDS_KEY = "TANK_IDS_KEY"
    }
    // TODO Step 3 - uncomment code
    private val tankIdsFlow: MutableStateFlow<List<Int>> = MutableStateFlow(
        persistence.getOrNull(TANK_IDS_KEY) ?: emptyList()
    )

    // TODO Step 4 - replace "flow { emit(emptyList()) }" with "placeIdsFlow.asStateFlow()"
    fun observeTankIds(): Flow<List<Int>> = tankIdsFlow.asStateFlow()

    fun getTankIds(): List<Int> = persistence.getOrNull(TANK_IDS_KEY) ?: emptyList()

    fun setTankIds(tankIds: List<Int>) {
        persistence[TANK_IDS_KEY] = tankIds
        tankIdsFlow.value = tankIds
    }

    // TODO Step 5 - create method/s to check if the place ID is already stored, if so, remove it, if not, add place id into list and save it
    fun addTankId(placeId: Int) {
        var tankIds = getTankIds().toMutableList()
        if (tankIds.contains(placeId)) {
            tankIds.remove(placeId)
        }
        else {
            tankIds.add((placeId))
        }
        setTankIds(tankIds)
    }
}
