package app.futured.academyproject.ui.screens.compareTable

import app.futured.academyproject.domain.GetTanksFlowUseCase
import app.futured.academyproject.domain.GetTanksSelectedFlowUseCase
import app.futured.academyproject.tools.arch.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.collections.immutable.toPersistentList
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class CompareTableViewModel @Inject constructor(
    override val viewState: CompareTableViewState,
    private val getTanksSelectedFlowUseCase: GetTanksSelectedFlowUseCase
) : BaseViewModel<CompareTableViewState>(), CompareTable.Actions {
    init {
        loadTanks()
    }

    private fun loadTanks(){
        getTanksSelectedFlowUseCase.execute {
            onNext {
                Timber.d("Tanks: $it")

                viewState.tanks = it.toPersistentList()
            }
            onError { error ->
                Timber.e(error)
            }
        }

    }

    override fun navigateBack() {
        sendEvent(NavigateBackEvent)
    }
}
