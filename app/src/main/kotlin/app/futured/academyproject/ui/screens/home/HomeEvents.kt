package app.futured.academyproject.ui.screens.home

import app.futured.arkitekt.core.event.Event

sealed class HomeEvents : Event<HomeViewState>()

data class NavigateToDetailEvent(val tankId: Int) : HomeEvents()

class FilterSelectedEvent() : HomeEvents()
