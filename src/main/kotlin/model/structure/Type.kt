package model.structure


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Type(
    @SerialName("name")
    val name: String = "",
    @SerialName("positive")
    val positive: String = "",
    @SerialName("negative")
    val negative: String = ""
)