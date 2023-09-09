package app.futured.academyproject.data.persistence

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class TanksPersistence @Inject constructor(
    private val persistence: Persistence,
) {
    companion object {
        private const val TANK_IDS_FAVORITE_KEY = "TANK_IDS_FAVORITE_KEY"
        private const val TANK_IDS_SELECTED_KEY = "TANK_IDS_SELECTED_KEY"
    }

    private val tankIdsFavoriteFlow: MutableStateFlow<List<Int>> = MutableStateFlow(
        persistence.getOrNull(TANK_IDS_FAVORITE_KEY) ?: emptyList()
    )

    fun observeTankIdsFavorite(): Flow<List<Int>> = tankIdsFavoriteFlow.asStateFlow()

    fun getTankIdsFavorite(): List<Int> = persistence.getOrNull(TANK_IDS_FAVORITE_KEY) ?: emptyList()

    fun setTankIdsFavorite(tankIds: List<Int>) {
        persistence[TANK_IDS_FAVORITE_KEY] = tankIds
        tankIdsFavoriteFlow.value = tankIds
    }

    fun addTankIdFavorite(tankId: Int) {
        var tankIds = getTankIdsFavorite().toMutableList()
        if (tankIds.contains(tankId)) {
            tankIds.remove(tankId)
        }
        else {
            tankIds.add((tankId))
        }
        setTankIdsFavorite(tankIds)
    }

    private val tankIdsSelectedFlow: MutableStateFlow<List<Int>> = MutableStateFlow(
        persistence.getOrNull(TANK_IDS_SELECTED_KEY) ?: emptyList()
    )

    fun observeTankIdsSelected(): Flow<List<Int>> = tankIdsSelectedFlow.asStateFlow()

    fun getTankIdsSelected(): List<Int> = persistence.getOrNull(TANK_IDS_SELECTED_KEY) ?: emptyList()

    fun setTankIdsSelected(tankIds: List<Int>) {
        persistence[TANK_IDS_SELECTED_KEY] = tankIds
        tankIdsSelectedFlow.value = tankIds
    }

    fun addTankIdSelected(tankId: Int) {
        var tankIds = getTankIdsSelected().toMutableList()
        if (tankIds.contains(tankId)) {
            tankIds.remove(tankId)
        }
        else {
            tankIds.add((tankId))
        }
        setTankIdsSelected(tankIds)
    }
}
