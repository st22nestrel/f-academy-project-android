package app.futured.academyproject.domain.util.filters

class TierFilter : Filter {
    override val description: String = "Tier"
    override val values: List<Int> = listOf(1,2,3,4,5,6,7,8,9,10)
    override val selectedValues: MutableList<Any> = mutableListOf()
}