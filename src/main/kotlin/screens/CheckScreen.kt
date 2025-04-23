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
        log("CHEK")

        val macFile = javaClass.getResource("/macAddresses.json")?.readText() ?: "Ничего"
        val macAddresses = Json.decodeFromString<ArrayList<String>>(macFile)

        val network = NetworkInterface.networkInterfaces().filter { it.hardwareAddress != null }.toList()
        val macs = mutableListOf<String>()
        network.map {
            val hexadecimal = arrayOfNulls<String>(it.hardwareAddress.size)
            for (i in it.hardwareAddress.indices) {
                hexadecimal[i] = java.lang.String.format("%02X", it.hardwareAddress[i])
            }
            val mac = java.lang.String.join("-", *hexadecimal)
            macs += mac
        }
        val composableScope = rememberCoroutineScope()

        composableScope.launch { setupConnection() }
        var haveAccess by remember { mutableStateOf(false) }
        TabScreen()

//        if (haveAccess) {
//            println("HERE")
//            TabScreen()
//        } else {
//            Button(onClick = {
//                try {
//                    val access = DBController.getAllAccess()
//                    if ((access?.count ?: 0) > 0) {
//                        access?.count = access?.count!! - 1
////                        accessDoc.save(access)
//                        val js = Json.encodeToString(macs)
//                        val macFileToWrite = javaClass.getResource("/macAddresses.json").file
//                        val f = File(macFileToWrite)
//                        f.writeText(js)
//                        println(macFileToWrite)
//                        haveAccess = true
//                    }
//                } catch (e: Exception) {
//                    log(e.stackTraceToString())
//                    e.printStackTrace()
//                }
//            }
//            ) {
//                Text("Проверить доступ")
//            }
//        }
    }
}

fun log(e: String) {
//    val f = File("C:/Users/Oleg/Downloads/log.txt")
//    f.writeText(e)
}

fun main() {
    val network = NetworkInterface.networkInterfaces().filter { it.hardwareAddress != null }.toList()
    val macs = mutableListOf<String>()
    network.map {
        val hexadecimal = arrayOfNulls<String>(it.hardwareAddress.size)
        for (i in it.hardwareAddress.indices) {
            hexadecimal[i] = java.lang.String.format("%02X", it.hardwareAddress[i])
        }
        val mac = java.lang.String.join("-", *hexadecimal)
        macs += mac
    }
    val js = Json.encodeToString(macs)
    val macFile = Patient::class.java.getResource("/macAddresses.json").file
    val f = File(macFile)
    f.writeText(js)
    println(macFile)
}