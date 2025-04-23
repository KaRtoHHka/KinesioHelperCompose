package model.structure


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Sub(
    @SerialName("name")
    val name: String = "",
    @SerialName("types")
    val types: MutableList<Type> = mutableListOf()
)