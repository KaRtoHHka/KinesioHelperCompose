package model.roses
import kotlinx.serialization.Serializable

import kotlinx.serialization.SerialName


class Roses : ArrayList<RosesItem>()

@Serializable
data class RosesItem(
    @SerialName("name")
    val name: String = "",
    //negative and positive inside
    @SerialName("text")
    val text: List<String> = listOf()
)