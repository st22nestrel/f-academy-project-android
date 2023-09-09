package app.futured.academyproject.ui.screens.home

import app.futured.academyproject.data.model.local.Tank
import app.futured.academyproject.domain.FilterTanksUseCase
import app.futured.academyproject.domain.GetTanksFlowUseCase
import app.futured.academyproject.domain.SetSelectedTankUseCase
import app.futured.academyproject.domain.util.filters.Filters
import app.futured.academyproject.tools.arch.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.collections.immutable.PersistentList
import kotlinx.collections.immutable.toPersistentList
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    override val viewState: HomeViewState,
    private val getTanksFlowUseCase: GetTanksFlowUseCase,
    private val filterTanksUseCase: FilterTanksUseCase,
    private val setSelectedTankUseCase: SetSelectedTankUseCase
) : BaseViewModel<HomeViewState>(), Home.Actions {

    init {
        loadApiTanks()
    }

    private fun loadApiTanks() {
        viewState.error = null

        getTanksFlowUseCase.execute {
            onNext {
                Timber.d("Tanks: $it")

                viewState.loadedTanks = it.toPersistentList()
                viewState.tanks = it.toPersistentList()
            }
            onError { error ->
                Timber.e(error)
                viewState.error = error
            }
        }
    }

    override fun filterTanks(filters: Filters) {
        viewState.tanks = filterTanksUseCase.invoke(filters, viewState.loadedTanks)
    }

    override fun onSelected(tankId: Int) {
        setSelectedTankUseCase.execute(SetSelectedTankUseCase.Args(tankId)){

        }
    }

    override fun tryAgain() {
        loadApiTanks()
    }

    override fun navigateToDetailScreen(tankId: Int) {
        sendEvent(NavigateToDetailEvent(tankId))
    }

    override fun navigateToCompareTableScreen() {
        sendEvent(NavigateToCompareTableEvent())
    }
}
