package model.barometer


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class BarometerItem(
    @SerialName("name")
    val name: String = "",
    @SerialName("subList")
    val subItem: List<Sub> = listOf()
)