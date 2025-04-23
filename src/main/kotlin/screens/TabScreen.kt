package screens

import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.ScrollableTabRow
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import model.patient.Patient
import myUI.MyTab
import myUI.MyTabNew

@Composable
@Preview
fun TabScreen() {
    val patients = remember { mutableStateListOf(Patient()) }
    patients[0].ini()
    patients[0].isInitialize = true

    var tabIndex by remember { mutableStateOf(0) }

    Column(
        modifier = Modifier.fillMaxWidth()
    ) {

        ScrollableTabRow(
            selectedTabIndex = tabIndex,
        ) {
            patients.forEachIndexed { index, patient ->
                if (!patient.isInitialize) {
                    patient.ini()
                    patient.isInitialize = true
                }
                MyTabNew(
                    selected = tabIndex == index,
                    text = patient.patientName.value.text.ifBlank { "Новый пациент" },
                    onClick = { tabIndex = index },
                    onCloseClick = {
                        if (patients.size > 1){
                            patients.removeAt(index)
                            if (patients.size <= tabIndex) {
                                tabIndex--
                            }
                        }
                    }
                )

            }

            MyTab(
                selected = false,
                closable = false,
                text = "+",
                onClick = {
                    patients.add(
                        Patient()
                    )
                },
                onCloseClick = {
                }
            )

        }
        PatientScreen(patients[tabIndex])
    }
}

@Composable
fun newPatient(): Patient {
    val newPatient = Patient()
    newPatient.ini()
    return newPatient
}