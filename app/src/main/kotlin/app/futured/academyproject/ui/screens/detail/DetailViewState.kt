package app.futured.academyproject.ui.screens.detail

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import app.futured.academyproject.data.model.local.Tank
import app.futured.academyproject.tools.Constants.Args.TANK_ID
import app.futured.arkitekt.core.ViewState
import dagger.hilt.android.scopes.ViewModelScoped
import javax.inject.Inject

@ViewModelScoped
class DetailViewState @Inject constructor(
    savedStateHandle: SavedStateHandle,
) : ViewState {

    val tankId = savedStateHandle.get<Int>(TANK_ID) ?: throw IllegalArgumentException("Missing tankId argument")

    var tank by mutableStateOf<Tank?>(null)
}
