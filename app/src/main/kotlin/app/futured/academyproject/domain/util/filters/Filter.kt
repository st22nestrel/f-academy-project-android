package app.futured.academyproject.domain.util.filters

interface Filter {
    val description: String
    val values: List<Any>
    val selectedValues: MutableList<Any>
}