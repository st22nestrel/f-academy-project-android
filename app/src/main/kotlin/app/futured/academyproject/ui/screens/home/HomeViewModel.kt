package app.futured.academyproject.ui.screens.home

import app.futured.academyproject.domain.GetTanksFlowUseCase
import app.futured.academyproject.tools.arch.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.collections.immutable.toPersistentList
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    override val viewState: HomeViewState,
    private val getTanksFlowUseCase: GetTanksFlowUseCase,
) : BaseViewModel<HomeViewState>(), Home.Actions {

    init {
        loadApiTanks()
    }

    private fun loadApiTanks() {
        viewState.error = null

        getTanksFlowUseCase.execute {
            onNext {
                Timber.d("Tanks: $it")

                viewState.tanks = it.toPersistentList()
            }
            onError { error ->
                Timber.e(error)
                viewState.error = error
            }
        }
    }

    override fun tryAgain() {
        loadApiTanks()
    }

    override fun navigateToDetailScreen(tankId: Int) {
        sendEvent(NavigateToDetailEvent(tankId))
    }
}
