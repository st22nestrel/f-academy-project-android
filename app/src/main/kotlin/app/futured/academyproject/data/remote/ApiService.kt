package app.futured.academyproject.data.remote

import app.futured.academyproject.data.model.api.ApiTanks
import app.futured.academyproject.data.model.api.CulturalPlaces
import retrofit2.http.GET

interface ApiService {

    @GET("omi_ok_kulturni_instituce/FeatureServer/0/query?outFields=*&where=1%3D1&f=geojson")
    suspend fun culturalPlaces(): CulturalPlaces

    @GET("tanks")
    suspend fun apiTanks(): ApiTanks
}
