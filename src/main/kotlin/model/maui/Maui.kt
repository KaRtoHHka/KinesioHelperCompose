package model.maui
import kotlinx.serialization.Serializable

import kotlinx.serialization.SerialName


class Maui : ArrayList<MauiItem>()

@Serializable
data class MauiItem(
    @SerialName("name")
    val name: String = "",
    @SerialName("subList")
    val subList: List<Sub> = listOf()
)

@Serializable
data class Sub(
    @SerialName("name")
    val name: String = "",
    @SerialName("text")
    val text: String = ""
)