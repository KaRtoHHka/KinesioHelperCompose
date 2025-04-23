package model.patient

import androidx.compose.runtime.*
import androidx.compose.runtime.snapshots.SnapshotStateMap
import androidx.compose.ui.UiComposable
import androidx.compose.ui.text.input.TextFieldValue
import java.text.SimpleDateFormat
import java.util.*

data class Patient(
    var isInitialize: Boolean = false
) {
    lateinit var patientName: MutableState<TextFieldValue>
    lateinit var patientAge: MutableState<TextFieldValue>

    //true - male
    lateinit var patientSex: MutableState<Boolean>
    lateinit var dateOfWork: MutableState<TextFieldValue>
    lateinit var numberOfCorrection: MutableState<TextFieldValue>
    lateinit var patientProblem: MutableState<TextFieldValue>
    lateinit var barometerLevel: MutableState<Int>
    lateinit var barometerPart: MutableState<Int>
    lateinit var barometerNegative: MutableState<Boolean>
    lateinit var barometerDropDown: MutableState<Boolean>
    lateinit var barometerItem: MutableState<Int>

    lateinit var painNumber: MutableState<Int>
    lateinit var painTextField: MutableState<TextFieldValue>
    lateinit var painError: MutableState<Boolean>

    lateinit var structureLevel: MutableState<Int>
    lateinit var structureLevelDropDown: MutableState<Boolean>
    lateinit var structurePart: MutableState<Int>
    lateinit var structurePartDropDown: MutableState<Boolean>
    lateinit var structureType: MutableState<Int>
    lateinit var structureNegative: MutableState<Boolean>

    lateinit var baxNumber: MutableState<Int>
    lateinit var baxTextField: MutableState<TextFieldValue>
    lateinit var baxError: MutableState<Boolean>
    lateinit var baxNegative: MutableState<Boolean>

    lateinit var avoidNumber: MutableState<Int>
    lateinit var avoidTextField: MutableState<TextFieldValue>
    lateinit var avoidError: MutableState<Boolean>
    lateinit var avoidNegative: MutableState<Boolean>

    lateinit var numbersNumber: MutableState<Int>
    lateinit var numbersTextField: MutableState<TextFieldValue>
    lateinit var numbersError: MutableState<Boolean>

    lateinit var kobiNumber: MutableState<Int>
    lateinit var kobiTextField: MutableState<TextFieldValue>
    lateinit var kobiError: MutableState<Boolean>
    lateinit var kobiNegative: MutableState<Boolean>

    lateinit var mauiNature: MutableState<Boolean>
    lateinit var mauiNumber: MutableState<Int>
    lateinit var mauiTextField: MutableState<TextFieldValue>
    lateinit var mauiError: MutableState<Boolean>
    
    lateinit var rosesNumber: MutableState<Int>
    lateinit var rosesTextField: MutableState<TextFieldValue>
    lateinit var rosesError: MutableState<Boolean>

    lateinit var evaNumber: MutableState<Int>
    lateinit var evaTextField: MutableState<TextFieldValue>
    lateinit var evaError: MutableState<Boolean>
    lateinit var evaNegative: MutableState<Boolean>

    lateinit var homework: SnapshotStateMap<String, String>

    @Composable
    @UiComposable
    fun ini() {
        patientName = remember { mutableStateOf(TextFieldValue("")) }
        patientAge = remember { mutableStateOf(TextFieldValue("")) }
        patientSex = remember { mutableStateOf(true) }
        numberOfCorrection = remember { mutableStateOf(TextFieldValue("")) }
        val calendar: Calendar = Calendar.getInstance()
        val dateFormat = SimpleDateFormat("dd.MM.yyyy")
        dateOfWork = remember { mutableStateOf(TextFieldValue(dateFormat.format(calendar.time))) }
        patientProblem = remember { mutableStateOf(TextFieldValue("")) }

        barometerLevel = remember { mutableStateOf(0) }
        barometerPart = remember { mutableStateOf(0) }
        barometerNegative = remember { mutableStateOf(false) }
        barometerDropDown = remember { mutableStateOf(false) }
        barometerItem = remember { mutableStateOf(0) }

        painNumber = remember { mutableStateOf(0) }
        painTextField = remember { mutableStateOf(TextFieldValue("")) }
        painError = remember { mutableStateOf(false) }

        structureLevel = remember { mutableStateOf(0) }
        structureLevelDropDown = remember { mutableStateOf(false) }
        structurePart = remember { mutableStateOf(0) }
        structurePartDropDown = remember { mutableStateOf(false) }
        structureType = remember { mutableStateOf(0) }
        structureNegative = remember { mutableStateOf(true) }

        baxNumber = remember { mutableStateOf(0) }
        baxTextField = remember { mutableStateOf(TextFieldValue("")) }
        baxError = remember { mutableStateOf(false) }
        baxNegative = remember { mutableStateOf(true) }

        avoidNumber = remember { mutableStateOf(0) }
        avoidTextField = remember { mutableStateOf(TextFieldValue("")) }
        avoidError = remember { mutableStateOf(false) }
        avoidNegative = remember { mutableStateOf(true) }

        numbersNumber = remember { mutableStateOf(0) }
        numbersTextField = remember { mutableStateOf(TextFieldValue("")) }
        numbersError = remember { mutableStateOf(false) }

        kobiNumber = remember { mutableStateOf(0) }
        kobiTextField = remember { mutableStateOf(TextFieldValue("")) }
        kobiError = remember { mutableStateOf(false) }
        kobiNegative = remember { mutableStateOf(true) }
        
        mauiNature = remember { mutableStateOf(true) }
        mauiNumber = remember { mutableStateOf(0) }
        mauiTextField = remember { mutableStateOf(TextFieldValue("")) }
        mauiError = remember { mutableStateOf(false) }

        rosesNumber = remember { mutableStateOf(0) }
        rosesTextField = remember { mutableStateOf(TextFieldValue("")) }
        rosesError = remember { mutableStateOf(false) }

        evaNumber = remember { mutableStateOf(0) }
        evaTextField = remember { mutableStateOf(TextFieldValue("")) }
        evaError = remember { mutableStateOf(false) }
        evaNegative = remember { mutableStateOf(true) }

        homework = remember { mutableStateMapOf() }
    }
}
