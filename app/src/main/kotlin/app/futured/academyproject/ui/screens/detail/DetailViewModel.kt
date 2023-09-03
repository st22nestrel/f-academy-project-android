package app.futured.academyproject.ui.screens.detail

import app.futured.academyproject.domain.GetPlaceFlowUseCase
import app.futured.academyproject.domain.SetFavoritePlaceUseCase
import app.futured.academyproject.tools.arch.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    override val viewState: DetailViewState,
    private val getPlaceUseCase: GetPlaceFlowUseCase,
    private val setFavoritePlacesUseCase: SetFavoritePlaceUseCase
    // TODO Step 7 - Inject SetFavoritePlaceUseCase
) : BaseViewModel<DetailViewState>(), Detail.Actions {

    init {
        loadPlace()
    }

    private fun loadPlace() {
        getPlaceUseCase.execute(GetPlaceFlowUseCase.Args(viewState.placeId)) {
            onNext {
                viewState.place = it
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
        setFavoritePlacesUseCase.execute(SetFavoritePlaceUseCase.Args(viewState.placeId)){

        }

        // Don't worry - change is automatically observed in GetPlaceFlowUseCase
    }
}
