@file:Suppress("UnusedPrivateMember")

package app.futured.academyproject.data.remote

import app.futured.academyproject.data.model.api.ApiTanksComparable
import app.futured.academyproject.data.model.api.ApiTanks
import app.futured.academyproject.data.model.api.CulturalPlaces
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ApiManager @Inject constructor(
    private val apiService: ApiService,
) {
    suspend fun getCulturalPlaces(): CulturalPlaces = try {
        apiService.culturalPlaces()
    } catch (e: Exception) {
        throw ApiExceptionUnknown(e.localizedMessage, e)
    }

    suspend fun getApiTanks(): ApiTanks = try {
        apiService.apiTanks()
    } catch (e: Exception) {
        throw ApiExceptionUnknown(e.localizedMessage, e)
    }

    suspend fun getApiComparableTank(tankId: Int): ApiTanksComparable = try {
        apiService.apiComparableTank(tankId)
    } catch (e: Exception) {
        throw ApiExceptionUnknown(e.localizedMessage, e)
    }
}
