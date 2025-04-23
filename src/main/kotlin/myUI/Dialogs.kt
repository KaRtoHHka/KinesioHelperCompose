package myUI

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Checkbox
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.UiComposable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.dokar.sonner.ToastType
import com.dokar.sonner.Toaster
import com.dokar.sonner.rememberToasterState
import controller.createHomework
import model.patient.Patient
import saveFileChooserDialog
import screens.log

@Composable
fun HomeworkDialog(showDialog: Boolean, pat: Patient, setShowDialog: (Boolean) -> Unit) {
    if (showDialog) {
        val map = pat.homework.toMap()
        val elements = remember { mutableStateListOf<Element>() }
        elements.addAll(map.map {
            val e = Element(element = it)
            e.ini()
            e
        })
        Dialog({ setShowDialog(false) }, DialogProperties(true, true, false)) {
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(375.dp)
                    .padding(16.dp),
                shape = RoundedCornerShape(16.dp),
            ) {
                val toaster = rememberToasterState()
                Toaster(
                    state = toaster
                )
                Column(
                    modifier = Modifier
                        .fillMaxSize(),
                    verticalArrangement = Arrangement.Top,
                    horizontalAlignment = Alignment.Start,
                ) {
                    Text(
                        text = "Выберите, какие пункты должны быть в д/з",
                        color = Color.Black,
                        modifier = Modifier.padding(16.dp),
                    )
                    Column {
                        elements.onEach { entry ->
                            Row(
                                modifier = Modifier.fillMaxWidth().heightIn(10.dp, 100.dp),
                                horizontalArrangement = Arrangement.spacedBy(16.dp),
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Checkbox(
                                    checked = entry.checked.value,
                                    onCheckedChange = { entry.checked.value = !entry.checked.value }
                                )
                                Text(entry.element.key)
                            }
                        }
                    }
                    Row(
                        modifier = Modifier
                            .fillMaxWidth(),
                        horizontalArrangement = Arrangement.Center,
                    ) {
                        TextButton(
                            onClick = { setShowDialog(false) },
                            modifier = Modifier.padding(8.dp),
                        ) {
                            Text("Отмена")
                        }
                        TextButton(
                            onClick = {
                                setShowDialog(false)
                                val file = saveFileChooserDialog("Куда сохранить домашнее задание?", "${pat.patientName.value.text}.docx")
                                log("file - ${file.toString()}")
                                if (file != null) try {
                                    try {
                                        createHomework(file, map.toMutableMap())
                                    } catch (e: Exception) {
                                        log(e.stackTraceToString())
                                        e.printStackTrace()
                                    }

                                    toaster.show(
                                        "Сохранено",
                                        type = ToastType.Info
                                    )
                                } catch (e: Exception) {
                                    log(e.stackTraceToString())
                                    println(e)
                                }
                            },
                            modifier = Modifier.padding(8.dp),
                        ) {
                            Text("Сохранить")
                        }
                    }
                }
            }
        }
    }
}

data class Element(
    val element: Map.Entry<String, String>,
) {
    lateinit var checked: MutableState<Boolean>

    @Composable
    @UiComposable
    fun ini() {
        checked = mutableStateOf(true)
    }
}