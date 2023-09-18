package app.futured.academyproject.domain

import app.futured.academyproject.data.model.local.Tank
import app.futured.academyproject.util.filters.Filters
import kotlinx.collections.immutable.PersistentList
import kotlinx.collections.immutable.persistentListOf
import kotlinx.collections.immutable.toPersistentList
import timber.log.Timber
import javax.inject.Inject

class FilterTanksUseCase @Inject constructor() {
    operator fun invoke(
        filters: Filters,
        tanks: PersistentList<Tank>
    ): PersistentList<Tank> {
        val tanksNew: MutableList<Tank> = mutableListOf()

        val tierFilter = filters.tierFilter.selectedValues
        val nationFilter = filters.nationFilter.selectedValues
        val typeFilter = filters.typeFilter.selectedValues

        tanks.forEach { tank ->
            val isTierMatch = tierFilter.isEmpty() || tierFilter.contains(tank.tier)
            val isNationMatch = nationFilter.isEmpty() || nationFilter.contains(tank.nation)
            val isTypeMatch = typeFilter.isEmpty() || typeFilter.contains(tank.tankType)

            if (isTierMatch && isNationMatch && isTypeMatch) {
                tanksNew.add(tank)
            }
        }

        return if (tanksNew.isEmpty()) tanks.toPersistentList() else tanksNew.toPersistentList()
    }
}