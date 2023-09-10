package app.futured.academyproject.ui.screens.compareTable

import app.futured.academyproject.data.model.local.TankComparable
import app.futured.academyproject.domain.CompareTanksComparableUseCase
import app.futured.academyproject.domain.GetTanksComparableSelectedFlowUseCase
import app.futured.academyproject.domain.GetTanksComparableSelected_And_TankStatisticsOutliersFlowUseCase
import app.futured.academyproject.domain.GetTanksSelectedFlowUseCase
import app.futured.academyproject.tools.arch.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.collections.immutable.toPersistentList
import kotlinx.collections.immutable.toPersistentMap
import kotlinx.coroutines.awaitAll
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class CompareTableViewModel @Inject constructor(
    override val viewState: CompareTableViewState,
    private val getTanksComparableSelectedFlowUseCase: GetTanksComparableSelected_And_TankStatisticsOutliersFlowUseCase,
    private val getTanksSelectedFlowUseCase: GetTanksSelectedFlowUseCase,
    private val compareTanksComparableUseCase: CompareTanksComparableUseCase
) : BaseViewModel<CompareTableViewState>(), CompareTable.Actions {
    init {
        loadTanks()
    }

    private fun loadTanks(){
        getTanksComparableSelectedFlowUseCase.execute {
            onNext {
                Timber.d("Tanks: $it")

                viewState.tanksComparable = it.second.associateBy(TankComparable::id).toPersistentMap()

                viewState.tanksComparableValueOutliers = it.first
            }
            onError { error ->
                Timber.e(error)
            }
        }
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

    override fun navigateToDetailScreen(tankId: Int) {
        sendEvent(NavigateToDetailEvent(tankId))
    }

    override fun navigateBack() {
        sendEvent(NavigateBackEvent)
    }
}
