package app.futured.academyproject.util.filters

class Filters (
    val tierFilter: Filter = TierFilter(),
    val nationFilter: Filter = NationFilter(),
    val typeFilter: Filter = TypeFilter()
){
    operator fun iterator(): Iterator<Filter> {
        return listOf(tierFilter, nationFilter, typeFilter).iterator()
    }
}