package app.futured.academyproject.util.filters

class NationFilter : Filter {
    override val description: String = "Nation"
    override val values: List<String> = listOf(
        "china", "czech", "france", "germany", "italy", "japan",
        "poland", "sweden", "uk", "usa", "ussr"
    )
    override val selectedValues: MutableList<Any> = mutableListOf()
}