package model.kobi
import kotlinx.serialization.Serializable

import kotlinx.serialization.SerialName


class Kobi : ArrayList<KobiSubList>()

@Serializable
class KobiSubList : ArrayList<KobiNegPosItem>()

@Serializable
data class KobiNegPosItem(
    @SerialName("name")
    val name: String = "",
    @SerialName("text")
    val text: String = ""
)