package screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import controller.setupConnection
import kotlinx.coroutines.launch
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import model.patient.Patient
import java.io.File
import java.net.NetworkInterface


@Composable
fun CheckScreen() {
    Box(modifier = Modifier.fillMaxSize()) {
        TabScreen()
    }
}

fun log(e: String) {
//    val f = File("C:/Users/Oleg/Downloads/log.txt")
//    f.writeText(e)
}

fun main() {
}