package app.futured.academyproject.ui.screens.compareTable

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import app.futured.academyproject.data.model.local.Tank
import app.futured.academyproject.data.model.local.TankComparable
import app.futured.arkitekt.core.ViewState
import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.collections.immutable.PersistentList
import kotlinx.collections.immutable.persistentListOf
import javax.inject.Inject

@ViewModelScoped
class CompareTableViewState @Inject constructor() : ViewState{
    var tanks: PersistentList<Pair<Tank, TankComparable>> by mutableStateOf(persistentListOf())
}