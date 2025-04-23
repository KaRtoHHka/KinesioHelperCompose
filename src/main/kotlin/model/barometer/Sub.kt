package model.barometer


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Sub(
    @SerialName("namePositive")
    val namePositive: String = "",
    @SerialName("nameNegative")
    val nameNegative: String = "",
    @SerialName("subList")
    val subItem: List<List<SubX>> = listOf()
)