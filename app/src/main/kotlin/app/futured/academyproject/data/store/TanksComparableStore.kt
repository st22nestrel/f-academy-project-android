package app.futured.academyproject.data.store

import app.futured.academyproject.data.model.api.ApiTankComparable
import app.futured.academyproject.data.model.api.ApiTanksComparable
import app.futured.academyproject.data.remote.ApiManager
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.onStart
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class TanksComparableStore @Inject constructor(
    private val apiManager: ApiManager
){
    private val comparableTanks = MutableStateFlow<MutableMap<Int, ApiTanksComparable>?>(null)

    suspend fun downloadComparabletank(id: Int){
        comparableTanks.value?.put(id, apiManager.getApiComparableTank(id))
    }

    //TODO create a more subtle way of downloading details about selected tanks
//    fun getTanksComparableFlow(selectedTanksIds: List<Int>): Flow<ApiTanksComparable>? =
//        comparableTanks.value?.values?.asFlow()?.onStart {
//            selectedTanksIds.forEach(::getTank)
//        }?.filterNotNull()

//        selectedTanksIds.forEach{
//            getTank(it)
//        }

    suspend fun getTank(tankId: Int) : ApiTankComparable? {
        val tank: ApiTanksComparable? = comparableTanks.value?.get(tankId)
        if (tank == null) {
            downloadComparabletank(tankId)
        }
        return comparableTanks.value?.get(tankId)?.data?.get(tankId.toString())
    }
}