package app.futured.academyproject.ui.screens.home

import app.futured.academyproject.data.model.local.Tank
import app.futured.academyproject.data.persistence.TanksPersistence
import app.futured.academyproject.domain.FilterTanksUseCase
import app.futured.academyproject.domain.GetTanksFlowUseCase
import app.futured.academyproject.domain.SetSelectedTankUseCase
import app.futured.academyproject.util.filters.Filters
import app.futured.academyproject.tools.arch.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.collections.immutable.PersistentList
import kotlinx.collections.immutable.toPersistentList
import kotlinx.coroutines.CoroutineScope
import timber.log.Timber
import javax.inject.Inject
import kotlin.system.exitProcess

@HiltViewModel
class HomeViewModel @Inject constructor(
    override val viewState: HomeViewState,
    private val getTanksFlowUseCase: GetTanksFlowUseCase,
    private val filterTanksUseCase: FilterTanksUseCase,
    private val setSelectedTankUseCase: SetSelectedTankUseCase,
    private val tanksPersistence: TanksPersistence
) : BaseViewModel<HomeViewState>(), Home.Actions {

    init {
        loadApiTanks()
    }

    private fun loadApiTanks() {
        viewState.error = null

        //This is not working, getTanksFlowUseCase gets fired every time any
        //of its observable arguments get changed
        //MonkeyPatch solution to reloading and reinitializing Home screen
        //if(!viewState.loadedTanks.isEmpty()) return

        if (tanksPersistence.getTankList().isNotEmpty()){
            viewState.loadedTanks = tanksPersistence.getTankList().toPersistentList()
            viewState.tanks = tanksPersistence.getTankList().toPersistentList()
        }
        else{
            getTanksFlowUseCase.execute {
                onNext {
                    Timber.d("Tanks: $it")

                    viewState.loadedTanks = it.toPersistentList()
                    viewState.tanks = it.toPersistentList()
                    if (it.isNotEmpty()){
                        tanksPersistence.setTankList(it)
                    }
                }
                onError { error ->
                    Timber.e(error)
                    viewState.error = error
                }
            }
        }
    }

    override fun filterTanks() {
        viewState.tanks = filterTanksUseCase.invoke(viewState.filters, viewState.loadedTanks)
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
