package app.futured.academyproject.ui.screens.detail

import app.futured.academyproject.domain.GetTankFlowUseCase
import app.futured.academyproject.domain.SetFavoritePlaceUseCase
import app.futured.academyproject.domain.SetFavoriteTankUseCase
import app.futured.academyproject.tools.arch.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    override val viewState: DetailViewState,
    private val getTankUseCase: GetTankFlowUseCase,
    private val setFavoriteTanksUseCase: SetFavoriteTankUseCase
    // TODO Step 7 - Inject SetFavoritePlaceUseCase
) : BaseViewModel<DetailViewState>(), Detail.Actions {

    init {
        loadPlace()
    }

    private fun loadPlace() {
        getTankUseCase.execute(GetTankFlowUseCase.Args(viewState.tankId)) {
            onNext {
                viewState.tank = it
            }
            onError {
                Timber.e(it)
            }
        }
    }

    override fun navigateBack() {
        sendEvent(NavigateBackEvent)
    }

    override fun onFavorite() {
        // TODO Step 8 - Implement onFavorite method
        // Use SetFavoritePlaceUseCase
        // Place id you can find in DetailViewState "viewState.placeId"
        setFavoriteTanksUseCase.execute(SetFavoriteTankUseCase.Args(viewState.tankId)){

        }
        // Don't worry - change is automatically observed in GetPlaceFlowUseCase
    }
}
