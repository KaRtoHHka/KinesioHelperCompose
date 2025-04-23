package model.numbers
import kotlinx.serialization.Serializable

import kotlinx.serialization.SerialName


class Numbers : ArrayList<NumbersItem>()

@Serializable
data class NumbersItem(
    @SerialName("name")
    val name: String = "",
    @SerialName("text")
    val text: String = ""
)