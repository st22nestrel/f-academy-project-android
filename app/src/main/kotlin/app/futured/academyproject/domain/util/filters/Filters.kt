package app.futured.academyproject.domain.util.filters

class Filters (
    val tierFilter: Filter = TierFilter()
){
    operator fun iterator(): Iterator<Filter> {
        return listOf(tierFilter).iterator()
    }
}