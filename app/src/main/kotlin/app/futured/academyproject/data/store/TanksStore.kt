package app.futured.academyproject.data.store

import app.futured.academyproject.data.model.api.ApiTanks
import app.futured.academyproject.data.remote.ApiManager
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.onStart
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class TanksStore @Inject constructor(
    private val apiManager: ApiManager,
) {
    private val tanks = MutableStateFlow<ApiTanks?>(null)

    fun getTanksFlow(): Flow<ApiTanks> = tanks.asStateFlow().onStart {
        tanks.value = apiManager.getApiTanks()
    }.filterNotNull()

    //TODO maybe replace with non-nullable call? (ask on discord)
    fun getTank(tankId: Int) = tanks.value?.data?.get(tankId.toString())
}
