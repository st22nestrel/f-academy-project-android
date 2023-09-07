package app.futured.academyproject.domain.util.filters

class TierFilter {
    val description: String = "Tier"
    val values: List<Int> = listOf(1,2,3,4,5,6,7,8,9,10)
    val selectedValues: MutableList<Int> = mutableListOf()
}