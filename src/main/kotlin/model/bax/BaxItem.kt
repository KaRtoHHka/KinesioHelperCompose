package model.bax


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class BaxItem(
    @SerialName("name")
    val name: String = "",
    @SerialName("subList")
    val subList: List<List<String>> = listOf()
)