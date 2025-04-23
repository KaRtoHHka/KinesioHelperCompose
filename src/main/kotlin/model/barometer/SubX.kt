package model.barometer


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class SubX(
    @SerialName("name")
    val name: String = "",
    @SerialName("text")
    val text: String = ""
)