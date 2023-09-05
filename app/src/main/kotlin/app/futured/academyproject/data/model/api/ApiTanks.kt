package app.futured.academyproject.data.model.api

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ApiTanks(
    @SerialName("status") val status: String,
    @SerialName("data") val data: Map<String, ApiTank>,
)
