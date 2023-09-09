package app.futured.academyproject.domain

// TODO Step 6 - Create UseCase to set favorite place
// Hint : Use "uc" live template to create use case
// Named it SetFavoritePlaceUseCase
// Args: Place ID (Int)
// Result: Unit
// Inject PlacesPersistence and use its method/s you have created before

import app.futured.academyproject.data.persistence.TanksPersistence
import app.futured.arkitekt.crusecases.UseCase
import javax.inject.Inject

class SetSelectedTankUseCase @Inject constructor(
    private val persistence: TanksPersistence,
): UseCase<SetSelectedTankUseCase.Args, Unit>() {

    override suspend fun build(args: Args) {
        persistence.addTankIdSelected(args.tankId)
    }

    data class Args(val tankId: Int)
}
