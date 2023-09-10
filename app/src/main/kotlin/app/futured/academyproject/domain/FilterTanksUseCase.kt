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
        filters.tierFilter.selectedValues.forEach{ filterVal->
            tanksNew.addAll(
                tanks.filter { e->
                    Timber.d("Comparison result: ${e.tier == filterVal}")
                    Timber.d("tank tier: ${e.tier}")
                    Timber.d("selected tier: ${filterVal}")
                    e.tier == filterVal
            } )
        }
        return (if (tanksNew.isEmpty()) tanks.toPersistentList() else tanksNew.toPersistentList())
    }
}