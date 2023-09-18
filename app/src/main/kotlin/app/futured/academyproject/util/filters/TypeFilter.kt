package app.futured.academyproject.util.filters

class TypeFilter : Filter {
    override val description: String = "Type"
    override val values: List<String> = listOf(
        "AT-SPG", "heavyTank", "mediumTank", "lightTank", "SPG",
    )
    override val selectedValues: MutableList<Any> = mutableListOf()
}