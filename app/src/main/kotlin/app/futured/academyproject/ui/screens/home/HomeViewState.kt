package app.futured.academyproject.ui.screens.home

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import app.futured.academyproject.data.model.local.Place
import app.futured.academyproject.data.model.local.Tank
import app.futured.academyproject.util.filters.Filters
import app.futured.arkitekt.core.ViewState
import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.collections.immutable.PersistentList
import kotlinx.collections.immutable.persistentListOf
import javax.inject.Inject

@ViewModelScoped
class HomeViewState @Inject constructor() : ViewState {

    //var places: PersistentList<Place> by mutableStateOf(persistentListOf())


    var loadedTanks: PersistentList<Tank> = persistentListOf()

    var tanks: PersistentList<Tank> by mutableStateOf(persistentListOf())

    val filters: Filters = Filters()

    var error: Throwable? by mutableStateOf(null)
}
