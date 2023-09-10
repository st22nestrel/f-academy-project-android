package app.futured.academyproject.util.filters

interface Filter {
    val description: String
    val values: List<Any>
    val selectedValues: MutableList<Any>
}