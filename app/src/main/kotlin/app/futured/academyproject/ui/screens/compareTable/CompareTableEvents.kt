package app.futured.academyproject.ui.screens.compareTable

import app.futured.academyproject.ui.screens.home.HomeEvents
import app.futured.arkitekt.core.event.Event

sealed class CompareTableEvents : Event<CompareTableViewState>()

data class NavigateToDetailEvent(val tankId: Int) : CompareTableEvents()

object NavigateBackEvent : CompareTableEvents()
