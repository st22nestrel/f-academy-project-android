package app.futured.academyproject.domain

import app.futured.academyproject.data.model.local.Tank
import app.futured.academyproject.domain.util.filters.Filters
import kotlinx.collections.immutable.PersistentList
import kotlinx.collections.immutable.persistentListOf

class FilterTanksUseCase{
    operator fun invoke(
        filters: Filters,
        tanks: PersistentList<Tank>
    ): PersistentList<Tank> {
        val tanksNew: PersistentList<Tank> = persistentListOf()
        filters.tierFilter.selectedValues.forEach{ e->
            tanksNew.addAll( tanks.filter { Tank::tier.equals(e) } )
        }
        return tanksNew
    }
}