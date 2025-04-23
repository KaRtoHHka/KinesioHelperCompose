package screens

import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import com.dokar.sonner.ToastType
import com.dokar.sonner.Toaster
import com.dokar.sonner.rememberToasterState
import controller.createDoc
import kotlinx.serialization.json.Json
import model.barometer.BarometerItem
import model.bax.BaxItem
import model.eva.EvaNegPosItem
import model.kobi.KobiNegPosItem
import model.maui.MauiItem
import model.numbers.NumbersItem
import model.patient.Patient
import model.structure.StructureItem
import myUI.HomeworkDialog
import saveFileChooserDialog
import theme.lightGrey

@Preview
@Composable
fun PatientScreen(pat: Patient) {
    val (showDialog, setShowDialog) = remember { mutableStateOf(false) }
    HomeworkDialog(showDialog, pat, setShowDialog)

    val toaster = rememberToasterState()
    Column(
        modifier = Modifier
            .background(lightGrey)
            .fillMaxWidth()
            .fillMaxHeight()
            .padding(start = 16.dp, end = 16.dp)
            .verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Toaster(
            state = toaster
        )
        Row(
            modifier = Modifier.fillMaxWidth().heightIn(10.dp, 100.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Button(onClick = {
                val file = saveFileChooserDialog(
                    "Куда сохранить отчет?",
                    "Отчет ${pat.patientName.value.text}${pat.numberOfCorrection.value.text}.docx"
                )
                if (file != null) try {
                    toaster.show(
                        "Дождитесь сохранения",
                        type = ToastType.Info
                    )
                    createDoc(file, pat)
                    toaster.show(
                        "Сохранено",
                        type = ToastType.Info
                    )
                } catch (e: Exception) {
                    println(e)
                }
            }) {
                Text("Создать отчет")
            }
            Button(onClick = {
                setShowDialog(true)
            }) {
                Text("Создать домашнее задание")
            }
        }
        Row(
            modifier = Modifier.fillMaxWidth().heightIn(10.dp, 100.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            val modifier = Modifier
                .widthIn(10.dp, 1000.dp)
                .heightIn(10.dp, 100.dp)
                .weight(1f)
            //patient name
            OutlinedTextField(
                modifier = modifier,
                textStyle = TextStyle(),
                label = { Text(text = "Имя пациента") },
                value = pat.patientName.value,
                onValueChange = { newText ->
                    pat.patientName.value = newText
                }
            )
            //patient age
            OutlinedTextField(
                modifier = modifier,
                placeholder = { Text(text = "Возраст") },
                label = { Text(text = "Возраст") },
                value = pat.patientAge.value,
                onValueChange = { newText ->
                    pat.patientAge.value = newText
                },
                maxLines = 1
            )
            Column(
                modifier = Modifier,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(text = "Пол")
                //sex
                Switch(
                    checked = pat.patientSex.value,
                    onCheckedChange = { pat.patientSex.value = it },
                    colors = SwitchDefaults.colors(
                        checkedTrackColor = Color.Blue,
                        uncheckedTrackColor = Color.Red
                    )
                )
            }
            //date
            OutlinedTextField(
                modifier = modifier,
                placeholder = { Text(text = "Дата") },
                label = { Text(text = "Дата") },
                value = pat.dateOfWork.value,
                onValueChange = { newText ->
                    pat.dateOfWork.value = newText
                },
                maxLines = 1
            )
            //number of correction
            OutlinedTextField(
                modifier = modifier,
                placeholder = { Text(text = "Номер коррекции") },
                label = { Text(text = "Номер коррекции") },
                value = pat.numberOfCorrection.value,
                onValueChange = { newText ->
                    pat.numberOfCorrection.value = newText
                },
                maxLines = 1
            )
        }
        //problem
        OutlinedTextField(
            modifier = Modifier.sizeIn(0.dp, 300.dp)
                .fillMaxWidth(),
            textStyle = TextStyle(),
            label = { Text(text = "Проблема") },
            value = pat.patientProblem.value,
            onValueChange = { newText ->
                pat.patientProblem.value = newText
            }
        )

        //barometer
        val barometerFile = javaClass.getResource("/values/barometer.json")?.readText() ?: ""
        val barometer = Json.decodeFromString<List<BarometerItem>>(barometerFile)
        //barometer choose
        Row(
            modifier = Modifier.fillMaxWidth().heightIn(10.dp, 100.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            //barometer level
            Column(
                modifier = Modifier.wrapContentHeight()
                    .width(IntrinsicSize.Max),
                verticalArrangement = Arrangement.SpaceBetween
            ) {
                val modifier = Modifier.wrapContentHeight().weight(1.0f).fillMaxWidth()
                barometer.onEachIndexed { index, barometerItem ->
                    Row(
                        modifier = modifier.clickable { pat.barometerLevel.value = index },
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        RadioButton(
                            selected = pat.barometerLevel.value == index,
                            onClick = { pat.barometerLevel.value = index })
                        Text(text = barometerItem.name, maxLines = 1)
                    }

                }
            }
            //barometer part
            Column(
                modifier = Modifier.wrapContentHeight()
                    .width(IntrinsicSize.Max),
                verticalArrangement = Arrangement.SpaceBetween
            ) {
                val modifier = Modifier.wrapContentHeight().weight(1.0f).fillMaxWidth()
                barometer[pat.barometerLevel.value].subItem.onEachIndexed { index, sub ->
                    Row(
                        modifier = modifier.clickable { pat.barometerPart.value = index },
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        RadioButton(
                            selected = pat.barometerPart.value == index,
                            onClick = { pat.barometerPart.value = index })
                        Text(
                            text = if (pat.barometerNegative.value) sub.nameNegative else sub.namePositive,
                            maxLines = 1
                        )
                    }

                }
            }
            //barometer item
            Box(modifier = Modifier.clickable {
                pat.barometerDropDown.value = true
            }) {
                Button(
                    modifier = Modifier,
                    onClick = {
                        pat.barometerDropDown.value = !pat.barometerDropDown.value
                    }
                ) {
                    Text(barometer[pat.barometerLevel.value].subItem[pat.barometerPart.value].subItem[pat.barometerItem.value][if (pat.barometerNegative.value) 0 else 1].name)
                    Icon(Icons.Default.ArrowDropDown, contentDescription = "")
                }
                DropdownMenu(expanded = pat.barometerDropDown.value,
                    onDismissRequest = { pat.barometerDropDown.value = false }) {
                    barometer[pat.barometerLevel.value].subItem[pat.barometerPart.value].subItem.onEachIndexed { index, strings ->
                        DropdownMenuItem(onClick = {
                            pat.barometerItem.value = index
                            pat.barometerDropDown.value = false
                        }) {
                            Text(
                                text = if (pat.barometerNegative.value) strings[0].name else strings[1].name,
                                color = theme.textColorDark
                            )
                        }
                    }
                }
            }
            //barometer positive
            Column(
                modifier = Modifier.wrapContentHeight()
                    .width(IntrinsicSize.Max),
                verticalArrangement = Arrangement.SpaceBetween
            ) {
                val modifier = Modifier.wrapContentHeight().weight(1.0f).fillMaxWidth()
                Row(
                    modifier = modifier.clickable { pat.barometerNegative.value = false },
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    RadioButton(
                        selected = !pat.barometerNegative.value,
                        onClick = { pat.barometerNegative.value = false })
                    Text(text = "Позитивное", maxLines = 1)
                }
                Row(
                    modifier = modifier.clickable { pat.barometerNegative.value = true },
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    RadioButton(
                        selected = pat.barometerNegative.value,
                        onClick = { pat.barometerNegative.value = true })
                    Text(text = "Негативное", maxLines = 1)
                }
            }
        }
        //barometer text
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            val barometerTextId =
                "Барометр ${pat.barometerLevel.value + 1} ${pat.barometerPart.value + 1} ${pat.barometerItem.value + 1}${if (pat.barometerNegative.value) "Нег" else "Поз"}"
            var barometerText =
                barometer[pat.barometerLevel.value].subItem[pat.barometerPart.value].subItem[pat.barometerItem.value][if (pat.barometerNegative.value) 0 else 1].text
            OutlinedTextField(
                modifier = Modifier.weight(1.0f).wrapContentHeight(),
                textStyle = TextStyle(),
                readOnly = true,
                value = barometerText,
                onValueChange = { },
            )
            Button(onClick = {
                if (pat.homework.containsKey(barometerTextId))
                    pat.homework.remove(barometerTextId)
                else
                    pat.homework[barometerTextId] = barometerText
                println(pat.homework.toMap())
            }, modifier = Modifier) {
                if (pat.homework.containsKey(barometerTextId))
                    Text("Удалить из д/з", maxLines = 1)
                else
                    Text("Добавить в д/з", maxLines = 1)
            }

        }


        //pain
        val painFile = javaClass.getResource("/values/pain.json")?.readText() ?: ""
        val pain = Json.decodeFromString<ArrayList<String>>(painFile)

        //pain choose
        Row(
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text("Болевое поведение:")
            OutlinedTextField(
                textStyle = TextStyle(),
                label = { Text("Число 1-15") },
                isError = pat.painError.value,
                value = pat.painTextField.value,
                onValueChange = { newText ->
                    if ((newText.text.toIntOrNull() ?: -1) in 1..15) {
                        pat.painError.value = false
                        pat.painTextField.value = newText
                        pat.painNumber.value = pat.painTextField.value.text.toInt() - 1
                    } else {
                        pat.painError.value = true
                        pat.painTextField.value = newText
                    }
                }
            )
        }
        //pain text
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            val painText = pain[pat.painNumber.value]
            val painTextId = "Болевое поведение ${pat.painNumber.value + 1}"
            OutlinedTextField(
                modifier = Modifier.weight(1.0f).wrapContentHeight(),
                textStyle = TextStyle(),
                readOnly = true,
                value = painText,
                onValueChange = { },
            )
            Button(onClick = {
                if (pat.homework.containsKey(painTextId))
                    pat.homework.remove(painTextId)
                else
                    pat.homework[painTextId] = painText
                println(pat.homework.toMap())
            }, modifier = Modifier) {
                if (pat.homework.containsKey(painTextId))
                    Text("Удалить из д/з", maxLines = 1)
                else
                    Text("Добавить в д/з", maxLines = 1)
            }

        }

        //structure
        val structureFile = javaClass.getResource("/values/structure.json")?.readText() ?: ""
        val structure = Json.decodeFromString<ArrayList<StructureItem>>(structureFile)

        //structure choice
        Row(
            modifier = Modifier.fillMaxWidth().heightIn(10.dp, 100.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            //structure level
            Box(modifier = Modifier) {
                Button(
                    modifier = Modifier,
                    onClick = {
                        pat.structureLevelDropDown.value = !pat.structureLevelDropDown.value
                    }
//
                ) {
                    Text(structure[pat.structureLevel.value].name)
                    Icon(Icons.Default.ArrowDropDown, contentDescription = "")
                }
                DropdownMenu(expanded = pat.structureLevelDropDown.value,
                    onDismissRequest = { pat.structureLevelDropDown.value = false }) {
                    structure.onEachIndexed { index, structureItem ->
                        DropdownMenuItem(onClick = {
                            pat.structureLevel.value = index
                            pat.structurePart.value = 0
                            pat.structureLevelDropDown.value = false
                        }) {
                            Text(
                                text = structureItem.name,
                                maxLines = 1,
                                color = theme.textColorDark
                            )
                        }
                    }
                }
            }
            //structure part
            Box(modifier = Modifier) {
                Button(
                    modifier = Modifier,
                    onClick = {
                        pat.structurePartDropDown.value = !pat.structurePartDropDown.value
                    }
                ) {
                    Text(structure[pat.structureLevel.value].subList[pat.structurePart.value].name)
                    Icon(Icons.Default.ArrowDropDown, contentDescription = "")
                }
                DropdownMenu(expanded = pat.structurePartDropDown.value,
                    onDismissRequest = { pat.structurePartDropDown.value = false }) {
                    structure[pat.structureLevel.value].subList.onEachIndexed { index, structureItem ->
                        DropdownMenuItem(onClick = {
                            pat.structurePart.value = index
                            pat.structureType.value = 0
                            pat.structurePartDropDown.value = false
                        }) {
                            Text(
                                text = structureItem.name,
                                maxLines = 1,
                                color = theme.textColorDark
                            )
                        }
                    }
                }
            }
            //structure type
            Column(
                modifier = Modifier.wrapContentHeight()
                    .width(IntrinsicSize.Max),
                verticalArrangement = Arrangement.SpaceBetween
            ) {
                val modifier = Modifier.wrapContentHeight().weight(1.0f).fillMaxWidth()
                structure[pat.structureLevel.value].subList[pat.structurePart.value].types.onEachIndexed { index, sub ->
                    Row(
                        modifier = modifier.clickable { pat.structureType.value = index },
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        RadioButton(
                            selected = pat.structureType.value == index,
                            onClick = { pat.structureType.value = index })
                        Text(
                            text = sub.name,
                            maxLines = 1
                        )
                    }

                }
            }
            //structure positive
            Column(
                modifier = Modifier.wrapContentHeight()
                    .width(IntrinsicSize.Max),
                verticalArrangement = Arrangement.SpaceBetween
            ) {
                val modifier = Modifier.wrapContentHeight().weight(1.0f).fillMaxWidth()
                Row(
                    modifier = modifier.clickable { pat.structureNegative.value = true },
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    RadioButton(
                        selected = pat.structureNegative.value,
                        onClick = { pat.structureNegative.value = true })
                    Text(text = "Негативное", maxLines = 1)
                }
                Row(
                    modifier = modifier.clickable { pat.structureNegative.value = false },
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    RadioButton(
                        selected = !pat.structureNegative.value,
                        onClick = { pat.structureNegative.value = false })
                    Text(text = "Позитивное", maxLines = 1)
                }
            }
        }
        //structure text
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            val (structureText, structureTextId) =
                if (pat.structureNegative.value) {
                    listOf(
                        structure[pat.structureLevel.value].subList[pat.structurePart.value].types[pat.structureType.value].negative,
                        "Структура ${pat.structureLevel.value + 1} ${pat.structurePart.value + 1} ${pat.structureType.value + 1} Нег"
                    )
                } else {
                    listOf(
                        structure[pat.structureLevel.value].subList[pat.structurePart.value].types[pat.structureType.value].positive,
                        "Структура ${pat.structureLevel.value + 1} ${pat.structurePart.value + 1} ${pat.structureType.value + 1} Поз"
                    )
                }

            OutlinedTextField(
                modifier = Modifier.weight(1.0f).wrapContentHeight(),
                textStyle = TextStyle(),
                readOnly = true,
                value = structureText,
                onValueChange = { },
            )
            Button(onClick = {
                if (pat.homework.containsKey(structureTextId))
                    pat.homework.remove(structureTextId)
                else
                    pat.homework[structureTextId] = structureText
                println(pat.homework.toMap())
            }, modifier = Modifier) {
                if (pat.homework.containsKey(structureTextId))
                    Text("Удалить из д/з", maxLines = 1)
                else
                    Text("Добавить в д/з", maxLines = 1)
            }
        }

        //bax
        val baxFile = javaClass.getResource("/values/bax.json")?.readText() ?: ""
        val bax = Json.decodeFromString<ArrayList<BaxItem>>(baxFile)

        //bax choose
        Row(
            modifier = Modifier.fillMaxWidth().heightIn(10.dp, 100.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text("Бах:")
            OutlinedTextField(
                textStyle = TextStyle(),
                label = { Text("Число 1-39") },
                isError = pat.baxError.value,
                value = pat.baxTextField.value,
                onValueChange = { newText ->
                    if ((newText.text.toIntOrNull() ?: -1) in 1..39) {
                        pat.baxError.value = false
                        pat.baxTextField.value = newText
                        pat.baxNumber.value = pat.baxTextField.value.text.toInt() - 1
                    } else {
                        pat.baxError.value = true
                        pat.baxTextField.value = newText
                    }
                }
            )
            //bax positive
            Column(
                modifier = Modifier.wrapContentHeight()
                    .width(IntrinsicSize.Max),
                verticalArrangement = Arrangement.SpaceBetween
            ) {
                val modifier = Modifier.wrapContentHeight().weight(1.0f).fillMaxWidth()
                Row(
                    modifier = modifier.clickable { pat.baxNegative.value = true },
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    RadioButton(selected = pat.baxNegative.value, onClick = { pat.baxNegative.value = true })
                    Text(text = "Негативное", maxLines = 1)
                }
                Row(
                    modifier = modifier.clickable { pat.baxNegative.value = false },
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    RadioButton(selected = !pat.baxNegative.value, onClick = { pat.baxNegative.value = false })
                    Text(text = "Позитивное", maxLines = 1)
                }
            }
        }
        //bax text
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            val baxText =
                bax[if (pat.patientSex.value) 0 else 1].subList[pat.baxNumber.value][if (pat.baxNegative.value) 0 else 1]
            val baxTextId = "Бах ${pat.baxNumber.value + 1} ${if (pat.baxNegative.value) "Нег" else "Поз"}"
            OutlinedTextField(
                modifier = Modifier.weight(1.0f).wrapContentHeight(),
                textStyle = TextStyle(),
                readOnly = true,
                value = baxText,
                onValueChange = { },
            )
            Button(onClick = {
                if (pat.homework.containsKey(baxTextId))
                    pat.homework.remove(baxTextId)
                else
                    pat.homework[baxTextId] = baxText
                println(pat.homework.toMap())
            }, modifier = Modifier) {
                if (pat.homework.containsKey(baxTextId))
                    Text("Удалить из д/з", maxLines = 1)
                else
                    Text("Добавить в д/з", maxLines = 1)
            }

        }

        //avoid
        val avoidFile = javaClass.getResource("/values/avoid.json")?.readText() ?: ""
        val avoid = Json.decodeFromString<ArrayList<ArrayList<String>>>(avoidFile)
        //avoid choose
        Row(
            modifier = Modifier.fillMaxWidth().heightIn(10.dp, 100.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text("Бах:")
            OutlinedTextField(
                textStyle = TextStyle(),
                label = { Text("Число 1-9") },
                isError = pat.avoidError.value,
                value = pat.avoidTextField.value,
                onValueChange = { newText ->
                    if ((newText.text.toIntOrNull() ?: -1) in 1..9) {
                        pat.avoidError.value = false
                        pat.avoidTextField.value = newText
                        pat.avoidNumber.value = pat.avoidTextField.value.text.toInt() - 1
                    } else {
                        pat.avoidError.value = true
                        pat.avoidTextField.value = newText
                    }
                }
            )
            //avoid positive
            Column(
                modifier = Modifier.wrapContentHeight()
                    .width(IntrinsicSize.Max),
                verticalArrangement = Arrangement.SpaceBetween
            ) {
                val modifier = Modifier.wrapContentHeight().weight(1.0f).fillMaxWidth()
                Row(
                    modifier = modifier.clickable { pat.avoidNegative.value = true },
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    RadioButton(selected = pat.avoidNegative.value, onClick = { pat.avoidNegative.value = true })
                    Text(text = "Негативное", maxLines = 1)
                }
                Row(
                    modifier = modifier.clickable { pat.avoidNegative.value = false },
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    RadioButton(selected = !pat.avoidNegative.value, onClick = { pat.avoidNegative.value = false })
                    Text(text = "Позитивное", maxLines = 1)
                }
            }
        }
        //avoid text
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            val avoidText = avoid[pat.avoidNumber.value][if (pat.avoidNegative.value) 0 else 1]
            val avoidTextId =
                "Избегание ${pat.avoidNumber.value + 1} ${if (pat.avoidNegative.value) "Нег" else "Поз"}"
            OutlinedTextField(
                modifier = Modifier.weight(1.0f).wrapContentHeight(),
                textStyle = TextStyle(),
                readOnly = true,
                value = avoidText,
                onValueChange = { },
            )
            Button(onClick = {
                if (pat.homework.containsKey(avoidTextId))
                    pat.homework.remove(avoidTextId)
                else
                    pat.homework[avoidTextId] = avoidText
                println(pat.homework.toMap())
            }, modifier = Modifier) {
                if (pat.homework.containsKey(avoidTextId))
                    Text("Удалить из д/з", maxLines = 1)
                else
                    Text("Добавить в д/з", maxLines = 1)
            }

        }

        //numbers
        val numbersFile = javaClass.getResource("/values/numbers.json")?.readText() ?: ""
        val numbers = Json.decodeFromString<ArrayList<NumbersItem>>(numbersFile)

        //numbers choose
        Row(
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text("Числа:")
            OutlinedTextField(
                textStyle = TextStyle(),
                label = { Text("Число 1-11") },
                isError = pat.numbersError.value,
                value = pat.numbersTextField.value,
                onValueChange = { newText ->
                    if ((newText.text.toIntOrNull() ?: -1) in 1..11) {
                        pat.numbersError.value = false
                        pat.numbersTextField.value = newText
                        pat.numbersNumber.value = pat.numbersTextField.value.text.toInt() - 1
                    } else {
                        pat.numbersError.value = true
                        pat.numbersTextField.value = newText
                    }
                }
            )
        }
        //numbers text
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            val numbersText = numbers[pat.numbersNumber.value].text
            val numbersTextId = "Числа ${pat.numbersNumber.value + 1}"
            OutlinedTextField(
                modifier = Modifier.weight(1.0f).wrapContentHeight(),
                textStyle = TextStyle(),
                readOnly = true,
                value = numbersText,
                onValueChange = { },
            )
            Button(onClick = {
                if (pat.homework.containsKey(numbersTextId))
                    pat.homework.remove(numbersTextId)
                else
                    pat.homework[numbersTextId] = numbersText
                println(pat.homework.toMap())
            }, modifier = Modifier) {
                if (pat.homework.containsKey(numbersTextId))
                    Text("Удалить из д/з", maxLines = 1)
                else
                    Text("Добавить в д/з", maxLines = 1)
            }

        }

        //kobi
        val kobiFile = javaClass.getResource("/values/kobi.json")?.readText() ?: ""
        val kobi = Json.decodeFromString<ArrayList<ArrayList<KobiNegPosItem>>>(kobiFile)

        //kobi choose
        Row(
            modifier = Modifier.fillMaxWidth().heightIn(10.dp, 100.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text("Коби:")
            OutlinedTextField(
                textStyle = TextStyle(),
                label = { Text("Число 1-9") },
                isError = pat.kobiError.value,
                value = pat.kobiTextField.value,
                onValueChange = { newText ->
                    if ((newText.text.toIntOrNull() ?: -1) in 1..9) {
                        pat.kobiError.value = false
                        pat.kobiTextField.value = newText
                        pat.kobiNumber.value = pat.kobiTextField.value.text.toInt() - 1
                    } else {
                        pat.kobiError.value = true
                        pat.kobiTextField.value = newText
                    }
                }
            )
            //kobi positive
            Column(
                modifier = Modifier.wrapContentHeight()
                    .width(IntrinsicSize.Max),
                verticalArrangement = Arrangement.SpaceBetween
            ) {
                val modifier = Modifier.wrapContentHeight().weight(1.0f).fillMaxWidth()
                Row(
                    modifier = modifier.clickable { pat.kobiNegative.value = true },
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    RadioButton(selected = pat.kobiNegative.value, onClick = { pat.kobiNegative.value = true })
                    Text(text = "Негативное", maxLines = 1)
                }
                Row(
                    modifier = modifier.clickable { pat.kobiNegative.value = false },
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    RadioButton(selected = !pat.kobiNegative.value, onClick = { pat.kobiNegative.value = false })
                    Text(text = "Позитивное", maxLines = 1)
                }
            }
        }
        //kobi text
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            val kobiText =
                kobi[pat.kobiNumber.value][if (pat.kobiNegative.value) 0 else 1].text
            val kobiTextId = "Бах ${pat.kobiNumber.value + 1} ${if (pat.kobiNegative.value) "Нег" else "Поз"}"
            OutlinedTextField(
                modifier = Modifier.weight(1.0f).wrapContentHeight(),
                textStyle = TextStyle(),
                readOnly = true,
                value = kobiText,
                onValueChange = { },
            )
            Button(onClick = {
                if (pat.homework.containsKey(kobiTextId))
                    pat.homework.remove(kobiTextId)
                else
                    pat.homework[kobiTextId] = kobiText
                println(pat.homework.toMap())
            }, modifier = Modifier) {
                if (pat.homework.containsKey(kobiTextId))
                    Text("Удалить из д/з", maxLines = 1)
                else
                    Text("Добавить в д/з", maxLines = 1)
            }

        }

        //maui
        val mauiFile = javaClass.getResource("/values/maui.json")?.readText() ?: ""
        val maui = Json.decodeFromString<ArrayList<MauiItem>>(mauiFile)

        //maui choose
        Row(
            modifier = Modifier.fillMaxWidth().heightIn(10.dp, 100.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text("Мауи:")
            OutlinedTextField(
                textStyle = TextStyle(),
                label = { Text("Число 1-12") },
                isError = pat.mauiError.value,
                value = pat.mauiTextField.value,
                onValueChange = { newText ->
                    if ((newText.text.toIntOrNull() ?: -1) in 1..12) {
                        pat.mauiError.value = false
                        pat.mauiTextField.value = newText
                        pat.mauiNumber.value = pat.mauiTextField.value.text.toInt() - 1
                    } else {
                        pat.mauiError.value = true
                        pat.mauiTextField.value = newText
                    }
                }
            )
            //maui positive
            Column(
                modifier = Modifier.wrapContentHeight()
                    .width(IntrinsicSize.Max),
                verticalArrangement = Arrangement.SpaceBetween
            ) {
                val modifier = Modifier.wrapContentHeight().weight(1.0f).fillMaxWidth()
                Row(
                    modifier = modifier.clickable { pat.mauiNature.value = true },
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    RadioButton(selected = pat.mauiNature.value, onClick = { pat.mauiNature.value = true })
                    Text(text = "Природа", maxLines = 1)
                }
                Row(
                    modifier = modifier.clickable { pat.mauiNature.value = false },
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    RadioButton(selected = !pat.mauiNature.value, onClick = { pat.mauiNature.value = false })
                    Text(text = "Камни", maxLines = 1)
                }
            }
        }
        //maui text
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            val mauiText =
                maui[if (pat.mauiNature.value) 0 else 1].subList[pat.mauiNumber.value].name + "\n" + maui[if (pat.mauiNature.value) 0 else 1].subList[pat.mauiNumber.value].text
            val mauiTextId = "Мауи ${pat.mauiNumber.value + 1} ${if (pat.mauiNature.value) "Природа" else "Камни"}"
            OutlinedTextField(
                modifier = Modifier.weight(1.0f).wrapContentHeight(),
                textStyle = TextStyle(),
                readOnly = true,
                value = mauiText,
                onValueChange = { },
            )
            Button(onClick = {
                if (pat.homework.containsKey(mauiTextId))
                    pat.homework.remove(mauiTextId)
                else
                    pat.homework[mauiTextId] = mauiText
                println(pat.homework.toMap())
            }, modifier = Modifier) {
                if (pat.homework.containsKey(mauiTextId))
                    Text("Удалить из д/з", maxLines = 1)
                else
                    Text("Добавить в д/з", maxLines = 1)
            }

        }

        //eva
        val evaFile = javaClass.getResource("/values/eva.json")?.readText() ?: ""
        val eva = Json.decodeFromString<ArrayList<ArrayList<EvaNegPosItem>>>(evaFile)

        //eva choose
        Row(
            modifier = Modifier.fillMaxWidth().heightIn(10.dp, 100.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text("Ева:")
            OutlinedTextField(
                textStyle = TextStyle(),
                label = { Text("Число 1-9") },
                isError = pat.evaError.value,
                value = pat.evaTextField.value,
                onValueChange = { newText ->
                    if ((newText.text.toIntOrNull() ?: -1) in 1..9) {
                        pat.evaError.value = false
                        pat.evaTextField.value = newText
                        pat.evaNumber.value = pat.evaTextField.value.text.toInt() - 1
                    } else {
                        pat.evaError.value = true
                        pat.evaTextField.value = newText
                    }
                }
            )
            //eva positive
            Column(
                modifier = Modifier.wrapContentHeight()
                    .width(IntrinsicSize.Max),
                verticalArrangement = Arrangement.SpaceBetween
            ) {
                val modifier = Modifier.wrapContentHeight().weight(1.0f).fillMaxWidth()
                Row(
                    modifier = modifier.clickable { pat.evaNegative.value = true },
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    RadioButton(selected = pat.evaNegative.value, onClick = { pat.evaNegative.value = true })
                    Text(text = "Негативное", maxLines = 1)
                }
                Row(
                    modifier = modifier.clickable { pat.evaNegative.value = false },
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    RadioButton(selected = !pat.evaNegative.value, onClick = { pat.evaNegative.value = false })
                    Text(text = "Позитивное", maxLines = 1)
                }
            }
        }
        //eva text
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            val evaText =
                eva[pat.evaNumber.value][if (pat.evaNegative.value) 0 else 1].text
            val evaTextId = "Ева ${pat.evaNumber.value + 1} ${if (pat.evaNegative.value) "Нег" else "Поз"}"
            OutlinedTextField(
                modifier = Modifier.weight(1.0f).wrapContentHeight(),
                textStyle = TextStyle(),
                readOnly = true,
                value = evaText,
                onValueChange = { },
            )
            Button(onClick = {
                if (pat.homework.containsKey(evaTextId))
                    pat.homework.remove(evaTextId)
                else
                    pat.homework[evaTextId] = evaText
                println(pat.homework.toMap())
            }, modifier = Modifier) {
                if (pat.homework.containsKey(evaTextId))
                    Text("Удалить из д/з", maxLines = 1)
                else
                    Text("Добавить в д/з", maxLines = 1)
            }

        }
    }

}
