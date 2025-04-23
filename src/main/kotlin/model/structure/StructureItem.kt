package model.structure


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class StructureItem(
    @SerialName("name")
    val name: String = "",
    @SerialName("subList")
    val subList: MutableList<Sub> = mutableListOf()
)