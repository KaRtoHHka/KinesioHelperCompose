package controller

import model.patient.Patient
import org.apache.poi.xwpf.usermodel.XWPFDocument
import java.io.File
import java.io.FileOutputStream

fun createDoc(dir: File, pat: Patient) {
    try {
        val fileOutputStream = FileOutputStream(dir)
        val document = XWPFDocument()

        document.createParagraph().createRun().setText("Имя: ${pat.patientName.value.text}")
        document.createParagraph().createRun().setText("Возраст: ${pat.patientAge.value.text}")
        document.createParagraph().createRun().setText("Пол: ${if (pat.patientSex.value) "Женский" else "Мужской"}")
        document.createParagraph().createRun().setText("Дата коррекции: ${pat.dateOfWork.value.text}")
        document.createParagraph().createRun().setText("Номер коррекции: ${pat.numberOfCorrection.value.text}")
        document.createParagraph().createRun().setText("")
        document.createParagraph().createRun().setText("Проблема: ${pat.patientProblem.value.text}")
        document.createParagraph().createRun().setText("""Барометр: ${pat.barometerLevel.value} - ${pat.barometerPart.value} - ${pat.barometerItem.value}""")
        document.createParagraph().createRun().setText("Болевое поведение: ${pat.painNumber.value}")
        document.createParagraph().createRun().setText("Структуры: ${pat.structureLevel.value} – ${pat.structurePart.value} – ${pat.structureType.value}")
        document.createParagraph().createRun().setText("Бах: ${pat.baxNumber.value}")
        document.createParagraph().createRun().setText("Избегание: ${pat.baxNumber.value}")

        document.write(fileOutputStream)
        fileOutputStream.close()
    } catch (e: Exception) {
        e.printStackTrace()
    }

}

fun createHomework(dir: File, map: MutableMap<String, String>) {
    val fileOutputStream = FileOutputStream(dir)
    val document = XWPFDocument()

    for (i in map.values) {
        document.createParagraph().createRun().setText(i)
        document.createParagraph().createRun().setText("")
    }
    document.write(fileOutputStream)
    fileOutputStream.close()

//    val wordPackage = WordprocessingMLPackage.createPackage()
//    val mainDocumentPart = wordPackage.mainDocumentPart
//
//    for (i in map.values) {
//        mainDocumentPart.content.add(paragraphCreate(i))
//        mainDocumentPart.content.add(paragraphCreate(""))
//    }
//
//    wordPackage.save(dir)
}

//private fun paragraphCreate(text: String): P {
//    val factory = Context.getWmlObjectFactory()
//    val p = factory.createP()
//    val r = factory.createR()
//    val t = factory.createText()
//    val pPr = factory.createPPr()
//
//    //Междустрочные и междуабзацные интервалы
//    val spacing = PPrBase.Spacing()
//    spacing.before = BigInteger.valueOf(0)
//    spacing.after = BigInteger.valueOf(0)
//    spacing.line = BigInteger.valueOf(240)
//    pPr.spacing = spacing
//    p.pPr = pPr
//
//    val rpr = factory.createRPr()
//    //размер шрифта
//    val measure = factory.createHpsMeasure()
//    measure.`val` = BigInteger.valueOf(28)
//    rpr.sz = measure
//    //язык
//    val language = factory.createCTLanguage()
//    language.`val` = "ru-RU"
//    rpr.lang = language
//    r.rPr = rpr
//
//    t.value = text
//    r.content.add(t)
//    p.content.add(r)
//
//    return p
//}
