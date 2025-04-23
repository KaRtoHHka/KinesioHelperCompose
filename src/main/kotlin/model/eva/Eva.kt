package model.eva
import kotlinx.serialization.Serializable

import kotlinx.serialization.SerialName


class Eva : ArrayList<EvaSubList>()

class EvaSubList : ArrayList<EvaNegPosItem>()

@Serializable
data class EvaNegPosItem(
    @SerialName("name")
    val name: String = "",
    @SerialName("text")
    val text: String = ""
)