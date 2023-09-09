package app.futured.academyproject.ui.screens.compareTable

import app.futured.arkitekt.core.event.Event

sealed class CompareTableEvents : Event<CompareTableViewState>()

object NavigateBackEvent : CompareTableEvents()